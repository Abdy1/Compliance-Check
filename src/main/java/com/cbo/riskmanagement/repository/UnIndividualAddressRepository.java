package com.cbo.riskmanagement.repository;

import com.cbo.riskmanagement.model.un.IndividualAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UnIndividualAddressRepository extends JpaRepository<IndividualAddress, Long> {
    @Query(value = "select a from IndividualAddress  a where a.Id =:Id")
    IndividualAddress getIndividualAddressById(Long Id);
}
