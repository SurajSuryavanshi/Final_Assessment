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
import com.finzly.energyInvoice.entity.Receipt;
import com.finzly.energyInvoice.service.PaymentService;

@RestController
@CrossOrigin
public class PaymentController {
	
	
	@Autowired
	private PaymentService paymentService;
	
	/**
	 * 
	 * @author Suraj 
	 * Endpoint to make a payment for an invoice.
	 */
	
	@PostMapping(value="make-payment")
	public ResponseEntity<Receipt> makePayment(@RequestBody Invoice invoice){
		
		return paymentService.makePayment(invoice);
		
		
	}
	/**
	 * @author Suraj
	 * Endpoint to check if an invoice is pending
	 * 
	 */
	
	@PostMapping(value="/is-pending")
	public ResponseEntity<Map<String, Object>> isPending(@RequestBody Invoice invoice){
		
		return paymentService.isPending(invoice);
	}
	
	/**
	 * @author Aakanksha
	 * Endpoint to retrieve the payment history for a specific customer.
	 * 
	 */
	
	
	
	@PostMapping(value="get-history/{customerId}")
	public List<Payment> getHistory(@PathVariable long customerId) {
		
		return paymentService.getHistory(customerId);	
	}
	
	/**
	 * 
	 * @author Suraj 
	 * Endpoint to make a payment for an invoice get that invoice by it's invoiceID.
	 */
	
	@PostMapping(value="/make-payment1/{invoiceId}")
	public Map<String,String> makepayment1(@PathVariable long invoiceId){
	
		 return paymentService.makePayment1(invoiceId);		
	}
	
	
	
	
	
	
	
	
	

}
