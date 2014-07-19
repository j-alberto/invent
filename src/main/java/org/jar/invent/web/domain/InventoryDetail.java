package org.jar.invent.web.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class InventoryDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String comment;
	private Date dateEntry;
	private Date expiration;
	private BigDecimal priceEntry;
	private BigDecimal quantity;
	private BigDecimal quantityStd;
	private Storage storage;
	private Unit unit;
	private Inventory inventory;
	private List<OrderDetail> orderDetails;
	private List<StorageMovement> storageMovements;

	public InventoryDetail() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getDateEntry() {
		return this.dateEntry;
	}

	public void setDateEntry(Date dateEntry) {
		this.dateEntry = dateEntry;
	}

	public Date getExpiration() {
		return this.expiration;
	}

	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}

	public BigDecimal getPriceEntry() {
		return this.priceEntry;
	}

	public void setPriceEntry(BigDecimal priceEntry) {
		this.priceEntry = priceEntry;
	}

	public BigDecimal getQuantity() {
		return this.quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getQuantityStd() {
		return this.quantityStd;
	}

	public void setQuantityStd(BigDecimal quantityStd) {
		this.quantityStd = quantityStd;
	}

	public Storage getStorage() {
		return this.storage;
	}

	public void setStorage(Storage storage) {
		this.storage = storage;
	}

	public Unit getUnit() {
		return this.unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public Inventory getInventory() {
		return this.inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public List<OrderDetail> getOrderDetails() {
		return this.orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public OrderDetail addOrderDetail(OrderDetail orderDetail) {
		getOrderDetails().add(orderDetail);
		orderDetail.setInventoryDetail(this);

		return orderDetail;
	}

	public OrderDetail removeOrderDetail(OrderDetail orderDetail) {
		getOrderDetails().remove(orderDetail);
		orderDetail.setInventoryDetail(null);

		return orderDetail;
	}

	public List<StorageMovement> getStorageMovements() {
		return this.storageMovements;
	}

	public void setStorageMovements(List<StorageMovement> storageMovements) {
		this.storageMovements = storageMovements;
	}

	public StorageMovement addStorageMovement(StorageMovement storageMovement) {
		getStorageMovements().add(storageMovement);
		storageMovement.setInventoryDetail(this);

		return storageMovement;
	}

	public StorageMovement removeStorageMovement(StorageMovement storageMovement) {
		getStorageMovements().remove(storageMovement);
		storageMovement.setInventoryDetail(null);

		return storageMovement;
	}

}