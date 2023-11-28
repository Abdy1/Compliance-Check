package com.cbo.riskmanagement.repository;

import com.cbo.riskmanagement.model.uk.NonLatinName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NonLationNameRepository extends JpaRepository<NonLatinName, Long> {
    @Query(value = "select * from non_latin_name where non_latin_name_id =:nonLatinNamesId", nativeQuery = true)
    List<NonLatinName> getNonLatinNames(@Param("nonLatinNamesId") Long nonLatinNamesId);

}
