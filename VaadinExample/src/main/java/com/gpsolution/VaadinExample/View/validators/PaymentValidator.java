package com.gpsolution.VaadinExample.View.validators;

import com.gpsolution.VaadinExample.Entity.PaymentMethod;
import com.vaadin.data.ValidationResult;
import com.vaadin.data.Validator;
import com.vaadin.data.ValueContext;

public class PaymentValidator implements Validator<PaymentMethod>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public ValidationResult apply(PaymentMethod value, ValueContext context) {
		if (value.getPaymentMethod() == null) {
			return ValidationResult.error("The payment method is required");
		} else {
			return ValidationResult.ok();	
		}
	}
}
