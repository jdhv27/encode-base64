package com.jdhv.encondebase64.exception;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;

import com.google.gson.Gson;
import com.jdhv.encondebase64.model.EncodeBase64Request;
import com.jdhv.encondebase64.model.ErrorResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class GlobalExceptionHandlerTest {

	@Mock
	private WebRequest wr;

	@InjectMocks
	private GlobalExceptionHandler globalExceptionHandler;

	@Test
	public void testHandleNoHandlerFoundException() {
		ResponseEntity<ErrorResponse> responseEntity = globalExceptionHandler.handleNoHandlerFoundException(wr);
		log.info("response: {}", new Gson().toJson(responseEntity));
		assertNotNull(responseEntity);
		assertNotNull(responseEntity.getBody());
		assertEquals(responseEntity.getBody().getCode(), String.valueOf(HttpStatus.NOT_FOUND.value()));
	}

	@Test
	public void testHandleHttpMessageNotReadableException() {
		ResponseEntity<ErrorResponse> responseEntity = globalExceptionHandler.handleHttpMessageNotReadableException(wr);
		log.info("response: {}", new Gson().toJson(responseEntity));
		assertNotNull(responseEntity);
		assertNotNull(responseEntity.getBody());
		assertEquals(responseEntity.getBody().getCode(), String.valueOf(HttpStatus.BAD_REQUEST.value()));
	}

	@Test
	public void testHandleHttpMediaTypeNotSupportedException() {
		ResponseEntity<ErrorResponse> responseEntity = globalExceptionHandler
				.handleHttpMediaTypeNotSupportedException(wr);
		log.info("response: {}", new Gson().toJson(responseEntity));
		assertNotNull(responseEntity);
		assertNotNull(responseEntity.getBody());
		assertEquals(responseEntity.getBody().getCode(), String.valueOf(HttpStatus.BAD_REQUEST.value()));
	}

	@Test
	public void testHandleHttpRequestMethodNotSupportedException() {
		ResponseEntity<ErrorResponse> responseEntity = globalExceptionHandler
				.handleHttpRequestMethodNotSupportedException(wr);
		log.info("response: {}", new Gson().toJson(responseEntity));
		assertNotNull(responseEntity);
		assertNotNull(responseEntity.getBody());
		assertEquals(responseEntity.getBody().getCode(), String.valueOf(HttpStatus.BAD_REQUEST.value()));
	}

	@Test
	public void testMethodArgumentNotValidException() throws NoSuchMethodException, SecurityException {
		EncodeBase64Request encodeBase64Request = new EncodeBase64Request();
		BeanPropertyBindingResult errors = new BeanPropertyBindingResult(encodeBase64Request, "testBean");
		errors.rejectValue("textPlain", "invalid");
		MethodParameter methodParameter = new MethodParameter(encodeBase64Request.getClass().getMethod("getTextPlain"),
				-1);
		MethodArgumentNotValidException manve = new MethodArgumentNotValidException(methodParameter, errors);
		
		ResponseEntity<ErrorResponse> responseEntity = globalExceptionHandler
				.handleMethodArgumentNotValidException(manve, wr);
		log.info("response: {}", new Gson().toJson(responseEntity));
		assertNotNull(responseEntity);
		assertNotNull(responseEntity.getBody());
		assertEquals(responseEntity.getBody().getCode(), String.valueOf(HttpStatus.BAD_REQUEST.value()));
	}
	
	@Test
	public void testHandleIllegalArgumentException() {
		IllegalArgumentException illegalArgumentException = new IllegalArgumentException("Illegal Argument Exception");
		ResponseEntity<ErrorResponse> responseEntity = globalExceptionHandler
				.handleIllegalArgumentException(illegalArgumentException, wr);
		log.info("response: {}", new Gson().toJson(responseEntity));
		assertNotNull(responseEntity);
		assertNotNull(responseEntity.getBody());
		assertEquals(responseEntity.getBody().getCode(), String.valueOf(HttpStatus.BAD_REQUEST.value()));
	}

}
