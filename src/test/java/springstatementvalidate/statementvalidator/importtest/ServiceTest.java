package springstatementvalidate.statementvalidator.importtest;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;


import springstatementvalidate.statementvalidator.model.Record;
import springstatementvalidate.statementvalidator.model.ValidationResponse;
import springstatementvalidate.statementvalidator.services.StatementInterface;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTest {
	@Autowired
	ResourceLoader resourceLoader;

	@Autowired
	private StatementInterface statementService;
	
	@Test
	public void shouldreadAndValidate() throws Exception {
		List<ValidationResponse> results = new ArrayList<>();
		ValidationResponse result1 = new ValidationResponse(
				new Record(112806L, "NL27SNSB0917829871", "Clothes for Willem Dekker", 91.23, 15.57, 106.8));
		ValidationResponse result2 = new ValidationResponse(
				new Record(112806L, "NL69ABNA0433647324", "Clothes for Richard de Vries", 90.83, -10.97, 79.92));
		ValidationResponse result3 = new ValidationResponse(
				new Record(112806L, "NL93ABNA0585619023", "Tickets from Richard Bakker", 102.12, 45.87, 147.99));
		ValidationResponse result4 = new ValidationResponse(
				new Record(112806L, "NL91RABO0315273637", "Clothes from Jan Bakker", 21.6, -41.83, -20.23));
		ValidationResponse result5 = new ValidationResponse(
				new Record(112806L, "NL93ABNA0585619023", "Clothes for Vincent Bakker", 23.96, -27.43, -3.47));
		results.add(result1);
		results.add(result2);
		results.add(result3);
		results.add(result4);
		results.add(result5);

		Resource resource = resourceLoader.getResource("classpath:records.csv");
		byte[] csvFileBytes = Files.readAllBytes(resource.getFile().toPath());

		MockMultipartFile mockCsvFile = new MockMultipartFile("data", "records.csv", "text/plain", csvFileBytes);

		Assert.assertEquals(results.get(0).getDescription(),
				statementService.readAndValidate(mockCsvFile).get(0).getDescription());

	}

}
