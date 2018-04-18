package tech.financial.cloud.codingexercise.domain.model;

import java.util.UUID;
import lombok.Data;

@Data
public class PaymentResource {
    private Type type;
    private UUID id;
    private int version;
    private UUID organisation_id;
    private AttributesMap attributes;
}
