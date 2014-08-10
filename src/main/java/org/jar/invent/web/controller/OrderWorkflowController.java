package org.jar.invent.web.controller;

import javax.validation.Valid;

import org.jar.invent.core.service.CatalogsService;
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
@RequestMapping("/catalogs/workflows")
public class OrderWorkflowController {

	private CatalogsService catalogsService;
	private Logger log = LoggerFactory.getLogger(getClass());

	private static final int DEFAULT_PAGE_SIZE=10;
	private static final String TEMPLATE_ORDER_WFLOW= "catalogs/orderWorkflow";
	private static final String TEMPLATE_ORDER_WFLOW_ADD= "catalogs/orderWorkflowAdd";
	private static final String REDIR_ORDER_WFLOW= "redirect:/catalogs/workflows";
	
	@Autowired
	public OrderWorkflowController(CatalogsService catalogsService) {
		this.catalogsService = catalogsService;
	}
	

	@RequestMapping(method=RequestMethod.GET)
	public String getOrderWorkflows(Model model
								,@RequestParam(value="searchText",required=false,defaultValue="") String searchText
								,@PageableDefault(page=0,size=DEFAULT_PAGE_SIZE) Pageable pageRequest){
		
			
		model.addAttribute("searchText",searchText);
		Page<OrderWorkflow> wflows= catalogsService.getOrderWorkflows(searchText, pageRequest);
		model.addAttribute("orderWorkflows", wflows);
		
		return TEMPLATE_ORDER_WFLOW;
	}

	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String addOrderWorkflow(final Model model){
		return TEMPLATE_ORDER_WFLOW_ADD;
	}

	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String addOrderWorkflow(@Valid final OrderWorkflow orderWflow, final BindingResult bindResults, final ModelMap model
			,final RedirectAttributes redirectAttributes){
		
		log.info("adding orderWorkflow: "+orderWflow);
		if(bindResults.hasErrors()){
			return TEMPLATE_ORDER_WFLOW_ADD;
		}
		
		catalogsService.saveOrderWorkflow(orderWflow);
		model.clear();

		redirectAttributes.addFlashAttribute("eventDone","added");
		
		return REDIR_ORDER_WFLOW;
	}


	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public String editOrderWorkflow(final Model model, @PathVariable final short id
		,final RedirectAttributes redirectAttributes){

		OrderWorkflow orderWflow = catalogsService.findOrderWorkflow(id);
		if(orderWflow==null){
			redirectAttributes.addFlashAttribute("eventDone","idNotFound");
			return REDIR_ORDER_WFLOW;
		}
		model.addAttribute("orderWorkflow", orderWflow);
		model.addAttribute("edit", true);
		
		return TEMPLATE_ORDER_WFLOW_ADD;
	}

	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public String editOrderWorkflow(@Valid final OrderWorkflow orderWflow, final BindingResult bindResults
			,final ModelMap model
			,final RedirectAttributes redirectAttributes){

		model.addAttribute("edit", true);
		log.info("updating orderWorkflow: "+orderWflow);
		
		if(bindResults.hasErrors()){
			return TEMPLATE_ORDER_WFLOW_ADD;
		}
		
		catalogsService.saveOrderWorkflow(orderWflow);
		redirectAttributes.addFlashAttribute("eventDone","updated");
		
		return REDIR_ORDER_WFLOW;
	}
	
	@ModelAttribute
	public OrderWorkflow getOrderWorkflow(){
		return new OrderWorkflow();
	}

}
