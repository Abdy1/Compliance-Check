package com.cbo.riskmanagement.repository;

import com.cbo.riskmanagement.model.DeliquentList;
import com.cbo.riskmanagement.model.us.OfacSanction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliquentRepository extends JpaRepository<DeliquentList, Long> {
}
