package com.cbo.riskmanagement.repository;

import com.cbo.riskmanagement.model.us.GptSdnList;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface sdnRepository extends JpaRepository<GptSdnList, Long> {

//    @Query("SELECT sdn FROM SdnList sdn LEFT JOIN FETCH sdn.addressList LEFT JOIN FETCH sdn.akaList")
//    List<SdnList> getSdnListWithAddressAndAka();
}
