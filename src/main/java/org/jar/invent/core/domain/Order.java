package org.jar.invent.core.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the order database table.
 * 
 */
@Entity
@Table(name="order")
@NamedQuery(name="Order.findAll", query="SELECT o FROM Order o")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(length=50)
	private String comments;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date date;

	@Column(name="idsys_user")
	private int idsysUser;

	@Column(name="num_items", nullable=false)
	private int numItems;

	@Column(name="price_total", nullable=false, precision=10, scale=4)
	private BigDecimal priceTotal;

	@Column(name="sys_stamp")
	private Timestamp sysStamp;

	//bi-directional many-to-one association to OrderStatus
	@ManyToOne
	@JoinColumn(name="idstatus", nullable=false)
	private OrderStatus orderStatus;

	//bi-directional many-to-one association to OrderType
	@ManyToOne
	@JoinColumn(name="idtype", nullable=false)
	private OrderType orderType;

	//bi-directional many-to-one association to Customer
	@ManyToOne
	@JoinColumn(name="idcustomer")
	private Customer customer;

	//bi-directional many-to-one association to OrderDetail
	@OneToMany(mappedBy="order")
	private List<OrderDetail> orderDetails;

	public Order() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getIdsysUser() {
		return this.idsysUser;
	}

	public void setIdsysUser(int idsysUser) {
		this.idsysUser = idsysUser;
	}

	public int getNumItems() {
		return this.numItems;
	}

	public void setNumItems(int numItems) {
		this.numItems = numItems;
	}

	public BigDecimal getPriceTotal() {
		return this.priceTotal;
	}

	public void setPriceTotal(BigDecimal priceTotal) {
		this.priceTotal = priceTotal;
	}

	public Timestamp getSysStamp() {
		return this.sysStamp;
	}

	public void setSysStamp(Timestamp sysStamp) {
		this.sysStamp = sysStamp;
	}

	public OrderStatus getOrderStatus() {
		return this.orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public OrderType getOrderType() {
		return this.orderType;
	}

	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<OrderDetail> getOrderDetails() {
		return this.orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public OrderDetail addOrderDetail(OrderDetail orderDetail) {
		getOrderDetails().add(orderDetail);
		orderDetail.setOrder(this);

		return orderDetail;
	}

	public OrderDetail removeOrderDetail(OrderDetail orderDetail) {
		getOrderDetails().remove(orderDetail);
		orderDetail.setOrder(null);

		return orderDetail;
	}

}