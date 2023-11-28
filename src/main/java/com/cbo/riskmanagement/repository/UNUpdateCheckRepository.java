package com.cbo.riskmanagement.repository;

import com.cbo.riskmanagement.model.OfacUpdateCheck;
import com.cbo.riskmanagement.model.UNUpdateCheck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UNUpdateCheckRepository extends JpaRepository<UNUpdateCheck, Long> {

}
