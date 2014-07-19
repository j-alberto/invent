package org.jar.invent.core.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the order_detail database table.
 * 
 */
@Entity
@Table(name="order_detail")
public class OrderDetailEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false)
	private boolean cancelled;

	@Column(name="price_item", nullable=false, precision=10, scale=4)
	private BigDecimal priceItem;

	@Column(name="price_subtotal", precision=10, scale=4)
	private BigDecimal priceSubtotal;

	@Column(nullable=false, precision=10, scale=6)
	private BigDecimal quantity;

	//bi-directional many-to-one association to InventoryDetail
	@ManyToOne
	@JoinColumn(name="iditem", nullable=false)
	private InventoryDetailEntity inventoryDetail;

	//bi-directional many-to-one association to Order
	@ManyToOne
	@JoinColumn(name="idorder", nullable=false)
	private OrderEntity order;

	//bi-directional many-to-one association to StorageMovement
	@ManyToOne
	@JoinColumn(name="idstorage_mov")
	private StorageMovementEntity storageMovement;

	public OrderDetailEntity() {
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

	public InventoryDetailEntity getInventoryDetail() {
		return this.inventoryDetail;
	}

	public void setInventoryDetail(InventoryDetailEntity inventoryDetail) {
		this.inventoryDetail = inventoryDetail;
	}

	public OrderEntity getOrder() {
		return this.order;
	}

	public void setOrder(OrderEntity order) {
		this.order = order;
	}

	public StorageMovementEntity getStorageMovement() {
		return this.storageMovement;
	}

	public void setStorageMovement(StorageMovementEntity storageMovement) {
		this.storageMovement = storageMovement;
	}

}