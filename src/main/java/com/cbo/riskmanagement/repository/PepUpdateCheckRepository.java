package com.cbo.riskmanagement.repository;

import com.cbo.riskmanagement.model.pep.PepUpdateCheck;
import com.cbo.riskmanagement.model.uk.EuUpdateCheck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PepUpdateCheckRepository extends JpaRepository<PepUpdateCheck, Long> {

}
