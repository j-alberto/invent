package org.jar.invent.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.jar.invent.core.domain.EnumStatusGeneral;
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
		return "catalogs/itemCategory";
	}

	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String addCategories(final Model model){
		
		return "catalogs/itemCategoryAdd";
	}

	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String addCategories(@Valid final Category category, final BindingResult bindResults, final ModelMap model
			,final RedirectAttributes redirectAttributes){

		log.info("update>>>"+category);
		
		if(bindResults.hasErrors()){
			return "catalogs/itemCategoryAdd";
		}
		
		categoryService.saveCategory(category);
		model.clear();

		redirectAttributes.addFlashAttribute("added", true);
		
		return "redirect:/catalogs/categories";
	}


	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public String editCategory(final Model model, @PathVariable final short id
		,final RedirectAttributes redirectAttributes){

		Category cat = categoryService.findCategory(id);
		if(cat==null){
			redirectAttributes.addFlashAttribute("idNotFound", true);
			return "redirect:/catalogs/categories";
		}
		model.addAttribute("category", cat);
		model.addAttribute("edit", true);
		
		return "catalogs/itemCategoryAdd";
	}

	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public String editCategory(@Valid final Category category, final BindingResult bindResults
			,final ModelMap model
			,final RedirectAttributes redirectAttributes){

		model.addAttribute("edit", true);
		
		if(bindResults.hasErrors()){
			return "catalogs/itemCategoryAdd";
		}
		
		categoryService.saveCategory(category);
		redirectAttributes.addFlashAttribute("updated", true);
		
		return "redirect:/catalogs/categories";
	}
	
	@ModelAttribute
	public Category getCategory(){
		return new Category();
	}
	
	@ModelAttribute("categoryTypes")
	public List<EnumStatusGeneral> getCategoryStatuses(){
		return categoryService.getCategoryStatuses();
	}
}
