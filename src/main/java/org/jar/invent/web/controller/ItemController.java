package org.jar.invent.web.controller;

import javax.validation.Valid;

import org.jar.invent.business.ItemRegisterBR;
import org.jar.invent.core.service.CatalogsService;
import org.jar.invent.core.service.InventoryService;
import org.jar.invent.web.domain.Category;
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
@RequestMapping("/items")
public class ItemController {

	private InventoryService inventoryService;
	private CatalogsService catalogsService;
	private Logger log = LoggerFactory.getLogger(getClass());

	private static final String TEMPLATE_ITEM= "inventory/items";
	private static final String TEMPLATE_ITEM_ADD= "inventory/itemAdd";
	private static final String TEMPLATE_ITEM_IMAGE= "inventory/itemQuickView";
	private static final String REDIR_ITEM= "redirect:/items";
	
	@Autowired
	public ItemController(InventoryService inventoryService, CatalogsService catalogsService) {
		this.inventoryService = inventoryService;
		this.catalogsService = catalogsService;
	}
	

	@RequestMapping(method=RequestMethod.GET)
	public String getItems(Model model
								,@RequestParam(value="searchText",required=false,defaultValue="") String searchText
								,@PageableDefault(page=0,size=ItemRegisterBR.MAX_PAGE_SIZE) Pageable pageRequest){
		
		model.addAttribute("searchText",searchText);
		Page<Item> items= inventoryService.getItems(searchText, pageRequest);
		model.addAttribute("items", items);
		
		return TEMPLATE_ITEM;
	}

	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String addItems(final Model model){

		return TEMPLATE_ITEM_ADD;
	}

	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String addItems(@Valid final Item item, final BindingResult bindResults, final ModelMap model
			,final RedirectAttributes redirectAttributes){
		
		log.info("adding item: "+item);
		if(bindResults.hasErrors()){
			return TEMPLATE_ITEM_ADD;
		}
		
		inventoryService.registerNewItem(item);
		model.clear();

		redirectAttributes.addFlashAttribute("eventDone","added");
		
		return REDIR_ITEM;
	}


	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public String editItem(final Model model, @PathVariable final int id
		,final RedirectAttributes redirectAttributes){

		Item item = inventoryService.getItem(id);
		if(item==null){
			redirectAttributes.addFlashAttribute("eventDone","idNotFound");
			return REDIR_ITEM;
		}
		model.addAttribute("item", item);
		model.addAttribute("edit", true);
		
		return TEMPLATE_ITEM_ADD;
	}

	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public String editCategory(@Valid final Item item, final BindingResult bindResults
			,final ModelMap model
			,final RedirectAttributes redirectAttributes){

		model.addAttribute("edit", true);
		log.info("updating item: "+item);
		
		if(bindResults.hasErrors()){
			return TEMPLATE_ITEM_ADD;
		}
		
		
		MultipartFile image = item.getImage();
		log.info(image.getContentType());
		log.info(image.getName());
		log.info(image.getOriginalFilename());
		log.info("size: "+image.getSize());
		log.info("isEmpty: "+image.isEmpty());



		
		
		inventoryService.modifyItem(item);
		redirectAttributes.addFlashAttribute("eventDone","updated");
		
		return REDIR_ITEM;
	}
	
	@RequestMapping(value="/detail/{id}", method=RequestMethod.GET)
	public String showDeatilModal(@PathVariable final int id, final Model model){
		Item item = inventoryService.getItem(id);
		model.addAttribute("item", item);
		return TEMPLATE_ITEM_IMAGE;
	}

	@ModelAttribute
	public Item getItem(){
		return new Item();
	}
	
	@ModelAttribute("itemCategories")
	public Page<Category> getItemCategories(){
		return catalogsService.getCategories(null, new PageRequest(0, 1000));
	}
	
}
