package view;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import database.DatabaseManager;
import model.Customer;

@ManagedBean
public class CustomerView {
	
	  
	   private static List<Customer> custList;
	    
	   public List<Customer> getcustList() {
	       return custList;
	   }
	    
	   @PostConstruct
	   public static void init(String param) throws ClassNotFoundException {
		  custList = new ArrayList<Customer>();
		   
		   custList = database.DatabaseManager.listCustomer(param);
			
		}
		   
 }
	  
