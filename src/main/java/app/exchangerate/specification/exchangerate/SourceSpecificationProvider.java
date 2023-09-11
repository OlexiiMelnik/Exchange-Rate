package app.exchangerate.specification.exchangerate;

import app.exchangerate.model.ExchangeRate;
import app.exchangerate.specification.SpecificationProvider;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class SourceSpecificationProvider implements SpecificationProvider<ExchangeRate> {
    private static final String FILTER_KEY = "source";

    @Override
    public String getKey() {
        return FILTER_KEY;
    }

    @Override
    public Specification<ExchangeRate> getSpecification(String[] params) {
        return (root, query, criteriaBuilder) -> {
            Expression<String> expression = root.get(FILTER_KEY);
            Predicate predicate = expression.in((Object[]) params);
            return predicate;
        };
    }
}
