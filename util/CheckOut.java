package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CheckOut {
	
	public static int timer(String S1, String S2){
	SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
	long diff = (long) 0;
	
	try {
	    Date date1 = myFormat.parse(S1);
	    Date date2 = myFormat.parse(S2);
	    diff = date2.getTime() - date1.getTime();
	    System.out.println ("Days: " + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
	} catch (ParseException e) {
	    e.printStackTrace();
	}
	
	return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
}
	
	public static String check(int days, int damaged, int fee, int milage){
		System.out.println("days: "+days +" DAMAGED: "+ damaged + " FEE: "+fee +" MILAGE: "+ milage);
		int mil = milage * 2;
		int price = days * fee;
		price += mil;
		
		if (damaged == 1){
			price += 500;
		}
		
		String fin = String.valueOf(price);
		
		return fin;
	}

}
