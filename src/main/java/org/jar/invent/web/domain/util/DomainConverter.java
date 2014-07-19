package org.jar.invent.web.domain.util;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.jar.invent.core.domain.CoreDomainBean;
import org.jar.invent.web.domain.WebDomainBean;

public class DomainConverter{


	private Map<Class<? extends WebDomainBean>, ? extends WebDomainBean> webKeys = new HashMap<>();
	private Map<Class<? extends CoreDomainBean>, ? extends WebDomainBean> coreKeys  = new HashMap<>();
	
	public <W extends WebDomainBean,C extends CoreDomainBean> void registerClass(W webBean, Class<C> bean){
//		webKeys.put(webBean.getClass(), webBean);
//		coreKeys.put(coreClass, webBean);
		
	}
	
}
