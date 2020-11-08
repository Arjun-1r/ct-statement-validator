package springstatementvalidate.statementvalidator.dataextract;

import java.util.ArrayList;
import java.util.List;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.web.multipart.MultipartFile;

import springstatementvalidate.statementvalidator.exception.InvalidColumnLengthException;
import springstatementvalidate.statementvalidator.model.Record;
import springstatementvalidate.statementvalidator.util.AppConstants;
import springstatementvalidate.statementvalidator.util.DataValidatorUtl;

public class CsvReader implements DataReader {
	private static final String commaSplitter = ",";

	@Override
	public List<Record> readMultipartFile(MultipartFile file) {
		List<Record> records = new ArrayList<>(); // declared to get the converted object format from file format.
		try {
			InputStream inputStream = file.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
			br.lines().skip(1) // as we are mapping to user defined object format. We are skipping header
					.forEachOrdered(line -> {

						String[] tokens = line.split(commaSplitter);
						if (tokens.length == 6) {
							Record record = new Record();
							// Validating each value and mapping to user defined object 'record'
							record.setTransactionReference(DataValidatorUtl.validateTransacNumber(tokens[0]));
							record.setAccountNumber(DataValidatorUtl.validateAndGetAccountNumber(tokens[1]));
							record.setDescription(tokens[2]);
							record.setStartBalance(Double.parseDouble(tokens[3]));
							record.setMutation(DataValidatorUtl.getMutationValue(tokens[4]));
							record.setEndBalance(Double.parseDouble(tokens[5]));
							records.add(record);
						} else {
							// If number of columns differ from expected
							throw new InvalidColumnLengthException(String.format(AppConstants.INVALID_COLUMNS, line));
						}
					});

		} catch (IOException e) {
			e.printStackTrace();
		}

		return records;
	}

}
