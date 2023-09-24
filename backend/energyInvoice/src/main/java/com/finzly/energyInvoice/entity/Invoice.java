package com.finzly.energyInvoice.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="invoice")
public class Invoice {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long invoiceId;

	@Column(name = "invoice_number", unique = true, length = 50)
	private String invoiceNumber;

	@Column(name = "billing_duration", unique = false, updatable = true, nullable = false)
	private Date billingDuration;

	@Column(name = "unit_consumption", unique = false, updatable = true, nullable = false)
	private double unitConsumption;

	@Column(name = "due_date", unique = false, updatable = true, nullable = false)
	private Date dueDate;

	@Column(name = "amount_due", precision = 10, scale = 2)
	private double amountDue;

	@Column(name = "payment_status", unique = false, updatable = true, nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
	private Boolean paymentStatus = false;
	
	
	@Column(name = "map_id")
	private long mapId;
	
	@Column(name="billing_rate",columnDefinition = "DECIMAL(10, 2) DEFAULT 41.50")
	private double billingRate;
	
	@Column(name = "transaction_date", unique = false, updatable = true)
	private Date trasactionDate=Date.valueOf("1970-01-01");
	



	public Invoice() {
		
		this.billingRate = 41.50;
		this.trasactionDate = Date.valueOf("1970-01-01");	
	}


	public long getMapId() {
		return mapId;
	}


	public void setMapId(long mapId) {
		this.mapId = mapId;
	}


	public Long getInvoiceId() {
		return invoiceId;
	}


	public void setInvoiceId(Long invoiceId) {
		this.invoiceId = invoiceId;
	}


	public double getBillingRate() {
		return billingRate;
	}


	public void setBillingRate(double billingRate) {
		this.billingRate = billingRate;
	}


	public String getInvoiceNumber() {
		return invoiceNumber;
	}


	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}


	public Date getBillingDuration() {
		return billingDuration;
	}


	public void setBillingDuration(Date date) {
		this.billingDuration = date;
	}


	public double getUnitConsumption() {
		return unitConsumption;
	}


	public void setUnitConsumption(double unitConsumption) {
		this.unitConsumption = unitConsumption;
	}


	public Date getDueDate() {
		return dueDate;
	}


	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}


	public double getAmountDue() {
		return amountDue;
	}


	public void setAmountDue(double amountDue) {
		this.amountDue = amountDue;
	}


	public Boolean getPaymentStatus() {
		return paymentStatus;
	}


	public void setPaymentStatus(Boolean paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	
	public Date getTrasactionDate() {
		return trasactionDate;
	}


	public void setTrasactionDate(Date trasactionDate) {
		this.trasactionDate = trasactionDate;
	}



	
	
	

}
