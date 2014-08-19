package org.jar.invent.web.domain.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.jar.invent.core.service.CatalogsService;
import org.jar.invent.web.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

/**
 * Defines format applied to item beans from view(id) to controller (object) and vice-versa
 * @author jalberto
 *
 */
public class CategoryFormatter implements Formatter<Category>{

	@Autowired
	private	CatalogsService catalogsService;
	

	void setCatalogsService(CatalogsService catalogsService) {
		this.catalogsService = catalogsService;
	}
	
	@Override
	public String print(Category category, Locale locale) {
		return category == null ? "" : String.valueOf( category.getId() );
	}

	@Override
	public Category parse(String item, Locale locale)	throws ParseException {
		Short id = Short.valueOf(item);
		return catalogsService.findCategory(id);
	}
	
	
}
