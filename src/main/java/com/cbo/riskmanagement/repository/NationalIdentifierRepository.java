package com.cbo.riskmanagement.repository;

import com.cbo.riskmanagement.model.uk.NationalIdentifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NationalIdentifierRepository extends JpaRepository<NationalIdentifier, Long> {
    @Query(value = "select * from national_identifier where national_indetifier_id =:Id", nativeQuery = true)
    List<NationalIdentifier> getNationalIdentifiers(@Param("Id") Long Id);
}
