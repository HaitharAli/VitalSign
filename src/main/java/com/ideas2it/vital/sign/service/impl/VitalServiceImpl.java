package com.ideas2it.vital.sign.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ideas2it.vital.sign.dto.PatientDto;
import com.ideas2it.vital.sign.dto.VitalDto;
import com.ideas2it.vital.sign.dto.VitalInputDto;
import com.ideas2it.vital.sign.entity.VitalEntity;
import com.ideas2it.vital.sign.external.PatientFeignService;
import com.ideas2it.vital.sign.repository.VitalRepository;
import com.ideas2it.vital.sign.service.VitalService;
import com.ideas2it.vital.sign.util.VitalDtoConverter;
import com.ideas2it.vital.sign.util.VitalEntityConverter;

@Service
public class VitalServiceImpl implements VitalService {

	@Autowired
	private VitalRepository vitalRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PatientFeignService patientFeignService;

	@Override
	public VitalDto getVitalInfoById(Long vitalId, String loginUserId) {

		VitalEntity entity = vitalRepository.findById(vitalId).get();

		ResponseEntity<Optional<PatientDto>> patientDto = patientFeignService.getPatientById(authHeader,
				entity.getPatientId(), loginUserId);

		System.out.println("Patient Info " + patientDto.getBody().get());
		VitalDto dto = modelMapper.map(entity, VitalDto.class);

		dto.setPatientDto(patientDto.getBody().get());
		return dto;
	}

	@Override
	public VitalDto addVitalDetails(VitalInputDto vitalInputDto, String loginUserId) {

		VitalEntity vitalEntity = convertToVitalEntity(vitalInputDto);
		vitalEntity.setCreatedBy(loginUserId);
		vitalEntity.setModifiedBy(loginUserId);

		VitalEntity saveVitalEntity = vitalRepository.save(vitalEntity);

		return convertToVitalDto(saveVitalEntity);
	}

	@Override
	public List<VitalDto> getAllVitalInfo(String loginUserId) {

		List<VitalEntity> vitalList = vitalRepository.findAll();

		List<VitalDto> vitalDtoList = new ArrayList<>();

		vitalList.stream().forEach(entity -> {
			ResponseEntity<Optional<PatientDto>> patientDto = patientFeignService.getPatientById(authHeader,
					entity.getPatientId(), loginUserId);

			VitalDto dto = modelMapper.map(entity, VitalDto.class);
			if (patientDto != null) {
				System.out.println("Patient Info " + patientDto.getBody().get());
				dto.setPatientDto(patientDto.getBody().get());
			}

			vitalDtoList.add(dto);
		});

		return vitalDtoList;
	}

	@Override
	public VitalDto getVitalInfoByPatientId(Long patientId, String loginUserId) {
		VitalEntity entity = vitalRepository.findByPatientId(patientId);

		ResponseEntity<Optional<PatientDto>> patientDto = patientFeignService.getPatientById(authHeader, patientId,
				loginUserId);

		System.out.println("Patient Info " + patientDto.getBody().get());
		VitalDto dto = modelMapper.map(entity, VitalDto.class);

		dto.setPatientDto(patientDto.getBody().get());
		return dto;
	}

	@Override
	public Boolean deleteVitalInfoById(Long vitalId, String loginUserId) {
		vitalRepository.deleteById(vitalId);
		return true;
	}

	@Override
	public VitalDto updateVitalInfoById(Long vitalId, VitalInputDto vitalInputDto, String loginUserId) {
		VitalEntity entity = vitalRepository.findById(vitalId).get();

		getVitalInfoUpdated(vitalInputDto, loginUserId, entity);

		VitalDto dto = modelMapper.map(vitalRepository.save(entity), VitalDto.class);
		return dto;
	}

	private void getVitalInfoUpdated(VitalInputDto vitalInputDto, String loginUserId, VitalEntity entity) {
		if (entity != null) {
			entity.setBloodPressure(vitalInputDto.getBloodPressure());
			entity.setBloodSugar(vitalInputDto.getBloodSugar());
			entity.setHeight(vitalInputDto.getHeight());
			entity.setModifiedBy(loginUserId);
			entity.setPulse(vitalInputDto.getPulse());
			entity.setRespirations(vitalInputDto.getRespirations());
			entity.setRtInrRatio(vitalInputDto.getRtInrRatio());
			entity.setSpo2(vitalInputDto.getSpo2());
			entity.setTemperature(vitalInputDto.getTemperature());
			entity.setUserId(vitalInputDto.getUserId());
			entity.setPatientId(vitalInputDto.getPatientId());
		}
	}

	@Override
	public VitalDto updateVitalPatientId(Long vitalId, Long patientId, String loginUserId) {
		VitalEntity entity = vitalRepository.findById(vitalId).get();

		entity.setPatientId(patientId);
		entity.setModifiedBy(loginUserId);

		VitalDto dto = modelMapper.map(vitalRepository.save(entity), VitalDto.class);
		return dto;
	}

	public VitalEntity convertToVitalEntity(VitalInputDto vitalInputDto) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		modelMapper.addConverter(new VitalEntityConverter());
		return modelMapper.map(vitalInputDto, VitalEntity.class);
	}

	public VitalDto convertToVitalDto(VitalEntity vitalEntity) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		modelMapper.addConverter(new VitalDtoConverter());
		return modelMapper.map(vitalEntity, VitalDto.class);
	}

}
