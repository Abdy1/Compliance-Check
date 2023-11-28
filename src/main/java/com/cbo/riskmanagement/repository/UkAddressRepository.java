package com.cbo.riskmanagement.repository;

import com.cbo.riskmanagement.model.uk.UkAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UkAddressRepository extends JpaRepository<UkAddress, Long> {
    @Query(value = "select * from uk_address where address_id=:addressesId", nativeQuery = true )
    List<UkAddress> getUkAddress(@Param("addressesId")Long addressesId);
    @Query(value = "select * from uk_address where address_id=:addressesId",nativeQuery = true)
    List<UkAddress> getUkAddresses(@Param("addressesId") Long addressesId);
}
