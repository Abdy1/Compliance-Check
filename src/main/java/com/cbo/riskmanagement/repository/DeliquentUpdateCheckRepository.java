package com.cbo.riskmanagement.repository;

import com.cbo.riskmanagement.model.DeliquentListUpdate;
import com.cbo.riskmanagement.model.DeliquentUpdateCheck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliquentUpdateCheckRepository extends JpaRepository<DeliquentUpdateCheck, Long> {

}
