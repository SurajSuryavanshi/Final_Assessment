package com.finzly.energyInvoice.utility;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Random;

public class Validation {
	
	
	public static boolean dateCompare(Date date1,Date date2) {
		
		
		//Date sqlDate1 = Date.valueOf("2023-09-22");
        //Date sqlDate2 = Date.valueOf("2023-08-15");

        // Create SimpleDateFormat with the desired format
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");

        // Format the dates to get only year and month
        String yearMonth1 = dateFormat.format(date1);
        String yearMonth2 = dateFormat.format(date2);
        
        
        
        //Both dates have the same year and month
        if (yearMonth1.equals(yearMonth2)) {
          
            return true;
        }
        
        return false;
	}
	
	
	 public static int generateRandom() {
		 
		 
	        //Generate 4 Digit random number for opt 
		    Random random = new Random();
	        int min = 1000;
	        int max = 9999;
	        return random.nextInt(max - min + 1) + min;
	}

}
