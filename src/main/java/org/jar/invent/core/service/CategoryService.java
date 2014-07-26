package org.jar.invent.core.service;

import java.util.ArrayList;
import java.util.List;

import org.jar.invent.core.domain.Category;
import org.jar.invent.core.domain.dao.CategoryDAO;
import org.jar.invent.web.domain.CategoryWeb;
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

	@Autowired
	private CategoryDAO categoryDAO;
	
	private Logger log = LoggerFactory.getLogger(CategoryService.class);

	public void setClassGroupDAO(CategoryDAO categoryDAO) {
		this.categoryDAO = categoryDAO;
	}


	public List<CategoryWeb> getCategoryList(){
		return asWebDomainList((List<Category>)categoryDAO.findAll());
	}
	
	public Page<CategoryWeb> getCategoryList(Pageable pageRequest){
		Page<Category> resultPage = categoryDAO.findAll(pageRequest);
		List<CategoryWeb> categoriesWeb = asWebDomainList(resultPage.getContent());
		
		Page<CategoryWeb> categoriesPage = new PageImpl<CategoryWeb>(categoriesWeb, pageRequest, resultPage.getTotalElements());

		return categoriesPage;
	}
	
	
	private List<CategoryWeb> asWebDomainList(List<Category> categories){
		List<CategoryWeb> webCategories = new ArrayList<CategoryWeb>();
		
		for(Category c : categories){
			webCategories.add(new CategoryWeb(c.getId(), c.getDescription(), c.getStatus()));
		}
		return webCategories;
	}
}
