package com.cbo.riskmanagement.repository;

import com.cbo.riskmanagement.model.DeliquentList;
import com.cbo.riskmanagement.model.DeliquentListUpdate;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DeliquentRepositoryUpdated extends JpaRepository<DeliquentListUpdate, Long> {
      @Modifying
      @Transactional
      @Query("DELETE FROM DeliquentListUpdate d WHERE d.tin_Account = :tinAccount")
      void deleteByTin_Account(@Param("tinAccount") String tinAccount);

      @Query("SELECT b FROM DeliquentListUpdate b WHERE b.tin_Account = :tinAccount")
      DeliquentListUpdate findByTinAccount(@Param("tinAccount") String tinAccount);

      @Query("SELECT e.delinquent_list_id FROM DeliquentListUpdate  e ORDER BY e.delinquent_list_id DESC")
      List<Long> findLastElementId(Pageable pageable);
}
