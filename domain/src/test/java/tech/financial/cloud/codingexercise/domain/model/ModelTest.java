package tech.financial.cloud.codingexercise.domain.model;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

public class ModelTest {

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void canDeserializeSample() throws IOException {
        String json = readSampleJson("/sample.json");
        assertThat(json, is(notNullValue()));
        assertThat(json.isEmpty(), is(equalTo(false)));
        ApiResponse response = mapper.readValue(json, ApiResponse.class);
        List<PaymentResource> paymentResources = response.getData();
        assertThat(paymentResources, is(notNullValue()));
        assertThat(paymentResources.size(), is(equalTo(14)));
    }

    private String readSampleJson(String file) throws IOException {
        InputStream resource = ModelTest.class.getResourceAsStream(file);
        assertThat(resource, is(notNullValue()));
        return IOUtils.toString(resource, StandardCharsets.UTF_8.name());
    }
}
