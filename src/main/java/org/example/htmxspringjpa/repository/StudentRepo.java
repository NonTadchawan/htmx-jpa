package org.example.htmxspringjpa.repository;

import org.example.htmxspringjpa.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StudentRepo extends JpaRepository<Student, Long> , JpaSpecificationExecutor<Student> {
}
