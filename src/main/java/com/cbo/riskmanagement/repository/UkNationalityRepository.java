package com.cbo.riskmanagement.repository;

import com.cbo.riskmanagement.model.uk.UkNationalities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UkNationalityRepository extends JpaRepository<UkNationalities, Long> {
    @Query(value = "select * from uk_nationalities_nationality where uk_nationalities_id=:Id", nativeQuery = true)
    List<String> getUkNationalitiesById(@Param("Id") Long Id);
}
