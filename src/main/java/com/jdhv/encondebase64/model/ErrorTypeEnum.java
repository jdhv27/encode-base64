package com.jdhv.encondebase64.model;

public enum ErrorTypeEnum {
	
	ERROR("error"),
	WARN("warn"),
	INVALID("invalid"),
	FATAL("fatal");
	
	public final String nameEnum;
	
	ErrorTypeEnum(String nameEnum) {
		this.nameEnum = nameEnum;
	}

}
