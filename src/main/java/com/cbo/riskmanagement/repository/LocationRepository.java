package com.cbo.riskmanagement.repository;

import com.cbo.riskmanagement.model.uk.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    @Query(value = "select * from location where location_id =:Id" , nativeQuery = true)
    List<Location> getBirthLocation(@Param("Id") Long Id);
}
