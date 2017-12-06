package tech.financial.cloud.codingexercise.data.entity;

import lombok.Data;
import tech.financial.cloud.codingexercise.domain.model.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Data
public class PaymentResource implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "payment_resource_generator", sequenceName = "payment_resource_sequence", initialValue = 23)
    @GeneratedValue(generator = "payment_resource_generator")
    private UUID id;

    @Column(nullable = false)
    private int version;

    @Column(nullable = false)
    private Type type;

    @Column(nullable = false)
    private UUID organisation_id;

    @Column(nullable = false)
    private AttributesMap attributes;
}
