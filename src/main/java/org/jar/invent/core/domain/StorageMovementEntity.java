package org.jar.invent.core.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the storage_movement database table.
 * 
 */
@Entity
@Table(name="storage_movement")
public class StorageMovementEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public static enum EnumStorageMovType {
		INPUT,OUTPUT,RELOCATION;
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_move", nullable=false)
	private Date dateMove;

	@Column(name="idsys_user")
	private int idsysUser;

	@Column(name="sys_stamp")
	private Timestamp sysStamp;

	@Column(nullable=false)
	@Enumerated
	private EnumStorageMovType type;

	//bi-directional many-to-one association to OrderDetail
	@OneToMany(mappedBy="storageMovement")
	private List<OrderDetailEntity> orderDetails;

	//bi-directional many-to-one association to Storage
	@ManyToOne
	@JoinColumn(name="idstorage_src")
	private StorageEntity storage1;

	//bi-directional many-to-one association to Storage
	@ManyToOne
	@JoinColumn(name="idstorage_dst")
	private StorageEntity storage2;

	//bi-directional many-to-one association to InventoryDetail
	@ManyToOne
	@JoinColumn(name="idinventory_detail", nullable=false)
	private InventoryDetailEntity inventoryDetail;

	public StorageMovementEntity() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateMove() {
		return this.dateMove;
	}

	public void setDateMove(Date dateMove) {
		this.dateMove = dateMove;
	}

	public int getIdsysUser() {
		return this.idsysUser;
	}

	public void setIdsysUser(int idsysUser) {
		this.idsysUser = idsysUser;
	}

	public Timestamp getSysStamp() {
		return this.sysStamp;
	}

	public void setSysStamp(Timestamp sysStamp) {
		this.sysStamp = sysStamp;
	}

	public EnumStorageMovType getType() {
		return this.type;
	}

	public void setType(EnumStorageMovType type) {
		this.type = type;
	}

	public List<OrderDetailEntity> getOrderDetails() {
		return this.orderDetails;
	}

	public void setOrderDetails(List<OrderDetailEntity> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public OrderDetailEntity addOrderDetail(OrderDetailEntity orderDetail) {
		getOrderDetails().add(orderDetail);
		orderDetail.setStorageMovement(this);

		return orderDetail;
	}

	public OrderDetailEntity removeOrderDetail(OrderDetailEntity orderDetail) {
		getOrderDetails().remove(orderDetail);
		orderDetail.setStorageMovement(null);

		return orderDetail;
	}

	public StorageEntity getStorage1() {
		return this.storage1;
	}

	public void setStorage1(StorageEntity storage1) {
		this.storage1 = storage1;
	}

	public StorageEntity getStorage2() {
		return this.storage2;
	}

	public void setStorage2(StorageEntity storage2) {
		this.storage2 = storage2;
	}

	public InventoryDetailEntity getInventoryDetail() {
		return this.inventoryDetail;
	}

	public void setInventoryDetail(InventoryDetailEntity inventoryDetail) {
		this.inventoryDetail = inventoryDetail;
	}

}