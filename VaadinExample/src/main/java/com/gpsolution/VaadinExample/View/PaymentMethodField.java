package com.gpsolution.VaadinExample.View;

import java.util.ArrayList;

import com.gpsolution.VaadinExample.Entity.PaymentMethod;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.RadioButtonGroup;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
public class PaymentMethodField extends CustomField<PaymentMethod> {

	private final static String CARD = "Credit Card";
	private final static String CASH = "Cash";

	private ArrayList<String> methods = new ArrayList<>();
	private RadioButtonGroup<String> paymentMethod;
	private TextField guarantyDeposit = new TextField();
	private Label lable = new Label("Payment will be made directly in the hotel");

	private PaymentMethod value;
	private String caption = "Pay";

	private HorizontalLayout radioButtonLayout;
	private VerticalLayout customFieldLayout;
	
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
		
		radioButtonLayout = new HorizontalLayout();
		customFieldLayout = new VerticalLayout();
		
		radioButtonLayout.addComponent(paymentMethod);
		radioButtonLayout.setComponentAlignment(paymentMethod, Alignment.TOP_CENTER);
		radioButtonLayout.setWidth(100, Unit.PERCENTAGE);
		
		customFieldLayout.addComponent(radioButtonLayout);
		customFieldLayout.setMargin(false);
		
		paymentMethod.addStyleName(ValoTheme.OPTIONGROUP_HORIZONTAL);

		guarantyDeposit.setPlaceholder("Guaranty Deposit");
		guarantyDeposit.setWidth(100, Unit.PERCENTAGE);
		
		paymentMethod.addValueChangeListener(l -> {
			value.setPaymentMethod(l.getValue().toString());
			if(l.getValue().equals(CASH)) {
				if (customFieldLayout.getComponentCount() == 2) {
					customFieldLayout.removeComponent(customFieldLayout.getComponent(1));		
				}
				customFieldLayout.addComponent(lable);
			} else {
				if (customFieldLayout.getComponentCount() == 2) {
					customFieldLayout.removeComponent(customFieldLayout.getComponent(1));		
				}
				if(getValue().getPrepayment() == null) {
					guarantyDeposit.setValue("0");
				}
				customFieldLayout.addComponent(guarantyDeposit);
				guarantyDeposit.addValueChangeListener(c -> {
					if(!c.getValue().isEmpty()) {
						value.setPrepayment(new Integer(c.getValue()));
					} else {
						value.setPrepayment(null);
					}
				});
			}
		});

		updateValues();
		return customFieldLayout;
	}

	private void updateValues() {
		paymentMethod.setItems(methods);
		if (customFieldLayout.getComponentCount() == 2) {
			customFieldLayout.removeComponent(customFieldLayout.getComponent(1));
		}
		if (getValue() != null && getValue().getPaymentMethod() != null) {
			paymentMethod.setSelectedItem(getValue().getPaymentMethod().toString());
			if(getValue().getPaymentMethod().equals(CASH)) {
				customFieldLayout.addComponent(lable);
			} else {
				customFieldLayout.addComponent(guarantyDeposit);
				guarantyDeposit.setValue(getValue().getPrepayment().toString());
			}
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
