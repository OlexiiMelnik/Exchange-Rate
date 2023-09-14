package app.exchangerate.controller;

import app.exchangerate.dto.exchangerate.ExchangeRateResponseDto;
import app.exchangerate.dto.exchangerate.ExchangeRateSearchParametersDto;
import app.exchangerate.service.ExchangeRateService;
import app.exchangerate.service.impl.ExternalDataSyncMonoServiceImpl;
import app.exchangerate.service.impl.ExternalDataSyncPrivatServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "ExchangeRate management", description = "Endpoints for managing exchangeRate")
@RestController
@RequestMapping("/exchangeRates")
@RequiredArgsConstructor
@Log4j2
public class ExchangeRateController {
    private final ExchangeRateService exchangeRateService;
    private final ExternalDataSyncMonoServiceImpl monoService;
    private final ExternalDataSyncPrivatServiceImpl privatService;

    @Operation(summary = "fill db by api exchangeRates",
            description = "adding new data on given resources every 2 hours")
    @PostConstruct
    @Scheduled(cron = "* 2 * * * ?")
    public void inject() throws IOException {
        monoService.syncExternalCharacters();
        privatService.syncExternalCharacters();
        log.info("cron job add info to db at: " + LocalDate.now());
    }

    @Operation(summary = "Find all exchangeRates",
            description = "Find all the exchangeRates in the DB and display them using pagination")
    @GetMapping
    public List<ExchangeRateResponseDto> findAll(Pageable pageable) {
        return exchangeRateService.findAll(pageable);
    }

    @Operation(summary = "Delete a exchangeRate",
            description = "Delete a exchangeRate by id into DB")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        exchangeRateService.delete(id);
    }

    @Operation(summary = "Search exchangeRates by parameters",
            description = "Search exchangeRates by parameters")
    @GetMapping("/search")
    public List<ExchangeRateResponseDto> search(
            ExchangeRateSearchParametersDto params, Pageable pageable) {
        return exchangeRateService.search(params, pageable);
    }

    @Operation(summary = "Returns the average bid and ask rates for USD and EUR for the period",
            description = "Returns the average bid and ask rates for USD and EUR for the period")
    @GetMapping("/allWithAvg")
    public ResponseEntity<Map<String, Object>> findAverage(
            @RequestParam LocalDateTime from,
            @RequestParam LocalDateTime to) {
        return exchangeRateService.findAverageByPeriod(from, to);
    }
}
