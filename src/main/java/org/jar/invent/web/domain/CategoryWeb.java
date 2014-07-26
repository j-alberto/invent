package org.jar.invent.web.domain;

import org.hibernate.validator.constraints.NotEmpty;
import org.jar.invent.core.domain.Category;
import org.jar.invent.core.domain.EnumStatusGeneral;

public class CategoryWeb implements WebBean<Category>{

	private short id;
	@NotEmpty
	private String description;
	private EnumStatusGeneral status;
	
		public CategoryWeb(){}
		public CategoryWeb(short id, String description, EnumStatusGeneral status) {
			this.id = id;
			this.description = description;
			this.status = status;
		}

	
	public short getId() {
		return id;
	}
	public void setId(short id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public EnumStatusGeneral getStatus() {
		return status;
	}
	public void setStatus(EnumStatusGeneral status) {
		this.status = status;
	}
	@Override
	public Category toCoreBean() {
		return new Category(this.id, this.description, this.status);
	}
	
	@Override
	public String toString() {
		return String.format("Category{id=%d, desc=%s, status=%s}", id, description, status.toString());
	}
}
