package com.cbo.riskmanagement.repository;

import com.cbo.riskmanagement.model.un.IndividualDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UnIndividualDocumentRepository extends JpaRepository<IndividualDocument, Long> {
    @Query(value = "select n from IndividualDocument  n where n.Id =:Id")
    IndividualDocument getIndividualDocumentById(Long Id);
}
