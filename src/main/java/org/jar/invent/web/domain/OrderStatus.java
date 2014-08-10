package org.jar.invent.web.domain;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.jar.invent.core.domain.EnumStatusGeneral;

public class OrderStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	@NotBlank
	@Length(min=1, max=30)
	private String description;
	private EnumStatusGeneral status;
	private List<Order> orders;
	@NotNull
	private OrderWorkflow orderWorkflow;

		public OrderStatus() {
			this.status = EnumStatusGeneral.REGISTERED;
		}
		
		public OrderStatus(int id, String description, EnumStatusGeneral status) {
			this.id = id;
			this.description = description;
			this.status = status;
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

	public EnumStatusGeneral getStatus() {
		return this.status;
	}

	public void setStatus(EnumStatusGeneral status) {
		this.status = status;
	}

	public List<Order> getOrders() {
		return this.orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Order addOrder(Order order) {
		getOrders().add(order);
		order.setOrderStatus(this);

		return order;
	}

	public Order removeOrder(Order order) {
		getOrders().remove(order);
		order.setOrderStatus(null);

		return order;
	}

	public OrderWorkflow getOrderWorkflow() {
		return this.orderWorkflow;
	}

	public void setOrderWorkflow(OrderWorkflow orderWorkflow) {
		this.orderWorkflow = orderWorkflow;
	}
	
	@Override
	public String toString(){
		return String.format("OrderStatus{id=%d, desc=%s, status=%s}", id, description, status.toString());
	}
}