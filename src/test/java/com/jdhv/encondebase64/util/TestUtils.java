package com.jdhv.encondebase64.util;

import com.jdhv.encondebase64.model.EncodeBase64Request;
import com.jdhv.encondebase64.model.EncodeBase64Response;

public class TestUtils {
	
	public static EncodeBase64Response getResponse () {
		EncodeBase64Response response = new EncodeBase64Response("1231231231");
		return response;
	}
	
	public static EncodeBase64Request getRequestWithBase64Format () {
		return new EncodeBase64Request("base64", "plainText");
	}
	
	public static EncodeBase64Request getRequestWithBase64UrlFormat () {
		return new EncodeBase64Request("base64url", "plainText");
	}
	
	public static EncodeBase64Request getRequestWithMimeFormat () {
		return new EncodeBase64Request("mime", "plainText");
	}
	
	public static EncodeBase64Request getRequestWithWrongFormat () {
		return new EncodeBase64Request("any", "plainText");
	}

}
