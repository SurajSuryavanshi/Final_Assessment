package com.finzly.energyInvoice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="login")
public class Login {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long customerId;


	@Column(name = "otp", nullable = false)
    private int otp;
	
	
	public Login() {
		
	}
	
	public long getCustomerId() {
		return customerId;
	}

	public void setEmployeeId(long customerId) {
		this.customerId = customerId;
	}

	public int getOtp() {
		return otp;
	}

	public void setOtp(int otp) {
		this.otp = otp;
	}

}
