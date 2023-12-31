package app.exchangerate.specification;

import org.springframework.data.jpa.domain.Specification;

public interface SpecificationBuilder<T, M> {
    Specification<T> build(M searchParameters);
}
