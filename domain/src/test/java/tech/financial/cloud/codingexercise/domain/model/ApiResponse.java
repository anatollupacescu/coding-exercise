package tech.financial.cloud.codingexercise.domain.model;

import lombok.Data;

import java.util.List;

@Data
class ApiResponse {

    private List<PaymentResource> data;
    private Links links;

    @Data
    public static class Links {
        private String self;
    }
}
