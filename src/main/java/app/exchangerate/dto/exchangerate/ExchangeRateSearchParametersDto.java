package app.exchangerate.dto.exchangerate;

import app.exchangerate.model.ExchangeRate;

public record ExchangeRateSearchParametersDto(
        String [] localDates, ExchangeRate.Source [] sources) {
}
