package com.cbo.riskmanagement.repository;

import com.cbo.riskmanagement.model.uk.Genders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UkGenderRepository extends JpaRepository<Genders, Long> {
    @Query(value = "select * from genders where id=:genderId", nativeQuery = true)
    Genders getGenders(@Param("genderId") Long genderId);
}
