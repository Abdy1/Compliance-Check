package com.cbo.riskmanagement.repository;

import com.cbo.riskmanagement.model.us.OfacSanction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfacRepository extends JpaRepository<OfacSanction, String> {
}
