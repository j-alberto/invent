package org.jar.invent.web.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class OrderDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;

	private boolean cancelled;
	private BigDecimal priceItem;
	private BigDecimal priceSubtotal;
	private BigDecimal quantity;
	private InventoryDetail inventoryDetail;
	private Order order;
	private StorageMovement storageMovement;

	public OrderDetail() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isCancelled() {
		return this.cancelled;
	}

	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}

	public BigDecimal getPriceItem() {
		return this.priceItem;
	}

	public void setPriceItem(BigDecimal priceItem) {
		this.priceItem = priceItem;
	}

	public BigDecimal getPriceSubtotal() {
		return this.priceSubtotal;
	}

	public void setPriceSubtotal(BigDecimal priceSubtotal) {
		this.priceSubtotal = priceSubtotal;
	}

	public BigDecimal getQuantity() {
		return this.quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public InventoryDetail getInventoryDetail() {
		return this.inventoryDetail;
	}

	public void setInventoryDetail(InventoryDetail inventoryDetail) {
		this.inventoryDetail = inventoryDetail;
	}

	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public StorageMovement getStorageMovement() {
		return this.storageMovement;
	}

	public void setStorageMovement(StorageMovement storageMovement) {
		this.storageMovement = storageMovement;
	}

}