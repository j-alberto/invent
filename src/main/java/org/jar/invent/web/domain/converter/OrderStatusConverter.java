package org.jar.invent.web.domain.converter;

import org.jar.invent.core.domain.OrderStatusEntity;
import org.jar.invent.web.domain.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class OrderStatusConverter implements Converter<OrderStatusEntity, OrderStatus>{

		public OrderStatusConverter() { }
		
	@Autowired
	private WorkflowConverter workflowConverter;
	
	@Override
	public OrderStatus convert(OrderStatusEntity bean) {
		if(null == bean) return null;
		
		OrderStatus orderStatus = new OrderStatus(bean.getId(), bean.getDescription(),bean.getStatus());
		
		if(null != bean.getOrderWorkflow()){
			orderStatus.setOrderWorkflow(workflowConverter.convert(bean.getOrderWorkflow()));
		}
		return orderStatus;
	}
		
	public void setWorkflowConverter(WorkflowConverter workflowConverter) {
		this.workflowConverter = workflowConverter;
	}
	

}
