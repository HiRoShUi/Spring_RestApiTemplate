package org.MaKro.Spring.RestApi.General.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	
    @RequestMapping(value="/testresource", method=RequestMethod.GET)
    public @ResponseBody ResponseEntity<String> handleFileUpload() {
		return new ResponseEntity<String>("OK",HttpStatus.ACCEPTED);
    }
	
}

