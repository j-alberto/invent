package org.jar.invent.web.controller;

import javax.validation.Valid;

import org.jar.invent.core.service.CustomerTypeService;
import org.jar.invent.web.domain.CustomerType;
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
@RequestMapping("/catalogs/custTypes")
public class CustomerTypeController {

	private CustomerTypeService customerTypeService;
	private Logger log = LoggerFactory.getLogger(getClass());

	private static final int DEFAULT_PAGE_SIZE=10;
	private static final String TEMPLATE_CUST_TYPE= "catalogs/customerType";
	private static final String TEMPLATE_CUST_TYPE_ADD= "catalogs/customerTypeAdd";
	private static final String REDIR_CUST_TYPE= "redirect:/catalogs/custTypes";
	
	@Autowired
	public CustomerTypeController(CustomerTypeService customerTypeService) {
		this.customerTypeService = customerTypeService;
	}
	

	@RequestMapping(method=RequestMethod.GET)
	public String getCustomerTypes(Model model
								,@RequestParam(value="searchText",required=false,defaultValue="") String searchText
								,@PageableDefault(page=0,size=DEFAULT_PAGE_SIZE) Pageable pageRequest){
		
			
		model.addAttribute("searchText",searchText);
		Page<CustomerType> cats= customerTypeService.getCustomerTypes(searchText, pageRequest);
		model.addAttribute("customerTypes", cats);
		
		return TEMPLATE_CUST_TYPE;
	}

	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String addCustomerType(final Model model){
		
		return TEMPLATE_CUST_TYPE_ADD;
	}

	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String addCustomerType(@Valid final CustomerType customerType, final BindingResult bindResults, final ModelMap model
			,final RedirectAttributes redirectAttributes){
		
		log.info("adding customerType: "+customerType);
		if(bindResults.hasErrors()){
			return TEMPLATE_CUST_TYPE_ADD;
		}
		
		customerTypeService.saveCustomerType(customerType);
		model.clear();

		redirectAttributes.addFlashAttribute("eventDone","added");
		
		return REDIR_CUST_TYPE;
	}


	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public String editCustomerType(final Model model, @PathVariable final short id
		,final RedirectAttributes redirectAttributes){

		CustomerType cat = customerTypeService.findCustomerType(id);
		if(cat==null){
			redirectAttributes.addFlashAttribute("eventDone","idNotFound");
			return REDIR_CUST_TYPE;
		}
		model.addAttribute("customerType", cat);
		model.addAttribute("edit", true);
		
		return TEMPLATE_CUST_TYPE_ADD;
	}

	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public String editCustomerType(@Valid final CustomerType customerType, final BindingResult bindResults
			,final ModelMap model
			,final RedirectAttributes redirectAttributes){

		model.addAttribute("edit", true);
		log.info("updating customerType: "+customerType);
		
		if(bindResults.hasErrors()){
			return TEMPLATE_CUST_TYPE_ADD;
		}
		
		customerTypeService.saveCustomerType(customerType);
		redirectAttributes.addFlashAttribute("eventDone","updated");
		
		return REDIR_CUST_TYPE;
	}
	
	@ModelAttribute
	public CustomerType getCustomerType(){
		return new CustomerType();
	}

}
