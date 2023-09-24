package com.finzly.energyInvoice.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.finzly.energyInvoice.dao.InvoiceDao;
import com.finzly.energyInvoice.dao.PaymentDao;
import com.finzly.energyInvoice.entity.Invoice;
import com.finzly.energyInvoice.entity.Payment;

@Service
public class PaymentService {
	
	
	@Autowired
	PaymentDao paymentDao;
	
	@Autowired
	InvoiceService invoiceService;
	
	@Autowired
	InvoiceDao invoiceDao;
	
	


	public ResponseEntity<Map<String,String>> makePayment(Invoice invoice) {
		
		
		 boolean isEarly = false;
		 boolean isOnline = false;
		 double totalBill = invoice.getAmountDue();
		 LocalDate currentDate = LocalDate.now();
		 Date date = Date.valueOf(currentDate);
		 
	     
		 
		 // Apply a 5% discount for on-time payment
		  
		 if (isEarlyPayment(date,invoice.getDueDate())) {
		       
			 totalBill -= (totalBill * (5/100));
			 isEarly = true;
		 }

		 // Apply an additional 5% discount for online payment
		 if (isOnlinePayment()) {
		       
			 totalBill -= (totalBill * (5/100));
			 isOnline = true;
		 }
		 
		 if(totalBill<0) {
			 
			 totalBill = invoice.getAmountDue();
		 }
		
	     paymentDao.makePayment(invoice,totalBill,isEarly,isOnline);
	     
	     Map<String,String> response = new HashMap<>();
	     
	     response.put("Message","Payment done successfully..... ");
	     
	     return ResponseEntity.ok().body(response);
	     
	     
	     
	     
	     
	     
	}



	private boolean isOnlinePayment() {
		
		return true;
	}
	
	
	private boolean isEarlyPayment(Date currentDate,Date dueDate) {
		
		return currentDate.before(dueDate) || currentDate.equals(dueDate);

	}



	public ResponseEntity<Map<String,Object>> isPending(Invoice invoice) {
		
		
	    List<Invoice> invoices = invoiceService.getUnpaidInvoices(invoice.getMapId());
	    List<Invoice> lists = new LinkedList<Invoice>();

	    for (Invoice val : invoices) {
	    	if (!val.getInvoiceNumber().equals(invoice.getInvoiceNumber()) &&
	                (val.getDueDate().before(invoice.getDueDate()) || val.getDueDate().equals(invoice.getDueDate()))) {
	                lists.add(val);
	    	}

	    }
	    
	    Map<String, Object> response = new HashMap<>();

	    if (lists.isEmpty()) {
	       
	        response.put("No-Pending",null);
	        return ResponseEntity.ok().body(response);
	    }

	    
	    response.put("Pending", lists);
	    return ResponseEntity.ok().body(response);
	}



	public List<Payment> getHistory(long customerId) {
		
        List<Payment> payments = paymentDao.getHistory();
		
		List<Payment> response = new LinkedList<>();
		
		for(Payment payment: payments) {
			
			if(payment.getMappedCustomer()==customerId){
				
				response.add(payment);
			}
		}
		return(response);
	
	
	}

	
	

}
