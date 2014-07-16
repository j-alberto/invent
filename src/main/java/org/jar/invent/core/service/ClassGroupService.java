package org.jar.invent.core.service;

import java.util.List;

import org.jar.invent.web.domain.ClassGroupBean;

public interface ClassGroupService {

	public List<ClassGroupBean> getClassGroups();
	public ClassGroupBean saveGroup(ClassGroupBean classGroup);
}
