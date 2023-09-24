package com.finzly.energyInvoice.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.finzly.energyInvoice.entity.Invoice;
import com.finzly.energyInvoice.entity.Payment;
import com.finzly.energyInvoice.service.PaymentService;

@RestController
@CrossOrigin
public class PaymentController {
	
	
	@Autowired
	PaymentService paymentService;
	
	@PostMapping(value="/make-payment")
	public ResponseEntity<Map<String, String>> makePayment(@RequestBody Invoice invoice){
		
		return paymentService.makePayment(invoice);
		
		
	}
	
	
	@PostMapping(value="/is-pending")
	public ResponseEntity<Map<String, Object>> isPending(@RequestBody Invoice invoice){
		
		return paymentService.isPending(invoice);
	}
	
	
	
	@PostMapping(value="/get-history/{customerId}")
	public List<Payment> getHistory(@PathVariable long customerId) {
		
		return paymentService.getHistory(customerId);	
	}
	
	
	/*@PostMapping(value="/get-history/{invoiceno}")
	public List<Payment> getHistory(@PathVariable string customerId) {
		
		return paymentService.getHistory(customerId);	
	}
	*/
	
	
	
	
	
	

}
