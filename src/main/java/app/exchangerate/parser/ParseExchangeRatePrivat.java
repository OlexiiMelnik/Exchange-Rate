package app.exchangerate.parser;

import app.exchangerate.dto.external.ApiExchangeRatePrivat;
import app.exchangerate.model.ExchangeRate;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class ParseExchangeRatePrivat implements ParseExchangeRate<ApiExchangeRatePrivat> {
    @Override
    public ExchangeRate parseApiExchangeRate(ApiExchangeRatePrivat privat) {
        ExchangeRate exchangeRate = new ExchangeRate();
        exchangeRate.setBaseCurrency(privat.getBase_ccy());
        exchangeRate.setCurrencyDepended(privat.getCcy());

        BigDecimal rateBuy =
                BigDecimal.valueOf(privat.getBuy()).setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal rateSell =
                BigDecimal.valueOf(privat.getSale()).setScale(2, RoundingMode.HALF_EVEN);

        exchangeRate.setRateBuy(rateBuy);
        exchangeRate.setRateSell(rateSell);

        exchangeRate.setLocalDate(LocalDateTime.now());
        exchangeRate.setSource(ExchangeRate.Source.PRIVAT);
        return exchangeRate;
    }
}
