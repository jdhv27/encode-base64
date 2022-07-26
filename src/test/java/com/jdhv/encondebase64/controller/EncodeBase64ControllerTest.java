package com.jdhv.encondebase64.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.google.gson.Gson;
import com.jdhv.encondebase64.model.EncodeBase64Response;
import com.jdhv.encondebase64.service.EncodeBase64Service;
import com.jdhv.encondebase64.util.TestUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class EncodeBase64ControllerTest {

	@Mock
	private EncodeBase64Service encodeBase64Service;

	@InjectMocks
	private EncodeBase64Controller encodeBase64Controller;

	@Test
	public void encodeTest() {
		when(encodeBase64Service.encodeInBase64(Mockito.any())).thenReturn(TestUtils.getResponse());
		ResponseEntity<EncodeBase64Response> response = encodeBase64Controller
				.encode(TestUtils.getRequestWithBase64Format());
		log.info("response: {}", new Gson().toJson(response));
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(200, response.getStatusCodeValue());
	}

}
