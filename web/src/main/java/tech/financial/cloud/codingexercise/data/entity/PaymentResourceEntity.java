package tech.financial.cloud.codingexercise.data.entity;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import lombok.Data;
import tech.financial.cloud.codingexercise.domain.model.Type;

@Entity
@Data
public class PaymentResourceEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(
            name = "payment_resource_generator",
            sequenceName = "payment_resource_sequence"
    )
    @GeneratedValue(generator = "payment_resource_generator")
    private UUID id;

    @Column(nullable = false)
    private int version;

    @Column(nullable = false)
    @Enumerated
    private Type type;

    @Column(nullable = false)
    private UUID organisation_id;

    @Embedded
    private AttributesMap attributes;
}
