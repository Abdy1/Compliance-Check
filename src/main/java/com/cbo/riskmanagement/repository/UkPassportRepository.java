package com.cbo.riskmanagement.repository;

import com.cbo.riskmanagement.model.uk.Passport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UkPassportRepository extends JpaRepository<Passport, Long> {
    @Query(value = "select * from passport where passport_id =:Id", nativeQuery = true)
    List<Passport> getPassports(@Param("Id") Long Id);
}
