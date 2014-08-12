package org.jar.invent.core.domain.converter;

import org.jar.invent.core.domain.OrderTypeEntity;
import org.jar.invent.web.domain.OrderType;
import org.springframework.core.convert.converter.Converter;

public class OrderTypeEntityConverter implements Converter<OrderType,OrderTypeEntity>{
		
		public OrderTypeEntityConverter() { }
	
	@Override
	public OrderTypeEntity convert(OrderType bean) {
		if(null == bean) return null;
		
		return new OrderTypeEntity(bean.getId(), bean.getDescription(),bean.getStatus());
	}

}
