package tech.financial.cloud.codingexercise.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tech.financial.cloud.codingexercise.data.entity.PaymentResourceEntity;
import tech.financial.cloud.codingexercise.domain.model.PaymentResource;

@Mapper
public interface ModelToEntityMapper {

    ModelToEntityMapper INSTANCE = Mappers.getMapper(ModelToEntityMapper.class);

    PaymentResourceEntity toEntity(PaymentResource target);

    PaymentResource fromEntity(PaymentResourceEntity source);
}