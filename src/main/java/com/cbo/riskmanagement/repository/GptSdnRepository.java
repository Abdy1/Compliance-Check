package com.cbo.riskmanagement.repository;

import com.cbo.riskmanagement.model.us.GptSdnList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface GptSdnRepository extends JpaRepository<GptSdnList, Long> {


}
