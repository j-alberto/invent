package org.jar.invent.web.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the order_workflow database table.
 * 
 */
@Entity
@Table(name="order_workflow")
@NamedQuery(name="OrderWorkflow.findAll", query="SELECT o FROM OrderWorkflow o")
public class OrderWorkflow implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false, length=30)
	private String description;

	//bi-directional many-to-one association to OrderStatus
	@OneToMany(mappedBy="orderWorkflow")
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