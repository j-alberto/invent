package org.jar.invent.web.domain.formatter;

import java.text.ParseException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jar.invent.core.service.CatalogsService;
import org.jar.invent.web.domain.OrderWorkflow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

/**
 * Defines format applied for workflows from view(id) to controller (object)
 * @author jalberto
 *
 */
public class WorkFlowFormatter implements Formatter<OrderWorkflow>{

	@Autowired
	CatalogsService catalogsService;
	
	@Override
	public String print(OrderWorkflow workflow, Locale locale) {
		return workflow == null ? "" : String.valueOf( workflow.getId() );
	}

	@Override
	public OrderWorkflow parse(String workflow, Locale locale)	throws ParseException {
		Integer id = Integer.valueOf(workflow);
		return catalogsService.findOrderWorkflow(id);
	}
	
	
}
