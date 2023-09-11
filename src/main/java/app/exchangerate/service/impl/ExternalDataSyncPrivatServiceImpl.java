package app.exchangerate.service.impl;

import app.exchangerate.api.HttpClientImpl;
import app.exchangerate.dto.external.ApiExchangeRatePrivat;
import app.exchangerate.model.ExchangeRate;
import app.exchangerate.parser.ParseExchangeRatePrivat;
import app.exchangerate.repository.ExchangeRateRepository;
import app.exchangerate.service.ExternalDataSyncService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExternalDataSyncPrivatServiceImpl implements ExternalDataSyncService {
    private static final String URL = "https://api.privatbank.ua/p24api/pubinfo?exchange&coursid=5";
    private final HttpClientImpl httpClient;
    private final ExchangeRateRepository exchangeRateRepository;
    private final ParseExchangeRatePrivat parseExchangeRatePrivat;

    @Override
    public void syncExternalCharacters() {
        ApiExchangeRatePrivat[] apis
                = httpClient.get(URL, ApiExchangeRatePrivat[].class);
        List<ApiExchangeRatePrivat> results = new ArrayList<>();
        results.addAll(Arrays.asList(apis));
        List<ExchangeRate> collect = results.stream()
                .map(parseExchangeRatePrivat::parseApiExchangeRate)
                .collect(Collectors.toList());
        Collections.reverse(collect);
        exchangeRateRepository.saveAll(collect);
    }
}
