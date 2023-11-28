package com.cbo.riskmanagement.repository;

import com.cbo.riskmanagement.model.eu.NameAlias;
import com.cbo.riskmanagement.model.uk.Name;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UkNameRepository extends JpaRepository<Name, Long> {
    @Query(value = "select u from Name u  where u.Name1 like %:keyword% or u.Name2 like %:keyword% or u.Name3 like %:keyword% or u.Name4 Like %:keyword% or  u.Name5 like %:keyword% or u.Name6 like %:keyword%")
    List<Name> findUkSanctionByName(@Param("keyword") String keyword);
    @Query(value = "select name_id from name  where  id =?1", nativeQuery = true)
    Long getNamesId(Long nameId);
    @Query(value = "select * from name  where  name_id =?1", nativeQuery = true)
    List<Name> getNameByNamesId(Long sanctionId);
}
