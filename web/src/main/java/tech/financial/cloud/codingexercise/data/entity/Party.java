package tech.financial.cloud.codingexercise.data.entity;

import lombok.Data;
import tech.financial.cloud.codingexercise.domain.model.AccountNumberCode;

import javax.persistence.*;

@Embeddable
@Data
public class Party {

    @Column(nullable = false)
    private String account_name;

    @Column(nullable = false)
    private String account_number;

    @Column(nullable = false)
    @Enumerated
    private AccountNumberCode account_number_code;

    @Column(nullable = false)
    private int account_type;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private long bank_id;

    @Column(nullable = false)
    private String bank_id_code;

    @Column(nullable = false)
    private String name;
}
