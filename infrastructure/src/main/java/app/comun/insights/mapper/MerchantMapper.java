package app.comun.insights.mapper;

import app.comun.insights.domain.MerchantDTO;
import app.comun.insights.entity.MerchantEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MerchantMapper {

    MerchantMapper INSTANCE = Mappers.getMapper(MerchantMapper.class);

    MerchantDTO merchantEntityToMerchantDTO(MerchantEntity merchantEntity);
    MerchantEntity merchantDTOToMerchantEntity(MerchantDTO merchantDTO);

    List<MerchantDTO> merchantEntityListToMerchantDTOList(List<MerchantEntity> merchantEntityList);
    List<MerchantEntity> merchantDTOListToMerchantEntityList(List<MerchantDTO> merchantDTOList);
}
