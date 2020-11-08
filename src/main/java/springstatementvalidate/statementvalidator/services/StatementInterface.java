package springstatementvalidate.statementvalidator.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import springstatementvalidate.statementvalidator.model.ValidationResponse;

public interface StatementInterface {
	List<ValidationResponse> readAndValidate(MultipartFile file);
}
