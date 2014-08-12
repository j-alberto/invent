package org.jar.invent.web.domain.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.jar.invent.core.service.CatalogsService;
import org.jar.invent.web.domain.OrderWorkflow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

/**
 * Defines format applied to workflow beans from view(id) to controller (object) and vice-versa
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
