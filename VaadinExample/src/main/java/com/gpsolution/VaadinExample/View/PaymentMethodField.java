package com.gpsolution.VaadinExample.View;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import com.gpsolution.VaadinExample.Entity.PaymentMethod;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.RadioButtonGroup;
import com.vaadin.ui.themes.ValoTheme;

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
		paymentMethod = new RadioButtonGroup<>(null, methods);
	}

	@Override
	public PaymentMethod getValue() {
		return value;
	}

	@Override
	protected Component initContent() {
		super.setCaption(caption);
		
		HorizontalLayout layout = new HorizontalLayout();
		layout.addComponent(paymentMethod);
		layout.setComponentAlignment(paymentMethod, Alignment.TOP_CENTER);
		layout.setWidth(100, Unit.PERCENTAGE);
		
		paymentMethod.addStyleName(ValoTheme.OPTIONGROUP_HORIZONTAL);
			paymentMethod.addValueChangeListener(l -> value.setPaymentMethod(l.getValue()));

		updateValues();

		paymentMethod.addValueChangeListener(l -> value.setPaymentMethod(l.getValue().toString()));

		updateValues();
		return layout;
	}

	private void updateValues() {
		paymentMethod.setItems(methods);
		if (getValue() != null && getValue().getPaymentMethod() != null) {
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
