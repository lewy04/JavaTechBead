package view;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import util.CheckOut;
 
@ManagedBean
public class ButtonView {
     
    public void check(ActionEvent actionEvent) throws IOException {
    	FacesContext.getCurrentInstance().getExternalContext().redirect
		("check_out.jsf");
    }
     
    public void rent(ActionEvent actionEvent) throws IOException {
    	FacesContext.getCurrentInstance().getExternalContext().redirect
		("new_rent.jsf");
    }
     
    public void listCust(ActionEvent actionEvent) throws ClassNotFoundException, IOException {
        view.CustomerView.init("");
        util.CheckOut.timer("S1", "S2");
        FacesContext.getCurrentInstance().getExternalContext().redirect
			("list_customer.jsf");
    }
    
    public void addCust(ActionEvent actionEvent) throws IOException {
    	FacesContext.getCurrentInstance().getExternalContext().redirect
		("add_customer.jsf");
    }
    
    public void delCust(ActionEvent actionEvent) throws IOException {
    	FacesContext.getCurrentInstance().getExternalContext().redirect
		("del_customer.jsf");
    }
    
    public void searchCust(ActionEvent actionEvent) throws IOException {
    	FacesContext.getCurrentInstance().getExternalContext().redirect
		("search_customer.jsf");
    }
    
    public void listVehic(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
    	 view.VehicleView.init("","");
         FacesContext.getCurrentInstance().getExternalContext().redirect
 			("list_vehicle.jsf");
    }
    
    public void addVehic(ActionEvent actionEvent) throws IOException {
    	 FacesContext.getCurrentInstance().getExternalContext().redirect
			("add_vehicle.jsf");
    }
    
    public void delVehic(ActionEvent actionEvent) throws IOException {
    	 FacesContext.getCurrentInstance().getExternalContext().redirect
			("del_vehicle.jsf");
    }
    
    public void searchTypeVehic(ActionEvent actionEvent) throws IOException {
    	FacesContext.getCurrentInstance().getExternalContext().redirect
		("search_vh_type.jsf");
    }
    
    public void searchPlateVehic(ActionEvent actionEvent) throws IOException {
    	FacesContext.getCurrentInstance().getExternalContext().redirect
		("search_vh_plate.jsf");
    }
    
    public void avaliableVehic(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
    	view.VehicleView.init("0","Rented");
        FacesContext.getCurrentInstance().getExternalContext().redirect
			("list_vehicle.jsf");
    }
     
    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
