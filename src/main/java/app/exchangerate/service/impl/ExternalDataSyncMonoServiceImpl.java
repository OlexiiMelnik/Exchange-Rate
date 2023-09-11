package app.exchangerate.service.impl;

import app.exchangerate.api.HttpClientImpl;
import app.exchangerate.dto.external.ApiExchangeRateMono;
import app.exchangerate.model.ExchangeRate;
import app.exchangerate.parser.ParseExchangeRateMono;
import app.exchangerate.repository.ExchangeRateRepository;
import app.exchangerate.service.ExternalDataSyncService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ExternalDataSyncMonoServiceImpl implements ExternalDataSyncService {
    private static final String URL = "https://api.monobank.ua/bank/currency";
    private final HttpClientImpl httpClient;
    private final ExchangeRateRepository exchangeRateRepository;
    private final ParseExchangeRateMono parseExchangeRateMono;

    @Override
    public void syncExternalCharacters() {
        ApiExchangeRateMono[] apis =
                httpClient.get(URL, ApiExchangeRateMono[].class);
        List<ApiExchangeRateMono> results = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            results.add(apis[i]);
        }
        List<ExchangeRate> collect = results.stream()
                .map(parseExchangeRateMono::parseApiExchangeRate)
                .collect(Collectors.toList());
        exchangeRateRepository.saveAll(collect);
    }
}
