package app.exchangerate.dto.exchangerate;

import app.exchangerate.model.ExchangeRate;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExchangeRateResponseDto {
    private Long id;
    private String currencyDepended;
    private String baseCurrency;
    private BigDecimal rateSell;
    private BigDecimal rateBuy;
    private ExchangeRate.Source source;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime localDate;
}
