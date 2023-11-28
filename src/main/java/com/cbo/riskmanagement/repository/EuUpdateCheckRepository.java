package com.cbo.riskmanagement.repository;

import com.cbo.riskmanagement.model.BusinessContinuityUpdateCheck;
import com.cbo.riskmanagement.model.uk.EuUpdateCheck;
import com.cbo.riskmanagement.model.uk.UkUpdateCheck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EuUpdateCheckRepository extends JpaRepository<EuUpdateCheck, Long> {

}
