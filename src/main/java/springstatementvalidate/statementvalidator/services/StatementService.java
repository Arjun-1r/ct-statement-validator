package springstatementvalidate.statementvalidator.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import springstatementvalidate.statementvalidator.dataextract.DataReader;
import springstatementvalidate.statementvalidator.factory.ReaderFactory;
import springstatementvalidate.statementvalidator.model.Record;
import springstatementvalidate.statementvalidator.model.ValidationResponse;

@Service
public class StatementService implements StatementInterface {

	// Core function of the application. Here we are validating the duplicate
	// statement and end balance negative value.
	@Override
	public List<ValidationResponse> readAndValidate(MultipartFile file) {
		DataReader importer = ReaderFactory.getFactory(file.getOriginalFilename());
		List<Record> records = importer.readMultipartFile(file);

		List<Record> validationResults = new ArrayList<>();
		validationResults.addAll(collectDuplicateTransactionReferences(records));
		validationResults.addAll(validateRecordEndBalance(records));

		// prepare response
		return validationResults.stream().map(ValidationResponse::new).collect(Collectors.toList());
	}

	// to collect all the duplicate records in list of objects
	private List<Record> collectDuplicateTransactionReferences(List<Record> records) {
		Map<Long, List<Record>> groupByTransactionRef = records.stream()
				.collect(Collectors.groupingBy(record -> record.getTransactionReference()));

		List<Record> validationResults = new ArrayList<>();
		groupByTransactionRef.forEach((k, v) -> {
			if (v.size() > 1) {
				validationResults.addAll(v);
			}
		});
		return validationResults;
	}

	// End balance cannot be negative always. So we are validating by this method.
	private List<Record> validateRecordEndBalance(List<Record> records) {
		Function<Record, Record> functionToValidateTransactionAmount = record -> {
			double balanceValue = new BigDecimal(record.getStartBalance()).add(new BigDecimal(record.getMutation()))
					.doubleValue();
			if (balanceValue < 0.0d && record.getEndBalance() < 0.0d)
				return record;
			return null;
		};

		return records.stream().map(functionToValidateTransactionAmount).filter(Objects::nonNull)
				.collect(Collectors.toList());
	}

}
