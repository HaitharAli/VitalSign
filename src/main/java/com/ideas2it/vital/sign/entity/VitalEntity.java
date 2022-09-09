package com.ideas2it.vital.sign.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "vital")
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class VitalEntity extends AuditEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long pulse;
	private Long bloodPressure;
	private double temperature;
	private Long spo2;
	private Long respirations;
	private Long bloodSugar;
	private double height;
	private double weight;
	private double rtInrRatio;
	private Long patientId;
	private String userId;

}
