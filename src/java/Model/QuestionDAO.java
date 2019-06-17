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
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Junior
 */
public class QuestionDAO implements DAO<Question>{
    @Override
    public Optional<Question> find(String s) {
        return null;
    }
    
    @Override
    public Optional<Question> find(int id) {
        try {
            Connection connexion = null;
            Statement statement = null;
            
            try {
                Class.forName(DRIVER);
            } catch(ClassNotFoundException e) {
                Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE,null,e);
            }
            connexion = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            statement = connexion.createStatement();
            
            String query = "SELECT * FROM questions WHERE id= '" + id + "' ORDER BY rank;";
            System.out.println("Executing command: " + query + "\n");
            ResultSet result = statement.executeQuery(query);
            
            result.next();
            int id_question =result.getInt(1);
            String statement_question=result.getString(2);
            String status = result.getString(3);
            int rank = result.getInt(4);
            int id_form = result.getInt(5);

            ArrayList<Answer> answers = new ArrayList<>();
            AnswerDAO answerDAO = new AnswerDAO();
            answers = answerDAO.getAll(id);

            result.close();
            connexion.close();
            Question ret = new Question(id_question,statement_question,status,rank,id_form,answers);
            return Optional.ofNullable(ret);
        } catch(SQLException e) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE,null,e);
            return null;
        }
    }
    
    @Override
    public ArrayList<Question> getAll() {
        try {
            Connection connexion = null;
            Statement statement = null;

            try {
                Class.forName(DRIVER);
            } catch(ClassNotFoundException e) {
                Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE,null,e);
            }
            connexion = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            statement = connexion.createStatement();

            String query = "SELECT * from questions";
            System.out.println("Executing command : " + query + "\n");
            ArrayList<Question> ret;
            ResultSet result = statement.executeQuery(query);
            ret = new ArrayList<Question>();
            while(result.next()) {
                int id_question =result.getInt(1);
                String statement_question=result.getString(2);
                String status = result.getString(3);
                int rank = result.getInt(4);
                int id_form = result.getInt(5);

                ArrayList<Answer> answers = new ArrayList<>();
                AnswerDAO answerDAO = new AnswerDAO();
                answers = answerDAO.getAll(id_question);

                ret.add(new Question(id_question,statement_question,status,rank,id_form,answers));
            }
            connexion.close();
            return ret;
        } catch(SQLException e) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE,null,e);
            return null;
        }
    }
    
    @Override
    public void create(Question q) {
        try {
            Connection connexion = null;

            try {
                Class.forName(DRIVER);
            } catch(ClassNotFoundException e) {
                Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE,null,e);
            }

            connexion = DriverManager.getConnection(URL,USERNAME,PASSWORD);
   
            String query = "INSERT INTO questions (statement,status,rank,form_id) VALUES (?,?,?,(SELECT id FROM forms WHERE id=?))";
            PreparedStatement statement = connexion.prepareStatement(query);
            System.out.println("id_form : " + q.getId_form() + "\n");
            statement.setString(1,q.getStatement());
            statement.setString(2,q.getStatus());
            statement.setInt(3,q.getRank()); 
            statement.setInt(4,q.getId_form());

            System.out.println("Executing command : " + query + "\n");
            statement.executeUpdate();

            AnswerDAO answerDAO = new AnswerDAO();
            for (Answer answer : q.getAnswers()) {
                answerDAO.create(answer);
            }
            connexion.close();
        } catch(SQLException e) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE,null,e);           
        }
    }
    
    @Override
    public void update(Question q) {
        try {
            Connection connexion = null;

            try {
                Class.forName(DRIVER);
            } catch(ClassNotFoundException e) {
                Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE,null,e);
            }
            connexion = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            String query = "UPDATE questions SET statement=?,status=?, rank=?,form_id=(SELECT id FROM forms WHERE id=?) WHERE id=?;";
            PreparedStatement statement = connexion.prepareStatement(query);
            statement.setString(1,q.getStatement());
            statement.setString(2,q.getStatus());
            statement.setInt(3,q.getRank());
            statement.setInt(4,q.getId_form());
            statement.setInt(5,q.getId());
            statement.executeUpdate();
            statement.close();

            /*AnswerDAO answerDAO = new AnswerDAO();
            for (Answer answer : q.getAnswers()) {
                answerDAO.create(answer);
            }*/

            connexion.close();
        } catch(SQLException e) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE,null,e);
        }
    }
    
    public void delete(Question q) {
        try {
            Connection connexion = null;

            try {
                Class.forName(DRIVER);
            } catch(ClassNotFoundException e) {
                Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE,null,e);
            }

            connexion = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            String query = "DELETE FROM questions WHERE id=?";
            PreparedStatement statement = connexion.prepareStatement(query);
            statement.setInt(1,q.getId());
            statement.executeUpdate(query);
            statement.close();

            AnswerDAO answerDAO = new AnswerDAO();
            for (Answer answer : q.getAnswers()) {
                answerDAO.delete(answer);
            }
            connexion.close();
        } catch (SQLException e) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE,null,e);            
        }
    }
    
    /* Lister toutes les questions d'un formulaire */
    
    public ArrayList<Question> getAll(int id_form) {
        try {
            Connection connexion = null;
            Statement statement = null;

            try {
                Class.forName(DRIVER);
            } catch(ClassNotFoundException e) {
                Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE,null,e);
            }
            connexion = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            statement = connexion.createStatement();

            String query = "SELECT * FROM questions WHERE form_id = " + id_form + " ORDER BY rank;";
            System.out.println("Executing command : " + query + "\n");
            ArrayList<Question> ret;
            ResultSet result = statement.executeQuery(query);
            ret = new ArrayList<Question>();
            while(result.next()) {
                int id_question =result.getInt(1);
                String statement_question=result.getString(2);
                String status = result.getString(3);
                int rank = result.getInt(4);

                ArrayList<Answer> answers = new ArrayList<>();
                AnswerDAO answerDAO = new AnswerDAO();
                answers = answerDAO.getAll(id_question);

                ret.add(new Question(id_question,statement_question,status,rank,id_form,answers));
            }
            connexion.close();
            return ret;
        } catch(SQLException e) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE,null,e);
            return null;
        }
    }
    
    // Créer une question avec une bonne réponse
    public void create(Question q, Answer a) {
        try {
            Connection connexion = null;
            ResultSet keys = null;

            try {
                Class.forName(DRIVER);
            } catch(ClassNotFoundException e) {
                Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE,null,e);
            }

            connexion = DriverManager.getConnection(URL,USERNAME,PASSWORD);
   
            String query = "INSERT INTO questions (statement,status,rank,form_id) VALUES (?,?,?,(SELECT id FROM forms WHERE id=?))";
            PreparedStatement statement = connexion.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            System.out.println("id_form : " + q.getId_form() + "\n");
            statement.setString(1,q.getStatement());
            statement.setString(2,q.getStatus());
            statement.setInt(3,q.getRank()); 
            statement.setInt(4,q.getId_form());

            System.out.println("Executing command : " + query + "\n");
            statement.executeUpdate();
            
            keys = statement.getGeneratedKeys();
            keys.next();
            int newKey = keys.getInt(1);
            q.setId(newKey);
            System.out.println("Id de la question créée : " + newKey + "\n");
            if (keys!= null) {
                keys.close();
            }

            AnswerDAO answerDAO = new AnswerDAO();
            a.setQuestion_id(newKey);
            answerDAO.create(a);
            connexion.close();
        } catch(SQLException e) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE,null,e);           
        }
    }
}
