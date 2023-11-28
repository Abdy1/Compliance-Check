package com.cbo.riskmanagement.repository;

import com.cbo.riskmanagement.model.adverser.Adverser;
import com.cbo.riskmanagement.model.pep.PepList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdverserRepository extends JpaRepository<Adverser, Long> {
    @Query(value = "select e from Adverser  e where e.name LIKE %?1%")
        // @Query(value = "select e from PepList  e where e.nameInEng =?1")
    List<Adverser> findByKeyword(String keyword);

    @Query(value = "select u from Adverser u where u.Id= :Id")
    PepList findByCustomerId(@Param("Id") String id);
}
