package org.jar.invent.core.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the Stock database table.
 * This class actually represents an element registered in an inventory, whether they are in stock or not, 
 * TODO : Rename this entity to Stock and create InventoryEntity to replace inventoryCode field
 */
@Entity
@Table(name="inventory")
public class InventoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false, length=8)
	private String code;

	@Column(nullable=false)
	private boolean locked;

	@Column(nullable=false, precision=10, scale=6)
	private BigDecimal quantity;

	@Column(name="quantity_min", nullable=false, precision=10, scale=6)
	private BigDecimal quantityMin;

	//bi-directional many-to-one association to Unit
	@ManyToOne
	@JoinColumn(name="idunit", nullable=false)
	private UnitEntity unit;

	//bi-directional many-to-one association to Item
	@ManyToOne
	@JoinColumn(name="iditem", nullable=false)
	private ItemEntity item;

	//bi-directional many-to-one association to InventoryDetail
	@OneToMany(mappedBy="inventory")
	private List<InventoryDetailEntity> inventoryDetails;

	public InventoryEntity() {
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

	public UnitEntity getUnit() {
		return this.unit;
	}

	public void setUnit(UnitEntity unit) {
		this.unit = unit;
	}

	public ItemEntity getItem() {
		return this.item;
	}

	public void setItem(ItemEntity item) {
		this.item = item;
	}

	public List<InventoryDetailEntity> getInventoryDetails() {
		return this.inventoryDetails;
	}

	public void setInventoryDetails(List<InventoryDetailEntity> inventoryDetails) {
		this.inventoryDetails = inventoryDetails;
	}

	public InventoryDetailEntity addInventoryDetail(InventoryDetailEntity inventoryDetail) {
		getInventoryDetails().add(inventoryDetail);
		inventoryDetail.setInventory(this);

		return inventoryDetail;
	}

	public InventoryDetailEntity removeInventoryDetail(InventoryDetailEntity inventoryDetail) {
		getInventoryDetails().remove(inventoryDetail);
		inventoryDetail.setInventory(null);

		return inventoryDetail;
	}

}