package org.jar.invent.web.domain;

import java.io.Serializable;
import java.util.List;


public class OrderWorkflow implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String description;
	private List<OrderStatus> orderStatuses;

	public OrderWorkflow() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<OrderStatus> getOrderStatuses() {
		return this.orderStatuses;
	}

	public void setOrderStatuses(List<OrderStatus> orderStatuses) {
		this.orderStatuses = orderStatuses;
	}

	public OrderStatus addOrderStatus(OrderStatus orderStatus) {
		getOrderStatuses().add(orderStatus);
		orderStatus.setOrderWorkflow(this);

		return orderStatus;
	}

	public OrderStatus removeOrderStatus(OrderStatus orderStatus) {
		getOrderStatuses().remove(orderStatus);
		orderStatus.setOrderWorkflow(null);

		return orderStatus;
	}

}