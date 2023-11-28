package com.cbo.riskmanagement.repository;

import com.cbo.riskmanagement.model.uk.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UkIndividualRepository extends JpaRepository<UkIndividual, Long> {
    @Query(value = "select * from uk_individual where individual_details_id =:Id", nativeQuery = true)
    UkIndividual getUkIndividualById(@Param("Id") Long Id);
    @Query(value = "select nationalities_id from uk_individual where id=:Id", nativeQuery = true)
    Long getUKNationalitiesId( @Param("Id")Long Id);
    @Query(value = "select birth_details_id from uk_individual where id=:Id", nativeQuery = true)
    Long getBirthDetailsId( @Param("Id")Long Id);
    @Query(value = "select dobs_id from uk_individual where id=:Id", nativeQuery = true)
    Long getDobsId(@Param("Id")Long Id);
    @Query(value = "select genders_id from uk_individual where id=:Id", nativeQuery = true)
    Long getGendersId(@Param("Id")Long Id);
    @Query(value = "select national_idenitifier_details_id from uk_individual where id=:Id", nativeQuery = true)
    Long getNationalIdentifierDetailsId(@Param("Id")Long Id);
    @Query(value = "select  passport_details_id from uk_individual where id=:Id", nativeQuery = true)
    Long getPassportDetailsId(@Param("Id")Long Id);
    @Query(value = "select positions_id from uk_individual where id=:Id", nativeQuery = true)
    Long getPositionsId(@Param("Id")Long Id);
    @Query(value = "select position from positions_position where positions_id=:Id", nativeQuery = true)
    List<String> getPostions(@Param("Id")Long Id);

    @Query(value = "select nationality from uk_nationalities_nationality where uk_nationalities_id=:Id", nativeQuery = true)
    List<String> getNationalities(@Param("Id")Long Id);
    @Query(value = "select phone_number from phone_numbers_phone_number where phone_numbers_id =:Id", nativeQuery = true)
    List<String> getPhoneNumbers(@Param("Id") Long Id);


    @Query(value = "select dob from dobs_dob where dobs_id=:Id", nativeQuery = true)
    List<String> getDobs(@Param("Id")Long Id);




}

