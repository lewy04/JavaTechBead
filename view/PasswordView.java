package view;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import database.DatabaseManager;

import java.io.IOException;
 
@ManagedBean
public class PasswordView {
     
    private String password1;  
    private String password2;  

 
    public String getPassword1() {
        return password1;
    }
 
    public void setPassword1(String password1) {
        this.password1 = password1;
    }
 
    public String getPassword2() {
        return password2;
    }
 
    public void setPassword2(String password2) {
        this.password2 = password2;
    }
    
    public void save() throws IOException {
    	
    	try {
			if(database.DatabaseManager.db_aut(password1).equals(password2)){
			 FacesContext.getCurrentInstance().getExternalContext().redirect
			 											("menu.jsf");
			}else{
				FacesContext.getCurrentInstance().addMessage(null,
			               new FacesMessage("Invalid pass or user"));
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
