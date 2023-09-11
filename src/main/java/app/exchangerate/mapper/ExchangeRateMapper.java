package app.exchangerate.mapper;

import app.exchangerate.config.MapperConfig;
import app.exchangerate.dto.exchangerate.ExchangeRateResponseDto;
import app.exchangerate.model.ExchangeRate;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface ExchangeRateMapper {
    ExchangeRateResponseDto toDto(ExchangeRate exchangeRate);
}
