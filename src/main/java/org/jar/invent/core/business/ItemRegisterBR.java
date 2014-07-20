package org.jar.invent.core.business;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.jar.invent.core.domain.dao.ItemDAO;
import org.jar.invent.web.domain.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.util.DateUtils;

public class ItemRegisterBR {
	private final int BASE = 36;
	private final int BASEX2 = 36*36;
	private final int BASEX3 = 36*36*36;

	@Autowired
	private ItemDAO itemDao;
	@Autowired
	private itemconverter;
	
	
	public Item registerNewItem(Item item){
		item.setCode(generateCode(null));
		itemDao.save(entity)
		return item;
	}
	

	/**
	 * Generates codes for items. Simplistic approach for 8-char codes, must use a better one
	 * @param rnd Random number generator for last part of the code, if null, default generator is used
	 * @return a 8-char code for use in new items 
	 */
	private String generateCode(Random rnd){
		Calendar cal = DateUtils.createNow();
		if(null == rnd)rnd = new Random();

		int dayOfYear = cal.get(Calendar.DAY_OF_YEAR);
		int yearDays = (cal.get(Calendar.YEAR)%100)*366;
		int numDay = yearDays+dayOfYear;
		
		String day = Long.toString(BASEX3-1-(numDay % BASEX3), BASE );
		String tim = Long.toString(System.currentTimeMillis() % BASEX2, BASE );

		int seq = Math.round(rnd.nextFloat()*BASEX3);
		String rand = Long.toString(seq % BASEX3, BASE );
		
		return String.format("%s%2s%3s", day,tim,rand).replace(" ", "0");
	}
	

	/**
	 * Generates a list of unique codes for a set of items
	 * @param numCodes The number of codes to generate
	 * @return a Set containing all the codes generated
	 */
	private Set<String> generateCodes(int numCodes){
		Set<String> set = new HashSet<>(numCodes);
		Random r = new Random();
		
		int i=0;
		while(set.size() < numCodes){
			i++;
			set.add(generateCode(r));
		}

		return set;
	}


	public void setItemDao(ItemDAO itemDao) {
		this.itemDao = itemDao;
	}
	
}
