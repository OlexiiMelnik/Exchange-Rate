package app.exchangerate.specification.exchangerate;

import app.exchangerate.model.ExchangeRate;
import app.exchangerate.specification.SpecificationProvider;
import jakarta.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class LocalDateSpecificationProvider implements SpecificationProvider<ExchangeRate> {
    private static final String FILTER_KEY = "local_date";

    @Override
    public String getKey() {
        return FILTER_KEY;
    }

    @Override
    public Specification<ExchangeRate> getSpecification(Object[] params) {
        if (params.length < 1 || !(params[0] instanceof LocalDateTime)) {
            throw new IllegalArgumentException("Expected at least one LocalDateTime parameter.");
        }
        LocalDateTime startDateTime = (LocalDateTime) params[0];
        LocalDateTime endDateTime = (params.length > 1 && params[1] instanceof LocalDateTime)
                ? (LocalDateTime) params[1]
                : startDateTime;
        return (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder
                    .between(root.get("localDate"), startDateTime, endDateTime);
            return criteriaBuilder.and(predicate);
        };
    }
}
