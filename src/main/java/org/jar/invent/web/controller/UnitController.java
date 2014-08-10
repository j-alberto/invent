package org.jar.invent.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.jar.invent.core.domain.UnitEntity.DataType;
import org.jar.invent.core.service.CatalogsService;
import org.jar.invent.web.domain.Unit;
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
@RequestMapping("/catalogs/units")
public class UnitController {

	private CatalogsService catalogsService;
	private Logger log = LoggerFactory.getLogger(getClass());

	private static final int DEFAULT_PAGE_SIZE=10;
	private static final String TEMPLATE_ITEM_UNIT= "catalogs/itemUnit";
	private static final String TEMPLATE_ITEM_UNIT_ADD= "catalogs/itemUnitAdd";
	private static final String REDIR_ITEM_UNIT= "redirect:/catalogs/units";
	
	@Autowired
	public UnitController(CatalogsService catalogsService) {
		this.catalogsService = catalogsService;
	}
	

	@RequestMapping(method=RequestMethod.GET)
	public String getUnits(Model model
								,@RequestParam(value="searchText",required=false,defaultValue="") String searchText
								,@PageableDefault(page=0,size=DEFAULT_PAGE_SIZE) Pageable pageRequest){

		model.addAttribute("searchText",searchText);
		Page<Unit> units= catalogsService.getUnits(searchText, pageRequest);
		model.addAttribute("itemUnits", units);
		
		return TEMPLATE_ITEM_UNIT;
	}

	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String addCategories(final Model model){
		
		return TEMPLATE_ITEM_UNIT_ADD;
	}

	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String addCategories(@Valid final Unit unit, final BindingResult bindResults, final ModelMap model
			,final RedirectAttributes redirectAttributes){
		
		log.info("adding unit: "+unit);
		if(bindResults.hasErrors()){
			return TEMPLATE_ITEM_UNIT_ADD;
		}
		
		catalogsService.saveUnit(unit);
		model.clear();

		redirectAttributes.addFlashAttribute("eventDone", "added");
		
		return REDIR_ITEM_UNIT;
	}


	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public String editUnit(final Model model, @PathVariable final int id
		,final RedirectAttributes redirectAttributes){

		Unit unit = catalogsService.findUnit(id);
		if(null == unit){
			redirectAttributes.addFlashAttribute("eventDone", "idNotFound");
			return REDIR_ITEM_UNIT;
		}
		model.addAttribute("unit", unit);
		model.addAttribute("edit", true);
		
		return TEMPLATE_ITEM_UNIT_ADD;
	}

	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public String editUnit(@Valid final Unit unit, final BindingResult bindResults
			,final ModelMap model
			,final RedirectAttributes redirectAttributes){

		model.addAttribute("edit", true);
		log.info("updating category: "+unit);
		
		if(bindResults.hasErrors()){
			return TEMPLATE_ITEM_UNIT_ADD;
		}
		
		catalogsService.saveUnit(unit);
		redirectAttributes.addFlashAttribute("eventDone", "updated");
		
		return REDIR_ITEM_UNIT;
	}
	
	@ModelAttribute
	public Unit getUnit(){
		return new Unit();
	}
	
	@ModelAttribute("unitDataTypes")
	public List<DataType> getUnitDataTypes(){
		return catalogsService.getUnitDataTypes();
	}
}
