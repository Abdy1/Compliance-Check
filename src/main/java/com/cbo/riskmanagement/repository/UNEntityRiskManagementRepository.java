package com.cbo.riskmanagement.repository;

import com.cbo.riskmanagement.model.un.UnEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UNEntityRiskManagementRepository extends JpaRepository<UnEntity, Long> {
    //or CONCAT( e.firstName, e.thirdName, e.secondName, e.secondName) LIKE %?1%
    @Query(value = "select e from UnEntity  e where CONCAT( e.firstName, e.thirdName, e.secondName, e.secondName) LIKE %?1% ")
    List<UnEntity> findByKeyword(String keyword);

}
