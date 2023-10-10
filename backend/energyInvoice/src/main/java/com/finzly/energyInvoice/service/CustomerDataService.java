package com.finzly.energyInvoice.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.finzly.energyInvoice.controller.LoginController;
import com.finzly.energyInvoice.dao.CustomerDataDao;
import com.finzly.energyInvoice.dao.InvoiceDao;
import com.finzly.energyInvoice.entity.CustomerData;
import com.finzly.energyInvoice.entity.Invoice;
import com.finzly.energyInvoice.utility.Parsing;
import com.finzly.energyInvoice.utility.Validation;

@Service
public class CustomerDataService {
	
	@Autowired
	private CustomerDataDao customerDataDao;
	
	@Autowired
	private InvoiceDao invoiceDao;
	
	@Autowired
	private InvoiceService invoiceService;
	
	
	
	
	
	@Autowired
	private SessionFactory factory;
	
	@Transactional	
	public Map<String, String> addCustomer(CustomerData customerData){
		
		Map<String,String> response = new HashMap<String, String>();
		
		
	    CustomerData isExists = customerDataDao.isExist(customerData.getEmail());
	   
	    CustomerData newData = new CustomerData();
	    
	    if(isExists == null) {
	    	
	    	 newData = customerDataDao.addCustomer(customerData);
	    	 invoiceDao.generateInvoice(newData);
	    	 response.put("Meassage", "Created successfully");
			 return (response);
	    	 
	    }else {
	    	
	    	
			List<Invoice> list = invoiceService.getCustInvoices(isExists.getCustomerId());
			
			System.out.println("InvoiceList-----"+list+"Size"+list.size());
			
			
	    	for(Invoice val:list) {
	    		
	    		System.out.println("VAl Duration"+ val.getBillingDuration());
	    		
	    		//if(val.getBillingDuration().equals(customerData.getBillingDuration())) {
	    		if(Validation.dateCompare(val.getBillingDuration(),customerData.getBillingDuration())){ 
	    			 response.put("Error", "Already Present");
	    			 return (response);
	    		}
	         }
	    	
	    	
	    	isExists.setBillingDuration(customerData.getBillingDuration());
	    	invoiceDao.generateInvoice(isExists);
	    	response.put("Meassage", "Added successfully");
	    	return (response);
	    	
	    
	     }	
	    
	    /*
	     * 
	     */
//	    public void test() {
//	    	LoginController l=new LoginController();
//	    	l.
//	    }
	    
}

	public ResponseEntity<List<CustomerData>> getCustomers() {
		
		List<CustomerData> customers = customerDataDao.getCustomers();
		
		return ResponseEntity.ok().body(customers);
	}
	
	
   
	public ResponseEntity<String> uploadCustomer(MultipartFile dataFile) {
		
		try {
            // Check if the uploaded file is a CSV file
            if (!dataFile.getOriginalFilename().endsWith(".csv")) {
                return ResponseEntity.badRequest().body("Invalid file format. Please upload a CSV file.");
            }

            // Process the CSV file and add customer data
            List<CustomerData> customers = Parsing.parseCSV(dataFile.getInputStream());
            
            
            System.out.println(customers);

            // Validate and save the customer records
           List<CustomerData> validCustomers = validateAndSaveCustomers(customers);
           
           
           

            int totalRecords = customers.size();
            int validRecords = validCustomers.size();
            int invalidRecords = totalRecords - validRecords;

            String responseMessage = "Total Records: " + totalRecords + "\n"
                    + "Valid Records: " + validRecords + "\n"
                    + "Invalid Records: " + invalidRecords;

            return ResponseEntity.ok(responseMessage);
        } catch (IOException e) {
        	
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error processing the CSV file: " + e.getMessage());
        }
        
		
	}
	
	

	private List<CustomerData> validateAndSaveCustomers(List<CustomerData> customers) {
		
		 List<CustomerData> response = new ArrayList<CustomerData>();
		 Map<String,String> map = new HashMap<String, String>();
		 
		 for(CustomerData customerData:customers) {
			 
			 
			 map = addCustomer(customerData);
			 
				 if(!map.containsKey("Error")) {
				 
			           response.add(customerData);
			 }
			 
			 
			 
		 }
         return response;
         
	}
	
	
      /* private Map<String, String> addBulkCustomer(CustomerData customerData) {
        
		Map<String,String> response = new HashMap<String, String>();
		
		
	    CustomerData isExists = customerDataDao.isExist(customerData.getEmail());
	   
	    CustomerData newData = new CustomerData();
	    
	    if(isExists == null) {
	    	
	    	 newData = customerDataDao.addCustomer(customerData);
	    	 invoiceDao.generateInvoice(newData);
	    	 response.put("Meassage", "Created successfully");
			 return(response);
	    	 
	    	
	    }else {
	    	
	    	Session session = factory.openSession();
			Criteria query = session.createCriteria(Invoice.class);
			List<Invoice> list = query.list();
	    	
	    	for(Invoice val:list) {
	    		
	    		if(val.getMapId() == customerData.getCustomerId() && val.getBillingDuration().equals(customerData.getBillingDuration())) {
	    			 
	    			 response.put("Error", "Already Present");
	    			 return (response);
	    		}
	         }
	    	
	    	
	    	isExists.setBillingDuration(customerData.getBillingDuration());
	    	invoiceDao.generateInvoice(isExists);
	    	response.put("Meassage", "Added successfully");
	    	return (response);
	    	
	    
	    	
	    	
	     }	
	}*/

}
