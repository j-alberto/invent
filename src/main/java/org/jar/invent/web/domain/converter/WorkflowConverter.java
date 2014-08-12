package org.jar.invent.web.domain.converter;

import org.jar.invent.core.domain.OrderWorkflowEntity;
import org.jar.invent.web.domain.OrderWorkflow;
import org.springframework.core.convert.converter.Converter;

public class WorkflowConverter implements Converter<OrderWorkflowEntity, OrderWorkflow>{

	@Override
	public OrderWorkflow convert(OrderWorkflowEntity bean) {
		return new OrderWorkflow(bean.getId(), bean.getDescription());
	}

}
