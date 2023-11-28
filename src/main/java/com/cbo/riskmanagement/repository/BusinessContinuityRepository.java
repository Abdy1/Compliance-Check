package com.cbo.riskmanagement.repository;

import com.cbo.riskmanagement.model.BusinessContinuity;
import com.cbo.riskmanagement.model.DeliquentListUpdate;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BusinessContinuityRepository extends JpaRepository<BusinessContinuity, Long> {

//    @Query("SELECT b FROM BusinessContinuity  b WHERE b.nbe_reference = :nbe_reference")
//    BusinessContinuity findByRefno(@Param("nbe_reference") String nbe_reference);

    @Query("SELECT e.delinquent_list_id FROM BusinessContinuity  e ORDER BY e.delinquent_list_id DESC")
    List<Long> findLastElementId(Pageable pageable);
}
