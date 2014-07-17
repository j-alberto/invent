package org.jar.invent.core.domain;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the category database table.
 * 
 */
@Entity
@Table(name="category")
public class CategoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private short id;

	@Column(nullable=false, length=30)
	private String description;

	@Column(nullable=false)
	@Enumerated(EnumType.ORDINAL)
	private EnumStatusGeneral status;

	//bi-directional many-to-one association to Item
	@OneToMany(mappedBy="category")
	private List<Item> items;

		public CategoryEntity() {
		}
		public CategoryEntity(short id, String description, EnumStatusGeneral status) {
			this.id = id;
			this.description = description;
			this.status = status;
		}

	public short getId() {
		return this.id;
	}

	public void setId(short id) {
		this.id = id;
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

	public List<Item> getItems() {
		return this.items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Item addItem(Item item) {
		getItems().add(item);
		item.setCategory(this);

		return item;
	}

	public Item removeItem(Item item) {
		getItems().remove(item);
		item.setCategory(null);

		return item;
	}
	
	@Override
	public String toString() {
		return String.format("CategoryEntity{id=%d, desc=%s, status=%s}", id, description, status.toString());
	}
}