package Database;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Country {

	public Country() {}
	
	
	// Pattern-Date is depend on used languages
	public String getDateTime() {
		
		String date = "";
		
		if(Locale.getDefault().getLanguage().equals("de")) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.GERMANY);
			date = sdf.format(new Date());
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm aa", Locale.UK);
			date = sdf.format(new Date());
		}
		
		return date;
	}
	
	
}
