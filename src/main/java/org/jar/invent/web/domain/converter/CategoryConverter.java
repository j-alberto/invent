package org.jar.invent.web.domain.converter;

import org.jar.invent.core.domain.CategoryEntity;
import org.jar.invent.web.domain.Category;
import org.springframework.core.convert.converter.Converter;

public class CategoryConverter implements Converter<CategoryEntity,Category>{

	@Override
	public Category convert(CategoryEntity bean) {
		if(null == bean) return null;
		
		return new Category(bean.getId(), bean.getDescription(), bean.getStatus());
	}

	

}
