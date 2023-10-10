package com.finzly.energyInvoice.dao;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.finzly.energyInvoice.entity.CustomerData;
import com.finzly.energyInvoice.entity.Invoice;
import com.finzly.energyInvoice.entity.Payment;
import com.finzly.energyInvoice.entity.Receipt;

@Repository
public class PaymentDao {
	
	
	@Autowired
	private SessionFactory factory;
	
	
	public Receipt makePayment(Invoice invoice, double billAmount, boolean isEarly, boolean isOnline) {
		
		 Session session = factory.openSession();
		 session.beginTransaction();
		 
			 
		 Payment payment = new Payment();
		 
		 payment.setPaymentAmount(billAmount);
		 payment.setIsEarly(isEarly);
		 payment.setIsOnline(isOnline);
		
		 if(isOnline)
			 payment.setPaymentMethod("Online");
		 else
			 payment.setPaymentMethod("Offline");
		 
		 payment.setTransactionDate(Date.valueOf(LocalDate.now()));
		 payment.setMappedInvoice(invoice.getInvoiceId());
		 payment.setMappedCustomer(invoice.getMapId());
		 
		 session.save(payment);
		 
		 session.getTransaction().commit();
		 
		 session.close();
		 
		 // Invoice status -update
		 
		  session = factory.openSession();
		  session.beginTransaction();
		 
		  invoice.setPaymentStatus(true);
		  invoice.setTrasactionDate(Date.valueOf(LocalDate.now()));
           
		  session.update(invoice);
		  session.getTransaction().commit();
		  
		  session.close();
		  
		  
		  // Receipt
		  
		  session = factory.openSession();
		  session.beginTransaction();
		  
		  String name = getName(invoice.getMapId());
		  
		  Receipt receipt = new Receipt();
		  
		  receipt.setName(name);
		  receipt.setCustomerId(invoice.getMapId());
		  receipt.setInvoiceNumber(invoice.getInvoiceNumber());
		  receipt.setUnitConsumption(invoice.getUnitConsumption());
		  receipt.setAmount(invoice.getAmountDue());
		  receipt.setDiscount_Amount(billAmount);
		  receipt.setTrasactionDate(Date.valueOf(LocalDate.now()));
		  receipt.setPaymentMode(payment.getPaymentMethod());
		  
		  session.save(receipt);
          session.getTransaction().commit();
		  
		  session.close();
		   
		  return receipt;
		  
		  
		 
		 
		 
	}
	
	
	private String getName(long mapId) {
		
		Session session = factory.openSession();
		CustomerData customer = session.get(CustomerData.class,mapId);
		
		return customer.getName();
		
	}


	public List<Payment> getHistory() {
		
		Session session = factory.openSession();
		Criteria query = session.createCriteria(Payment.class);
		List<Payment> list = query.list();
		return list;
	}


}
