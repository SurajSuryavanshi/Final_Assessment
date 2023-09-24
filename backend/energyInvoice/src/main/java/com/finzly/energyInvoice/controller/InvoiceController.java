package com.finzly.energyInvoice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finzly.energyInvoice.entity.CustomerData;
import com.finzly.energyInvoice.entity.Invoice;
import com.finzly.energyInvoice.service.CustomerDataService;
import com.finzly.energyInvoice.service.InvoiceService;


@CrossOrigin()
@RestController
public class InvoiceController {
	

	@Autowired
	private InvoiceService invoiceService;

	
	@GetMapping(value="/get-invoices")
	public ResponseEntity<List<Invoice>> getInvoices(){
		
		
		return invoiceService.getInvoices();
	}
	
	
	@PostMapping(value="/get-custInvoices/{customerId}")
	public List<Invoice> getCustInvoices(@PathVariable long customerId ){
		
		return invoiceService.getCustInvoices(customerId);	
		
	}
	
	
	@PostMapping(value="get-unpaid/{customerId}")
	public List<Invoice> getUnpaidInvoices(@PathVariable long customerId) {
		
		return invoiceService.getUnpaidInvoices(customerId);	
	}
	
	@PostMapping(value="get-paid/{customerId}")
	public List<Invoice> getpaidInvoices(@PathVariable long customerId) {
		
		return invoiceService.getpaidInvoices(customerId);	
	}
}
