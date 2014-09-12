package org.jar.invent.web.domain.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.jar.invent.core.service.CatalogsService;
import org.jar.invent.web.domain.CustomerStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

/**
 * Defines format applied to CustomerStatus beans from view(id) to controller (object) and vice-versa
 * @author jalberto
 *
 */
public class CustomerStatusFormatter implements Formatter<CustomerStatus>{

	@Autowired
	private CatalogsService catalogsService;

	public void setInventoryService(CatalogsService catalogsService) {
		this.catalogsService = catalogsService;
	}	
	
	@Override
	public String print(CustomerStatus custStatus, Locale locale) {
		return custStatus == null ? "" : String.valueOf( custStatus.getId() );
	}

	@Override
	public CustomerStatus parse(String custStatus, Locale locale)	throws ParseException {
		Integer id = Integer.valueOf(custStatus);
		return catalogsService.findCustomerStatus(id);
	}

	
}
