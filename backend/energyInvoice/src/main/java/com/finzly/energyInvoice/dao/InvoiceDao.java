package com.finzly.energyInvoice.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.finzly.energyInvoice.entity.CustomerData;
import com.finzly.energyInvoice.entity.Invoice;

@Repository
public class InvoiceDao {
	
	
	@Autowired
	SessionFactory factory;

	public void generateInvoice(CustomerData customerData) {
		
		// 21 sept
		
		
		//System.out.println("IN DAOOOOOOOOOOOOOOOOO"+customerData);
		
		Invoice invoice = new Invoice();
		
		String uniqueId = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 8);
		String invoiceNumber = LocalDate.now().toString().replace("-", "")+"/" +uniqueId+"/"+customerData.getCustomerId();
		invoice.setInvoiceNumber(invoiceNumber);
		
		
		System.out.println("Billling Rate"+invoice.getBillingRate());
		System.out.println("UnitConsumption"+customerData.getUnitConsumption());
		invoice.setAmountDue(invoice.getBillingRate()*customerData.getUnitConsumption());
     	invoice.setPaymentStatus(false);   
     	
     	invoice.setBillingDuration(customerData.getBillingDuration());
     	invoice.setDueDate(customerData.getBillDueDate());
     	invoice.setUnitConsumption(customerData.getUnitConsumption());
     	invoice.setMapId(customerData.getCustomerId());
     	
     	Session session = factory.openSession();
     	session.beginTransaction();
     	session.save(invoice);
     	
     	//customerData.getInvoices().add(invoice);
     	
       //session.save(customerData);
     	session.getTransaction().commit();
     	
     	session.close();
     	
     	
     	//System.out.print(invoice);	
		
	}
	
	
      public List<Invoice> getInvoices() {
		
		Session session = factory.openSession();
		Criteria query = session.createCriteria(Invoice.class);
		List<Invoice> list = query.list();
		return list;
	}


	
	
	

}
