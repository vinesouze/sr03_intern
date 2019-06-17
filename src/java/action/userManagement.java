/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import Model.User;
import Model.UserDAO;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;

/**
 *
 * @author Junior
 */
public class userManagement extends ActionSupport {
    public ArrayList<User> users = new ArrayList<User>();
    public ArrayList<User> users_actif = new ArrayList<User>();
    public ArrayList<User> users_inactif = new ArrayList<User>();
    
    @Override
    public String execute() throws Exception {
        UserDAO userDAO = new UserDAO();
        setUserList(userDAO.getAll());
        
        users_actif = new ArrayList<User>();
        users_inactif = new ArrayList<User>();
        for (User user : users) {
            System.out.println("GetUser email: " + user.getEmail() + "\n");
            if ((user.getStatus()).equals("actif")) {
                users_actif.add(user);
            } else {
                users_inactif.add(user);
            }
        }
        System.out.println("GetUser_actif email: " + users_actif.get(0).getEmail() + "\n");
        setUsers_actif(users_actif);
        setUsers_inactif(users_inactif);
        return "success";
    }
    
    public ArrayList<User> getUserList() {
        return users;
    }
    
    public void setUserList(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<User> getUsers_actif() {
        return users_actif;
    }

    public void setUsers_actif(ArrayList<User> users_actif) {
        this.users_actif = users_actif;
    }

    public ArrayList<User> getUsers_inactif() {
        return users_inactif;
    }

    public void setUsers_inactif(ArrayList<User> users_inactif) {
        this.users_inactif = users_inactif;
    }
    
}
