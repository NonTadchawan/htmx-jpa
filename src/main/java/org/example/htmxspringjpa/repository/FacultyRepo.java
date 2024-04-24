package org.example.htmxspringjpa.repository;

import org.example.htmxspringjpa.domain.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacultyRepo extends JpaRepository<Faculty, Long> {
}
