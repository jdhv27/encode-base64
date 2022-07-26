package com.jdhv.encondebase64.service.impl;

import java.util.Base64;

import org.springframework.stereotype.Service;

import com.jdhv.encondebase64.model.EncodeBase64Request;
import com.jdhv.encondebase64.model.EncodeBase64Response;
import com.jdhv.encondebase64.service.EncodeBase64Service;

@Service
public class EncodeBase64ServiceImpl implements EncodeBase64Service {

	@Override
	public EncodeBase64Response encodeInBase64(EncodeBase64Request request) {

		String encodedString = switch (request.getEncodeType()) {
		case "base64" -> Base64.getEncoder().encodeToString(request.getTextPlain().getBytes());
		case "base64url" -> Base64.getUrlEncoder().encodeToString(request.getTextPlain().getBytes());
		case "mime" -> Base64.getMimeEncoder().encodeToString(request.getTextPlain().getBytes());
		default -> throw new IllegalArgumentException("Unexpected value: " + request.getEncodeType());
		};

		return new EncodeBase64Response(encodedString);

	}

}
