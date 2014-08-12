package org.jar.invent.core.domain.converter;

import org.jar.invent.core.domain.CategoryEntity;
import org.jar.invent.web.domain.Category;
import org.springframework.core.convert.converter.Converter;

public class CategoryEntityConverter implements Converter<Category,CategoryEntity>{

	@Override
	public CategoryEntity convert(Category bean) {
		if(null == bean) return null;
		
		return new CategoryEntity(bean.getId(), bean.getDescription(), bean.getStatus());
	}

	

}
