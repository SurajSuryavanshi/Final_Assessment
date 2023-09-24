package com.finzly.energyInvoice.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="receipt")
public class Receipt {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long receiptId;
	
	@Column(name = "name",  unique = false,nullable = false)
	private String name;

	

	@Column(name = "customer_id", unique = false, updatable = true, nullable = false)
	private long customerId;

	@Column(name = "invoice_number",  unique = false,nullable = false)
	private String invoiceNumber;
	
	@Column(name = "unit_consumption", unique = false, updatable = true, nullable = false)
	private double unitConsumption;
	
	@Column(name = "amount", precision = 10, scale = 2)
	private double amount;
	
	
	@Column(name = "discount_amount", precision = 10, scale = 2)
	private double discount_Amount;
	
	
	@Column(name = "transaction_date", unique = false, updatable = true)
	private Date trasactionDate;
	
	@Column(name = "payment_mode",  unique = false,nullable = false)
	private String paymentMode;
	
	
	
	public Receipt() {
		
		
	}

	public Long getReceiptId() {
		return receiptId;
	}


	public void setReceiptId(Long receiptId) {
		this.receiptId = receiptId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public long getCustomerId() {
		return customerId;
	}


	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}


	public String getInvoiceNumber() {
		return invoiceNumber;
	}


	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}


	public double getUnitConsumption() {
		return unitConsumption;
	}


	public void setUnitConsumption(double unitConsumption) {
		this.unitConsumption = unitConsumption;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}


	public double getDiscount_Amount() {
		return discount_Amount;
	}


	public void setDiscount_Amount(double discount_Amount) {
		this.discount_Amount = discount_Amount;
	}


	public Date getTrasactionDate() {
		return trasactionDate;
	}


	public void setTrasactionDate(Date trasactionDate) {
		this.trasactionDate = trasactionDate;
	}


	public String getPaymentMode() {
		return paymentMode;
	}


	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}


	
	
	
	
	
}
	
	
