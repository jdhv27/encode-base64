package com.jdhv.encondebase64.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.jdhv.encondebase64.model.EncodeBase64Request;
import com.jdhv.encondebase64.model.EncodeBase64Response;
import com.jdhv.encondebase64.service.EncodeBase64Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Validated
@RestController
public class EncodeBase64Controller {

	@Autowired
	private EncodeBase64Service encodeBase64Service;

	@ResponseBody
	@PostMapping(value = "${encodebase64.api.encode.path}")
	public ResponseEntity<EncodeBase64Response> encode(@Valid @RequestBody EncodeBase64Request request) {
		log.info("***** Starting encode API *****");
		log.debug("request data: {}", new Gson().toJson(request));
		return new ResponseEntity<>(encodeBase64Service.encodeInBase64(request), HttpStatus.OK);
	}

}
