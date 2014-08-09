package org.jar.invent.core.service;

import java.util.List;

import org.jar.invent.core.domain.CustomerTypeEntity;
import org.jar.invent.core.domain.dao.CustomerTypeDAO;
import org.jar.invent.web.domain.CustomerType;
import org.jar.invent.web.domain.util.BeanParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CustomerTypeService {

	private CustomerTypeDAO customerTypeDAO;
	
	private Logger log = LoggerFactory.getLogger(CustomerTypeService.class);
	
	private static final int MAX_PAGE_SIZE= 100;
	
	@Autowired
	public CustomerTypeService(CustomerTypeDAO customerTypeDAO) {
		this.customerTypeDAO = customerTypeDAO;
	}


	public List<CustomerType> getAllCustomerTypes(){
		return BeanParser.asWebCustomerTypeList((List<CustomerTypeEntity>)customerTypeDAO.findAll());
	}
	
	public Page<CustomerType> getCustomerTypes(String desc, Pageable pageRequest){
		if(pageRequest.getPageSize()>MAX_PAGE_SIZE){
			//TODO: let consumer know about change in page's size
			pageRequest = new PageRequest(pageRequest.getPageNumber(), MAX_PAGE_SIZE);
		}

		Page<CustomerTypeEntity> resultPage;
		
		if(desc.isEmpty()){
			resultPage = customerTypeDAO.findAll(pageRequest);
		}else{
			resultPage = customerTypeDAO.findByDescriptionContainingIgnoreCase(desc, pageRequest);
		}

		List<CustomerType> categoriesWeb = BeanParser.asWebCustomerTypeList(resultPage.getContent());
		
		Page<CustomerType> categoriesPage = new PageImpl<CustomerType>(categoriesWeb, pageRequest, resultPage.getTotalElements());

		return categoriesPage;
	}
	
	public CustomerType saveCustomerType(CustomerType cat){
		CustomerTypeEntity c = customerTypeDAO.save(BeanParser.toEntityBean(cat));
		return BeanParser.toWebBean(c);
	}
	
	public CustomerType findCustomerType(int id){
		CustomerTypeEntity c = customerTypeDAO.findOne(id);
		return c==null ? null : BeanParser.toWebBean(c);
	}

}
