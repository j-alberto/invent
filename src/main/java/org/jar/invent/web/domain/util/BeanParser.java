package org.jar.invent.web.domain.util;

import java.util.ArrayList;
import java.util.List;

import org.jar.invent.core.domain.CategoryEntity;
import org.jar.invent.core.domain.CustomerTypeEntity;
import org.jar.invent.core.domain.UnitEntity;
import org.jar.invent.web.domain.Category;
import org.jar.invent.web.domain.CustomerType;
import org.jar.invent.web.domain.Unit;

public class BeanParser{


	public static CategoryEntity toEntityBean(Category bean) {
		return new CategoryEntity(bean.getId(), bean.getDescription(), bean.getStatus());
	}
	public static Category toWebBean(CategoryEntity bean) {
		return new Category(bean.getId(), bean.getDescription(),bean.getStatus());
	}
	/**
	 * Transforms a Core-object List into a Web-object list
	 * @param entity categories
	 * @return
	 */
	public static List<Category> asWebCategoryList(List<CategoryEntity> categories){
		List<Category> webCategories = new ArrayList<>();
		
		for(CategoryEntity cat : categories){
			webCategories.add(new Category(cat.getId(), cat.getDescription(), cat.getStatus()));
		}
		return webCategories;
	}

	
	public static UnitEntity toEntityBean(Unit bean) {
		//todo resolve transitive dependencies
		return new UnitEntity(bean.getId()
				,bean.getDataType()
				,bean.getDescription()
				,bean.getName());
	}
	public static Unit toWebBean(UnitEntity bean) {
		//todo resolve transitive dependencies
		return new Unit(bean.getId()
				,bean.getDataType()
				,bean.getDescription()
				,bean.getName());
	}
	
	/**
	 * Transforms a Core-object List into a Web-object list
	 * @param entity units
	 * @return
	 */
	public static List<Unit> asWebUnitList(List<UnitEntity> units){
		List<Unit> webItemUnits = new ArrayList<>();
		
		for(UnitEntity unit : units){
			webItemUnits.add(BeanParser.toWebBean(unit));
		}
		return webItemUnits;
	}
	
	public static CustomerTypeEntity toEntityBean(CustomerType bean) {
		return new CustomerTypeEntity(bean.getId(), bean.getDescription());
	}
	public static CustomerType toWebBean(CustomerTypeEntity bean) {
		return new CustomerType(bean.getId(), bean.getDescription());
	}
	
	/**
	 * Transforms a Core-object List into a Web-object list
	 * @param entity units
	 * @return
	 */
	public static List<CustomerType> asWebCustomerTypeList(List<CustomerTypeEntity> customerTypes){
		List<CustomerType> webCustomerTypes = new ArrayList<>();
		
		for(CustomerTypeEntity custType : customerTypes){
			webCustomerTypes.add(BeanParser.toWebBean(custType));
		}
		return webCustomerTypes;
	}

}
