package com.example.block7crudvalidation.Specifications;

import com.example.block7crudvalidation.Entity.PersonaEntity;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PersonaSpecs {

    public static Specification<PersonaEntity> filter(String username, String name, String surname, Date fechaCreacion) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (username != null) {
                predicates.add(cb.equal(root.get("usuario"), username));
            }

            if (name != null) {
                predicates.add(cb.like(root.get("name"), "%" + name + "%"));
            }

            if (surname != null) {
                predicates.add(cb.like(root.get("surname"), "%" + surname + "%"));
            }

            if (fechaCreacion != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("created_date"), fechaCreacion));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
