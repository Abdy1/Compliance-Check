package com.cbo.riskmanagement.repository;

import com.cbo.riskmanagement.model.nbeblacklist.NbeBacklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NbeBackListRepository extends JpaRepository<NbeBacklist, String> {
@Query(value = "select n from NbeBacklist n where n.name like %:keyword%")
List<NbeBacklist> getNbeBacklistByName(@Param("keyword") String keyword);
}
