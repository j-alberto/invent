package org.jar.invent.web.domain.converter;

import org.jar.invent.core.domain.UnitEntity;
import org.jar.invent.web.domain.Unit;
import org.springframework.core.convert.converter.Converter;

public class UnitConverter implements Converter<UnitEntity,Unit> {

	@Override
	public Unit convert(UnitEntity bean) {
		if(null == bean) return null;
		
		return new Unit(bean.getId(), bean.getDataType(), bean.getDescription(), bean.getName());
	}

}
