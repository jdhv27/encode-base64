package com.jdhv.encondebase64.service;

import com.jdhv.encondebase64.model.EncodeBase64Request;
import com.jdhv.encondebase64.model.EncodeBase64Response;

public interface EncodeBase64Service {
	
	public EncodeBase64Response encodeInBase64(EncodeBase64Request request);

}
