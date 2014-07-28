package org.jar.invent.web.controller;

import java.util.List;

import org.jar.invent.core.service.CategoryService;
import org.jar.invent.web.domain.CategoryWeb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/catalogs/categories")
public class CategoryController {

	private CategoryService categoryService;
	
	@Autowired
	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String getCategories(Model model,
								@PageableDefault(page=0,size=50) Pageable pageRequest){
		
		Page<CategoryWeb> cats= categoryService.getCategoryList(pageRequest);
		model.addAttribute("itemCategories", cats);
		
		return "general/itemCategory";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String getCategories(@PageableDefault(page=0,size=50) Pageable pageRequest){
		categoryService.getCategoryList();
		
		return "general/itemCategory";
	}

}
