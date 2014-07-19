package org.jar.invent.web.service;

import org.jar.invent.core.service.CategoryService;
import org.jar.invent.web.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/categories")
public class CategoryRestService {

	private CategoryService categoryService;
	
	public CategoryRestService(){}

	@Autowired
	public CategoryRestService(CategoryService categoryService){
		this.categoryService = categoryService;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public HttpEntity<Page<Category>> getCategoriesPage(
			 @RequestParam(value="name",defaultValue="") String name
			,@PageableDefault(page=0,size=50) Pageable pageRequest){
		
		Page<Category> categories = categoryService.getCategories(name, pageRequest);
		
		return new ResponseEntity<Page<Category>>(categories, HttpStatus.OK);
	}

}
