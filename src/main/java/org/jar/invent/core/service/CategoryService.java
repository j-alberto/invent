package org.jar.invent.core.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jar.invent.core.domain.CategoryEntity;
import org.jar.invent.core.domain.EnumStatusGeneral;
import org.jar.invent.core.domain.dao.CategoryDAO;
import org.jar.invent.web.domain.Category;
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
public class CategoryService {

	private CategoryDAO categoryDAO;
	
	private Logger log = LoggerFactory.getLogger(CategoryService.class);
	
	private static final int MAX_PAGE_SIZE= 100;
	
	@Autowired
	public CategoryService(CategoryDAO categoryDAO) {
		this.categoryDAO = categoryDAO;
	}


	public List<Category> getAllCategories(){
		return asWebDomainList((List<CategoryEntity>)categoryDAO.findAll());
	}
	
	public Page<Category> getCategories(String name, Pageable pageRequest){
		if(pageRequest.getPageSize()>MAX_PAGE_SIZE){
			//TODO: let consumer know about change in page's size
			pageRequest = new PageRequest(pageRequest.getPageNumber(), MAX_PAGE_SIZE);
		}

		Page<CategoryEntity> resultPage;
		
		if(name.isEmpty()){
			resultPage = categoryDAO.findAll(pageRequest);
		}else{
			resultPage = categoryDAO.findByDescriptionContainingIgnoreCase(name, pageRequest);
		}
		
		
		List<Category> categoriesWeb = asWebDomainList(resultPage.getContent());
		
		Page<Category> categoriesPage = new PageImpl<Category>(categoriesWeb, pageRequest, resultPage.getTotalElements());

		return categoriesPage;
	}
	
	public Category saveCategory(Category cat){
		CategoryEntity c = categoryDAO.save(BeanParser.toEntityBean(cat));
		return BeanParser.toWebBean(c);
	}
	
	public Category findCategory(short id){
		CategoryEntity c = categoryDAO.findOne(id);
		return c==null ? null : BeanParser.toWebBean(c);
	}
	
	public List<EnumStatusGeneral> getCategoryStatuses(){
		return Arrays.asList(EnumStatusGeneral.values());
	}
	
	/**
	 * Transforms a Core-object List into a Web-object list
	 * @param categories
	 * @return
	 */
	private List<Category> asWebDomainList(List<CategoryEntity> categories){
		List<Category> webCategories = new ArrayList<Category>();
		
		for(CategoryEntity c : categories){
			webCategories.add(new Category(c.getId(), c.getDescription(), c.getStatus()));
		}
		return webCategories;
	}
}
