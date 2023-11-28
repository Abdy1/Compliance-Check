package com.cbo.riskmanagement.repository;

import com.cbo.riskmanagement.model.un.IndividualDateOfBirth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UnIndividualBirthDateRepository extends JpaRepository<IndividualDateOfBirth, Long> {
    @Query(value = "select a from IndividualDateOfBirth  a where a.Id =:Id")
    IndividualDateOfBirth getIndividualDateOfBirthById(Long Id);
}
