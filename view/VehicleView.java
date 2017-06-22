package view;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import database.DatabaseManager;
import model.Vehicle;

@ManagedBean
public class VehicleView {
	  
	   private static List<Vehicle> vehiceList;
	    
	   public List<Vehicle> getvehiceList() {
	       return vehiceList;
	   }
	    
	   @PostConstruct
	   public static void init(String param, String type) throws ClassNotFoundException {
		  vehiceList = new ArrayList<Vehicle>();
		   
		   vehiceList = database.DatabaseManager.listVehicle(param, type);
		   	   
	   }  
}
