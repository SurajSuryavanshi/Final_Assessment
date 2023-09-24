package com.finzly.energyInvoice.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.finzly.energyInvoice.entity.CustomerData;
import com.finzly.energyInvoice.entity.Login;
import com.finzly.energyInvoice.utility.Validation;


@Repository
public class LoginDao {
	
	@Autowired
	SessionFactory factory;

public CustomerData checkValid(Long customerId) {
		
        Session session = factory.openSession();
		
		Criteria criteria = session.createCriteria(CustomerData.class);
		
		List<CustomerData> isValid = criteria.add(Restrictions.eq("customerId",customerId)).list();
		

		if(isValid.isEmpty()) {
			
			return null;
		}
		
		isValid.get(0).setOtp(Validation.generateRandom());
		session.update(isValid.get(0));
		
		session.beginTransaction().commit();
		
		
		return isValid.get(0);
		
		
	}

	   public boolean login(CustomerData customer) {
        
		Session session = factory.openSession();
		
		Criteria criteria = session.createCriteria(CustomerData.class);
		
		List<CustomerData> isValid = criteria.add(Restrictions.eq("customerId",customer.getCustomerId())).list();
		
		if(isValid.isEmpty()) {
			
			return false;
		} 
		
		
		if(isValid.get(0).getOtp()== customer.getOtp()){
			
			return true;
		}
		
		return false;
	}

}
