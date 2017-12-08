package tech.financial.cloud.codingexercise.rest;

import lombok.Data;

@Data
public class ErrorMessage {
    private int status;
    private String message;
}
