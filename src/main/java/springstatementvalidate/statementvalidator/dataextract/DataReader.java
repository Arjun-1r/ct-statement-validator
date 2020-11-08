package springstatementvalidate.statementvalidator.dataextract;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import springstatementvalidate.statementvalidator.model.Record;

public interface DataReader {
	List<Record> readMultipartFile(MultipartFile file);
}
