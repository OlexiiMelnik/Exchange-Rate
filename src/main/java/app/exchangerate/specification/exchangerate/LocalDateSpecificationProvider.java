package app.exchangerate.specification.exchangerate;

import app.exchangerate.model.ExchangeRate;
import app.exchangerate.specification.SpecificationProvider;
import jakarta.persistence.criteria.Predicate;
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
    public Specification<ExchangeRate> getSpecification(String[] params) {
        return (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.between(root.get(FILTER_KEY), params[0],
                    (params.length > 1) ? params[1] : params[0]);
            return criteriaBuilder.and(predicate);
        };
    }
}
