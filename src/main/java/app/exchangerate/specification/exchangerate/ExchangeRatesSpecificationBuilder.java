package app.exchangerate.specification.exchangerate;

import app.exchangerate.dto.exchangerate.ExchangeRateSearchParametersDto;
import app.exchangerate.model.ExchangeRate;
import app.exchangerate.specification.SpecificationBuilder;
import app.exchangerate.specification.SpecificationProviderManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExchangeRatesSpecificationBuilder
        implements SpecificationBuilder<ExchangeRate, ExchangeRateSearchParametersDto> {
    private final SpecificationProviderManager<ExchangeRate>
            exchangeRateSpecificationProviderManager;

    @Override
    public Specification<ExchangeRate> build(
            ExchangeRateSearchParametersDto exchangeRateSearchParametersDto) {
        Specification<ExchangeRate> spec = Specification.where(null);
        if (exchangeRateSearchParametersDto.localDates() != null
                && exchangeRateSearchParametersDto.localDates().length > 0) {
            spec = spec.and(exchangeRateSpecificationProviderManager
                    .getSpecificationProvider("local_date")
                    .getSpecification(exchangeRateSearchParametersDto.localDates()));
        }
        if (exchangeRateSearchParametersDto.sources() != null
                && exchangeRateSearchParametersDto.sources().length > 0) {
            ExchangeRate.Source[] sources = exchangeRateSearchParametersDto.sources();
            String[] sourceValues = new String[sources.length];
            for (int i = 0; i < sources.length; i++) {
                sourceValues[i] = sources[i].toString();
            }
            spec = spec.and(exchangeRateSpecificationProviderManager
                    .getSpecificationProvider("source")
                    .getSpecification(sourceValues));
        }
        return spec;
    }
}
