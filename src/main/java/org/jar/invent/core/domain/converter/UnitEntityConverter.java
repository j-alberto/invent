package org.jar.invent.core.domain.converter;

import org.springframework.core.convert.converter.Converter;
import org.jar.invent.core.domain.UnitEntity;
import org.jar.invent.web.domain.Unit;

public class UnitEntityConverter implements Converter<Unit,UnitEntity> {

	@Override
	public UnitEntity convert(Unit bean) {
		if(null == bean) return null;
		
		return new UnitEntity(bean.getId(), bean.getDataType(), bean.getDescription(), bean.getName());
	}

}
