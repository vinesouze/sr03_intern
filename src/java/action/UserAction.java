/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import com.opensymphony.xwork2.ActionSupport;
import Model.User;
import Model.UserDAO;
import com.opensymphony.xwork2.ActionContext;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author Junior
 */
public class UserAction extends ActionSupport{
    public User user=new User();
    
    public ArrayList<User> users_actif = new ArrayList<User>();
    public ArrayList<User> users_inactif = new ArrayList<User>();
    public ArrayList<User> users = new ArrayList<User>();
    
    public String adminMail;
    public String modifyEmail;
    public int id=0;

    public String addUser() throws SQLException{
        UserDAO userDAO = new UserDAO();

        // ** On génère un password aléatoire
        String pwd="";
        Random rand = new Random();
        String alphabet = "abcdefghijklmnopqrstuvwxyz0123456789";
        int longueur = alphabet.length();
        for (int i=0; i< 10; i++) {
            int k = rand.nextInt(longueur);
            pwd = pwd + alphabet.charAt(k);
        }
        getUser().setPassword(pwd);
        System.out.println("GetUser (is_admin): " + user.is_admin() + "\n");
        userDAO.create(getUser());
         // On actualise toutes les listes users
        setUsers(userDAO.getAll());
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

    public String getAll() throws SQLException {
        UserDAO userdao = new UserDAO();
        setUsers(userdao.getAll());
        return "success";
    }
    
    public String activateUser() throws SQLException{
        UserDAO userdao = new UserDAO();
        user = userdao.find(modifyEmail).get();
        System.out.println("id user : " + user.getStatus() + "\n");
        if((user.getStatus()).equals("actif")) 
            user.setStatus("inactif"); 
        else 
            user.setStatus("actif");
        userdao.update(getUser());
        
        // On actualise toutes les listes users
        setUsers(userdao.getAll());
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
    
    public String modifyUser() throws Exception {
        Map<String, Object> currentSession = ActionContext.getContext().getSession();
        System.out.println("session user : " + currentSession.get("userMail") + "\n");
        System.out.println("email modifier : " + modifyEmail + "\n");
        UserDAO userdao = new UserDAO();
        user = userdao.find(modifyEmail).get();
        return "success";
    }
    
    public String modifyUserSubmit() throws Exception {
        UserDAO userdao = new UserDAO();
        System.out.println("status modifié : " + getUser().getStatus() + "\n");
        userdao.update(getUser());
        
        // On actualise toutes les listes users
        setUsers(userdao.getAll());
        users_actif = new ArrayList<User>();
        users_inactif = new ArrayList<User>();
        for (User user : users) {
            if ((user.getStatus()).equals("actif")) {
                users_actif.add(user);
            } else {
                users_inactif.add(user);
            }
        }
        setUsers_actif(users_actif);
        setUsers_inactif(users_inactif);
        return "success";
    }
    
    // GETTER & SETTER
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public String getAdminMail() {
        return adminMail;
    }

    public void setAdminMail(String adminMail) {
        this.adminMail = adminMail;
    }

    public String getModifyEmail() {
        return modifyEmail;
    }

    public void setModifyEmail(String modifyEmail) {
        this.modifyEmail = modifyEmail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
