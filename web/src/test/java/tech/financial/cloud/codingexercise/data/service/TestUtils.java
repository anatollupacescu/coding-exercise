package tech.financial.cloud.codingexercise.data.service;

import tech.financial.cloud.codingexercise.data.entity.*;

import tech.financial.cloud.codingexercise.domain.model.AccountNumberCode;
import tech.financial.cloud.codingexercise.domain.model.Type;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Currency;
import java.util.Date;
import java.util.UUID;

class TestUtils {

    public PaymentResourceEntity createPaymentResourceEntity() {
        PaymentResourceEntity entity = new PaymentResourceEntity();
        entity.setOrganisation_id(UUID.randomUUID());
        entity.setAttributes(attributesMap());
        entity.setType(Type.Payment);
        return entity;
    }

    private AttributesMap attributesMap() {
        AttributesMap attributes = new AttributesMap();
        attributes.setAmount(BigDecimal.valueOf(100.21));
        attributes.setBeneficiary_party(party("beneficiary"));
        attributes.setCharges_information(chargesInformation("bearerCode"));
        attributes.setCurrency(Currency.getInstance("GBP"));
        attributes.setDebtor_party(party("debtor"));
        attributes.setEnd_to_end_reference("ref");
        attributes.setFx(fx());
        attributes.setNumeric_reference(23L);
        attributes.setPayment_id(45L);
        attributes.setPayment_purpose("purpose");
        attributes.setPayment_scheme("paymentScheme");
        attributes.setPayment_type("credit");
        attributes.setProcessing_date(new Date());
        attributes.setReference("reference");
        attributes.setSponsor_party(party("sponsor"));
        attributes.setScheme_payment_type("paymentType");
        attributes.setScheme_payment_sub_type("subType");
        return attributes;
    }

    private Fx fx() {
        Fx fx = new Fx();
        fx.setContract_reference("contractRef");
        fx.setExchange_rate("exchangeRate");
        fx.setOriginal_amount(BigDecimal.valueOf(42.22));
        fx.setOriginal_currency(Currency.getInstance("RUB"));
        return fx;
    }

    private ChargesInformation chargesInformation(String bearerCode) {
        ChargesInformation chargesInformation = new ChargesInformation();
        chargesInformation.setBearer_code(bearerCode);
        chargesInformation.setReceiver_charges_amount(BigDecimal.valueOf(12.34));
        chargesInformation.setSender_charges(Collections.singletonList(charge()));
        chargesInformation.setReceiver_charges_currency(Currency.getInstance("GBP"));
        return chargesInformation;
    }

    private Charge charge() {
        Charge charge = new Charge();
        charge.setAmount(BigDecimal.valueOf(45.22));
        charge.setCurrency(Currency.getInstance("USD"));
        return charge;
    }

    private Party party(String name) {
        Party party = new Party();
        party.setAccount_name("123");
        party.setAccount_number_code(AccountNumberCode.BBAN);
        party.setAccount_number("456");
        party.setAccount_name("accName");
        party.setAccount_type(0);
        party.setAddress("address");
        party.setBank_id(2L);
        party.setBank_id_code("bankIdCode");
        party.setName(name);
        return party;
    }
}
