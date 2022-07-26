package com.jdhv.encondebase64.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
	
	private ErrorTypeEnum errorType;
	
	private String timeStamp;
	
	private String code;
	
	private String details;
	
	private String location;
	
	private String moreInfo;

}
