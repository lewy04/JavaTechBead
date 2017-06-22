package view;

import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import model.Customer;
import model.Vehicle;
import util.CheckOut;
import database.DatabaseManager;
 
@ManagedBean
public class InputTextareaView {
	
	////// Customers
	
	private String CID;
	private String Name;
	private String Phone;
	private String Address;
	
	/////// Vehciles
	
	
	protected int VID;
	protected String Manufacturer;
	protected boolean Iscar;
	protected String Chassisnum;
	protected String Pruchased;
	protected int Milage;
	protected int Fee;
	protected boolean Isrented;
	protected String Plateno;
	
	
	protected String Date;
	protected boolean Isdamaged;
	protected String text;
	
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	
	public boolean isIsdamaged() {
		return Isdamaged;
	}
	
	public void setIsdamaged(boolean isdamaged) {
		Isdamaged = isdamaged;
	}
	
	public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    
    /////////////////////////////
	
	public int getVID() {
		return VID;
	}
	public void setVID(int vID) {
		VID = vID;
	}
	public String getManufacturer() {
		return Manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		Manufacturer = manufacturer;
	}
	public boolean isIscar() {
		return Iscar;
	}
	public void setIscar(boolean iscar) {
		Iscar = iscar;
	}
	public String getChassisNum() {
		return Chassisnum;
	}
	public void setChassisNum(String chassisNum) {
		Chassisnum = chassisNum;
	}
	public String getPruchased() {
		return Pruchased;
	}
	public void setPruchased(String pruchased) {
		Pruchased = pruchased;
	}
	public int getMilage() {
		return Milage;
	}
	public void setMilage(int milage) {
		Milage = milage;
	}
	public int getFee() {
		return Fee;
	}
	public void setFee(int fee) {
		Fee = fee;
	}
	public boolean isIsrented() {
		return Isrented;
	}
	public void setIsrented(boolean isrented) {
		Isrented = isrented;
	}
	public String getPlateNo() {
		return Plateno;
	}
	public void setPlateNo(String plateNo) {
		Plateno = plateNo;
	}
	
	//////// Costumer getter & setter
	
	public String getCID() {
		return CID;
	}
	public void setCID(String cID) {
		CID = cID;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getPhone() {
		return Phone;
	}
	public void setPhone(String phone) {
		Phone = phone;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
    
	public void newRent() throws IOException {
	   	   
		   DatabaseManager.addRent(CID, String.valueOf(VID), this.Milage, this.Fee, Date);
		   addMessage("Rent Added!");
	    }
    
    
   public void check() throws IOException, ClassNotFoundException {
	  String startdate = DatabaseManager.checkDate(String.valueOf(VID));
	  int days = CheckOut.timer(startdate, Date);
	  int fee = DatabaseManager.checkFee(String.valueOf(VID));
	  int damage;
	  System.out.println(String.valueOf(DatabaseManager.checkMilage(String.valueOf(VID))));
	  int milage = Milage -DatabaseManager.checkMilage(String.valueOf(VID));
	  
	  if(Isdamaged){
		  damage = 1;
	  }else damage = 0;
	  
	  String price = CheckOut.check(days, damage, fee, milage);
	  
	  this.setText("The price is: " + price +" EUR");
    }
   
   public void payed() throws IOException {
	   DatabaseManager.removeRent(String.valueOf(VID), Milage);
	   addMessage("Payed!");
	   this.setText("");
    }
   
   public void addCustomer() throws IOException {
	   DatabaseManager.addCustomer(new Customer(CID, Name, Phone, Address));
	   addMessage("Added to Customers!");
    }
   
   public void delCustomer() throws IOException {
	   DatabaseManager.removeCustomer(CID);
	   addMessage("Customer Deleted!");
    }
   
   public void searchCustomer() throws IOException, ClassNotFoundException {
	   CustomerView.init(CID);
       FacesContext.getCurrentInstance().getExternalContext().redirect
			("list_customer.jsf");
    }
   
   public void addVehicle() throws IOException {
	   	   
	   DatabaseManager.addVehicle(new Vehicle(VID, Manufacturer, Iscar ,Chassisnum ,Pruchased,Milage, Fee, Isrented, Plateno));
	   addMessage("Added to Vehicles!");
    }
   
   public void delVehicle() throws IOException {
	  DatabaseManager.removeVehicle(String.valueOf(VID));
	   addMessage("Vehicle Deleted!");
    }
   
   public void searchPlate() throws IOException, ClassNotFoundException {
	  VehicleView.init(Plateno,"plateNo");
       FacesContext.getCurrentInstance().getExternalContext().redirect
       ("list_vehicle.jsf");
    }
   
   public void cars() throws IOException, ClassNotFoundException {
   	VehicleView.init("1","type");
       FacesContext.getCurrentInstance().getExternalContext().redirect
			("list_vehicle.jsf");
   }
   
   public void boats() throws IOException, ClassNotFoundException {
   VehicleView.init("0","type");
       FacesContext.getCurrentInstance().getExternalContext().redirect
			("list_vehicle.jsf");
   }
   
   public static void addMessage(String summary) {
       FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
       FacesContext.getCurrentInstance().addMessage(null, message);
   }
}