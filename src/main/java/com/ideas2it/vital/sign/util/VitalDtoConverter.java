package com.ideas2it.vital.sign.util;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import com.ideas2it.vital.sign.dto.VitalDto;
import com.ideas2it.vital.sign.entity.VitalEntity;

public class VitalDtoConverter implements Converter<VitalEntity, VitalDto> {

	@Override
	public VitalDto convert(MappingContext<VitalEntity, VitalDto> context) {
		VitalEntity entity = context.getSource();
		VitalDto vitalDto = context.getDestination();

		if (vitalDto == null) {
			vitalDto = new VitalDto();
		}

		if (entity != null) {
			vitalDto.setBloodPressure(entity.getBloodPressure());
			vitalDto.setBloodSugar(entity.getBloodSugar());
			vitalDto.setHeight(entity.getHeight());
			vitalDto.setPulse(entity.getPulse());
			vitalDto.setRespirations(entity.getRespirations());
			vitalDto.setRtInrRatio(entity.getRtInrRatio());
			vitalDto.setSpo2(entity.getSpo2());
			vitalDto.setTemperature(entity.getTemperature());
			vitalDto.setUserId(entity.getUserId());
			vitalDto.setPatientId(entity.getPatientId());
		}

		return vitalDto;
	}

}
