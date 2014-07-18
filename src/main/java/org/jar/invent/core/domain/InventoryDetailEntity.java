package org.jar.invent.core.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the inventory_detail database table.
 * 
 */
@Entity
@Table(name="inventory_detail")
@NamedQuery(name="InventoryDetail.findAll", query="SELECT i FROM InventoryDetail i")
public class InventoryDetailEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(length=50)
	private String comment;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_entry", nullable=false)
	private Date dateEntry;

	@Temporal(TemporalType.DATE)
	private Date expiration;

	@Column(name="price_entry", precision=10, scale=4)
	private BigDecimal priceEntry;

	@Column(nullable=false, precision=10, scale=6)
	private BigDecimal quantity;

	@Column(name="quantity_std", nullable=false, precision=10, scale=6)
	private BigDecimal quantityStd;

	//bi-directional many-to-one association to Storage
	@ManyToOne
	@JoinColumn(name="idstorage")
	private StorageEntity storage;

	//bi-directional many-to-one association to Unit
	@ManyToOne
	@JoinColumn(name="idunit", nullable=false)
	private UnitEntity unit;

	//bi-directional many-to-one association to Inventory
	@ManyToOne
	@JoinColumn(name="idinventory", nullable=false)
	private InventoryEntity inventory;

	//bi-directional many-to-one association to OrderDetail
	@OneToMany(mappedBy="inventoryDetail")
	private List<OrderDetailEntity> orderDetails;

	//bi-directional many-to-one association to StorageMovement
	@OneToMany(mappedBy="inventoryDetail")
	private List<StorageMovementEntity> storageMovements;

	public InventoryDetailEntity() {
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

	public StorageEntity getStorage() {
		return this.storage;
	}

	public void setStorage(StorageEntity storage) {
		this.storage = storage;
	}

	public UnitEntity getUnit() {
		return this.unit;
	}

	public void setUnit(UnitEntity unit) {
		this.unit = unit;
	}

	public InventoryEntity getInventory() {
		return this.inventory;
	}

	public void setInventory(InventoryEntity inventory) {
		this.inventory = inventory;
	}

	public List<OrderDetailEntity> getOrderDetails() {
		return this.orderDetails;
	}

	public void setOrderDetails(List<OrderDetailEntity> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public OrderDetailEntity addOrderDetail(OrderDetailEntity orderDetail) {
		getOrderDetails().add(orderDetail);
		orderDetail.setInventoryDetail(this);

		return orderDetail;
	}

	public OrderDetailEntity removeOrderDetail(OrderDetailEntity orderDetail) {
		getOrderDetails().remove(orderDetail);
		orderDetail.setInventoryDetail(null);

		return orderDetail;
	}

	public List<StorageMovementEntity> getStorageMovements() {
		return this.storageMovements;
	}

	public void setStorageMovements(List<StorageMovementEntity> storageMovements) {
		this.storageMovements = storageMovements;
	}

	public StorageMovementEntity addStorageMovement(StorageMovementEntity storageMovement) {
		getStorageMovements().add(storageMovement);
		storageMovement.setInventoryDetail(this);

		return storageMovement;
	}

	public StorageMovementEntity removeStorageMovement(StorageMovementEntity storageMovement) {
		getStorageMovements().remove(storageMovement);
		storageMovement.setInventoryDetail(null);

		return storageMovement;
	}

}