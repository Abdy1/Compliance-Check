package com.cbo.riskmanagement.repository;

import com.cbo.riskmanagement.model.un.UnIndividual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UNIndividualRiskManagementRepository extends JpaRepository<UnIndividual, Long> {
    @Query(value = "select a from UnIndividual a where a.firstName LIKE %:keyword% or a.secondName LIKE %:keyword% or a.thirdName LIKE %:keyword% or a.fourthName LIKE %:keyword% or CONCAT( COALESCE(a.firstName, ' ') , ' ', COALESCE(a.secondName,' '), ' ', COALESCE(a.thirdName, ' '),' ', COALESCE(a.fourthName, ' ')) LIKE %:keyword%")
    List<UnIndividual> findByKeyword(@Param("keyword") String keyword);
    @Query(value = "select individual_alias_id from un_individual where id=:Id", nativeQuery = true)
    Long getUnIndividualAliasId(Long Id);
    @Query(value = "select individual_document_id from un_individual where id=:Id", nativeQuery = true)
    Long getUnIndividualDocumentId(Long Id);
    @Query(value = "select nationality_id from un_individual where id=:Id", nativeQuery = true)
    Long getUnNationalityId(Long Id);
    @Query(value = "select individual_date_of_birth_id from un_individual where id=:Id", nativeQuery = true)
    Long getBirtDateId(Long Id);
    @Query(value = "select individual_address_id from un_individual where id=:Id", nativeQuery = true)
    Long getIndividualAddressId(Long Id);
    @Query(value = "select u from UnIndividual u where u.Id =:Id")
    List<UnIndividual> getUnIndividualById(Long Id);
}
