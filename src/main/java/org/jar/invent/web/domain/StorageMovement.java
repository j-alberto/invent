package org.jar.invent.web.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the storage_movement database table.
 * 
 */
@Entity
@Table(name="storage_movement")
@NamedQuery(name="StorageMovement.findAll", query="SELECT s FROM StorageMovement s")
public class StorageMovement implements Serializable {
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
	private List<OrderDetail> orderDetails;

	//bi-directional many-to-one association to Storage
	@ManyToOne
	@JoinColumn(name="idstorage_src")
	private Storage storage1;

	//bi-directional many-to-one association to Storage
	@ManyToOne
	@JoinColumn(name="idstorage_dst")
	private Storage storage2;

	//bi-directional many-to-one association to InventoryDetail
	@ManyToOne
	@JoinColumn(name="idinventory_detail", nullable=false)
	private InventoryDetail inventoryDetail;

	public StorageMovement() {
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

	public List<OrderDetail> getOrderDetails() {
		return this.orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public OrderDetail addOrderDetail(OrderDetail orderDetail) {
		getOrderDetails().add(orderDetail);
		orderDetail.setStorageMovement(this);

		return orderDetail;
	}

	public OrderDetail removeOrderDetail(OrderDetail orderDetail) {
		getOrderDetails().remove(orderDetail);
		orderDetail.setStorageMovement(null);

		return orderDetail;
	}

	public Storage getStorage1() {
		return this.storage1;
	}

	public void setStorage1(Storage storage1) {
		this.storage1 = storage1;
	}

	public Storage getStorage2() {
		return this.storage2;
	}

	public void setStorage2(Storage storage2) {
		this.storage2 = storage2;
	}

	public InventoryDetail getInventoryDetail() {
		return this.inventoryDetail;
	}

	public void setInventoryDetail(InventoryDetail inventoryDetail) {
		this.inventoryDetail = inventoryDetail;
	}

}