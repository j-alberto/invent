package generic;

import java.util.HashMap;
import java.util.Map;

import org.jar.invent.core.domain.CoreDomainBean;
import org.jar.invent.web.domain.WebDomainBean;

public class BeanFactory {

	private static BeanFactory instance = new BeanFactory();
	private static Map<Class<? extends WebBean>,WebBean> classes = new HashMap<>();
	
	private BeanFactory(){}
	
	public  static synchronized BeanFactory instance(){
		if(null == instance){
			instance = new BeanFactory();
		}
		return instance;
	}
	
	public boolean addDomain(WebBean bean){
		
		classes.put(bean.getClass(), bean);
		
		return true;
	}
	
	public boolean convertBean(WebBean bean){
		
		classes.get(bean.getClass()).asCoreBean(bean);
		
		return true;
	}
	
	
	public static void main(String... args){
		BeanFactory.instance().addDomain(new PersonWeb());
		
		
		PersonEntity entity = new PersonEntity();
		
		
	}

}
