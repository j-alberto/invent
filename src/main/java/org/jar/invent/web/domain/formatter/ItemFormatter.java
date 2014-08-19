package org.jar.invent.web.domain.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.jar.invent.web.domain.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.jar.invent.core.service.InventoryService;

/**
 * Defines format applied to item beans from view(id) to controller (object) and vice-versa
 * @author jalberto
 *
 */
public class ItemFormatter implements Formatter<Item>{

	@Autowired
	private InventoryService inventoryService;
	

	public void setInventoryService(InventoryService inventoryService) {
		this.inventoryService = inventoryService;
	}	
	
	@Override
	public String print(Item item, Locale locale) {
		return item == null ? "" : String.valueOf( item.getId() );
	}

	@Override
	public Item parse(String item, Locale locale)	throws ParseException {
		Integer id = Integer.valueOf(item);
		return inventoryService.getItem(id);
	}

	
}
