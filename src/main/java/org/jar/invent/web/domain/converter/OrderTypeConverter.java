package org.jar.invent.web.domain.converter;

import org.jar.invent.core.domain.OrderTypeEntity;
import org.jar.invent.web.domain.OrderType;
import org.springframework.core.convert.converter.Converter;

public class OrderTypeConverter implements Converter<OrderTypeEntity, OrderType>{
		
		public OrderTypeConverter() { }
	
	@Override
	public OrderType convert(OrderTypeEntity bean) {
		if(null == bean) return null;
		
		return new OrderType(bean.getId(), bean.getDescription(),bean.getStatus());
	}

}
