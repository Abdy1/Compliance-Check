package com.cbo.riskmanagement.repository;

import com.cbo.riskmanagement.model.pep.PepList;
import com.cbo.riskmanagement.model.un.UnEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PepListRepository extends JpaRepository<PepList, String> {
    @Query(value = "select e from PepList  e where e.nameInEng LIKE %?1%")
   // @Query(value = "select e from PepList  e where e.nameInEng =?1")
    List<PepList> findByKeyword( String keyword);

    @Query(value = "select u from PepList u where u.Id= :Id")
    List<PepList> findByCustomerId(@Param("Id") String id);
}
