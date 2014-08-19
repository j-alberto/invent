package org.jar.invent.core.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the item database table.
 * @author zero
 * TODO enforce idUser constraint in database
 * TODO check datatype for rating
 * TODO enforce 1:1 relationship with inventory
 */
@Entity
@Table(name="item")
public class ItemEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	//bi-directional many-to-one association to Category
	@ManyToOne
	@JoinColumn(name="idcategory", nullable=false)
	private CategoryEntity category;

	@Column(nullable=false, length=20)
	private String name;

	@Column(length=50)
	private String description;

	@Column(length=45)
	private String provider;

	@Column(precision=4, scale=2)
	private BigDecimal rating;

	@Column(length=254, name="url_snapshot")
	private String urlSnapshot;

	@Column(length=254, name="url_image")
	private String urlImage;

	@Column(name="sys_stamp")
	private Timestamp sysStamp;

	@Column(name="idsys_user")
	private int idsysUser;
	
	//bi-directional many-to-one association to Inventory
	@OneToMany(mappedBy="item")
	private List<InventoryEntity> inventories;

		public ItemEntity() {
		}
		public ItemEntity(int id, CategoryEntity category, String name, String description,
		String provider, BigDecimal rating, String urlSnapshot, String urlImage,
		Timestamp sysStamp, int idsysUser){
			this.id = id;
			this.category = category;
			this.name = name;
			this.description = description;
			this.provider = provider;
			this.rating = rating;
			this.urlSnapshot = urlSnapshot;
			this.urlImage = urlImage;
			this.sysStamp = sysStamp;
			this.idsysUser = idsysUser;
		}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProvider() {
		return this.provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
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

	public BigDecimal getRating() {
		return this.rating;
	}

	public void setRating(BigDecimal rating) {
		this.rating = rating;
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

	public String getUrlSnapshot() {
		return urlSnapshot;
	}

	public void setUrlSnapshot(String urlSnapshot) {
		this.urlSnapshot = urlSnapshot;
	}

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	public Timestamp getSysStamp() {
		return sysStamp;
	}

	public void setSysStamp(Timestamp sysStamp) {
		this.sysStamp = sysStamp;
	}

	public int getIdsysUser() {
		return idsysUser;
	}

	public void setIdsysUser(int idsysUser) {
		this.idsysUser = idsysUser;
	}
	@Override
	public String toString(){
		return String.format("ItemEntity{id=%d,name=%s, provider=%s}", id,name,provider);
	}
	
}