package tech.financial.cloud.codingexercise.data.entity;

import lombok.Data;

import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
@Data
public class AttributesMap {
    private BigDecimal amount;
}
