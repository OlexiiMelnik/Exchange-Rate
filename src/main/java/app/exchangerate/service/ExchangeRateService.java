package app.exchangerate.service;

import app.exchangerate.dto.exchangerate.ExchangeRateResponseDto;
import app.exchangerate.dto.exchangerate.ExchangeRateSearchParametersDto;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ExchangeRateService {
    List<ExchangeRateResponseDto> findAll(Pageable pageable);

    List<ExchangeRateResponseDto> search(
            ExchangeRateSearchParametersDto params, Pageable pageable);

    ResponseEntity<Map<String, Object>> findAverageByPeriod(LocalDateTime from, LocalDateTime to);

    void delete(Long id);
}
