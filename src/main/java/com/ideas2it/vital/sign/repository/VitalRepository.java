package com.ideas2it.vital.sign.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ideas2it.vital.sign.entity.VitalEntity;

@Repository
public interface VitalRepository extends JpaRepository<VitalEntity, Long> {

	VitalEntity findByPatientId(Long patientId);
	
	
}
