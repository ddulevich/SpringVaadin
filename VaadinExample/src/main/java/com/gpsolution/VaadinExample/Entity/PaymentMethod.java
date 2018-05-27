package com.gpsolution.VaadinExample.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class PaymentMethod implements Serializable {

	@Column(name = "PAYMENT_METHOD")
	private String paymentMethod;

	@Column(name = "PREPAYMENT")
	private Integer prepayment;
	
	public PaymentMethod() {
		super();
	}
	
	public PaymentMethod(PaymentMethod value) {
		super();
		this.paymentMethod = value.getPaymentMethod();
		this.prepayment = value.getPrepayment();
	}
	
	public Integer getPrepayment() {
		return prepayment;
	}

	public void setPrepayment(Integer prepayment) {
		this.prepayment = prepayment;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}
	
	@Override
	public String toString() {
		if (paymentMethod.equals("Credit Card")) {
			return paymentMethod + " (" + prepayment + ")";
		} else {
			return paymentMethod;
		}
	}
}
