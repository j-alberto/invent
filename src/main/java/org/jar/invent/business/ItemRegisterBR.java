package org.jar.invent.business;

import org.jar.invent.core.domain.ItemEntity;
import org.jar.invent.core.domain.dao.ItemDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class ItemRegisterBR {
	
	@Autowired
	private ItemDAO itemDao;

	public void setItemDao(ItemDAO itemDao) {
		this.itemDao = itemDao;
	}
	
	public static final int MAX_PAGE_SIZE = 10; 
	
	public ItemEntity registerNewItem(ItemEntity item){
		itemDao.save(item);
		return item;
	}
	
	public boolean deleteItem(ItemEntity item){
		itemDao.delete(item.getId());
		
		return true;
	}

	public boolean modifyItem(ItemEntity item){
		itemDao.save(item);
		return true;
	}
	
	public  Page<ItemEntity> getItems(String desc, Pageable pageRequest){
		pageRequest = validatePageRequest(pageRequest);

		Page<ItemEntity> resultPage;
		if(null == desc || desc.isEmpty()){
			resultPage = itemDao.findAll(pageRequest);
		}else{
			resultPage = itemDao.findByNameContainingOrDescriptionContainingAllIgnoreCase(desc, desc, pageRequest);
		}

		return resultPage;
	}
	
	public ItemEntity getItem(int id){
		return itemDao.findOne(id);
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
