package com.ideas2it.vital.sign.util;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import com.ideas2it.vital.sign.dto.VitalInputDto;
import com.ideas2it.vital.sign.entity.VitalEntity;

public class VitalEntityConverter implements Converter<VitalInputDto, VitalEntity> {

	@Override
	public VitalEntity convert(MappingContext<VitalInputDto, VitalEntity> context) {

		VitalInputDto vitalInputDto = context.getSource();
		VitalEntity entity = context.getDestination();

		if (entity == null) {
			entity = new VitalEntity();
		}

		if (vitalInputDto != null) {
			entity.setBloodPressure(vitalInputDto.getBloodPressure());
			entity.setBloodSugar(vitalInputDto.getBloodSugar());
			entity.setHeight(vitalInputDto.getHeight());
			entity.setPulse(vitalInputDto.getPulse());
			entity.setRespirations(vitalInputDto.getRespirations());
			entity.setRtInrRatio(vitalInputDto.getRtInrRatio());
			entity.setSpo2(vitalInputDto.getSpo2());
			entity.setTemperature(vitalInputDto.getTemperature());
			entity.setUserId(vitalInputDto.getUserId());
			entity.setPatientId(vitalInputDto.getPatientId());
		}

		return entity;
	}

}
