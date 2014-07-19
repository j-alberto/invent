package org.jar.invent.core.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jar.invent.core.domain.EnumStatusGeneral;
import org.jar.invent.core.domain.UnitEntity;
import org.jar.invent.core.domain.dao.UnitDAO;
import org.jar.invent.web.domain.Unit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UnitService {

	private UnitDAO unitDAO;
	
	private Logger log = LoggerFactory.getLogger(UnitService.class);
	
	private static final int MAX_PAGE_SIZE= 100;
	
	@Autowired
	public UnitService(UnitDAO unitDAO) {
		this.unitDAO = unitDAO;
	}


	public List<Unit> getAllUnits(){
		return asWebDomainList((List<UnitEntity>)unitDAO.findAll());
	}
	
	public Page<Unit> getCategories(String name, Pageable pageRequest){
		if(pageRequest.getPageSize()>MAX_PAGE_SIZE){
			//TODO: let consumer know about change in page's size
			pageRequest = new PageRequest(pageRequest.getPageNumber(), MAX_PAGE_SIZE);
		}

		Page<UnitEntity> resultPage;
		
		if(name.isEmpty()){
			resultPage = unitDAO.findAll(pageRequest);
		}else{
			resultPage = unitDAO.findByNameOrDescriptionContainingIgnoreCase(name, pageRequest);
		}
		
		
		List<Unit> categoriesWeb = asWebDomainList(resultPage.getContent());
		
		Page<Unit> categoriesPage = new PageImpl<Unit>(categoriesWeb, pageRequest, resultPage.getTotalElements());

		return categoriesPage;
	}
	
	public Unit saveUnit(Unit cat){
		//UnitEntity c = unitDAO.save(cat.toEntityBean());
		return null;//Unit.parseViewBean(c);
	}
	
	public Unit findUnit(int id){
		UnitEntity c = unitDAO.findOne(id);
		return null;//c==null ? null : Unit.parseViewBean(c);
	}
	
	public List<EnumStatusGeneral> getUnitStatuses(){
		return Arrays.asList(EnumStatusGeneral.values());
	}
	
	/**
	 * Transforms a Core-object List into a Web-object list
	 * @param categories
	 * @return
	 */
	private List<Unit> asWebDomainList(List<UnitEntity> categories){
		List<Unit> webCategories = new ArrayList<Unit>();
		
		for(UnitEntity c : categories){
			//webCategories.add(new Unit(c.getId(), c.getDescription(), c.getStatus()));
		}
		return webCategories;
	}
}
