package com.jdhv.encondebase64.exception;

import java.text.MessageFormat;
import java.time.Instant;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.jdhv.encondebase64.model.ErrorResponse;
import com.jdhv.encondebase64.model.ErrorTypeEnum;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableWebMvc
@RestControllerAdvice
public class GlobalExceptionHandler {

	private static final String LOG_MSG = "HttpResponse: Exception {} throw with message: [ {} ]. Returning with ErrorResponse: [ {} ].";

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ErrorResponse> handleNoHandlerFoundException(WebRequest wr) {
		ErrorResponse errorResponse = getErrorResponse(ErrorTypeEnum.INVALID,
				String.valueOf(HttpStatus.NOT_FOUND.value()), "Resource Not Found", wr.getContextPath(),
				wr.getDescription(false));
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(WebRequest wr) {
		ErrorResponse errorResponse = getErrorResponse(ErrorTypeEnum.INVALID,
				String.valueOf(HttpStatus.BAD_REQUEST.value()), "Message Not Readable", wr.getContextPath(),
				wr.getDescription(false));
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public ResponseEntity<ErrorResponse> handleHttpMediaTypeNotSupportedException(WebRequest wr) {
		ErrorResponse errorResponse = getErrorResponse(ErrorTypeEnum.INVALID,
				String.valueOf(HttpStatus.BAD_REQUEST.value()), "Media Type Not Supported", wr.getContextPath(),
				wr.getDescription(false));
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(WebRequest wr) {
		ErrorResponse errorResponse = getErrorResponse(ErrorTypeEnum.INVALID,
				String.valueOf(HttpStatus.BAD_REQUEST.value()), "Request Method Not Supported", wr.getContextPath(),
				wr.getDescription(false));
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException manve,
			WebRequest wr) {
		Optional<FieldError> optfe = manve.getBindingResult().getFieldErrors().stream().findFirst();
		ErrorResponse errorResponse = null;
		if(optfe.isPresent()) {
			String message = MessageFormat.format("Invalid Request with {0}, {1}", optfe.get().getField(), optfe.get().getDefaultMessage());
			errorResponse = getErrorResponse(ErrorTypeEnum.INVALID,
					String.valueOf(HttpStatus.BAD_REQUEST.value()), message, wr.getContextPath(),
					wr.getDescription(false));
		}
		
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException iae,
			WebRequest wr) {
		ErrorResponse errorResponse = getErrorResponse(ErrorTypeEnum.ERROR,
					String.valueOf(HttpStatus.BAD_REQUEST.value()), iae.getMessage(), wr.getContextPath(),
					wr.getDescription(false));
		
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	private ErrorResponse getErrorResponse(ErrorTypeEnum errorTypeEnum, String code, String details, String location,
			String moreInfo) {
		ErrorResponse response = new ErrorResponse();
		response.setErrorType(errorTypeEnum);
		response.setCode(code);
		response.setDetails(details);
		response.setLocation(location);
		response.setMoreInfo(moreInfo);
		response.setTimeStamp(String.valueOf(Instant.now().toEpochMilli()));
		log.info(LOG_MSG, response.getErrorType(), response.getCode(), response.getDetails(), response.getMoreInfo(),
				response.getLocation());
		return response;
	}

}
