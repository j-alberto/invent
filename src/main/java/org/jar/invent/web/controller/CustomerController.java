package org.jar.invent.web.controller;

import javax.validation.Valid;

import org.jar.invent.business.ItemRegisterBR;
import org.jar.invent.core.service.CatalogsService;
import org.jar.invent.core.service.CustomerService;
import org.jar.invent.core.service.InventoryService;
import org.jar.invent.web.domain.Category;
import org.jar.invent.web.domain.Customer;
import org.jar.invent.web.domain.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/customers")
public class CustomerController {

	private CustomerService customerService;
	private CatalogsService catalogsService;
	private Logger log = LoggerFactory.getLogger(getClass());

	private static final String TEMPLATE_CUSTOMER= "customer/customers";
	private static final String TEMPLATE_CUSTOMER_ADD= "customer/customerAdd";
	private static final String TEMPLATE_CUSTOMER_IMAGE= "customer/custQuickView";
	private static final String REDIR_CUSTOMER= "redirect:/customers";
	
	@Autowired
	public CustomerController(CustomerService customerService, CatalogsService catalogsService) {
		this.customerService = customerService;
		this.catalogsService = catalogsService;
	}
	

	@RequestMapping(method=RequestMethod.GET)
	public String getCustomers(Model model
								,@RequestParam(value="searchText",required=false,defaultValue="") String searchText
								,@PageableDefault(page=0,size=ItemRegisterBR.MAX_PAGE_SIZE) Pageable pageRequest){
		
		model.addAttribute("searchText",searchText);
		Page<Customer> customers= customerService.getCustomers(searchText, pageRequest);
		model.addAttribute("customers", customers);
		
		return TEMPLATE_CUSTOMER;
	}

	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String addCustomers(final Model model){
		addComboLists(model);
		
		return TEMPLATE_CUSTOMER_ADD;
	}

	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String addCustomers(@Valid final Customer customer, final BindingResult bindResults, final Model model
			,final RedirectAttributes redirectAttributes){
		
		log.info("adding customer: "+customer);

		if(bindResults.hasErrors()){
			addComboLists(model);

			return TEMPLATE_CUSTOMER_ADD;
		}
		
		customerService.registerNewCustomer(customer);
		model.asMap().clear();

		redirectAttributes.addFlashAttribute("eventDone","added");
		
		return REDIR_CUSTOMER;
	}


	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public String editCustomers(final Model model, @PathVariable final int id
		,final RedirectAttributes redirectAttributes){

		Customer customer = customerService.getCustomer(id);

		if(null==customer){
			redirectAttributes.addFlashAttribute("eventDone","idNotFound");
			return REDIR_CUSTOMER;
		}
		addComboLists(model);
		model.addAttribute("customer", customer);
		model.addAttribute("edit", true);
		
		return TEMPLATE_CUSTOMER_ADD;
	}

	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public String editCustomer(@Valid final Customer customer, final BindingResult bindResults
			,final Model model
			,final RedirectAttributes redirectAttributes){

		model.addAttribute("edit", true);
		log.info("updating customer: "+customer);
		
		if(bindResults.hasErrors()){
			addComboLists(model);
			return TEMPLATE_CUSTOMER_ADD;
		}		
		
		customerService.modifyCustomer(customer);
		model.asMap().clear();

		redirectAttributes.addFlashAttribute("eventDone","updated");
		
		return REDIR_CUSTOMER;
	}
	
	@RequestMapping(value="/detail/{id}", method=RequestMethod.GET)
	public String showDetailModal(@PathVariable final int id, final ModelMap model){
		Customer customer = customerService.getCustomer(id);
		model.addAttribute("customer", customer);
		return TEMPLATE_CUSTOMER_IMAGE;
	}

	@ModelAttribute
	public Customer getCustomer(){
		return new Customer();
	}

	/**
	 * Adds lists to given model used to fill comboBox-like input forms for customers 
	 */
	private void addComboLists(Model model){
		model.addAttribute("customerTypes", catalogsService.getCustomerTypes(null, null));
		model.addAttribute("customerStatuses", catalogsService.getCustomerStatuses(null, null));
	}
	
//	@ModelAttribute("itemCategories")
//	public Page<Category> getItemCategories(){
//		return catalogsService.getCategories(null, new PageRequest(0, 1000));
//	}
	
}
