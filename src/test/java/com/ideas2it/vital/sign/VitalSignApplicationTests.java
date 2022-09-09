package com.ideas2it.vital.sign;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.ideas2it.vital.sign.controller.VitalSignController;
import com.ideas2it.vital.sign.entity.VitalEntity;
import com.ideas2it.vital.sign.external.PatientFeignService;
import com.ideas2it.vital.sign.repository.VitalRepository;
import com.ideas2it.vital.sign.service.VitalService;

@SpringBootTest
class VitalSignApplicationTests {

	@Autowired
	private VitalService vitalService;

	@MockBean
	private VitalRepository vitalRepository;

	@MockBean
	private PatientFeignService feignService;
	
	@MockBean
	private VitalSignController vitalSignController;
	
	private String authHeader = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJJMjIyNTAiLCJleHAiOjE2NjI3NTcwMzIsImlhdCI6MTY2MjczOTAzMn0.WFLKcpX7XtmCG8YM9ft2zDvSgSDunko7xsn5ekXqwqQ";


	@Test
	void contextLoads() {
	}

	@Test
	public void getAllVitalInformationTest() {
		when(vitalRepository.findAll()).thenReturn(Stream
				.of(new VitalEntity(Long.valueOf(1), Long.valueOf(1), Long.valueOf(1), 1.0, Long.valueOf(1),
						Long.valueOf(1), Long.valueOf(1), 1.0, 1.0, 1.0, Long.valueOf(1), "I22250"))
				.collect(Collectors.toList()));
		when(feignService.getPatientById(authHeader,Long.valueOf(1), "I22250")).thenReturn(null);

		assertEquals(1, vitalService.getAllVitalInfo("I22250").size());
	}

	@Test
	public void getAllVitalInformationTest2() {
		when(vitalRepository.findAll()).thenReturn(Stream
				.of(new VitalEntity(Long.valueOf(1), Long.valueOf(1), Long.valueOf(1), 1.0, Long.valueOf(1),
						Long.valueOf(1), Long.valueOf(1), 1.0, 1.0, 1.0, Long.valueOf(1), "I22250"))
				.collect(Collectors.toList()));
		when(feignService.getPatientById(authHeader,Long.valueOf(1), "I22250")).thenReturn(null);

		assertThat(vitalService.getAllVitalInfo("I22250")).hasSize(1);
	}
	
	@Test
	public void getAllVitalInformationTestJsonAssert() throws JSONException {
	
		String actual = "{\n"
				+ "    \"pulse\": 0,\n"
				+ "    \"bloodPressure\": 0,\n"
				+ "    \"temperature\": 0,\n"
				+ "    \"spo2\": 0,\n"
				+ "    \"respirations\": 0,\n"
				+ "    \"bloodSugar\": 0,\n"
				+ "    \"height\": 0,\n"
				+ "    \"weight\": 0,\n"
				+ "    \"rtInrRatio\": 0,\n"
				+ "    \"patientId\": 0,\n"
				+ "    \"userId\": 0,\n"
				+ "    \"patientDto\": {\n"
				+ "      \"patientId\": 0,\n"
				+ "      \"medicalRecordNumber\": 0,\n"
				+ "      \"startCareDate\": \"2022-09-06T08:24:49.398Z\",\n"
				+ "      \"birthDate\": \"2022-09-06T08:24:49.398Z\",\n"
				+ "      \"status\": \"string\",\n"
				+ "      \"firstName\": \"string\",\n"
				+ "      \"lastName\": \"string\",\n"
				+ "      \"phoneNumber\": 0,\n"
				+ "      \"sex\": \"string\",\n"
				+ "      \"maritalStatus\": \"string\",\n"
				+ "      \"address\": \"string\",\n"
				+ "      \"state\": \"string\",\n"
				+ "      \"country\": \"string\",\n"
				+ "      \"zipCode\": 0,\n"
				+ "      \"email\": \"string\"\n"
				+ "    }\n"
				+ "  }";
		JSONAssert.assertEquals(
		  "{\n"
		  + "    \"pulse\": 0,\n"
		  + "    \"bloodPressure\": 0,\n"
		  + "    \"temperature\": 0,\n"
		  + "    \"spo2\": 0,\n"
		  + "    \"respirations\": 0,\n"
		  + "    \"bloodSugar\": 0,\n"
		  + "    \"height\": 0,\n"
		  + "    \"weight\": 0,\n"
		  + "    \"rtInrRatio\": 0,\n"
		  + "    \"patientId\": 0,\n"
		  + "    \"userId\": 0,\n"
		  + "    \"patientDto\": {\n"
		  + "      \"patientId\": 0,\n"
		  + "      \"medicalRecordNumber\": 0,\n"
		  + "      \"startCareDate\": \"2022-09-06T08:24:49.398Z\",\n"
		  + "      \"birthDate\": \"2022-09-06T08:24:49.398Z\",\n"
		  + "      \"status\": \"string\",\n"
		  + "      \"firstName\": \"string\",\n"
		  + "      \"lastName\": \"string\",\n"
		  + "      \"phoneNumber\": 0,\n"
		  + "      \"sex\": \"string\",\n"
		  + "      \"maritalStatus\": \"string\",\n"
		  + "      \"address\": \"string\",\n"
		  + "      \"state\": \"string\",\n"
		  + "      \"country\": \"string\",\n"
		  + "      \"zipCode\": 0,\n"
		  + "      \"email\": \"string\"\n"
		  + "    }\n"
		  + "  }", actual, JSONCompareMode.LENIENT);
	}

}
