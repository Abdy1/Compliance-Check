package com.cbo.riskmanagement.repository;



import com.cbo.riskmanagement.model.adverser.Adverser;
import com.cbo.riskmanagement.model.pep.PepList;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import com.cbo.riskmanagement.model.un.UNSanctions;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SanctionRepository extends JpaRepository<UNSanctions, Long> {
    Optional<List<UNSanctions>> findByFullNameContaining(String fullName);
    Optional<List<UNSanctions>> findById ( @Param("dataid")String dataid);


}





