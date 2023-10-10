package com.finzly.energyInvoice.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.finzly.energyInvoice.entity.CustomerData;
import com.finzly.energyInvoice.entity.Login;
import com.finzly.energyInvoice.service.LoginService;

@RestController
@CrossOrigin
public class LoginController {
	
	
	@Autowired
	private LoginService loginService;
	
	/**
	 * 
	 * @author Suraj
	 * Checks the validity of a customer
	 * 
	 */
	
	@PostMapping(value = "/check-valid/{customerId}")
	ResponseEntity<Map<String, Object>> checkValid(@PathVariable Long customerId){
		   
		return loginService.checkValid(customerId);
	}

	/**
	 * 
	 * @author Suarj
	 * Handles customer login
	 */
	@PostMapping(value = "/login")
	ResponseEntity<Map<String,String>> login(@RequestBody CustomerData customer){
		   
		return loginService.login(customer);
		
	}
}
