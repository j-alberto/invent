package org.jar.invent.web.domain.converter;

import org.jar.invent.core.domain.ItemEntity;
import org.jar.invent.web.domain.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class ItemConverter implements Converter<ItemEntity, Item> {

	@Autowired
	private CategoryConverter categoryConverter;
	
	@Override
	public Item convert(ItemEntity bean) {
		if(null == bean) return null;
		
		Item item = new Item(bean.getId(),null, bean.getName(),bean.getDescription(),
				bean.getProvider(),bean.getRating(),bean.getUrlSnapshot(),bean.getUrlImage(),bean.getSysStamp(),bean.getIdsysUser());
		
		if(null != bean.getCategory()){
			item.setCategory(categoryConverter.convert(bean.getCategory()));
		}
		
		return  item;
	}


	public void setCategoryConverter(CategoryConverter categoryConverter) {
		this.categoryConverter = categoryConverter;
	}
}
