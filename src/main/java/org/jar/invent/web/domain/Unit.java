package org.jar.invent.web.domain;

import java.io.Serializable;
import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.jar.invent.core.domain.UnitEntity.DataType;


/**
 * The persistent class for the unit database table.
 * 
 */
public class Unit implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	
	private DataType dataType;
	
	@Length(min=0, max=50)
	private String description;

	@NotBlank
	@Length(min=1, max=30)
	private String name;
	private List<Inventory> inventories;
	private List<InventoryDetail> inventoryDetails;

	public Unit() {
		dataType = DataType.OTHER;
	}
	public Unit(int id, DataType dataType,String description, String name) {
		this.id = id;
		this.dataType = dataType;
		this.description = description;
		this.name = name;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public DataType getDataType() {
		return this.dataType;
	}

	public void setDataType(DataType dataType) {
		this.dataType = dataType;
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

	public List<Inventory> getInventories() {
		return this.inventories;
	}

	public void setInventories(List<Inventory> inventories) {
		this.inventories = inventories;
	}

	public Inventory addInventory(Inventory inventory) {
		getInventories().add(inventory);
		inventory.setUnit(this);

		return inventory;
	}

	public Inventory removeInventory(Inventory inventory) {
		getInventories().remove(inventory);
		inventory.setUnit(null);

		return inventory;
	}

	public List<InventoryDetail> getInventoryDetails() {
		return this.inventoryDetails;
	}

	public void setInventoryDetails(List<InventoryDetail> inventoryDetails) {
		this.inventoryDetails = inventoryDetails;
	}

	public InventoryDetail addInventoryDetail(InventoryDetail inventoryDetail) {
		getInventoryDetails().add(inventoryDetail);
		inventoryDetail.setUnit(this);

		return inventoryDetail;
	}

	public InventoryDetail removeInventoryDetail(InventoryDetail inventoryDetail) {
		getInventoryDetails().remove(inventoryDetail);
		inventoryDetail.setUnit(null);

		return inventoryDetail;
	}
	
	@Override
	public String toString() {
		return String.format("Unit{id=%d,name=%s, desc=%s, dataType=%s}", id, name, description, dataType.toString());
	}

}