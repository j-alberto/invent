package org.jar.invent.core.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the unit database table.
 * 
 */
@Entity
@Table(name="unit")
@NamedQuery(name="Unit.findAll", query="SELECT u FROM Unit u")
public class Unit implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static enum DataType {
		DATE,TEXT,INTEGER,FLOAT,OTHER;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(name="data_type", nullable=false)
	@Enumerated
	private DataType dataType;

	@Column(length=50)
	private String description;

	@Column(nullable=false, length=30)
	private String name;

	//bi-directional many-to-one association to Inventory
	@OneToMany(mappedBy="unit")
	private List<Inventory> inventories;

	//bi-directional many-to-one association to InventoryDetail
	@OneToMany(mappedBy="unit")
	private List<InventoryDetail> inventoryDetails;

	public Unit() {
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

}