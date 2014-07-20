package org.jar.invent.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.jar.invent.core.domain.EnumStatusGeneral;
import org.jar.invent.core.service.CatalogsService;
import org.jar.invent.web.domain.OrderType;
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
@RequestMapping("/catalogs/orderTypes")
public class OrderTypeController {

	private CatalogsService catalogsService;
	private Logger log = LoggerFactory.getLogger(getClass());

	private static final int DEFAULT_PAGE_SIZE=10;
	private static final String TEMPLATE_ORDER_TYPE= "catalogs/orderType";
	private static final String TEMPLATE_ORDER_TYPE_ADD= "catalogs/orderTypeAdd";
	private static final String REDIR_ORDER_TYPE= "redirect:/catalogs/orderTypes";
	
	@Autowired
	public OrderTypeController(CatalogsService catalogsService) {
		this.catalogsService = catalogsService;
	}
	

	@RequestMapping(method=RequestMethod.GET)
	public String getOrderTypes(Model model
								,@RequestParam(value="searchText",required=false,defaultValue="") String searchText
								,@PageableDefault(page=0,size=DEFAULT_PAGE_SIZE) Pageable pageRequest){
		
			
		model.addAttribute("searchText",searchText);
		Page<OrderType> orderTypes= catalogsService.getOrderTypes(searchText, pageRequest);
		model.addAttribute("orderTypes", orderTypes);
		
		return TEMPLATE_ORDER_TYPE;
	}

	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String addOrderType(final Model model){
		return TEMPLATE_ORDER_TYPE_ADD;
	}

	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String addOrderType(@Valid final OrderType orderType, final BindingResult bindResults, final ModelMap model
			,final RedirectAttributes redirectAttributes){
		
		log.info("adding orderType: "+orderType);
		if(bindResults.hasErrors()){
			return TEMPLATE_ORDER_TYPE_ADD;
		}
		
		catalogsService.saveOrderType(orderType);
		model.clear();

		redirectAttributes.addFlashAttribute("eventDone","added");
		
		return REDIR_ORDER_TYPE;
	}


	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public String editOrderType(final Model model, @PathVariable final int id
		,final RedirectAttributes redirectAttributes){

		OrderType orderType = catalogsService.findOrderType(id);
		if(orderType==null){
			redirectAttributes.addFlashAttribute("eventDone","idNotFound");
			return REDIR_ORDER_TYPE;
		}
		model.addAttribute("orderType", orderType);
		model.addAttribute("edit", true);
		
		return TEMPLATE_ORDER_TYPE_ADD;
	}

	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public String editOrderType(@Valid final OrderType orderType, final BindingResult bindResults
			,final ModelMap model
			,final RedirectAttributes redirectAttributes){

		model.addAttribute("edit", true);
		log.info("updating orderType: "+orderType);
		
		if(bindResults.hasErrors()){
			return TEMPLATE_ORDER_TYPE_ADD;
		}
		
		catalogsService.saveOrderType(orderType);
		redirectAttributes.addFlashAttribute("eventDone","updated");
		
		return REDIR_ORDER_TYPE;
	}
	
	@ModelAttribute
	public OrderType getOrderType(){
		return new OrderType();
	}
	@ModelAttribute("orderTypeStatuses")
	public List<EnumStatusGeneral> getCustomerStatusTypes(){
		return catalogsService.getOrderTypeStatuses();
	}

}
