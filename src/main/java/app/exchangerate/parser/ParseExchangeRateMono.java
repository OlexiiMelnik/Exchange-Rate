package app.exchangerate.parser;

import app.exchangerate.dto.external.ApiExchangeRateMono;
import app.exchangerate.model.ExchangeRate;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class ParseExchangeRateMono implements ParseExchangeRate<ApiExchangeRateMono> {
    @Override
    public ExchangeRate parseApiExchangeRate(ApiExchangeRateMono mono) {
        ExchangeRate exchangeRate = new ExchangeRate();
        exchangeRate.setBaseCurrency("UAH");
        if (mono.getCurrencyCodeA().equals("840")) {
            exchangeRate.setCurrencyDepended("USD");
        } else {
            exchangeRate.setCurrencyDepended("EUR");
        }
        BigDecimal rateBuy =
                BigDecimal.valueOf(mono.getRateBuy()).setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal rateSell =
                BigDecimal.valueOf(mono.getRateSell()).setScale(2, RoundingMode.HALF_EVEN);

        exchangeRate.setRateBuy(rateBuy);
        exchangeRate.setRateSell(rateSell);
        exchangeRate.setLocalDate(LocalDateTime.now());
        exchangeRate.setSource(ExchangeRate.Source.MONO);
        return exchangeRate;
    }
}
