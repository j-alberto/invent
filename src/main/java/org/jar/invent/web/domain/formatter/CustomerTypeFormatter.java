package org.jar.invent.web.domain.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.jar.invent.core.service.CatalogsService;
import org.jar.invent.web.domain.CustomerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

/**
 * Defines format applied to CustomerType beans from view(id) to controller (object) and vice-versa
 * @author jalberto
 *
 */
public class CustomerTypeFormatter implements Formatter<CustomerType>{

	@Autowired
	private CatalogsService catalogsService;
	

	public void setInventoryService(CatalogsService catalogsService) {
		this.catalogsService = catalogsService;
	}	
	
	@Override
	public String print(CustomerType custType, Locale locale) {
		return custType == null ? "" : String.valueOf( custType.getId() );
	}

	@Override
	public CustomerType parse(String custType, Locale locale)	throws ParseException {
		Integer id = Integer.valueOf(custType);
		return catalogsService.findCustomerType(id);
	}

	
}
