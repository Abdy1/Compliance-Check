package com.cbo.riskmanagement.repository;

import com.cbo.riskmanagement.model.DeliquentUpdateCheck;
import com.cbo.riskmanagement.model.OfacUpdateCheck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfacUpdateCheckRepository extends JpaRepository<OfacUpdateCheck, Long> {

}
