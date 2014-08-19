package org.jar.invent.core.service;

import org.jar.invent.web.domain.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InventoryService {

	public Item registerNewItem(Item item);
	public boolean deleteItem(Item item);
	public boolean modifyItem(Item item);
	Page<Item> getItems(String code, Pageable pageRequest);
	public Item getItem(int id);
	
//	public boolean addItemsToStock(int inventoryId,List<Item> newItems);
//	public boolean removeItemsFromInventory(int inventoryId,List<Item> itemsToRemove);
//	public boolean moveItems(int idInventTarget, List<InventoryDetail> inventoryDetails);
//	
//	public boolean addItemsToInventory(Order order);
//	public boolean removeItemsFromInventory(Order order);

}
