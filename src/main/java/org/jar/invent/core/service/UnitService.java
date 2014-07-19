package org.jar.invent.core.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jar.invent.core.domain.UnitEntity;
import org.jar.invent.core.domain.UnitEntity.DataType;
import org.jar.invent.core.domain.dao.UnitDAO;
import org.jar.invent.web.domain.Unit;
import org.jar.invent.web.domain.util.BeanParser;
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
	
	public Page<Unit> getUnits(String text, Pageable pageRequest){
		if(pageRequest.getPageSize()>MAX_PAGE_SIZE){
			//TODO: let consumer know about change in page's size
			pageRequest = new PageRequest(pageRequest.getPageNumber(), MAX_PAGE_SIZE);
		}

		Page<UnitEntity> resultPage;
		
		if(text.isEmpty()){
			resultPage = unitDAO.findAll(pageRequest);
		}else{
			resultPage = unitDAO.findByNameOrDescriptionContainingAllIgnoreCase(text, text, pageRequest);
		}
		
		
		List<Unit> categoriesWeb = asWebDomainList(resultPage.getContent());
		
		Page<Unit> categoriesPage = new PageImpl<Unit>(categoriesWeb, pageRequest, resultPage.getTotalElements());

		return categoriesPage;
	}
	
	public Unit saveUnit(Unit cat){
		UnitEntity c = unitDAO.save(BeanParser.toEntityBean(cat));
		return BeanParser.toWebBean(c);
	}
	
	public Unit findUnit(int id){
		UnitEntity c = unitDAO.findOne(id);
		return c==null ? null : BeanParser.toWebBean(c);
	}
	
	public List<DataType> getUnitDataTypes(){
		return Arrays.asList(DataType.values());
	}
	
	/**
	 * Transforms a Core-object List into a Web-object list
	 * @param categories
	 * @return
	 */
	private List<Unit> asWebDomainList(List<UnitEntity> categories){
		List<Unit> webItemUnits = new ArrayList<Unit>();
		
		for(UnitEntity c : categories){
			webItemUnits.add(BeanParser.toWebBean(c));
		}
		return webItemUnits;
	}
}
