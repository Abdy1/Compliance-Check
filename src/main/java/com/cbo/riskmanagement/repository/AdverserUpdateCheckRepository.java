package com.cbo.riskmanagement.repository;

import com.cbo.riskmanagement.model.adverser.AdverserUpdateCheck;
import com.cbo.riskmanagement.model.pep.PepUpdateCheck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdverserUpdateCheckRepository extends JpaRepository<AdverserUpdateCheck, Long> {

}
