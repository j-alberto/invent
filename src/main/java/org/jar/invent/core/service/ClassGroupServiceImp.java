package org.jar.invent.core.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jar.invent.core.domain.ClassGroup;
import org.jar.invent.core.domain.dao.ClassGroupRepository;
import org.jar.invent.web.domain.ClassGroupBean;
import org.springframework.beans.factory.annotation.Autowired;

public class ClassGroupServiceImp implements ClassGroupService {

	@Autowired
	private ClassGroupRepository classGroupDAO;
	
	
	@Override
	public List<ClassGroupBean> getClassGroups() {
		Iterator<ClassGroup> classGroups = classGroupDAO.findAll().iterator();
		List<ClassGroupBean> classGroupBeans = new ArrayList<ClassGroupBean>();
		
		while(classGroups.hasNext()){
			classGroupBeans.add(ClassGroupBean.parseDomain(classGroups.next()));
		}
		
		return classGroupBeans;
	}

	@Override
	public ClassGroupBean saveGroup(ClassGroupBean classGroupBean) {
		
		classGroupDAO.save( ClassGroupBean.toDomain(classGroupBean) );
		return classGroupBean;
	}

}
