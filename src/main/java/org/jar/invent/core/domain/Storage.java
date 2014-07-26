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
@NamedQuery(name="Storage.findAll", query="SELECT s FROM Storage s")
public class Storage implements Serializable {
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
	private List<InventoryDetail> inventoryDetails;

	//bi-directional many-to-one association to StorageMovement
	@OneToMany(mappedBy="storage1")
	private List<StorageMovement> storageMovements1;

	//bi-directional many-to-one association to StorageMovement
	@OneToMany(mappedBy="storage2")
	private List<StorageMovement> storageMovements2;

	public Storage() {
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

	public List<InventoryDetail> getInventoryDetails() {
		return this.inventoryDetails;
	}

	public void setInventoryDetails(List<InventoryDetail> inventoryDetails) {
		this.inventoryDetails = inventoryDetails;
	}

	public InventoryDetail addInventoryDetail(InventoryDetail inventoryDetail) {
		getInventoryDetails().add(inventoryDetail);
		inventoryDetail.setStorage(this);

		return inventoryDetail;
	}

	public InventoryDetail removeInventoryDetail(InventoryDetail inventoryDetail) {
		getInventoryDetails().remove(inventoryDetail);
		inventoryDetail.setStorage(null);

		return inventoryDetail;
	}

	public List<StorageMovement> getStorageMovements1() {
		return this.storageMovements1;
	}

	public void setStorageMovements1(List<StorageMovement> storageMovements1) {
		this.storageMovements1 = storageMovements1;
	}

	public StorageMovement addStorageMovements1(StorageMovement storageMovements1) {
		getStorageMovements1().add(storageMovements1);
		storageMovements1.setStorage1(this);

		return storageMovements1;
	}

	public StorageMovement removeStorageMovements1(StorageMovement storageMovements1) {
		getStorageMovements1().remove(storageMovements1);
		storageMovements1.setStorage1(null);

		return storageMovements1;
	}

	public List<StorageMovement> getStorageMovements2() {
		return this.storageMovements2;
	}

	public void setStorageMovements2(List<StorageMovement> storageMovements2) {
		this.storageMovements2 = storageMovements2;
	}

	public StorageMovement addStorageMovements2(StorageMovement storageMovements2) {
		getStorageMovements2().add(storageMovements2);
		storageMovements2.setStorage2(this);

		return storageMovements2;
	}

	public StorageMovement removeStorageMovements2(StorageMovement storageMovements2) {
		getStorageMovements2().remove(storageMovements2);
		storageMovements2.setStorage2(null);

		return storageMovements2;
	}

}