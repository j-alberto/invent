package generic;

import java.util.HashMap;
import java.util.Map;
/**
 * Test class for implementing a baen parser factory.
 * Each non-core bean must implement conversions and statically register in factory.
 * @author zero
 *
 */
public class BeanFactory {

	private static BeanFactory instance = new BeanFactory();
	private static Map<Class<? extends WebBean>,WebBean> classes = new HashMap<>();
	private static Map<Class<? extends CoreBean>,WebBean> keyClasses = new HashMap<>();
	
	private BeanFactory(){}
	
	public  static synchronized BeanFactory instance(){
		if(null == instance){
			instance = new BeanFactory();
		}
		return instance;
	}
	
	public <W extends WebBean,C extends CoreBean>void addDomain(W bean, Class<C> clazz){
		
		classes.put(bean.getClass(), bean);
		keyClasses.put(clazz,bean);

	}

	
	public static<C extends CoreBean,W extends WebBean<W,C>>C toCoreBean(W bean){
		return (C)classes.get(bean.getClass()).asCoreBean(bean);
	}

	public static<Y extends WebBean,C extends CoreBean,W extends WebBean<W,C>>W toWebBean(C bean){
		return (W)keyClasses.get(bean.getClass()).asWebBean(bean);
	}
	
	public static void main(String... args){
		BeanFactory.instance().addDomain(new PersonWeb(),PersonEntity.class);

		PersonWeb bean = new PersonWeb("name","lname");
		PersonEntity p = BeanFactory.toCoreBean(bean); 
		
		System.out.println(p);
		
		PersonEntity bean2 = new PersonEntity("name2","lname2");
		PersonWeb p2 = BeanFactory.toWebBean(bean2);
		
		System.out.println(p2);
	}

}
