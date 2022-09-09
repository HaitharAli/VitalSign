package com.ideas2it.vital.sign.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class VitalInputDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5007749435653930396L;
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
