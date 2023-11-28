package com.cbo.riskmanagement.repository;

import com.cbo.riskmanagement.model.UsersAddedThisWeek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Weekly extends JpaRepository<UsersAddedThisWeek, Long> {
    @Query("SELECT u FROM UsersAddedThisWeek u WHERE u.customerName IN (SELECT i.firstName FROM UnIndividual i) OR u.customerName IN (SELECT i.secondName FROM UnIndividual i) OR u.customerName IN (SELECT i.thirdName FROM UnIndividual i) OR u.customerName IN (SELECT i.fourthName FROM UnIndividual i) OR u.customerName IN (SELECT e.firstName FROM UnEntity e) OR u.customerName IN (SELECT e.secondName FROM UnEntity e) OR u.customerName IN (SELECT e.thirdName FROM UnEntity e)")
    List<UsersAddedThisWeek> findCommonNames();


}
