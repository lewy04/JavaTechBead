package model;

public class Vehicle {
	
	protected int VID;
	protected String Manufacturer;
	protected boolean Iscar;
	protected String Chassisnum;
	protected String Pruchased;
	protected int Milage;
	protected int Fee;
	protected boolean Isrented;
	protected String Plateno;
	
	
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
	public Vehicle(int vID, String manufacturer, boolean iscar, String chassisNum, String pruchased, int milage,
			int fee, boolean isrented, String plateNo) {
		super();
		VID = vID;
		Manufacturer = manufacturer;
		Iscar = iscar;
		Chassisnum = chassisNum;
		Pruchased = pruchased;
		Milage = milage;
		Fee = fee;
		Isrented = isrented;
		Plateno = plateNo;
	}
	
	
	
}
