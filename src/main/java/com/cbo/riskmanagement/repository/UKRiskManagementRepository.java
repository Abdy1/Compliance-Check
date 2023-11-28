package com.cbo.riskmanagement.repository;

import com.cbo.riskmanagement.model.eu.SanctionEntity;
import com.cbo.riskmanagement.model.uk.Designations;
import com.cbo.riskmanagement.model.uk.NonLatinName;
import com.cbo.riskmanagement.model.uk.UkAddress;
import com.cbo.riskmanagement.model.uk.UkDesignation;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UKRiskManagementRepository extends JpaRepository<UkDesignation, Long> {
   @Query(value = "select names_id from uk_designation  where id=:Id", nativeQuery = true)
   Long getUkNamesId(@Param("Id") Long id);
   @Query(value = "select addresses_id from uk_designation  where names_id=:Id", nativeQuery = true)
   Long getUkAddressesId(@Param("Id") Long id);

   @Query(value = "select email_addresses_id from uk_designation  where names_id=:Id", nativeQuery = true)
   Long getUkEmailAddressId(@Param("Id") Long id);
   @Query(value = "select email_address from email_addresses_email_address where email_addresses_id=:emailAddressId", nativeQuery = true)
   List<String> getEmailAddresses(@Param("emailAddressId") Long emailAddressId);
   @Query(value = "select individual_details_id from uk_designation  where names_id=:Id", nativeQuery = true)
   Long getUkIndividualDetailId(@Param("Id") Long id);
   @Query(value = "select non_lation_names_id from uk_designation  where names_id=:Id", nativeQuery = true)
   Long getUkNonLatinNamesId(@Param("Id") Long id);

   @Query(value = "select title_id from uk_designation  where names_id=:Id", nativeQuery = true)
   Long getTitlesId(@Param("Id") Long id);
   @Query(value = "select names_id from uk_designation  where names_id= :Id", nativeQuery = true)
   Long getWebsitesId(@Param("Id") Long id);
   @Query(value = "select entity_details_id from uk_designation  where names_id= :Id", nativeQuery = true)
   Long getUkEntitylId(@Param("Id") Long id);

   @Query(value = "select phone_numbers_id from uk_designation where names_id=:namesId", nativeQuery = true)
   Long getPhoneNumbersId(@Param("namesId") Long namesId);
   @Query(value = "select * from uk_designation where names_id=:namesId", nativeQuery = true)
   UkDesignation getUkDesignationByNamesId (@Param("namesId") Long namesId);
   @Query(value = "select website_list from websites_website_list where websites_id=:websitesId", nativeQuery = true)
   List<String> getWebsites(@Param("websitesId") Long  websitesId);
   @Query(value = "select title from titles_title where titles_id=:titlesId", nativeQuery = true)
   List<String> getTitles(@Param("titlesId") Long titlesId);


}
