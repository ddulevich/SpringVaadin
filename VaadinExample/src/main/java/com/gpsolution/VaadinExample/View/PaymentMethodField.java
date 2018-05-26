package com.gpsolution.VaadinExample.View;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import com.gpsolution.VaadinExample.Entity.PaymentMethod;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.RadioButtonGroup;

@SuppressWarnings("serial")
public class PaymentMethodField extends CustomField<PaymentMethod> {

	private final static String CARD = "Credit Card";
	private final static String CASH = "Cash";

	private ArrayList<String> methods = new ArrayList<>();
	private RadioButtonGroup<String> paymentMethod;

	private PaymentMethod value;
	private String caption = "Pay";

	public PaymentMethodField(String caption) {
		super();
		this.caption = caption;
		methods.add(CARD);
		methods.add(CASH);
		paymentMethod = new RadioButtonGroup<>("", methods);
	}

	@Override
	public PaymentMethod getValue() {
		return value;
	}

	@Override
	protected Component initContent() {
		HorizontalLayout layout = new HorizontalLayout();
		super.setCaption(caption);
		paymentMethod.addValueChangeListener(l -> value.setPaymentMethod(l.getValue()));
		layout.addComponent(paymentMethod);

		updateValues();

		paymentMethod.addValueChangeListener(l -> value.setPaymentMethod(l.getValue().toString()));

		updateValues();

		return layout;
	}

	private void updateValues() {
		if (getValue() != null) {
			paymentMethod.setSelectedItem(getValue().toString());
		}
	}

	@Override
	protected void doSetValue(PaymentMethod value) {
		if (value == null) {
			this.value = new PaymentMethod();
		} else {
			this.value = new PaymentMethod(value);
		}
		updateValues();
	}
}
