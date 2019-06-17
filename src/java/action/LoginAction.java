/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import Model.User;
import Model.UserDAO;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author Junior
 */
public class LoginAction extends ActionSupport implements SessionAware{
    private String email;
    private String password;
    private String userEmail;
    Map<String, Object> map;
    //<%session.setAttribute(userEmail, request.getAttribute(name)); %> dans un fichier jsp
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    
    @Override
    public String execute() throws Exception{
        UserDAO userdao = new UserDAO();
        ArrayList<User> users = userdao.getAll();
        Map<String, Object> currentSession = ActionContext.getContext().getSession();
        currentSession.put("userMail", email);
        for (User user : users) {
            if(this.email.equals(user.getEmail()) && this.password.equals(user.getPassword())) {
                setUserEmail(user.getEmail());
                System.out.println("user id : " + user.getEmail() + "\n");
                System.out.println("session user : " + currentSession.get("userMail") + "\n");
                if(user.is_admin()==true) {
                    return "loginAdmin";
                } else {
                    return "loginTrainee";
                }
            }
        }
        return "failure";
    }

    @Override
    public void setSession(Map<String, Object> map) {
    this.map=map;
    }

   
}
