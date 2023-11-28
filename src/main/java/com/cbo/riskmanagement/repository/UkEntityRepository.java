package com.cbo.riskmanagement.repository;

import com.cbo.riskmanagement.model.uk.UkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UkEntityRepository extends JpaRepository<UkEntity, Long> {
}
