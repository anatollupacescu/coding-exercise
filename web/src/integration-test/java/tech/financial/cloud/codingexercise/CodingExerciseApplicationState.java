package tech.financial.cloud.codingexercise;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import java.util.List;
import java.util.Set;
import org.assertj.core.util.Lists;
import org.assertj.core.util.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tech.financial.cloud.codingexercise.domain.model.PaymentResource;
import tech.financial.cloud.codingexercise.rest.ApiResponse;

@JGivenStage
public class CodingExerciseApplicationState extends Stage<CodingExerciseApplicationState> {

  @Autowired
  private TestRestTemplate testRestTemplate;

  @Autowired
  protected Environment context;

  private final String uri = "/v1/payments";
  private final Set<Object> createdPayments = Sets.newHashSet();
  private final List<PaymentResource> fetchedPayments = Lists.newArrayList();

  private String endpoint() {
    Integer port = context.getProperty("local.server.port", Integer.class);
    return String.format("http://localhost:%s%s", port, uri);
  }

  public void fetch_payments() {
    ResponseEntity<ApiResponse> responseEntity = testRestTemplate.getForEntity(endpoint(), ApiResponse.class, (Object) null);
    assertThat(responseEntity.getStatusCode(), is(equalTo(HttpStatus.OK)));
    ApiResponse response = responseEntity.getBody();
    assertThat(response, is(notNullValue()));
    fetchedPayments.addAll(response.getData());
  }

  public void no_payments_have_been_created() {
    assertThat(createdPayments.size(), is(equalTo(0)));
  }

  public void response_contains_$_payments(int count) {
    assertThat(fetchedPayments.size(), is(equalTo(count)));
  }
}
