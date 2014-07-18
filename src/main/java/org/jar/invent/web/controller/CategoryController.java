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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/catalogs/categories")
public class CategoryController {

	private CategoryService categoryService;
	private Logger log = LoggerFactory.getLogger(getClass());
	private static final int DEFAULT_PAGE_SIZE=10;
	
	@Autowired
	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String getCategories(Model model,
								@PageableDefault(page=0,size=DEFAULT_PAGE_SIZE) Pageable pageRequest){
		
		Page<Category> cats= categoryService.getCategories(pageRequest);
		model.addAttribute("itemCategories", cats);
		return "general/itemCategory";
	}

	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public String getCategory(final Model model, @PathVariable final short id
		,final RedirectAttributes redirectAttributes){

		Category cat = categoryService.findCategory(id);
		if(cat==null){
			redirectAttributes.addFlashAttribute("idNotFound", true);
			return "redirect:/catalogs/categories";
		}
		model.addAttribute("category", cat);
		model.addAttribute("edit", true);
		return "general/itemCategoryAdd";
	}


	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String addCategories(final Model model){
		
		for(String s : model.asMap().keySet()){
			log.info(s+">>"+model.asMap().get(s));
		}
		
		
		return "general/itemCategoryAdd";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String addCategories(@Valid final Category category, final BindingResult bindResults, final ModelMap model
			,final RedirectAttributes redirectAttributes){

		if(bindResults.hasErrors()){
			return "general/itemCategoryAdd";
		}
		
		categoryService.saveCategory(category);
		model.clear();

		redirectAttributes.addFlashAttribute("added", true);
		
		return "redirect:/catalogs/categories";
	}

	
	

	@RequestMapping(value="/raw",method=RequestMethod.GET)
	public String addCategoriesRaw(Model model,
		@PageableDefault(page=0,size=DEFAULT_PAGE_SIZE) Pageable pageRequest){
		
		Page<Category> cats= categoryService.getCategories(pageRequest);
		model.addAttribute("itemCategories", cats);
		
		return "general/itemCategory :: table1";
	}

	
	@ModelAttribute
	public Category getCategory(){
		return new Category();
	}

}
