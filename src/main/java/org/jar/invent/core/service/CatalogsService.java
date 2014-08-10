package org.jar.invent.core.service;

import java.util.List;

import org.jar.invent.core.domain.EnumStatusGeneral;
import org.jar.invent.core.domain.UnitEntity.DataType;
import org.jar.invent.web.domain.Category;
import org.jar.invent.web.domain.CustomerStatus;
import org.jar.invent.web.domain.CustomerType;
import org.jar.invent.web.domain.OrderStatus;
import org.jar.invent.web.domain.OrderType;
import org.jar.invent.web.domain.OrderWorkflow;
import org.jar.invent.web.domain.Unit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CatalogsService {
	List<Category> getAllCategories();
	Page<Category> getCategories(String desc, Pageable pageRequest);
	Category saveCategory(Category cat);
	Category findCategory(short id);
	List<EnumStatusGeneral> getCategoryStatuses();
	
	List<Unit> getAllUnits();
	Page<Unit> getUnits(String text, Pageable pageRequest);
	Unit saveUnit(Unit cat);
	Unit findUnit(int id);
	List<DataType> getUnitDataTypes();
	
	List<CustomerType> getAllCustomerTypes();
	Page<CustomerType> getCustomerTypes(String desc, Pageable pageRequest);
	CustomerType saveCustomerType(CustomerType cat);
	CustomerType findCustomerType(int id);
	
	List<OrderWorkflow> getAllOrderWorkflows();
	Page<OrderWorkflow> getOrderWorkflows(String desc, Pageable pageRequest);
	OrderWorkflow saveOrderWorkflow(OrderWorkflow cat);
	OrderWorkflow findOrderWorkflow(int id);
	
	List<OrderStatus> getAllOrderStatuses();
	Page<OrderStatus> getOrderStatuses(String desc, Pageable pageRequest);
	OrderStatus saveOrderStatus(OrderStatus cat);
	OrderStatus findOrderStatus(short id);
	List<EnumStatusGeneral> getOrderStatusStatuses();
	
	List<CustomerStatus> getAllCustomerStatuses();
	Page<CustomerStatus> getCustomerStatuses(String desc, Pageable pageRequest);
	CustomerStatus saveCustomerStatus(CustomerStatus cat);
	CustomerStatus findCustomerStatus(short id);
	List<EnumStatusGeneral> getCustomerStatusStatuses();

	List<OrderType> getAllOrderTypes();
	Page<OrderType> getOrderTypes(String desc, Pageable pageRequest);
	OrderType saveOrderType(OrderType cat);
	OrderType findOrderType(short id);
	List<EnumStatusGeneral> getOrderTypeStatuses();

}
