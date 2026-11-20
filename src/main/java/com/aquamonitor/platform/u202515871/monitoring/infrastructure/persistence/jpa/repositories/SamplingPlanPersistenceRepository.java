package com.aquamonitor.platform.u202515871.monitoring.infrastructure.persistence.jpa.repositories;

import com.aquamonitor.platform.u202515871.monitoring.domain.model.valueobjects.PlanCode;
import com.aquamonitor.platform.u202515871.monitoring.infrastructure.persistence.jpa.entities.SamplingPlanPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SamplingPlanPersistenceRepository extends JpaRepository<SamplingPlanPersistenceEntity, Long> {
    @Query("select count(samplingPlan) from SamplingPlanPersistenceEntity samplingPlan where samplingPlan.planCode = :planCode")
    long countByPlanCode(@Param("planCode") PlanCode planCode);
}
