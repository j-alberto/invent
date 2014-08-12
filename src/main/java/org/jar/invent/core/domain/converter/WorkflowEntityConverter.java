package org.jar.invent.core.domain.converter;

import org.jar.invent.core.domain.OrderWorkflowEntity;
import org.jar.invent.web.domain.OrderWorkflow;
import org.springframework.core.convert.converter.Converter;

public class WorkflowEntityConverter implements Converter<OrderWorkflow, OrderWorkflowEntity>{
	
		public WorkflowEntityConverter() { }

	@Override
	public OrderWorkflowEntity convert(OrderWorkflow bean) {
		if(null == bean) return null;
		
		return new OrderWorkflowEntity(bean.getId(), bean.getDescription());
	}

}
