package app.exchangerate.repository;

import app.exchangerate.model.ExchangeRate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long>,
        JpaSpecificationExecutor<ExchangeRate> {
    List<ExchangeRate> findAllByLocalDateBetween(LocalDateTime from, LocalDateTime to);
}
