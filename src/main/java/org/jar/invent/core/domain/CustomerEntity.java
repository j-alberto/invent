package org.jar.invent.core.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the customer database table.
 * 
 */
@Entity
@Table(name="customer")
public class CustomerEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(updatable=false, nullable=false, length=8)
	private String code;

	@Column(length=50)
	private String description;

	@Column(nullable=false, length=30)
	private String name;

	//bi-directional many-to-one association to CustomerStatus
	@ManyToOne
	@JoinColumn(name="idstatus")
	private CustomerStatusEntity customerStatus;

	//bi-directional many-to-one association to CustomerType
	@ManyToOne
	@JoinColumn(name="idtype", nullable=false)
	private CustomerTypeEntity customerType;

	//bi-directional many-to-one association to Order
	@OneToMany(mappedBy="customer")
	private List<OrderEntity> orders;

	public CustomerEntity() {
	}
	
	public CustomerEntity(int id, String code, String description, String name, CustomerStatusEntity customerStatus
			,CustomerTypeEntity customerType){
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

	public CustomerStatusEntity getCustomerStatus() {
		return this.customerStatus;
	}

	public void setCustomerStatus(CustomerStatusEntity customerStatus) {
		this.customerStatus = customerStatus;
	}

	public CustomerTypeEntity getCustomerType() {
		return this.customerType;
	}

	public void setCustomerType(CustomerTypeEntity customerType) {
		this.customerType = customerType;
	}

	public List<OrderEntity> getOrders() {
		return this.orders;
	}

	public void setOrders(List<OrderEntity> orders) {
		this.orders = orders;
	}

	public OrderEntity addOrder(OrderEntity order) {
		getOrders().add(order);
		order.setCustomer(this);

		return order;
	}

	public OrderEntity removeOrder(OrderEntity order) {
		getOrders().remove(order);
		order.setCustomer(null);

		return order;
	}

}