package com.ideas2it.vital.sign.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ideas2it.vital.sign.dto.VitalDto;
import com.ideas2it.vital.sign.dto.VitalInputDto;
import com.ideas2it.vital.sign.service.VitalService;

@RestController
public class VitalSignController {

	@Autowired
	private VitalService vitalService;

	@GetMapping("/test")
	public ResponseEntity<HttpStatus> test() {
		return new ResponseEntity<>(HttpStatus.OK, HttpStatus.OK);
	}

	@GetMapping("/getAllVital")
	public ResponseEntity<List<VitalDto>> getAllVitalInformation(String loginUserId) {
		return new ResponseEntity<List<VitalDto>>(vitalService.getAllVitalInfo(loginUserId), HttpStatus.OK);

	}

	@GetMapping("/getByVitalId/{vitalId}")
	public ResponseEntity<VitalDto> getVitalInformationById(@PathVariable Long vitalId, String loginUserId) {
		return new ResponseEntity<VitalDto>(vitalService.getVitalInfoById(vitalId, loginUserId), HttpStatus.OK);
	}

	@GetMapping("/getVitalInfoByPatientId/{patientId}")
	public ResponseEntity<VitalDto> getVitalInfoByPatientId(@PathVariable Long patientId, String loginUserId) {
		return new ResponseEntity<VitalDto>(vitalService.getVitalInfoByPatientId(patientId, loginUserId),
				HttpStatus.OK);

	}

	@PostMapping("/addVitalInfo")
	public ResponseEntity<VitalDto> addVitalInformation(@RequestBody VitalInputDto vitalInputDto, String loginUserId) {
		return new ResponseEntity<VitalDto>(vitalService.addVitalDetails(vitalInputDto, loginUserId), HttpStatus.OK);

	}

	@PutMapping("/updateVitalInfo/{vitalId}")
	public ResponseEntity<VitalDto> updateVitalInfo(@RequestBody VitalInputDto vitalInputDto,
			@PathVariable Long vitalId, String loginUserId) {
		return new ResponseEntity<VitalDto>(vitalService.updateVitalInfoById(vitalId, vitalInputDto, loginUserId),
				HttpStatus.OK);
	}

	@PatchMapping("/patchVitalPatientId/{vitalId}/{patientId}")
	public ResponseEntity<VitalDto> updateVitalPatientId(@PathVariable Long vitalId, @PathVariable Long patientId,
			String loginUserId) {
		return new ResponseEntity<VitalDto>(vitalService.updateVitalPatientId(vitalId, patientId, loginUserId),
				HttpStatus.OK);
	}

	@DeleteMapping("/deleteVitalInfo/{vitalId}")
	public ResponseEntity<Boolean> deleteVitalInfo(@PathVariable Long vitalId, String loginUserId) {
		return new ResponseEntity<Boolean>(vitalService.deleteVitalInfoById(vitalId, loginUserId), HttpStatus.OK);
	}
}
