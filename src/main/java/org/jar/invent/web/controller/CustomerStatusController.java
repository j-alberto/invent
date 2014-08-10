package org.jar.invent.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.jar.invent.core.domain.EnumStatusGeneral;
import org.jar.invent.core.service.CatalogsService;
import org.jar.invent.web.domain.CustomerStatus;
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
@RequestMapping("/catalogs/custStatuses")
public class CustomerStatusController {

	private CatalogsService catalogsService;
	private Logger log = LoggerFactory.getLogger(getClass());

	private static final int DEFAULT_PAGE_SIZE=10;
	private static final String TEMPLATE_CUST_STATUS= "catalogs/customerStatus";
	private static final String TEMPLATE_CUST_STATUS_ADD= "catalogs/customerStatusAdd";
	private static final String REDIR_CUST_STATUS= "redirect:/catalogs/custStatuses";
	
	@Autowired
	public CustomerStatusController(CatalogsService catalogsService) {
		this.catalogsService = catalogsService;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String getCustomerStatuses(Model model
								,@RequestParam(value="searchText",required=false,defaultValue="") String searchText
								,@PageableDefault(page=0,size=DEFAULT_PAGE_SIZE) Pageable pageRequest){
		
			
		model.addAttribute("searchText",searchText);
		Page<CustomerStatus> custStatuses= catalogsService.getCustomerStatuses(searchText, pageRequest);
		model.addAttribute("customerStatuses", custStatuses);
		
		return TEMPLATE_CUST_STATUS;
	}

	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String addCustomerStatuses(final Model model){
		
		return TEMPLATE_CUST_STATUS_ADD;
	}

	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String addCustomerStatus(@Valid final CustomerStatus customerStatus, final BindingResult bindResults, final ModelMap model
			,final RedirectAttributes redirectAttributes){
		
		log.info("adding customerStatus: "+customerStatus);
		if(bindResults.hasErrors()){
			return TEMPLATE_CUST_STATUS_ADD;
		}
		
		catalogsService.saveCustomerStatus(customerStatus);
		model.clear();

		redirectAttributes.addFlashAttribute("eventDone","added");
		
		return REDIR_CUST_STATUS;
	}


	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public String editCustomerType(final Model model, @PathVariable final short id
		,final RedirectAttributes redirectAttributes){

		CustomerStatus customerStatus = catalogsService.findCustomerStatus(id);
		if(customerStatus==null){
			redirectAttributes.addFlashAttribute("eventDone","idNotFound");
			return REDIR_CUST_STATUS;
		}
		model.addAttribute("customerStatus", customerStatus);
		model.addAttribute("edit", true);
		
		return TEMPLATE_CUST_STATUS_ADD;
	}

	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public String editCustomerStatus(@Valid final CustomerStatus customerStatus, final BindingResult bindResults
			,final ModelMap model
			,final RedirectAttributes redirectAttributes){

		model.addAttribute("edit", true);
		log.info("updating customerStatus: "+customerStatus);
		
		if(bindResults.hasErrors()){
			return TEMPLATE_CUST_STATUS_ADD;
		}
		
		catalogsService.saveCustomerStatus(customerStatus);
		redirectAttributes.addFlashAttribute("eventDone","updated");
		
		return REDIR_CUST_STATUS;
	}
	
	@ModelAttribute
	public CustomerStatus getCustomerStatus(){
		return new CustomerStatus();
	}
	
	@ModelAttribute("customerStatusStatuses")
	public List<EnumStatusGeneral> getCustomerStatusStatuses(){
		return catalogsService.getCustomerStatusStatuses();
	}

}
