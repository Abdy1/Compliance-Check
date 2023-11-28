package com.cbo.riskmanagement.repository;

import com.cbo.riskmanagement.model.un.IndividualAlias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UnIndividualAliasRepository extends JpaRepository<IndividualAlias, Long> {
    @Query(value = "select n from IndividualAlias  n where n.Id =:Id")
    IndividualAlias getIndividualAliasById(Long Id);
}
