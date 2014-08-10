package org.jar.invent.core.domain;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the order_type database table.
 * 
 */
@Entity
@Table(name="order_type")
public class OrderTypeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

		public OrderTypeEntity() {
		}
	
		public OrderTypeEntity(int id, String description, EnumStatusGeneral status) {
			this.id = id;
			this.description = description;
			this.status = status;
		}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false, length=30)
	private String description;

	@Column(nullable=false, length=1)
	@Enumerated
	private EnumStatusGeneral status;

	//bi-directional many-to-one association to Order
	@OneToMany(mappedBy="orderType")
	private List<OrderEntity> orders;

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

	public List<OrderEntity> getOrders() {
		return this.orders;
	}

	public void setOrders(List<OrderEntity> orders) {
		this.orders = orders;
	}

	public OrderEntity addOrder(OrderEntity order) {
		getOrders().add(order);
		order.setOrderType(this);

		return order;
	}

	public OrderEntity removeOrder(OrderEntity order) {
		getOrders().remove(order);
		order.setOrderType(null);

		return order;
	}
	
	@Override
	public String toString(){
		return String.format("OrderTypeEntity{id=%d, desc=%s, status=%s}", id, description, status.toString());
	}

}