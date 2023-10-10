package com.finzly.energyInvoice.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.finzly.energyInvoice.dao.InvoiceDao;
import com.finzly.energyInvoice.dao.PaymentDao;
import com.finzly.energyInvoice.entity.Invoice;
import com.finzly.energyInvoice.entity.Payment;
import com.finzly.energyInvoice.entity.Receipt;

@Service
public class PaymentService {
	
	
	@Autowired
	private PaymentDao paymentDao;
	
	@Autowired
	private InvoiceService invoiceService;
	
	@Autowired
	private InvoiceDao invoiceDao;
	
	@Autowired
	private SessionFactory factory; 
	
	


	public ResponseEntity<Receipt> makePayment(Invoice invoice) {
		
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
		  
		 Receipt receipt=paymentDao.makePayment(invoice,totalBill,isEarly,isOnline);
	     

	     
	     return ResponseEntity.ok().body(receipt);
	     
	     
	     
	     
	     
	     
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
	
	public Map<String, String> makePayment1(long invoiceId) {
		
		
		 boolean isEarly = false;
		 boolean isOnline = false;
		 
		 Session session = factory.openSession();
		 session.beginTransaction();
		 Invoice invoice = session.get(Invoice.class,invoiceId);
		
		 
		 double totalBill = invoice.getAmountDue();
		 LocalDate currentDate = LocalDate.now();
		 Date date = Date.valueOf(currentDate);
		 
		 double bill =0.0;
		 
	     
		 
		 // Apply a 5% discount for on-time payment
		  
		 if (isEarlyPayment(date,invoice.getDueDate())) {
		       
            bill = invoice.getAmountDue()*0.05;
			 
			 totalBill = totalBill-bill;
			 isEarly = true;
		 }
		 
		 // Apply an additional 5% discount for online payment
		 if (isOnlinePayment()) {
		       
			  
            bill = invoice.getAmountDue()*0.05;
			 
			 totalBill = totalBill-bill;
			 isOnline = true;
		 
		 }
		 
			 
		 if(totalBill<0) {
			 
			 bill = invoice.getAmountDue();
		 }
		
	     paymentDao.makePayment(invoice,totalBill,isEarly,isOnline);
	     
	     Map<String,String> response = new HashMap<>();
	     
	     response.put("Message","Payment done successfully..... ");
	     
	     return (response);
		
		
	}

	
	

}
