package com.finzly.energyInvoice.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.finzly.energyInvoice.entity.CustomerData;
import com.finzly.energyInvoice.entity.Invoice;
import com.finzly.energyInvoice.utility.Validation;

@Repository
public class CustomerDataDao {
	
	@Autowired
	private SessionFactory factory;
	
	@Autowired
	private InvoiceDao invoiceDao;

	public CustomerData isExist(String email) {
		
		Session session = factory.openSession();
		
		Criteria criteria = session.createCriteria(CustomerData.class);
		
		List<CustomerData> customerData = criteria.add(Restrictions.eq("email", email)).list();
		
		session.close();
		
		return customerData.isEmpty()?null:customerData.get(0);
	}

	
	public CustomerData addCustomer(CustomerData customerData) {
		
		Session session = factory.openSession();
		
		session.beginTransaction();
		
		customerData.setOtp(Validation.generateRandom());
		
        session.save(customerData);
		
		session.getTransaction().commit();
		
		return customerData;
		
		
		
	}


	public List<CustomerData> getCustomers() {
		
		Session session = factory.openSession();
		
		Criteria query = session.createCriteria(CustomerData.class);
		
		List<CustomerData> list = query.list();
		
		return list;
	}
	
	
}
	