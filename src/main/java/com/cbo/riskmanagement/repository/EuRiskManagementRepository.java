package com.cbo.riskmanagement.repository;

import com.cbo.riskmanagement.model.eu.SanctionEntity;
import com.cbo.riskmanagement.model.un.Consolidated;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EuRiskManagementRepository extends JpaRepository<SanctionEntity, String> {
    @Query(value = "select u from SanctionEntity u where u.Id= :Id")
    SanctionEntity findByEuSanctionCustomerId(@Param("Id") String id);
    @Query(value = "select u from SanctionEntity u  where u.Id =:sanctionId")
    SanctionEntity findSanctionEntityById (@Param("sanctionId") String sanctionId);



}
