package org.jar.invent.web.domain.converter;

import org.jar.invent.core.domain.OrderWorkflowEntity;
import org.jar.invent.web.domain.OrderWorkflow;
import org.springframework.core.convert.converter.Converter;

public class WorkflowConverter implements Converter<OrderWorkflowEntity, OrderWorkflow>{

		public WorkflowConverter() { }
	
	@Override
	public OrderWorkflow convert(OrderWorkflowEntity bean) {
		if(null == bean) return null;
		
		return new OrderWorkflow(bean.getId(), bean.getDescription());
	}

}
