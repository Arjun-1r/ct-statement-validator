package springstatementvalidate.statementvalidator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import springstatementvalidate.statementvalidator.exception.FileNotSupportedException;
import springstatementvalidate.statementvalidator.model.ValidationResponse;
import springstatementvalidate.statementvalidator.services.StatementInterface;
import springstatementvalidate.statementvalidator.util.AppConstants;
import springstatementvalidate.statementvalidator.util.VerifyFileType;

@RestController
public class StatementController {

	public static final String PATH = "/file";

	@Autowired
	private StatementInterface statementService; // instance will be injected by spring

	@PostMapping(value = PATH, consumes = { "multipart/form-data" }) // file receivable post method/mapping
	public List<ValidationResponse> processData(@RequestParam("file") MultipartFile file) {
		if (!VerifyFileType.isCorrectFileType(file.getOriginalFilename())) // Verifying whether the file type is either
																			// XML or CSV
			throw new FileNotSupportedException( // if not expected file type then error thrown to the user
					String.format(AppConstants.FILE_VALIDATION_MESSAGE, file.getOriginalFilename()));
		return statementService.readAndValidate(file);
	}

}
