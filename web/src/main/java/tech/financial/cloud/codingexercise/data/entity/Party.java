package tech.financial.cloud.codingexercise.data.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Enumerated;
import lombok.Data;
import tech.financial.cloud.codingexercise.domain.model.AccountNumberCode;

@Embeddable
@Data
public class Party {

    @Column
    private String account_name;

    @Column(nullable = false)
    private String account_number;

    @Column
    @Enumerated
    private AccountNumberCode account_number_code;

    @Column
    private int account_type;

    @Column
    private String address;

    @Column(nullable = false)
    private long bank_id;

    @Column(nullable = false)
    private String bank_id_code;

    @Column
    private String name;
}
