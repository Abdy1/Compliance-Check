package com.cbo.riskmanagement.repository;

import com.cbo.riskmanagement.model.eu.NameAlias;
import com.cbo.riskmanagement.model.eu.SanctionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NameAliasRepository extends JpaRepository<NameAlias, String> {
    @Query(value = "select u from NameAlias u  where u.firstName like %:keyword% or u.middleName like %:keyword% or u.lastName like %:keyword% or u.wholeName Like %:keyword% or " +
            "CONCAT( COALESCE(u.firstName, ' ') , ' ', COALESCE(u.middleName,' '), ' ', COALESCE(u.lastName, ' ')) like %:keyword%")
    List<NameAlias> findSanctionEntityByNameAlias(@Param("keyword") String keyword);
    @Query(value = "select sanction_id from name_alias  where  id =?1", nativeQuery = true)
    String getNameAliasId(String nameId);
    @Query(value = "select * from name_alias  where  sanction_id =?1", nativeQuery = true)
    List<NameAlias> getNameAliasBySanctionId(String sanctionId);
}
