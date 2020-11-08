package springstatementvalidate.statementvalidator.importtest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import springstatementvalidate.statementvalidator.dataextract.CsvReader;
import springstatementvalidate.statementvalidator.dataextract.DataReader;
import springstatementvalidate.statementvalidator.dataextract.XmlReader;
import springstatementvalidate.statementvalidator.model.Record;

import org.springframework.mock.web.MockMultipartFile;

import java.nio.file.Files;
import java.util.List;

@SpringBootTest
public class DataReadTest {
	@Autowired
	ResourceLoader resourceLoader;

	@Test
	public void shouldImportCsvFile() throws Exception {
		Resource resource = resourceLoader.getResource("classpath:records.csv");
		byte[] csvFileBytes = Files.readAllBytes(resource.getFile().toPath());
		MockMultipartFile mockCsvFile = new MockMultipartFile("data", "records.csv", "text/plain", csvFileBytes);
		DataReader importer = new CsvReader();
		List<Record> records = importer.readMultipartFile(mockCsvFile);
	   assertEquals(10, records.size());
	}

	@Test
	public void shouldImportXmlFile() throws Exception {
		Resource resource = resourceLoader.getResource("classpath:records.xml");
		byte[] csvFileBytes = Files.readAllBytes(resource.getFile().toPath());
		MockMultipartFile mockXmlFile = new MockMultipartFile("data", "records.xml", "text/plain", csvFileBytes);
		DataReader importer = new XmlReader();
		List<Record> records = importer.readMultipartFile(mockXmlFile);
		assertEquals(10, records.size());
	}
}
