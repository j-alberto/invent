package org.jar.invent.web.domain.util;

import java.util.ArrayList;
import java.util.List;

import org.jar.invent.core.domain.CategoryEntity;
import org.jar.invent.core.domain.CustomerStatusEntity;
import org.jar.invent.core.domain.CustomerTypeEntity;
import org.jar.invent.core.domain.OrderStatusEntity;
import org.jar.invent.core.domain.OrderTypeEntity;
import org.jar.invent.core.domain.OrderWorkflowEntity;
import org.jar.invent.core.domain.UnitEntity;
import org.jar.invent.web.domain.Category;
import org.jar.invent.web.domain.CustomerStatus;
import org.jar.invent.web.domain.CustomerType;
import org.jar.invent.web.domain.OrderStatus;
import org.jar.invent.web.domain.OrderType;
import org.jar.invent.web.domain.OrderWorkflow;
import org.jar.invent.web.domain.Unit;

public class BeanParser{

	/**
	 * Transforms a Web-object into a Core-object
	 * @param bean of type Category
	 * @return bean of type CategoryEntity
	 */
	public static CategoryEntity toEntityBean(Category bean) {
		return new CategoryEntity(bean.getId(), bean.getDescription(), bean.getStatus());
	}
	/**
	 * Transforms a Core-object into a Web-object
	 * @param bean of type CategoryEntity
	 * @return bean of type Category
	 */
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

	/**
	 * Transforms a Web-object into a Core-object
	 * @param bean of type Unit
	 * @return bean of type UnitEntity
	 */
	public static UnitEntity toEntityBean(Unit bean) {
		//todo resolve transitive dependencies
		return new UnitEntity(bean.getId()
				,bean.getDataType()
				,bean.getDescription()
				,bean.getName());
	}
	/**
	 * Transforms a Core-object into a Web-object
	 * @param bean of type UnitEntity
	 * @return bean of type Unit
	 */
	public static Unit toWebBean(UnitEntity bean) {
		//todo resolve transitive dependencies
		return new Unit(bean.getId()
				,bean.getDataType()
				,bean.getDescription()
				,bean.getName());
	}
	
	/**
	 * Transforms a Core-object List into a Web-object list
	 * @param units
	 * @return units
	 */
	public static List<Unit> asWebUnitList(List<UnitEntity> units){
		List<Unit> webItemUnits = new ArrayList<>();
		
		for(UnitEntity unit : units){
			webItemUnits.add(BeanParser.toWebBean(unit));
		}
		return webItemUnits;
	}
	
	/**
	 * Transforms a Web-object into a Core-object
	 * @param bean of type CustomerType
	 * @return bean of type CustomerTypeEntity
	 */
	public static CustomerTypeEntity toEntityBean(CustomerType bean) {
		return new CustomerTypeEntity(bean.getId(), bean.getDescription());
	}
	/**
	 * Transforms a Core-object into a Web-object
	 * @param bean of type CustomerTypeEntity
	 * @return bean of type CustomerType
	 */
	public static CustomerType toWebBean(CustomerTypeEntity bean) {
		return new CustomerType(bean.getId(), bean.getDescription());
	}
	
	/**
	 * Transforms a Core-object List into a Web-object list
	 * @param units
	 * @return units
	 */
	public static List<CustomerType> asWebCustomerTypeList(List<CustomerTypeEntity> customerTypes){
		List<CustomerType> webCustomerTypes = new ArrayList<>();
		
		for(CustomerTypeEntity custType : customerTypes){
			webCustomerTypes.add(BeanParser.toWebBean(custType));
		}
		return webCustomerTypes;
	}
	/**
	 * Transforms a Web-object into a Core-object
	 * @param bean of type OrderStatus
	 * @return bean of type OrderStatusEntity
	 */
	public static OrderStatusEntity toEntityBean(OrderStatus bean) {
		return new OrderStatusEntity(bean.getId(), bean.getDescription(),bean.getStatus());
	}
	/**
	 * Transforms a Core-object into a Web-object
	 * @param bean of type OrderStatusEntity
	 * @return bean of type OrderStatus
	 */
	public static OrderStatus toWebBean(OrderStatusEntity bean) {
		return new OrderStatus(bean.getId(), bean.getDescription(),bean.getStatus());
	}
	
	/**
	 * Transforms a Core-object List into a Web-object list
	 * @param units
	 * @return units
	 */
	public static List<OrderStatus> asWebOrderStatusList(List<OrderStatusEntity> orderStatuses){
		List<OrderStatus> webOrderStatus = new ArrayList<>();
		
		for(OrderStatusEntity orderStatus : orderStatuses){
			webOrderStatus.add(BeanParser.toWebBean(orderStatus));
		}
		return webOrderStatus;
	}
	
	/**
	 * Transforms a Web-object into a Core-object
	 * @param bean of type CustomerStatus
	 * @return bean of type CustomerStatusEntity
	 */
	public static CustomerStatusEntity toEntityBean(CustomerStatus bean) {
		return new CustomerStatusEntity(bean.getId(), bean.getDescription(),bean.getStatus());
	}
	/**
	 * Transforms a Core-object into a Web-object
	 * @param bean of type CustomerStatusEntity
	 * @return bean of type CustomerStatus
	 */
	public static CustomerStatus toWebBean(CustomerStatusEntity bean) {
		return new CustomerStatus(bean.getId(), bean.getDescription(),bean.getStatus());
	}
	
	/**
	 * Transforms a Core-object List into a Web-object list
	 * @param units
	 * @return units
	 */
	public static List<CustomerStatus> asWebCustomerStatusList(List<CustomerStatusEntity> customerStatuses){
		List<CustomerStatus> webCustomerStatuses = new ArrayList<>();
		
		for(CustomerStatusEntity customerStatus : customerStatuses){
			webCustomerStatuses.add(BeanParser.toWebBean(customerStatus));
		}
		return webCustomerStatuses;
	}
	
	/**
	 * Transforms a Web-object into a Core-object
	 * @param bean of type OrderType
	 * @return bean of type OrderTypeEntity
	 */
	public static OrderTypeEntity toEntityBean(OrderType bean) {
		return new OrderTypeEntity(bean.getId(), bean.getDescription(),bean.getStatus());
	}
	/**
	 * Transforms a Core-object into a Web-object
	 * @param bean of type OrderTypeEntity
	 * @return bean of type OrderType
	 */
	public static OrderType toWebBean(OrderTypeEntity bean) {
		return new OrderType(bean.getId(), bean.getDescription(),bean.getStatus());
	}
	
	/**
	 * Transforms a Core-object List into a Web-object list
	 * @param units
	 * @return units
	 */
	public static List<OrderType> asWebOrderTypeList(List<OrderTypeEntity> orderTypes){
		List<OrderType> webOrderTypes = new ArrayList<>();
		
		for(OrderTypeEntity orderType : orderTypes){
			webOrderTypes.add(BeanParser.toWebBean(orderType));
		}
		return webOrderTypes;
	}
	
	/**
	 * Transforms a Web-object into a Core-object
	 * @param bean of type OrderWorkflow
	 * @return bean of type OrderWorkflowEntity
	 */
	public static OrderWorkflowEntity toEntityBean(OrderWorkflow bean) {
		return new OrderWorkflowEntity(bean.getId(), bean.getDescription());
	}
	/**
	 * Transforms a Core-object into a Web-object
	 * @param bean of type OrderWorkflowEntity
	 * @return bean of type OrderWorkflow
	 */
	public static OrderWorkflow toWebBean(OrderWorkflowEntity bean) {
		return new OrderWorkflow(bean.getId(), bean.getDescription());
	}
	
	/**
	 * Transforms a Core-object List into a Web-object list
	 * @param units
	 * @return units
	 */
	public static List<OrderWorkflow> asWebOrderWorkflowList(List<OrderWorkflowEntity> orderWorkflows){
		List<OrderWorkflow> webOrderWorkflows = new ArrayList<>();
		
		for(OrderWorkflowEntity orderWorkflow : orderWorkflows){
			webOrderWorkflows.add(BeanParser.toWebBean(orderWorkflow));
		}
		return webOrderWorkflows;
	}

}
