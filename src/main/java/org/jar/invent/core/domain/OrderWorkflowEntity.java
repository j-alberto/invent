package org.jar.invent.core.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the order_workflow database table.
 * 
 */
@Entity
@Table(name="order_workflow")
public class OrderWorkflowEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false, length=30)
	private String description;

	//bi-directional many-to-one association to OrderStatus
	@OneToMany(mappedBy="orderWorkflow")
	private List<OrderStatusEntity> orderStatuses;

	public OrderWorkflowEntity() {
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

	public List<OrderStatusEntity> getOrderStatuses() {
		return this.orderStatuses;
	}

	public void setOrderStatuses(List<OrderStatusEntity> orderStatuses) {
		this.orderStatuses = orderStatuses;
	}

	public OrderStatusEntity addOrderStatus(OrderStatusEntity orderStatus) {
		getOrderStatuses().add(orderStatus);
		orderStatus.setOrderWorkflow(this);

		return orderStatus;
	}

	public OrderStatusEntity removeOrderStatus(OrderStatusEntity orderStatus) {
		getOrderStatuses().remove(orderStatus);
		orderStatus.setOrderWorkflow(null);

		return orderStatus;
	}

}