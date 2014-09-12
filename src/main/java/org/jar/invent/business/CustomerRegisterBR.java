package org.jar.invent.business;

import org.jar.invent.core.domain.CustomerEntity;
import org.jar.invent.core.domain.ItemEntity;
import org.jar.invent.core.domain.dao.CustomerDAO;
import org.jar.invent.core.domain.dao.ItemDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class CustomerRegisterBR {
	
	@Autowired
	private CustomerDAO custDao;

	public void setCustomerDAO(CustomerDAO custDao) {
		this.custDao = custDao;
	}
	
	public static final int MAX_PAGE_SIZE = 10; 
	
	public CustomerEntity registerNewCustomer(CustomerEntity customer){
		custDao.save(customer);
		return customer;
	}
	
	public boolean deleteCustomer(CustomerEntity customer){
		custDao.delete(customer.getId());
		
		return true;
	}

	public boolean modifyCustomer(CustomerEntity customer){
		custDao.save(customer);
		return true;
	}
	
	public  Page<CustomerEntity> getCustomers(String desc, Pageable pageRequest){
		pageRequest = validatePageRequest(pageRequest);

		Page<CustomerEntity> resultPage;
		if(null == desc || desc.isEmpty()){
			resultPage = custDao.findAll(pageRequest);
		}else{
			resultPage = custDao.findByNameContainingOrCodeContainingOrDescriptionContainingAllIgnoreCase(desc, desc, desc, pageRequest);
		}

		return resultPage;
	}
	
	public CustomerEntity getCustomer(int id){
		return custDao.findOne(id);
	}
	

	/**
	 * Checks that no request asks for more than max page size
	 * @param pageRequest
	 * @return
	 */
	private Pageable validatePageRequest(Pageable pageRequest){
		if(pageRequest.getPageSize()>MAX_PAGE_SIZE){
			//TODO: let consumer know about change in page's size
			pageRequest = new PageRequest(pageRequest.getPageNumber(), MAX_PAGE_SIZE);
		}
		
		return pageRequest;
	}

}
