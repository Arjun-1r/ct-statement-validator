package springstatementvalidate.statementvalidator.importtest;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class IntegratedTest {

	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@Test
	public void testUpload() {
	    LinkedMultiValueMap<String, Object> parameters = new LinkedMultiValueMap<String, Object>();
	    parameters.add("file", new org.springframework.core.io.ClassPathResource("records.csv"));

	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.MULTIPART_FORM_DATA);

	    HttpEntity<LinkedMultiValueMap<String, Object>> entity = new HttpEntity<LinkedMultiValueMap<String, Object>>(parameters, headers);

	    ResponseEntity<String> response = testRestTemplate.exchange("/file", HttpMethod.POST, entity, String.class, "");

	    // Expect Ok
	    assertThat(response.getStatusCode(), is(HttpStatus.OK));
	}
	
}
