package org.jar.invent.web.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;
/**
 * Represents an item, not necessarily in inventory.
 * @author zero
 */
public class Item implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	@NotBlank
	@Length(min=1, max=20)
	private String name;
	@Length(min=0, max=50)
	private String description;
	@Length(min=0, max=45)
	private String provider;
	@Range(min=0, max=100)
	private BigDecimal rating;
	private String urlSnapshot;
	private String urlImage;
	private Timestamp sysStamp;
	private int idsysUser;
	private MultipartFile image;

	private List<Inventory> inventories;
	@NotNull
	private Category category;

		public Item() {
		}
		public Item(int id, Category category, String name, String description,
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

	public List<Inventory> getInventories() {
		return this.inventories;
	}

	public void setInventories(List<Inventory> inventories) {
		this.inventories = inventories;
	}

	public Inventory addInventory(Inventory inventory) {
		getInventories().add(inventory);
		inventory.setItem(this);

		return inventory;
	}

	public Inventory removeInventory(Inventory inventory) {
		getInventories().remove(inventory);
		inventory.setItem(null);

		return inventory;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
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
		return String.format("Item{id=%d,name=%s, provider=%s}", id,name,provider);
	}
}