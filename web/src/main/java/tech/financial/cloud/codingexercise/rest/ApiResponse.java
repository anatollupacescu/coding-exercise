package tech.financial.cloud.codingexercise.rest;

import lombok.Data;
import tech.financial.cloud.codingexercise.domain.model.PaymentResource;

import java.util.List;

@Data
public class ApiResponse {
    private List<PaymentResource> data;
    private Links links;

    @Data
    public static class Links {
        private String self;
    }

    public static Links newLinks(String url) {
        Links links = new Links();
        links.setSelf(url);
        return links;
    }
}
