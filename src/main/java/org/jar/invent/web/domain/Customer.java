package org.jar.invent.web.domain;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String code;
	@Length(min=0, max=1)
	private String description;
	@NotBlank
	@Length(min=0, max=1)
	private String name;
	@NotNull
	private CustomerStatus customerStatus;
	@NotNull
	private CustomerType customerType;
	private List<Order> orders;

	public Customer() {
	}
	
	public Customer(int id, String code, String description, String name, CustomerStatus customerStatus
			,CustomerType customerType){
		this.id = id;
		this.code = code;
		this.description = description;
		this.name = name;
		this.customerStatus = customerStatus;
		this.customerType = customerType;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CustomerStatus getCustomerStatus() {
		return this.customerStatus;
	}

	public void setCustomerStatus(CustomerStatus customerStatus) {
		this.customerStatus = customerStatus;
	}

	public CustomerType getCustomerType() {
		return this.customerType;
	}

	public void setCustomerType(CustomerType customerType) {
		this.customerType = customerType;
	}

	public List<Order> getOrders() {
		return this.orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Order addOrder(Order order) {
		getOrders().add(order);
		order.setCustomer(this);

		return order;
	}

	public Order removeOrder(Order order) {
		getOrders().remove(order);
		order.setCustomer(null);

		return order;
	}

}