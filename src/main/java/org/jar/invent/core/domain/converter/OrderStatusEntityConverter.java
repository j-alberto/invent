package org.jar.invent.core.domain.converter;

import org.jar.invent.core.domain.OrderStatusEntity;
import org.jar.invent.web.domain.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class OrderStatusEntityConverter implements Converter<OrderStatus,OrderStatusEntity>{
		
		public OrderStatusEntityConverter() { }

	@Autowired
	private WorkflowEntityConverter workflowEntityConverter;
	
	@Override
	public OrderStatusEntity convert(OrderStatus bean) {
		if(null == bean) return null;
		
		OrderStatusEntity orderStatusEntity = new OrderStatusEntity(bean.getId(), bean.getDescription(),bean.getStatus());
		
		if(null != bean.getOrderWorkflow()){
			orderStatusEntity.setOrderWorkflow(workflowEntityConverter.convert(bean.getOrderWorkflow()));
		}
		return orderStatusEntity;
	}
		
	public void setWorkflowEntityConverter(WorkflowEntityConverter workflowEntityConverter) {
		this.workflowEntityConverter = workflowEntityConverter;
	}
	

}
