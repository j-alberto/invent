package org.jar.invent.web.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String comments;
	private Date date;
	private int idsysUser;
	private int numItems;
	private BigDecimal priceTotal;
	private Timestamp sysStamp;
	private OrderStatus orderStatus;
	private OrderType orderType;
	private Customer customer;
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