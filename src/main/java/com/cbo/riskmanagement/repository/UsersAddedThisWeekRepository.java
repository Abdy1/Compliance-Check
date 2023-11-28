package com.cbo.riskmanagement.repository;

import com.cbo.riskmanagement.model.UsersAddedThisWeek;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersAddedThisWeekRepository extends JpaRepository<UsersAddedThisWeek, Long> {
}