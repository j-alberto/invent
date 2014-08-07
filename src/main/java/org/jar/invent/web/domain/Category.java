package org.jar.invent.web.domain;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.jar.invent.core.domain.CategoryEntity;
import org.jar.invent.core.domain.EnumStatusGeneral;

public class Category{

	private short id;
	@NotEmpty
	@Length(min=1, max=30)
	private String description;
	private EnumStatusGeneral status;
	
		public Category(){
			this.status = EnumStatusGeneral.REGISTERED;
		}
		public Category(short id, String description, EnumStatusGeneral status) {
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
	public String toString() {
		return String.format("Category{id=%d, desc=%s, status=%s}", id, description, status.toString());
	}
}
