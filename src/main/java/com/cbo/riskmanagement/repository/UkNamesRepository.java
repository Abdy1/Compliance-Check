package com.cbo.riskmanagement.repository;

import com.cbo.riskmanagement.model.uk.Names;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UkNamesRepository extends JpaRepository<Names, Long> {
    @Query(value = "select s.Id from Names s where s.Id =:namesId")
    Long getIdForDesignation(@Param("namesId") Long namesId);
}
