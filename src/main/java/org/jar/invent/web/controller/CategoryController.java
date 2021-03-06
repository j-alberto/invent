package org.jar.invent.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.jar.invent.core.domain.EnumStatusGeneral;
import org.jar.invent.core.service.CatalogsService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/catalogs/categories")
public class CategoryController {

	private CatalogsService catalogService;
	private Logger log = LoggerFactory.getLogger(getClass());

	private static final int DEFAULT_PAGE_SIZE=10;
	private static final String TEMPLATE_ITEM_CAT= "catalogs/itemCategory";
	private static final String TEMPLATE_ITEM_CAT_ADD= "catalogs/itemCategoryAdd";
	private static final String REDIR_ITEM_CAT= "redirect:/catalogs/categories";
	
	@Autowired
	public CategoryController(CatalogsService catalogService) {
		this.catalogService = catalogService;
	}
	

	@RequestMapping(method=RequestMethod.GET)
	public String getCategories(Model model
								,@RequestParam(value="searchText",required=false,defaultValue="") String searchText
								,@PageableDefault(page=0,size=DEFAULT_PAGE_SIZE) Pageable pageRequest){
		
			
		model.addAttribute("searchText",searchText);
		Page<Category> cats= catalogService.getCategories(searchText, pageRequest);
		model.addAttribute("itemCategories", cats);
		
		return TEMPLATE_ITEM_CAT;
	}

	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String addCategories(final Model model){
		
		return TEMPLATE_ITEM_CAT_ADD;
	}

	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String addCategories(@Valid final Category category, final BindingResult bindResults, final ModelMap model
			,final RedirectAttributes redirectAttributes){
		
		log.info("adding category: "+category);
		if(bindResults.hasErrors()){
			return TEMPLATE_ITEM_CAT_ADD;
		}
		
		catalogService.saveCategory(category);
		model.clear();

		redirectAttributes.addFlashAttribute("eventDone","added");
		
		return REDIR_ITEM_CAT;
	}


	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public String editCategory(final Model model, @PathVariable final short id
		,final RedirectAttributes redirectAttributes){

		Category cat = catalogService.findCategory(id);
		if(cat==null){
			redirectAttributes.addFlashAttribute("eventDone","idNotFound");
			return REDIR_ITEM_CAT;
		}
		model.addAttribute("category", cat);
		model.addAttribute("edit", true);
		
		return TEMPLATE_ITEM_CAT_ADD;
	}

	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public String editCategory(@Valid final Category category, final BindingResult bindResults
			,final ModelMap model
			,final RedirectAttributes redirectAttributes){

		model.addAttribute("edit", true);
		log.info("updating category: "+category);
		
		if(bindResults.hasErrors()){
			return TEMPLATE_ITEM_CAT_ADD;
		}
		
		catalogService.saveCategory(category);
		redirectAttributes.addFlashAttribute("eventDone","updated");
		
		return REDIR_ITEM_CAT;
	}
	
	@ModelAttribute
	public Category getCategory(){
		return new Category();
	}
	
	@ModelAttribute("categoryTypes")
	public List<EnumStatusGeneral> getCategoryStatuses(){
		return catalogService.getCategoryStatuses();
	}
}
