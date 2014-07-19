package org.jar.invent.web.domain.util;

import org.jar.invent.core.domain.CategoryEntity;
import org.jar.invent.core.domain.UnitEntity;
import org.jar.invent.web.domain.Category;
import org.jar.invent.web.domain.Unit;

public class BeanParser{


	public static CategoryEntity toEntityBean(Category bean) {
		return new CategoryEntity(bean.getId(), bean.getDescription(), bean.getStatus());
	}
	public static Category toWebBean(CategoryEntity bean) {
		return new Category(bean.getId(), bean.getDescription(),bean.getStatus());
	}
	
	public static UnitEntity toEntityBean(Unit bean) {
		//todo resolve transitive dependencies
		return new UnitEntity(bean.getId()
				,bean.getDataType()
				,bean.getDescription()
				,bean.getName());
	}
	public static Unit toWebBean(UnitEntity bean) {
		//todo resolve transitive dependencies
		return new Unit(bean.getId()
				,bean.getDataType()
				,bean.getDescription()
				,bean.getName());
	}
	
}
