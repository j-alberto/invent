package org.jar.invent.core.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the customer_status database table.
 * 
 */
@Entity
@Table(name="customer_status")
public class CustomerStatusEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false, length=30)
	private String description;

	@Column(nullable=false)
	@Enumerated
	private EnumStatusGeneral status;

	//bi-directional many-to-one association to Customer
	@OneToMany(mappedBy="customerStatus")
	private List<CustomerEntity> customers;

	public CustomerStatusEntity() {
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

	public List<CustomerEntity> getCustomers() {
		return this.customers;
	}

	public void setCustomers(List<CustomerEntity> customers) {
		this.customers = customers;
	}

	public CustomerEntity addCustomer(CustomerEntity customer) {
		getCustomers().add(customer);
		customer.setCustomerStatus(this);

		return customer;
	}

	public CustomerEntity removeCustomer(CustomerEntity customer) {
		getCustomers().remove(customer);
		customer.setCustomerStatus(null);

		return customer;
	}

}