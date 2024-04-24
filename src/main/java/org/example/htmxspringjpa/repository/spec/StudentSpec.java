package org.example.htmxspringjpa.repository.spec;

import org.apache.logging.log4j.util.Strings;
import org.example.htmxspringjpa.domain.Student;
import org.springframework.data.jpa.domain.Specification;

public interface StudentSpec {
    static Specification<Student> fNameLike(String fName) {
        if(Strings.isBlank(fName)){
            return null;
        }
        return (root, query, criteriaBuilder) -> criteriaBuilder.or(
                criteriaBuilder.like(root.get("fName"), "%" + fName + "%"));
    }
    static Specification<Student> lNameLike(String lName) {
        if(Strings.isBlank(lName)){

            return null;
        }
        return (root, query, criteriaBuilder) -> criteriaBuilder.or(
                criteriaBuilder.like(root.get("lName"), "%" + lName + "%"));
    }
}
