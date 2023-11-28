package com.cbo.riskmanagement.repository;

import com.cbo.riskmanagement.model.OfacLastUpdateDate;
import com.cbo.riskmanagement.model.UNLastUpdateDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UNLastUpdateDateRepository extends JpaRepository<UNLastUpdateDate, Long> {

}
