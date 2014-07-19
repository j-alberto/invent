package org.jar.invent.web.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class Inventory implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String code;
	private boolean locked;
	private BigDecimal quantity;
	private BigDecimal quantityMin;
	private Unit unit;
	private Item item;
	private List<InventoryDetail> inventoryDetails;

	public Inventory() {
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

	public boolean isLocked() {
		return this.locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public BigDecimal getQuantity() {
		return this.quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getQuantityMin() {
		return this.quantityMin;
	}

	public void setQuantityMin(BigDecimal quantityMin) {
		this.quantityMin = quantityMin;
	}

	public Unit getUnit() {
		return this.unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public Item getItem() {
		return this.item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public List<InventoryDetail> getInventoryDetails() {
		return this.inventoryDetails;
	}

	public void setInventoryDetails(List<InventoryDetail> inventoryDetails) {
		this.inventoryDetails = inventoryDetails;
	}

	public InventoryDetail addInventoryDetail(InventoryDetail inventoryDetail) {
		getInventoryDetails().add(inventoryDetail);
		inventoryDetail.setInventory(this);

		return inventoryDetail;
	}

	public InventoryDetail removeInventoryDetail(InventoryDetail inventoryDetail) {
		getInventoryDetails().remove(inventoryDetail);
		inventoryDetail.setInventory(null);

		return inventoryDetail;
	}

}