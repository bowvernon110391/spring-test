package com.bowie.app.demo.model.specification;

import org.springframework.data.jpa.domain.Specification;

import java.util.Date;

public class CommonTrait {
    static public <T>Specification<T> createdFrom(Date d) {
        return (t, cq, cb) -> cb.greaterThanOrEqualTo(cb.function("DATE", Date.class, t.get("createdAt")), d);
    }

    static public <T>Specification<T> createdTo(Date d) {
        return (t, cq, cb) -> cb.lessThanOrEqualTo(cb.function("DATE", Date.class, t.get("createdAt")), d);
    }
}
