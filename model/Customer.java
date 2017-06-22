package model;

public class Customer {
	
	protected String CID;
	protected String Name;
	protected String Phone;
	protected String Address;
	
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
	
	
	public Customer(String cID, String name, String phone, String address) {
		super();
		CID = cID;
		Name = name;
		Phone = phone;
		Address = address;
	}


}
