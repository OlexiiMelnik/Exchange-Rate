package app.exchangerate.specification.exchangerate;

import app.exchangerate.model.ExchangeRate;
import app.exchangerate.specification.SpecificationProvider;
import app.exchangerate.specification.SpecificationProviderManager;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExchangeRateSpecificationProviderManage
        implements SpecificationProviderManager<ExchangeRate> {
    private final List<SpecificationProvider<ExchangeRate>> exchangeRateSpecificationProviders;

    @Override
    public SpecificationProvider<ExchangeRate> getSpecificationProvider(String key) {
        return exchangeRateSpecificationProviders.stream()
                .filter(p -> p.getKey().equals(key))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(
                        "Can't find correct specification provider for key " + key));
    }
}
