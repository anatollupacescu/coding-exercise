package tech.financial.cloud.codingexercise;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import tech.financial.cloud.codingexercise.rest.ApiResponse;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class CodingExerciseApplicationIT {

  @Test
  public void springContextLoads() {
  }

  @Autowired
  private TestRestTemplate restTemplate;

  private final String uri = "/v1/payments";

  @Test
  public void createClient() {
    ResponseEntity<ApiResponse> responseEntity = restTemplate.getForEntity(uri, ApiResponse.class, (Object) null);
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    ApiResponse response = responseEntity.getBody();
    assertThat(response, is(notNullValue()));
  }
}