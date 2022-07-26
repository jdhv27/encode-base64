package com.jdhv.encondebase64.model;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EncodeBase64Request {
	
	@NotBlank(message = "encodeType is mandatory")
	@JsonProperty(value = "encodeType")
	private String encodeType;
	
	@NotBlank(message = "textPlain is mandatory")
	@JsonProperty(value = "textPlain")
	private String textPlain;

}
