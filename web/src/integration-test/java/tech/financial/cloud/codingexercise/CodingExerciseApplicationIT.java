package tech.financial.cloud.codingexercise;

import com.tngtech.jgiven.integration.spring.EnableJGiven;
import com.tngtech.jgiven.integration.spring.SimpleSpringRuleScenarioTest;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@EnableJGiven
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CodingExerciseApplicationIT extends SimpleSpringRuleScenarioTest<CodingExerciseApplicationState> {

  @Test
  public void fetchZeroPaymentsWhenNoneExists() {

    given().no_payments_have_been_created();

    when().fetch_payments();

    then().response_contains_$_payments(0);
  }
}