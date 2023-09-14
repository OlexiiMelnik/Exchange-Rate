package app.exchangerate.dto.exchangerate;

import app.exchangerate.model.ExchangeRate;
import java.time.LocalDateTime;

public record ExchangeRateSearchParametersDto(
        LocalDateTime [] localDates, ExchangeRate.Source [] sources) {
}
