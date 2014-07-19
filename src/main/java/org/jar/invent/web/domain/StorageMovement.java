package org.jar.invent.web.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.jar.invent.core.domain.StorageMovementEntity.EnumStorageMovType;


/**
 * The persistent class for the storage_movement database table.
 * 
 */
public class StorageMovement implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private Date dateMove;
	private int idsysUser;
	private Timestamp sysStamp;
	private EnumStorageMovType type;
	private List<OrderDetail> orderDetails;
	private Storage storage1;
	private Storage storage2;
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