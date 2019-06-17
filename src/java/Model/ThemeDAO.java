/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import static Model.DAO.DRIVER;
import static Model.DAO.PASSWORD;
import static Model.DAO.URL;
import static Model.DAO.USERNAME;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Junior
 */
public class ThemeDAO implements DAO<Theme>{
    @Override
    public Optional<Theme> find(int id) {
        return null;
    }
    
    @Override
    public Optional<Theme> find(String subject) {
        try {
            Connection connexion = null;
            Statement statement = null;
            
            try {
                Class.forName(DRIVER);
            } catch(ClassNotFoundException e) {
                Logger.getLogger(ThemeDAO.class.getName()).log(Level.SEVERE,null,e);
            }
            connexion = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            statement = connexion.createStatement();
            
            String query = "SELECT * FROM themes WHERE subject= '" + subject + "';";
            System.out.println("Executing command: " + query + "\n");
            ResultSet result = statement.executeQuery(query);
            
            result.next();
            String subject_form=result.getString(1);
            result.close();
            connexion.close();
            Theme ret = new Theme(subject_form);
            return Optional.ofNullable(ret);
        } catch(SQLException e) {
            Logger.getLogger(ThemeDAO.class.getName()).log(Level.SEVERE,null,e);
            return null;
        }
    }
    
    @Override
    public ArrayList<Theme> getAll() {
        try {
            Connection connexion = null;
            Statement statement = null;

            try {
                Class.forName(DRIVER);
            } catch(ClassNotFoundException e) {
                Logger.getLogger(ThemeDAO.class.getName()).log(Level.SEVERE,null,e);
            }
            connexion = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            statement = connexion.createStatement();

            String query = "SELECT * from themes";
            System.out.println("Executing command : " + query + "\n");
            ArrayList<Theme> ret;
            ResultSet result = statement.executeQuery(query);
            ret = new ArrayList<Theme>();
            while(result.next()) {
                String subject_Theme =result.getString(1);

                ret.add(new Theme(subject_Theme));
            }
            connexion.close();
            return ret;
        } catch(SQLException e) {
            Logger.getLogger(ThemeDAO.class.getName()).log(Level.SEVERE,null,e);
            return null;
        }
    }
    
    @Override
    public void create(Theme t) {
        try {
            Connection connexion = null;

            try {
                Class.forName(DRIVER);
            } catch(ClassNotFoundException e) {
                Logger.getLogger(ThemeDAO.class.getName()).log(Level.SEVERE,null,e);
            }

            connexion = DriverManager.getConnection(URL,USERNAME,PASSWORD);

            String query = "INSERT INTO themes VALUES (?)";
            PreparedStatement statement = connexion.prepareStatement(query);
            statement.setString(1,t.getSubject());

            System.out.println("Executing command : " + query + "\n");
            statement.executeUpdate();
            statement.close();
            connexion.close();
        } catch(SQLException e) {
            Logger.getLogger(ThemeDAO.class.getName()).log(Level.SEVERE,null,e);           
        }
    }
    
    @Override
    public void update(Theme t) {
        try {
            Connection connexion = null;

            try {
                Class.forName(DRIVER);
            } catch(ClassNotFoundException e) {
                Logger.getLogger(ThemeDAO.class.getName()).log(Level.SEVERE,null,e);
            }
            connexion = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            String query = "UPDATE themes SET subject WHERE subject=?;";
            PreparedStatement statement = connexion.prepareStatement(query);
            statement.setString(1,t.getSubject());
            statement.executeUpdate();
            statement.close();
            connexion.close();
        } catch(SQLException e) {
            Logger.getLogger(ThemeDAO.class.getName()).log(Level.SEVERE,null,e);
        }
    }
    
    public void delete(Theme t) {
        try {
            Connection connexion = null;

            try {
                Class.forName(DRIVER);
            } catch(ClassNotFoundException e) {
                Logger.getLogger(ThemeDAO.class.getName()).log(Level.SEVERE,null,e);
            }

            connexion = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            String query = "DELETE FROM themes WHERE subject=?";
            PreparedStatement statement = connexion.prepareStatement(query);
            statement.setString(1,t.getSubject());
            statement.executeUpdate();
            statement.close();
            connexion.close();
        } catch (SQLException e) {
            Logger.getLogger(ThemeDAO.class.getName()).log(Level.SEVERE,null,e);            
        }
    }
}
