package com.ideas2it.vital.sign.service;

import java.util.List;

import com.ideas2it.vital.sign.dto.VitalDto;
import com.ideas2it.vital.sign.dto.VitalInputDto;

public interface VitalService {
	
	VitalDto getVitalInfoById(Long vitalId,String loginUserId);
	
	VitalDto addVitalDetails(VitalInputDto vitalInputDto,String loginUserId);

	List<VitalDto> getAllVitalInfo(String loginUserId);

	VitalDto getVitalInfoByPatientId(Long patientId, String loginUserId);

	VitalDto updateVitalInfoById(Long vitalId, VitalInputDto vitalInputDto, String loginUserId);

	VitalDto updateVitalPatientId(Long vitalId, Long patientId, String loginUserId);

	Boolean deleteVitalInfoById(Long vitalId, String loginUserId);

}
