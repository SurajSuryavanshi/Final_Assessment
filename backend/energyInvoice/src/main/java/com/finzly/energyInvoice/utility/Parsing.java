package com.finzly.energyInvoice.utility;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import com.finzly.energyInvoice.entity.CustomerData;

public class Parsing {
	
	public static List<CustomerData> parseCSV(InputStream inputStream) throws IOException {
	    List<CustomerData> customerList = new ArrayList<>();
	    
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	    try (InputStreamReader reader = new InputStreamReader(inputStream);
	         CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader())) {

	        for (CSVRecord csvRecord : csvParser) {
	            
	        	String name = csvRecord.get("name");
	            String email = csvRecord.get("email");
	            String telephoneNumber = csvRecord.get("telephoneNumber");
	            String unitConsumptionStr = csvRecord.get("unitConsumption");
	            String billingDateString = csvRecord.get("billingDuration");// Assuming billingDuration is a date field
	            String dueDateString = csvRecord.get("billDueDate");

	            // Validate name (not null or empty)
	            if (name == null || name.isBlank()) {
	                System.err.println("Invalid name: " + name);
	                continue; // Skip this record and continue with the next one
	            }

	            // Validate email ( email validation)
	            if (email == null ||email.isBlank()|| !email.matches("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}")) {
	                System.err.println("Invalid email: " + email);
	                continue; // Skip this record and continue with the next one
	            }

	            // Validate telephone number 
	            if (telephoneNumber == null ||telephoneNumber.isBlank()||telephoneNumber.isBlank()){// !telephoneNumber.matches("\\d{10}")) {
	                System.err.println("Invalid telephone number: " + telephoneNumber);
	                continue; // Skip this record and continue with the next one
	            }

	            // Validate unit consumption (must be a valid double)
	            double unitConsumption;
	            try {
	                unitConsumption = Double.parseDouble(unitConsumptionStr);
	                
	                if(unitConsumption<0) {
	                	System.err.println("Invalid unit consumption: " + unitConsumptionStr);
	                	continue;
	                }  
	            } catch (NumberFormatException e) {
	                System.err.println("Invalid unit consumption: " + unitConsumptionStr);
	                continue; // Skip this record and continue with the next one
	            }

	            java.util.Date date = null;

	            // Validate and parse date (billingDuration)
	            if (billingDateString != null && !billingDateString.isEmpty()) {
	                try {
	                    date = new SimpleDateFormat("yyyy-MM-dd").parse(billingDateString);
	                } catch (ParseException e) {
	                    System.err.println("Invalid date format: " + billingDateString);
	                    continue; // Skip this record and continue with the next one
	                }
	            } else {
	                System.err.println("Date is null or empty");
	                continue; // Skip this record and continue with the next one
	            }

	            // Convert it to a java.sql.Date
	            java.sql.Date billDate = new java.sql.Date(date.getTime());

	            
	            // Validate and parse date (billDueDate)
	            if (dueDateString != null && !dueDateString.isEmpty()) {
	                try {
	                    date = new SimpleDateFormat("yyyy-MM-dd").parse(dueDateString);
	                } catch (ParseException e) {
	                    System.err.println("Invalid date format: " + dueDateString);
	                    continue; // Skip this record and continue with the next one
	                }
	            } else {
	                System.err.println("Date is null or empty");
	                continue; // Skip this record and continue with the next one
	            }

	            // Convert it to a java.sql.Date
	            java.sql.Date dueDate = new java.sql.Date(date.getTime());

	            // Create a CustomerData object and add it to the list
	            CustomerData customerData = new CustomerData();
	            customerData.setName(name);
	            customerData.setEmail(email);
	            customerData.setTelephoneNumber(telephoneNumber);
	            customerData.setBillingDuration(billDate);
	            customerData.setBillDueDate(dueDate);
	            customerData.setUnitConsumption(unitConsumption);
	            // Set other fields as needed

	            customerList.add(customerData);
	        }
	    }

	    return customerList;
	}

	
	

}
