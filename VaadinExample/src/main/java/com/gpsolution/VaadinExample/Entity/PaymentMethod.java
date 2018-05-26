package com.gpsolution.VaadinExample.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class PaymentMethod implements Serializable {

	@Column(name = "PAYMENT_METHOD")
	private String paymentMethod = " ";

	public PaymentMethod() {
		super();
	}

	public PaymentMethod(PaymentMethod value) {
		super();
		this.paymentMethod = value.getPaymentMethod();
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}
	
	@Override
	public String toString() {
		return paymentMethod;
	}
}
