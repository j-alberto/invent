package org.pke.liberalbus.service;

import java.util.List;

import org.pke.liberalbus.bean.ClassGroupBean;

public interface ClassGroupService {

	public List<ClassGroupBean> getClassGroups();
	public ClassGroupBean saveGroup(ClassGroupBean classGroup);
}
