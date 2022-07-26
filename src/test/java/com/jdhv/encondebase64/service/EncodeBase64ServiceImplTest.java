package com.jdhv.encondebase64.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.google.gson.Gson;
import com.jdhv.encondebase64.model.EncodeBase64Response;
import com.jdhv.encondebase64.service.impl.EncodeBase64ServiceImpl;
import com.jdhv.encondebase64.util.TestUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class EncodeBase64ServiceImplTest {
	
	@InjectMocks
	private EncodeBase64ServiceImpl encodeBase64ServiceImpl;

	@Test
	public void encodeInBase64Test() {
		EncodeBase64Response response = encodeBase64ServiceImpl.encodeInBase64(TestUtils.getRequestWithBase64Format());
		log.info("response: {}", new Gson().toJson(response));
		assertNotNull(response);
		assertNotNull(response.getTextEncoded());
	}
	
	@Test
	public void encodeInBase64URLTest() {
		EncodeBase64Response response = encodeBase64ServiceImpl.encodeInBase64(TestUtils.getRequestWithBase64UrlFormat());
		log.info("response: {}", new Gson().toJson(response));
		assertNotNull(response);
		assertNotNull(response.getTextEncoded());
	}
	
	@Test
	public void encodeInMimeTest() {
		EncodeBase64Response response = encodeBase64ServiceImpl.encodeInBase64(TestUtils.getRequestWithMimeFormat());
		log.info("response: {}", new Gson().toJson(response));
		assertNotNull(response);
		assertNotNull(response.getTextEncoded());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void encodeInDefaultTest() {
		encodeBase64ServiceImpl.encodeInBase64(TestUtils.getRequestWithWrongFormat());
	}

}
