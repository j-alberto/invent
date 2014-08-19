package org.jar.invent.core.domain.converter;

import org.jar.invent.core.domain.ItemEntity;
import org.jar.invent.web.domain.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class ItemEntityConverter implements Converter<Item,ItemEntity> {

	@Autowired
	private CategoryEntityConverter categoryEntityConverter;
	
	@Override
	public ItemEntity convert(Item bean) {
		if(null == bean) return null;
		
		ItemEntity itemEntity = new ItemEntity(bean.getId(),null, bean.getName(),bean.getDescription(),
				bean.getProvider(),bean.getRating(),bean.getUrlSnapshot(),bean.getUrlImage(),bean.getSysStamp(),bean.getIdsysUser());
		
		if(null != bean.getCategory()){
			itemEntity.setCategory(categoryEntityConverter.convert(bean.getCategory()));
		}
		
		return  itemEntity;
	}


	public void setCategoryEntityConverter(
			CategoryEntityConverter categoryEntityConverter) {
		this.categoryEntityConverter = categoryEntityConverter;
	}
}
