package com.ideas2it.vital.sign.external;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.ideas2it.vital.sign.dto.PatientDto;

@FeignClient("patient-module")
public interface PatientFeignService {

	@GetMapping("/getPatientById/{id}")
	public ResponseEntity<Optional<PatientDto>> getPatientById(@RequestHeader(value="Authorization") String authHeader,@PathVariable Long id, @RequestParam String loginUserId);

}
