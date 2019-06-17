/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Junior
 */
public class UserDAO implements DAO<User> {
    public UserDAO() {}
    
    @Override
    public Optional<User> find(int id) {
        return null;
    }
    
    @Override
    public Optional<User> find(String email) {
        try {
            Connection connexion = null;
            Statement statement = null;
            
            try {
                Class.forName(DRIVER);
            } catch(ClassNotFoundException e) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE,null,e);
            }
            connexion = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            statement = connexion.createStatement();
            
            String query = "SELECT * FROM users WHERE email= '" + email + "';";
            System.out.println("Executing command: " + query + "\n");
            ResultSet result = statement.executeQuery(query);
            
            result.next();
            String email_user =result.getString(1);
            String lastname=result.getString(2);
            String firstname=result.getString(3);
            String gender = result.getString(4);
            String password = result.getString(5);
            String tel = result.getString(6);
            Date date_creat = result.getDate(7);
            String status = result.getString(8);
            boolean is_admin = result.getBoolean(9);
            String company = result.getString(10);
            result.close();
            connexion.close();
            User ret = new User(lastname,firstname,email_user,password,tel,company,date_creat,is_admin,status,gender);
            return Optional.ofNullable(ret);
        } catch(SQLException e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE,null,e);
            return null;
        }
    }
    
    @Override
    public ArrayList<User> getAll() {
        try {
            Connection connexion = null;
            Statement statement = null;

            try {
                Class.forName(DRIVER);
            } catch(ClassNotFoundException e) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE,null,e);
            }
            connexion = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            statement = connexion.createStatement();

            String query = "SELECT * from users";
            System.out.println("Executing command : " + query + "\n");
            ArrayList<User> ret;
            try (ResultSet result = statement.executeQuery(query)) {
                ret = new ArrayList<User>();
                while(result.next()) {
                    String email_user =result.getString(1);
                    String lastname=result.getString(2);
                    String firstname=result.getString(3);
                    String gender = result.getString(4);
                    String password = result.getString(5);
                    String tel = result.getString(6);
                    Date date_creat = result.getDate(7);
                    String status = result.getString(8);
                    boolean is_admin = result.getBoolean(9);
                    String company = result.getString(10);
                    ret.add(new User(lastname,firstname,email_user,password,tel,company,date_creat,is_admin,status,gender));
                }
            }
            connexion.close();
            return ret;
        } catch(SQLException e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE,null,e);
            return null;
        }
    }
    
    @Override
    public void create(User u) {
        try {
            Connection connexion = null;

            try {
                Class.forName(DRIVER);
            } catch(ClassNotFoundException e) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE,null,e);
            }

            connexion = DriverManager.getConnection(URL,USERNAME,PASSWORD);

            Date today = new Date();
            String query = "INSERT INTO users (email, password, company, lastname, firstname, tel, gender, is_admin, date_creat) VALUES (?,?,?,?,?,?,?,?,NOW())";
            PreparedStatement statement = connexion.prepareStatement(query);
            statement.setString(1,u.getEmail());
            statement.setString(2,u.getPassword());
            statement.setString(3,u.getCompany());
            statement.setString(4,u.getLastname());
            statement.setString(5,u.getFirstname());
            statement.setString(6,u.getTel());
            statement.setString(7,u.getGender());
            statement.setBoolean(8,u.is_admin());

            System.out.println("Executing command : " + query + "\n");
            statement.executeUpdate();
            connexion.close();
        } catch(SQLException e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE,null,e);           
        }
    }
    
    @Override
    public void update(User u) {
        try {
            Connection connexion = null;

            try {
                Class.forName(DRIVER);
            } catch(ClassNotFoundException e) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE,null,e);
            }
            connexion = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            String query = "UPDATE users SET lastname=?,firstname=?,password=?,tel=?,company=?,is_admin=?,status=? WHERE email=?;";
            PreparedStatement statement = connexion.prepareStatement(query);
            statement.setString(1,u.getLastname());
            statement.setString(2,u.getFirstname());
            statement.setString(3,u.getPassword());
            statement.setString(4,u.getTel());
            statement.setString(5,u.getCompany());
            statement.setBoolean(6,u.is_admin());
            statement.setString(7,u.getStatus());
            statement.setString(8,u.getEmail());
            statement.executeUpdate();
            statement.close();
            connexion.close();
        } catch(SQLException e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE,null,e);
        }
    }
    
    public void delete(User u) {
        try {
            Connection connexion = null;

            try {
                Class.forName(DRIVER);
            } catch(ClassNotFoundException e) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE,null,e);
            }

            connexion = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            String query = "DELETE FROM users WHERE email=?";
            PreparedStatement statement = connexion.prepareStatement(query);
            statement.setString(1,u.getEmail());
            statement.executeUpdate();
            connexion.close();
        } catch (SQLException e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE,null,e);            
        }
    }
}
