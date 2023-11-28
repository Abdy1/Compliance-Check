package com.cbo.riskmanagement.repository;

import com.cbo.riskmanagement.model.un.IndividualAddress;
import com.cbo.riskmanagement.model.un.Nationality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UnNationalityRepository extends JpaRepository<Nationality, Long> {
    @Query(value = "select n from Nationality  n where n.Id =:Id")
    Nationality getNationalityById (Long Id);
}
