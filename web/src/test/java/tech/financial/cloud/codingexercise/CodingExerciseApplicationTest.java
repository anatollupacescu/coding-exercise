package tech.financial.cloud.codingexercise;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CodingExerciseApplicationTest {

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  public void getAllPayments() {
    ResponseEntity<String> entity = restTemplate.getForEntity("/v1/payments", String.class);
    assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
  }
}