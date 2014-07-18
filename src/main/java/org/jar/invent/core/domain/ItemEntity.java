package org.jar.invent.core.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the item database table.
 * 
 */
@Entity
@Table(name="item")
public class ItemEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false, length=8)
	private String code;

	@Column(length=50)
	private String description;

	@Column(nullable=false, length=20)
	private String name;

	@Column(precision=10, scale=2)
	private BigDecimal price;

	//bi-directional many-to-one association to Inventory
	@OneToMany(mappedBy="item")
	private List<InventoryEntity> inventories;

	//bi-directional many-to-one association to Category
	@ManyToOne
	@JoinColumn(name="idcategory", nullable=false)
	private CategoryEntity category;

	public ItemEntity() {
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

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public List<InventoryEntity> getInventories() {
		return this.inventories;
	}

	public void setInventories(List<InventoryEntity> inventories) {
		this.inventories = inventories;
	}

	public InventoryEntity addInventory(InventoryEntity inventory) {
		getInventories().add(inventory);
		inventory.setItem(this);

		return inventory;
	}

	public InventoryEntity removeInventory(InventoryEntity inventory) {
		getInventories().remove(inventory);
		inventory.setItem(null);

		return inventory;
	}

	public CategoryEntity getCategory() {
		return this.category;
	}

	public void setCategory(CategoryEntity category) {
		this.category = category;
	}

}