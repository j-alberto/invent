package org.jar.invent.core.domain.converter;

import org.jar.invent.core.domain.OrderWorkflowEntity;
import org.jar.invent.web.domain.OrderWorkflow;
import org.springframework.core.convert.converter.Converter;

public class WorkflowEntityConverter implements Converter<OrderWorkflow, OrderWorkflowEntity>{

	@Override
	public OrderWorkflowEntity convert(OrderWorkflow bean) {
		return new OrderWorkflowEntity(bean.getId(), bean.getDescription());
	}

}
