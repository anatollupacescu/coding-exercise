package tech.financial.cloud.codingexercise.domain.model;

import java.util.List;
import lombok.Data;

@Data
class ApiResponse {

    private List<PaymentResource> data;
    private Links links;

    @Data
    public static class Links {
        private String self;
    }
}
