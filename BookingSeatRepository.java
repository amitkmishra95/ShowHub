package com.project.BTS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.BTS.entity.BookingSeat;

public interface BookingSeatRepository extends JpaRepository<BookingSeat, Long> {
}