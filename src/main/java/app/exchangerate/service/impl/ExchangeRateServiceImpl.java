package app.exchangerate.service.impl;

import app.exchangerate.dto.exchangerate.ExchangeRateResponseDto;
import app.exchangerate.dto.exchangerate.ExchangeRateSearchParametersDto;
import app.exchangerate.mapper.ExchangeRateMapper;
import app.exchangerate.model.ExchangeRate;
import app.exchangerate.repository.ExchangeRateRepository;
import app.exchangerate.service.ExchangeRateService;
import app.exchangerate.specification.exchangerate.ExchangeRatesSpecificationBuilder;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExchangeRateServiceImpl implements ExchangeRateService {
    private final ExchangeRateRepository exchangeRateRepository;
    private final ExchangeRateMapper exchangeRateMapper;
    private final ExchangeRatesSpecificationBuilder exchangeRatesSpecificationBuilder;
    private final ExchangeRateUtilsService exchangeRateUtilsService;

    @Override
    public List<ExchangeRateResponseDto> findAll(Pageable pageable) {
        return exchangeRateRepository.findAll(pageable).stream()
                .map(exchangeRateMapper::toDto)
                .toList();
    }

    @Override
    public List<ExchangeRateResponseDto> search(
            ExchangeRateSearchParametersDto params, Pageable pageable) {
        Specification<ExchangeRate> exchangeRateSpecification
                = exchangeRatesSpecificationBuilder.build(params);
        return exchangeRateRepository.findAll(exchangeRateSpecification, pageable).stream()
                .map(exchangeRateMapper::toDto)
                .toList();
    }

    @Override
    public ResponseEntity<Map<String, Object>> findAverageByPeriod(
            LocalDateTime from, LocalDateTime to) {
        List<ExchangeRate> exchangeRates =
                exchangeRateRepository.findAllByLocalDateBetween(from, to);
        Map<String, String> averageRates =
                exchangeRateUtilsService.calculateAverageRates(exchangeRates);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("averageRates", averageRates);
        return ResponseEntity.ok(response);
    }

    @Override
    public void delete(Long id) {
        exchangeRateRepository.deleteById(id);
    }
}
