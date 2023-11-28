package com.cbo.riskmanagement.repository;

import com.cbo.riskmanagement.model.un.Consolidated;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsolidatedRepository extends JpaRepository<Consolidated, Long> {
    <S extends Consolidated> List<S> saveAll(Iterable<S> entities);

}
