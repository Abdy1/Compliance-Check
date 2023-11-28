package com.cbo.riskmanagement.repository;

import com.cbo.riskmanagement.model.OfacLastUpdateDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfacLastUpdateDateRepository extends JpaRepository<OfacLastUpdateDate, Long> {

}
