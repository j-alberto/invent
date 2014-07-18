package org.jar.invent.web.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the inventory database table.
 * 
 */
@Entity
@Table(name="inventory")
@NamedQuery(name="Inventory.findAll", query="SELECT i FROM Inventory i")
public class Inventory implements Serializable {
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
	private Unit unit;

	//bi-directional many-to-one association to Item
	@ManyToOne
	@JoinColumn(name="iditem", nullable=false)
	private Item item;

	//bi-directional many-to-one association to InventoryDetail
	@OneToMany(mappedBy="inventory")
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