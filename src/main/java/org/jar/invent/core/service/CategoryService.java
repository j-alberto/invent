package org.jar.invent.core.service;

import java.util.ArrayList;
import java.util.List;

import org.jar.invent.core.domain.CategoryEntity;
import org.jar.invent.core.domain.dao.CategoryDAO;
import org.jar.invent.web.domain.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

	private CategoryDAO categoryDAO;
	
	private Logger log = LoggerFactory.getLogger(CategoryService.class);
	
	@Autowired
	public CategoryService(CategoryDAO categoryDAO) {
		this.categoryDAO = categoryDAO;
	}


	public List<Category> getCategoryList(){
		return asWebDomainList((List<CategoryEntity>)categoryDAO.findAll());
	}
	
	public Page<Category> getCategoryList(Pageable pageRequest){
		Page<CategoryEntity> resultPage = categoryDAO.findAll(pageRequest);
		List<Category> categoriesWeb = asWebDomainList(resultPage.getContent());
		
		Page<Category> categoriesPage = new PageImpl<Category>(categoriesWeb, pageRequest, resultPage.getTotalElements());

		return categoriesPage;
	}
	
	public Category saveCategory(Category cat){
		CategoryEntity c = categoryDAO.save(cat.toEntityBean());
		return Category.parseViewBean(c);
	}
	
	
	private List<Category> asWebDomainList(List<CategoryEntity> categories){
		List<Category> webCategories = new ArrayList<Category>();
		
		for(CategoryEntity c : categories){
			webCategories.add(new Category(c.getId(), c.getDescription(), c.getStatus()));
		}
		return webCategories;
	}
}
