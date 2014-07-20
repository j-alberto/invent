package org.jar.invent.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.jar.invent.core.domain.EnumStatusGeneral;
import org.jar.invent.core.service.CatalogsService;
import org.jar.invent.web.domain.OrderStatus;
import org.jar.invent.web.domain.OrderWorkflow;
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
@RequestMapping("/catalogs/orderStatuses")
public class OrderStatusController {

	private CatalogsService catalogsService;
	private Logger log = LoggerFactory.getLogger(getClass());

	private static final int DEFAULT_PAGE_SIZE=10;
	private static final String TEMPLATE_ORDER_STATUS= "catalogs/orderStatus";
	private static final String TEMPLATE_ORDER_STATUS_ADD= "catalogs/orderStatusAdd";
	private static final String REDIR_ORDER_STATUS= "redirect:/catalogs/orderStatuses";
	private List<OrderWorkflow> orderWorkflows;
	
	@Autowired
	public OrderStatusController(CatalogsService catalogsService) {
		this.catalogsService = catalogsService;
	}
	

	@RequestMapping(method=RequestMethod.GET)
	public String getOrderStatuses(Model model
								,@RequestParam(value="searchText",required=false,defaultValue="") String searchText
								,@PageableDefault(page=0,size=DEFAULT_PAGE_SIZE) Pageable pageRequest){
		
			
		model.addAttribute("searchText",searchText);
		Page<OrderStatus> statuses= catalogsService.getOrderStatuses(searchText, pageRequest);
		model.addAttribute("orderStatuses", statuses);
		
		return TEMPLATE_ORDER_STATUS;
	}

	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String addOrderStatus(final Model model){
		return TEMPLATE_ORDER_STATUS_ADD;
	}

	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String addOrderStatus(@Valid final OrderStatus orderStatus, final BindingResult bindResults, final ModelMap model
			,final RedirectAttributes redirectAttributes){
		
		log.info("adding orderStatus: "+orderStatus);
		if(bindResults.hasErrors()){
			return TEMPLATE_ORDER_STATUS_ADD;
		}
		
		catalogsService.saveOrderStatus(orderStatus);
		model.clear();

		redirectAttributes.addFlashAttribute("eventDone","added");
		
		return REDIR_ORDER_STATUS;
	}


	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public String editOrderStatus(final Model model, @PathVariable final short id
		,final RedirectAttributes redirectAttributes){

		OrderStatus orderStatus = catalogsService.findOrderStatus(id);
		if(orderStatus==null){
			redirectAttributes.addFlashAttribute("eventDone","idNotFound");
			return REDIR_ORDER_STATUS;
		}
		model.addAttribute("orderStatus", orderStatus);
		model.addAttribute("edit", true);
		
		return TEMPLATE_ORDER_STATUS_ADD;
	}

	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public String editOrderStatus(@Valid final OrderStatus orderStatus, final BindingResult bindResults
			,final ModelMap model
			,final RedirectAttributes redirectAttributes){

		model.addAttribute("edit", true);
		log.info("updating orderStatus: "+orderStatus);
		
		if(bindResults.hasErrors()){
			return TEMPLATE_ORDER_STATUS_ADD;
		}
		
		catalogsService.saveOrderStatus(orderStatus);
		redirectAttributes.addFlashAttribute("eventDone","updated");
		
		return REDIR_ORDER_STATUS;
	}
	
	@ModelAttribute
	public OrderStatus getOrderStatus(){
		return new OrderStatus();
	}
	@ModelAttribute("orderWorkflows")
	public List<OrderWorkflow> getOrderWorkflows(){
		return catalogsService.getAllOrderWorkflows();
	}
	@ModelAttribute("orderStatusStatuses")
	public List<EnumStatusGeneral> getCustomerStatusStatuses(){
		return catalogsService.getOrderStatusStatuses();
	}

}
