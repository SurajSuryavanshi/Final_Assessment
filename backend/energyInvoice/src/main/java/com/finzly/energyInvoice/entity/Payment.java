package com.finzly.energyInvoice.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="payment")
public class Payment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;
		
	@Column(name = "payment_amount", unique = false, updatable = true, nullable = false)
    private double paymentAmount;
    
    @Column(name = "transaction_date", unique = false, updatable = true, nullable = false)
    private Date transactionDate;
    
    
    @Column(name = "payment_method", unique = false, updatable = true, nullable = false)
    private String paymentMethod;
    
    
    @Column(name = "is_early", unique = false, updatable = true, nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
	private Boolean isEarly = false; 
    
    
    @Column(name = "is_online", unique = false, updatable = true, nullable = false, columnDefinition = "BOOLEAN DEFAULT true")
	private Boolean isOnline = true;
    
    
    @Column(name="mapped_invoice")
    private long mappedInvoice;
    
    @Column(name="mapped_customer")
    private long mappedCustomer;
    
    
    
	public long getMappedCustomer() {
		return mappedCustomer;
	}


	public void setMappedCustomer(long mappedCustomer) {
		this.mappedCustomer = mappedCustomer;
	}


	public Long getTransactionId() {
		return transactionId;
	}


	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}


	public double getPaymentAmount() {
		return paymentAmount;
	}


	public void setPaymentAmount(double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}


	public Date getTransactionDate() {
		return transactionDate;
	}


	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}


	public String getPaymentMethod() {
		return paymentMethod;
	}


	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}


	public Boolean getIsEarly() {
		return isEarly;
	}


	public void setIsEarly(Boolean isEarly) {
		this.isEarly = isEarly;
	}


	public Boolean getIsOnline() {
		return isOnline;
	}


	public void setIsOnline(Boolean isOnline) {
		this.isOnline = isOnline;
	}


	public long getMappedInvoice() {
		return mappedInvoice;
	}


	public void setMappedInvoice(long mappedInvoice) {
		this.mappedInvoice = mappedInvoice;
	}

    
    
    

}
