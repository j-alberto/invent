package org.jar.invent.core.service;

import java.util.Arrays;
import java.util.List;

import org.jar.invent.core.domain.CategoryEntity;
import org.jar.invent.core.domain.CustomerStatusEntity;
import org.jar.invent.core.domain.CustomerTypeEntity;
import org.jar.invent.core.domain.EnumStatusGeneral;
import org.jar.invent.core.domain.OrderStatusEntity;
import org.jar.invent.core.domain.OrderTypeEntity;
import org.jar.invent.core.domain.OrderWorkflowEntity;
import org.jar.invent.core.domain.UnitEntity;
import org.jar.invent.core.domain.UnitEntity.DataType;
import org.jar.invent.core.domain.dao.CategoryDAO;
import org.jar.invent.core.domain.dao.CustomerStatusDAO;
import org.jar.invent.core.domain.dao.CustomerTypeDAO;
import org.jar.invent.core.domain.dao.OrderStatusDAO;
import org.jar.invent.core.domain.dao.OrderTypeDAO;
import org.jar.invent.core.domain.dao.OrderWorkflowDAO;
import org.jar.invent.core.domain.dao.UnitDAO;
import org.jar.invent.web.domain.Category;
import org.jar.invent.web.domain.CustomerStatus;
import org.jar.invent.web.domain.CustomerType;
import org.jar.invent.web.domain.OrderStatus;
import org.jar.invent.web.domain.OrderType;
import org.jar.invent.web.domain.OrderWorkflow;
import org.jar.invent.web.domain.Unit;
import org.jar.invent.web.domain.util.BeanParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@PropertySource(value="classpath:appConfig.properties")
public class CatalogsServiceImp implements CatalogsService {
	
		@Autowired
		public CatalogsServiceImp(CategoryDAO categoryDAO, UnitDAO unitDAO, CustomerTypeDAO customerTypeDAO,
				OrderWorkflowDAO orderWorkflowDAO, OrderStatusDAO orderStatusDAO, CustomerStatusDAO customerStatusDAO,
				OrderTypeDAO orderTypeDAO) {
			this.categoryDAO = categoryDAO;
			this.unitDAO = unitDAO;
			this.customerTypeDAO = customerTypeDAO;
			this.orderWorkflowDAO = orderWorkflowDAO;
			this.orderStatusDAO = orderStatusDAO;
			this.customerStatusDAO = customerStatusDAO;
			this.orderTypeDAO = orderTypeDAO;
		}
	
	private CategoryDAO categoryDAO;
	private UnitDAO unitDAO;
	private CustomerTypeDAO customerTypeDAO;
	private OrderWorkflowDAO orderWorkflowDAO;
	private OrderStatusDAO orderStatusDAO;
	private CustomerStatusDAO customerStatusDAO;
	private OrderTypeDAO orderTypeDAO;
	
//	@Autowired
	private	ConversionService conversionService;
	
	@Value("${page.maxsize}")
	private int MAX_PAGE_SIZE= 100;
	
	Logger log = LoggerFactory.getLogger(getClass());
	
	
	public List<Category> getAllCategories(){
		return BeanParser.asWebCategoryList(categoryDAO.findAll());
	}
	
	public Page<Category> getCategories(String desc, Pageable pageRequest){
		pageRequest = validatePageRequest(pageRequest);

		Page<CategoryEntity> resultPage;
		if(desc.isEmpty()){
			resultPage = categoryDAO.findAll(pageRequest);
		}else{
			resultPage = categoryDAO.findByDescriptionContainingIgnoreCase(desc, pageRequest);
		}
		
		List<Category> categoriesWeb = BeanParser.asWebCategoryList(resultPage.getContent());
		Page<Category> categoriesPage = new PageImpl<Category>(categoriesWeb, pageRequest, resultPage.getTotalElements());

		return categoriesPage;
	}
	
	public Category saveCategory(Category categ){
		CategoryEntity categEnt = categoryDAO.save(BeanParser.toEntityBean(categ));
		return BeanParser.toWebBean(categEnt);
	}
	
	public Category findCategory(short id){
		CategoryEntity categEnt = categoryDAO.findOne(id);
		return categEnt==null ? null : BeanParser.toWebBean(categEnt);
	}
	
	public List<EnumStatusGeneral> getCategoryStatuses(){
		return Arrays.asList(EnumStatusGeneral.values());
	}

	public List<Unit> getAllUnits(){
		return BeanParser.asWebUnitList(unitDAO.findAll());
	}
	
	public Page<Unit> getUnits(String text, Pageable pageRequest){
		pageRequest = validatePageRequest(pageRequest);

		Page<UnitEntity> resultPage;
		if(text.isEmpty()){
			resultPage = unitDAO.findAll(pageRequest);
		}else{
			resultPage = unitDAO.findByNameOrDescriptionContainingAllIgnoreCase(text, text, pageRequest);
		}
		
		List<Unit> unitsWeb = BeanParser.asWebUnitList(resultPage.getContent());
		Page<Unit> unitsPage = new PageImpl<Unit>(unitsWeb, pageRequest, resultPage.getTotalElements());

		return unitsPage;
	}
	
	public Unit saveUnit(Unit unit){
		UnitEntity unitEnt = unitDAO.save(BeanParser.toEntityBean(unit));
		return BeanParser.toWebBean(unitEnt);
	}
	
	public Unit findUnit(int id){
		UnitEntity unitEnt = unitDAO.findOne(id);
		return unitEnt==null ? null : BeanParser.toWebBean(unitEnt);
	}
	
	public List<DataType> getUnitDataTypes(){
		return Arrays.asList(DataType.values());
	}

	public List<CustomerType> getAllCustomerTypes(){
		return BeanParser.asWebCustomerTypeList(customerTypeDAO.findAll());
	}
	
	public Page<CustomerType> getCustomerTypes(String desc, Pageable pageRequest){
		pageRequest = validatePageRequest(pageRequest);

		Page<CustomerTypeEntity> resultPage;
		if(desc.isEmpty()){
			resultPage = customerTypeDAO.findAll(pageRequest);
		}else{
			resultPage = customerTypeDAO.findByDescriptionContainingIgnoreCase(desc, pageRequest);
		}

		List<CustomerType> custTypesWeb = BeanParser.asWebCustomerTypeList(resultPage.getContent());
		Page<CustomerType> custTypesPage = new PageImpl<CustomerType>(custTypesWeb, pageRequest, resultPage.getTotalElements());

		return custTypesPage;
	}
	
	public CustomerType saveCustomerType(CustomerType custType){
		CustomerTypeEntity custTypeEnt = customerTypeDAO.save(BeanParser.toEntityBean(custType));
		return BeanParser.toWebBean(custTypeEnt);
	}
	
	public CustomerType findCustomerType(int id){
		CustomerTypeEntity custTypeEnt = customerTypeDAO.findOne(id);
		return custTypeEnt==null ? null : BeanParser.toWebBean(custTypeEnt);
	}

	@Override
	public List<OrderWorkflow> getAllOrderWorkflows() {
		return BeanParser.asWebOrderWorkflowList(orderWorkflowDAO.findAll());
	}

	@Override
	public Page<OrderWorkflow> getOrderWorkflows(String desc, Pageable pageRequest) {
		pageRequest = validatePageRequest(pageRequest);
		
		Page<OrderWorkflowEntity> resultPage;
		if(desc.isEmpty()){
			resultPage = orderWorkflowDAO.findAll(pageRequest);
		}else{
			resultPage = orderWorkflowDAO.findByDescriptionContainingIgnoreCase(desc, pageRequest);
		}

		List<OrderWorkflow> orderWflowsWeb = BeanParser.asWebOrderWorkflowList(resultPage.getContent());
		Page<OrderWorkflow> orderWflowsPage = new PageImpl<OrderWorkflow>(orderWflowsWeb, pageRequest, resultPage.getTotalElements());

		return orderWflowsPage;
	}

	@Override
	public OrderWorkflow saveOrderWorkflow(OrderWorkflow wflow) {
		OrderWorkflowEntity wflowEnt = orderWorkflowDAO.save(BeanParser.toEntityBean(wflow));
		return BeanParser.toWebBean(wflowEnt);
	}

	@Override
	public OrderWorkflow findOrderWorkflow(int id) {
		OrderWorkflowEntity wflow = orderWorkflowDAO.findOne(id);
		return wflow==null ? null : BeanParser.toWebBean(wflow);
//		log.info("Converting with service!!");
//		return wflow==null ? null : conversionService.convert(wflow, OrderWorkflow.class);
	}

	@Override
	public List<OrderStatus> getAllOrderStatuses() {
		return BeanParser.asWebOrderStatusList(orderStatusDAO.findAll());
	}

	@Override
	public Page<OrderStatus> getOrderStatuses(String desc, Pageable pageRequest) {
		pageRequest = validatePageRequest(pageRequest);

		Page<OrderStatusEntity> resultPage;
		if(desc.isEmpty()){
			resultPage = orderStatusDAO.findAll(pageRequest);
		}else{
			resultPage = orderStatusDAO.findByDescriptionContainingIgnoreCase(desc, pageRequest);
		}

		List<OrderStatus> orderStatusesWeb = BeanParser.asWebOrderStatusList(resultPage.getContent());
		Page<OrderStatus> orderStatusesPage = new PageImpl<OrderStatus>(orderStatusesWeb, pageRequest, resultPage.getTotalElements());

		return orderStatusesPage;
	}

	@Override
	public OrderStatus saveOrderStatus(OrderStatus orderStatus) {
		OrderStatusEntity orderStatusEnt = orderStatusDAO.save(BeanParser.toEntityBean(orderStatus));
		return BeanParser.toWebBean(orderStatusEnt);
	}

	@Override
	public OrderStatus findOrderStatus(int id) {
		OrderStatusEntity orderStatus = orderStatusDAO.findOne(id);
		return orderStatus==null ? null : BeanParser.toWebBean(orderStatus);
	}

	@Override
	public List<EnumStatusGeneral> getOrderStatusStatuses() {
		return Arrays.asList(EnumStatusGeneral.values());
	}

	@Override
	public List<CustomerStatus> getAllCustomerStatuses() {
		return BeanParser.asWebCustomerStatusList(customerStatusDAO.findAll());
	}

	@Override
	public Page<CustomerStatus> getCustomerStatuses(String desc, Pageable pageRequest) {
		pageRequest = validatePageRequest(pageRequest);

		Page<CustomerStatusEntity> resultPage;
		if(desc.isEmpty()){
			resultPage = customerStatusDAO.findAll(pageRequest);
		}else{
			resultPage = customerStatusDAO.findByDescriptionContainingIgnoreCase(desc, pageRequest);
		}

		List<CustomerStatus> customerStatusesWeb = BeanParser.asWebCustomerStatusList(resultPage.getContent());
		Page<CustomerStatus> customerStatusesPage = new PageImpl<CustomerStatus>(customerStatusesWeb, pageRequest, resultPage.getTotalElements());

		return customerStatusesPage;
}

	@Override
	public CustomerStatus saveCustomerStatus(CustomerStatus customerStatus) {
		CustomerStatusEntity customerStatusEnt = customerStatusDAO.save(BeanParser.toEntityBean(customerStatus));
		return BeanParser.toWebBean(customerStatusEnt);
	}

	@Override
	public CustomerStatus findCustomerStatus(int id) {
		CustomerStatusEntity customerStatusEnt = customerStatusDAO.findOne(id);
		return customerStatusEnt==null ? null : BeanParser.toWebBean(customerStatusEnt);
	}

	@Override
	public List<EnumStatusGeneral> getCustomerStatusStatuses() {
		return Arrays.asList(EnumStatusGeneral.values());
	}

	@Override
	public List<OrderType> getAllOrderTypes() {
		return BeanParser.asWebOrderTypeList(orderTypeDAO.findAll());
	}

	@Override
	public Page<OrderType> getOrderTypes(String desc, Pageable pageRequest) {
		pageRequest = validatePageRequest(pageRequest);

		Page<OrderTypeEntity> resultPage;
		if(desc.isEmpty()){
			resultPage = orderTypeDAO.findAll(pageRequest);
		}else{
			resultPage = orderTypeDAO.findByDescriptionContainingIgnoreCase(desc, pageRequest);
		}

		List<OrderType> orderTypesWeb = BeanParser.asWebOrderTypeList(resultPage.getContent());
		Page<OrderType> orderTypesPage = new PageImpl<OrderType>(orderTypesWeb, pageRequest, resultPage.getTotalElements());

		return orderTypesPage;
	}

	@Override
	public OrderType saveOrderType(OrderType orderType) {
		OrderTypeEntity orderTypeEnt = orderTypeDAO.save(BeanParser.toEntityBean(orderType));
		return BeanParser.toWebBean(orderTypeEnt);
	}

	@Override
	public OrderType findOrderType(short id) {
		OrderTypeEntity orderTypeEnt = orderTypeDAO.findOne(id);
		return orderTypeEnt==null ? null : BeanParser.toWebBean(orderTypeEnt);
	}

	@Override
	public List<EnumStatusGeneral> getOrderTypeStatuses() {
		return Arrays.asList(EnumStatusGeneral.values());
	}
	
	private Pageable validatePageRequest(Pageable pageRequest){
		if(pageRequest.getPageSize()>MAX_PAGE_SIZE){
			//TODO: let consumer know about change in page's size
			log.info("changing request page size to "+MAX_PAGE_SIZE);
			pageRequest = new PageRequest(pageRequest.getPageNumber(), MAX_PAGE_SIZE);
		}
		
		return pageRequest;
	}

	public void setTransformService( ConversionService conversionService) {
		this.conversionService = conversionService;
	}

}
