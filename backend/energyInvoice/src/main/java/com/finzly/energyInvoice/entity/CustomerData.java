package com.finzly.energyInvoice.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="customer_data")
public class CustomerData {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long customerId;

	@Column(name = "name", unique = false, updatable = true, nullable = false)
	private String name;
	
	// check later
    @Column(name = "email", unique = true, updatable = true, nullable = false)
    private String email;
    
    
    // check later unique****
    @Column(name = "telephone_number", unique = false, updatable = true, nullable = false)
    private String telephoneNumber;
    
    
    @Column(name = "billing_duration", unique = false, updatable = true, nullable = false)
	private Date billingDuration;

	@Column(name = "unit_consumption", unique = false, updatable = true, nullable = false)
	private double unitConsumption;

	@Column(name = "bill_due_date", unique = false, updatable = true, nullable = false)
	private Date billDueDate;
	

	@Column(name = "otp")
	private int otp;
	
	
	public int getOtp() {
		return otp;
	}

	public void setOtp(int otp) {
		this.otp = otp;
	}
	
	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public Date getBillDueDate() {
		return billDueDate;
	}

	public void setBillDueDate(Date billDueDate) {
		this.billDueDate = billDueDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public Date getBillingDuration() {
		return billingDuration;
	}

	public void setBillingDuration(Date billingDuration) {
		this.billingDuration = billingDuration;
	}

	public double getUnitConsumption() {
		return unitConsumption;
	}

	public void setUnitConsumption(double unitConsumption) {
		this.unitConsumption = unitConsumption;
	}

	

}
