package org.jar.invent.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.jar.invent.core.service.CategoryService;
import org.jar.invent.web.domain.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/catalogs/categories")
public class CategoryController {

	private CategoryService categoryService;
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String getCategories(Model model,
								@PageableDefault(page=0,size=50) Pageable pageRequest){
		
		Page<Category> cats= categoryService.getCategoryList(pageRequest);
		model.addAttribute("itemCategories", cats);
		
		return "general/itemCategory";
	}

	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String addCategories(Model model){
		log.info("enters here! get");
		return "general/itemCategoryAdd";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String addCategories(@Valid final Category category, final BindingResult bindResults, final ModelMap model){
		log.info("entering");
		if(bindResults.hasErrors()){
			log.info("entering errors");
			for(String kay : model.keySet()){
				log.info(kay+">>"+model.get(kay));
			}
			return "general/itemCategoryAdd";
		}
		
		categoryService.saveCategory(category);
		model.clear();
		
		return "redirect:/catalogs/categories";
	}
	
	@ModelAttribute
	public Category getCategory(){
		return new Category();
	}

}
