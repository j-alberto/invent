package org.jar.invent.core.service;

import java.util.List;

import org.jar.invent.business.ItemRegisterBR;
import org.jar.invent.core.domain.ItemEntity;
import org.jar.invent.core.domain.converter.ListConverter;
import org.jar.invent.web.domain.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class InventoryServiceImp implements InventoryService {
	@Autowired
	private ItemRegisterBR itemRegister;
	@Autowired
	private	ConversionService conversionService;
	@Autowired
	private	ListConverter listConverter;
	
	
	public void setItemRegister(ItemRegisterBR itemRegister) {
		this.itemRegister = itemRegister;
	}
	public void setTransformService( ConversionService conversionService) {
		this.conversionService = conversionService;
	}
	public void setListConverter(ListConverter listConverter) {
		this.listConverter = listConverter;
	}
	
	@Override
	public Item registerNewItem(Item item) {
		ItemEntity itemEntity = conversionService.convert(item, ItemEntity.class);
		
		itemEntity = itemRegister.registerNewItem(itemEntity);
		
		return conversionService.convert(itemEntity, Item.class);
	}

	@Override
	public boolean deleteItem(Item item) {
		ItemEntity itemEntity = conversionService.convert(item, ItemEntity.class);
		itemRegister.deleteItem(itemEntity);
		
		return true;
	}

	@Override
	public boolean modifyItem(Item item) {
		ItemEntity itemEntity = conversionService.convert(item, ItemEntity.class);
		itemRegister.modifyItem(itemEntity);

		return true;
	}

	@Override
	public Page<Item> getItems(String desc, Pageable pageRequest) {

		Page<ItemEntity> itemsPage = itemRegister.getItems(desc, pageRequest);
		List<Item> itemsWeb = listConverter.convert(itemsPage.getContent(), Item.class);
		
		return new PageImpl<Item>(itemsWeb, pageRequest, itemsPage.getTotalElements());
	}
	@Override
	public Item getItem(int id) {
		ItemEntity item = itemRegister.getItem(id);
		return conversionService.convert(item, Item.class);
	}


}
