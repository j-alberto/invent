package org.jar.invent.core.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the storage database table.
 * 
 */
@Entity
@Table(name="storage")
public class StorageEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(updatable=false, nullable=false, length=8)
	private String code;

	@Column(nullable=false, length=30)
	private String description;

	@Column(nullable=false)
	@Enumerated
	private EnumStatusGeneral status;

	//bi-directional many-to-one association to InventoryDetail
	@OneToMany(mappedBy="storage")
	private List<InventoryDetailEntity> inventoryDetails;

	//bi-directional many-to-one association to StorageMovement
	@OneToMany(mappedBy="storage1")
	private List<StorageMovementEntity> storageMovements1;

	//bi-directional many-to-one association to StorageMovement
	@OneToMany(mappedBy="storage2")
	private List<StorageMovementEntity> storageMovements2;

	public StorageEntity() {
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

	public EnumStatusGeneral getStatus() {
		return this.status;
	}

	public void setStatus(EnumStatusGeneral status) {
		this.status = status;
	}

	public List<InventoryDetailEntity> getInventoryDetails() {
		return this.inventoryDetails;
	}

	public void setInventoryDetails(List<InventoryDetailEntity> inventoryDetails) {
		this.inventoryDetails = inventoryDetails;
	}

	public InventoryDetailEntity addInventoryDetail(InventoryDetailEntity inventoryDetail) {
		getInventoryDetails().add(inventoryDetail);
		inventoryDetail.setStorage(this);

		return inventoryDetail;
	}

	public InventoryDetailEntity removeInventoryDetail(InventoryDetailEntity inventoryDetail) {
		getInventoryDetails().remove(inventoryDetail);
		inventoryDetail.setStorage(null);

		return inventoryDetail;
	}

	public List<StorageMovementEntity> getStorageMovements1() {
		return this.storageMovements1;
	}

	public void setStorageMovements1(List<StorageMovementEntity> storageMovements1) {
		this.storageMovements1 = storageMovements1;
	}

	public StorageMovementEntity addStorageMovements1(StorageMovementEntity storageMovements1) {
		getStorageMovements1().add(storageMovements1);
		storageMovements1.setStorage1(this);

		return storageMovements1;
	}

	public StorageMovementEntity removeStorageMovements1(StorageMovementEntity storageMovements1) {
		getStorageMovements1().remove(storageMovements1);
		storageMovements1.setStorage1(null);

		return storageMovements1;
	}

	public List<StorageMovementEntity> getStorageMovements2() {
		return this.storageMovements2;
	}

	public void setStorageMovements2(List<StorageMovementEntity> storageMovements2) {
		this.storageMovements2 = storageMovements2;
	}

	public StorageMovementEntity addStorageMovements2(StorageMovementEntity storageMovements2) {
		getStorageMovements2().add(storageMovements2);
		storageMovements2.setStorage2(this);

		return storageMovements2;
	}

	public StorageMovementEntity removeStorageMovements2(StorageMovementEntity storageMovements2) {
		getStorageMovements2().remove(storageMovements2);
		storageMovements2.setStorage2(null);

		return storageMovements2;
	}

}