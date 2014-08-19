package generic;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.apache.log4j.Logger;
import org.thymeleaf.util.DateUtils;

public class Main {
	private final int BASE = 36;
	private final int BASEX3 = 36*36*36;
	private final int BASEX4 = 36*36*36*36;
	
	static Logger log = Logger.getLogger("test.log");
	
	public static void main(String[] args) {
		Main m = new Main();
		log.info(m.generateCode(null));
		log.info(m.generateCode(null));
		log.info(m.generateCode(null));
		log.info(m.generateCode(null));
		log.info(m.generateCode(null));
		log.info(m.generateCode(null));
		log.info(m.generateCode(null));
		log.info(m.generateCode(null));
		log.info(m.generateCode(null));
		log.info(m.generateCode(null));
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
		
		String tim = Long.toString(System.currentTimeMillis() % BASEX3, BASE );

		int seq = Math.round(rnd.nextFloat()*BASEX4);
		String rand = Long.toString(seq % BASEX4, BASE );
		
		return String.format("%s%3s%4s", day,tim,rand).replace(" ", "0");
	}
	

	/**
	 * Generates a list of unique codes for a set of items
	 * @param numCodes The number of codes to generate
	 * @return a Set containing all the codes generated
	 */
	private Set<String> generateCodes(int numCodes){
		Set<String> set = new HashSet<>(numCodes);
		Random r = new Random();

		while(set.size() < numCodes){
			set.add(generateCode(r));
		}

		return set;
	}

}
