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
public class AnswerDAO implements DAO<Answer>{
    @Override
    public Optional<Answer> find(String s) {
        return null;
    }
    
    @Override
    public Optional<Answer> find(int id) {
        try {
            Connection connexion = null;
            Statement statement = null;
            
            try {
                Class.forName(DRIVER);
            } catch(ClassNotFoundException e) {
                Logger.getLogger(AnswerDAO.class.getName()).log(Level.SEVERE,null,e);
            }
            connexion = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            statement = connexion.createStatement();
            
            String query = "SELECT * FROM answers WHERE id= '" + id + "' ORDER BY rank;";
            System.out.println("Executing command: " + query + "\n");
            ResultSet result = statement.executeQuery(query);
            
            result.next();
            int id_answer =result.getInt(1);
            String statement_answer=result.getString(2);
            String status = result.getString(3);
            int rank = result.getInt(4);
            boolean is_right = result.getBoolean(5);
            int question_id = result.getInt(6);

            result.close();
            connexion.close();
            
            Answer ret = new Answer(id_answer,statement_answer,status,rank,is_right,question_id);
            return Optional.ofNullable(ret);
        } catch(SQLException e) {
            Logger.getLogger(AnswerDAO.class.getName()).log(Level.SEVERE,null,e);
            return null;
        }
    }
    
    @Override
    public ArrayList<Answer> getAll() {
        try {
            Connection connexion = null;
            Statement statement = null;

            try {
                Class.forName(DRIVER);
            } catch(ClassNotFoundException e) {
                Logger.getLogger(AnswerDAO.class.getName()).log(Level.SEVERE,null,e);
            }
            connexion = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            statement = connexion.createStatement();

            String query = "SELECT * from answers";
            System.out.println("Executing command : " + query + "\n");
            ArrayList<Answer> ret;
            ResultSet result = statement.executeQuery(query);
            ret = new ArrayList<Answer>();
            while(result.next()) {
                int id_answer =result.getInt(1);
                String statement_answer=result.getString(2);
                String status = result.getString(3);
                int rank = result.getInt(4);
                boolean is_right = result.getBoolean(5);
                int question_id = result.getInt(6);

                ret.add(new Answer(id_answer,statement_answer,status,rank,is_right,question_id));
            }
            connexion.close();
            return ret;
        } catch(SQLException e) {
            Logger.getLogger(AnswerDAO.class.getName()).log(Level.SEVERE,null,e);
            return null;
        }
    }
    
    @Override
    public void create(Answer a) {
        try {
            Connection connexion = null;

            try {
                Class.forName(DRIVER);
            } catch(ClassNotFoundException e) {
                Logger.getLogger(AnswerDAO.class.getName()).log(Level.SEVERE,null,e);
            }

            connexion = DriverManager.getConnection(URL,USERNAME,PASSWORD);
   
            String query = "INSERT INTO answers (statement,status,rank,is_right,question_id) VALUES (?,?,?,?,(SELECT id FROM questions WHERE id=?))";
            PreparedStatement statement = connexion.prepareStatement(query);
            System.out.println("id_question : " + a.getQuestion_id() + "\n");
            statement.setString(1,a.getStatement());
            statement.setString(2,a.getStatus());
            statement.setInt(3,a.getRank()); 
            statement.setBoolean(4,a.Is_right());
            statement.setInt(5,a.getQuestion_id());

            System.out.println("Executing command : " + query + "\n");
            statement.executeUpdate();
            statement.close();
            connexion.close();
        } catch(SQLException e) {
            Logger.getLogger(AnswerDAO.class.getName()).log(Level.SEVERE,null,e);           
        }
    }
    
    @Override
    public void update(Answer a) {
        try {
            Connection connexion = null;

            try {
                Class.forName(DRIVER);
            } catch(ClassNotFoundException e) {
                Logger.getLogger(AnswerDAO.class.getName()).log(Level.SEVERE,null,e);
            }
            connexion = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            String query = "UPDATE answers SET statement=?,status=?, rank=?, is_right=?, question_id=? WHERE id=?;";
            PreparedStatement statement = connexion.prepareStatement(query);
            statement.setString(1,a.getStatement());
            statement.setString(2,a.getStatus());
            statement.setInt(3,a.getRank());
            statement.setBoolean(4,a.Is_right());
            statement.setInt(5,a.getQuestion_id());
            statement.setInt(6,a.getId());
            statement.executeUpdate();
            statement.close();
            connexion.close();
        } catch(SQLException e) {
            Logger.getLogger(AnswerDAO.class.getName()).log(Level.SEVERE,null,e);
        }
    }
    
    public void delete(Answer a) {
        try {
            Connection connexion = null;

            try {
                Class.forName(DRIVER);
            } catch(ClassNotFoundException e) {
                Logger.getLogger(AnswerDAO.class.getName()).log(Level.SEVERE,null,e);
            }

            connexion = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            String query = "DELETE FROM answers WHERE id=?";
            PreparedStatement statement = connexion.prepareStatement(query);
            statement.setInt(1,a.getId());
            statement.executeUpdate();
            statement.close();
            connexion.close();
        } catch (SQLException e) {
            Logger.getLogger(AnswerDAO.class.getName()).log(Level.SEVERE,null,e);            
        }
    }
    
    /* Lister toutes les answers d'une question */
    
    public ArrayList<Answer> getAll(int id_question) {
        try {
            Connection connexion = null;
            Statement statement = null;

            try {
                Class.forName(DRIVER);
            } catch(ClassNotFoundException e) {
                Logger.getLogger(AnswerDAO.class.getName()).log(Level.SEVERE,null,e);
            }
            connexion = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            statement = connexion.createStatement();

            String query = "SELECT * FROM answers WHERE question_id = " + id_question + " ORDER BY rank;";
            System.out.println("Executing command : " + query + "\n");
            ArrayList<Answer> ret;
            ResultSet result = statement.executeQuery(query);
            ret = new ArrayList<Answer>();
            while(result.next()) {
                int id_answer =result.getInt(1);
                String statement_answer=result.getString(2);
                String status = result.getString(3);
                int rank = result.getInt(4);
                boolean is_right = result.getBoolean(5);

                ret.add(new Answer(id_answer,statement_answer,status,rank,is_right,id_question));
            }
            connexion.close();
            return ret;
        } catch(SQLException e) {
            Logger.getLogger(AnswerDAO.class.getName()).log(Level.SEVERE,null,e);
            return null;
        }
    }
}
