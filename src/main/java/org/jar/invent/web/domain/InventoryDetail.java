package org.jar.invent.web.domain;

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
public class InventoryDetail implements Serializable {
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
	private Storage storage;

	//bi-directional many-to-one association to Unit
	@ManyToOne
	@JoinColumn(name="idunit", nullable=false)
	private Unit unit;

	//bi-directional many-to-one association to Inventory
	@ManyToOne
	@JoinColumn(name="idinventory", nullable=false)
	private Inventory inventory;

	//bi-directional many-to-one association to OrderDetail
	@OneToMany(mappedBy="inventoryDetail")
	private List<OrderDetail> orderDetails;

	//bi-directional many-to-one association to StorageMovement
	@OneToMany(mappedBy="inventoryDetail")
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