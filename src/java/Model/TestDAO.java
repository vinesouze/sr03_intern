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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Junior
 */
public class TestDAO implements DAO<Test> {
    @Override
    public Optional<Test> find(String s) {
        return null;
    }
    
    @Override
    public Optional<Test> find(int id) {
        try {
            Connection connexion = null;
            Statement statement = null;
            
            try {
                Class.forName(DRIVER);
            } catch(ClassNotFoundException e) {
                Logger.getLogger(TestDAO.class.getName()).log(Level.SEVERE,null,e);
            }
            connexion = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            statement = connexion.createStatement();
            
            String query = "SELECT * FROM tests WHERE id= '" + id + "';";
            System.out.println("Executing command: " + query + "\n");
            ResultSet result = statement.executeQuery(query);
            
            result.next();
            int id_test =result.getInt(1);
            int score=result.getInt(2);
            String user_id=result.getString(3);
            int form_id = result.getInt(4);
            Timestamp time_start = result.getTimestamp(5);
            Timestamp time_end = result.getTimestamp(6);

            result.close();
            
            TestDAO testDAO = new TestDAO();
            ArrayList<Answer> answers = testDAO.getAnswers(id_test);
            
            connexion.close();
            
            Test ret = new Test(id_test,score,user_id,form_id,time_start,time_end,answers);
            return Optional.ofNullable(ret);
        } catch(SQLException e) {
            Logger.getLogger(TestDAO.class.getName()).log(Level.SEVERE,null,e);
            return null;
        }
    }
    
    @Override
    public ArrayList<Test> getAll() {
        try {
            Connection connexion = null;
            Statement statement = null;

            try {
                Class.forName(DRIVER);
            } catch(ClassNotFoundException e) {
                Logger.getLogger(TestDAO.class.getName()).log(Level.SEVERE,null,e);
            }
            connexion = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            statement = connexion.createStatement();

            String query = "SELECT * FROM tests";
            System.out.println("Executing command : " + query + "\n");
            ArrayList<Test> ret;
            ResultSet result = statement.executeQuery(query);
            ret = new ArrayList<Test>();
            while(result.next()) {
                int id_test =result.getInt(1);
                int score=result.getInt(2);
                String user_id=result.getString(3);
                int form_id = result.getInt(4);
                Timestamp time_start = result.getTimestamp(5);
                Timestamp time_end = result.getTimestamp(6);
                TestDAO testDAO = new TestDAO();
                ArrayList<Answer> answers = testDAO.getAnswers(id_test);

                ret.add(new Test(id_test,score,user_id,form_id,time_start,time_end,answers));
            }
            result.close();
            connexion.close();
            return ret;
        } catch(SQLException e) {
            Logger.getLogger(TestDAO.class.getName()).log(Level.SEVERE,null,e);
            return null;
        }
    }
    
    @Override
    public void create(Test t) {
        try {
            Connection connexion = null;

            try {
                Class.forName(DRIVER);
            } catch(ClassNotFoundException e) {
                Logger.getLogger(TestDAO.class.getName()).log(Level.SEVERE,null,e);
            }

            connexion = DriverManager.getConnection(URL,USERNAME,PASSWORD);

            String query = "INSERT INTO tests (user_id, form_id,time_start,time_end) VALUES ((SELECT id FROM users WHERE email=?),(SELECT id FROM forms WHERE id=?),?,?,?)";
            PreparedStatement statement = connexion.prepareStatement(query);
            statement.setString(1,t.getUser_id());
            statement.setInt(2,t.getForm_id());

            Date now = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(now);
            cal.set(Calendar.MILLISECOND,0);
            Timestamp convertTime = new Timestamp(cal.getTimeInMillis());
            t.setTime_start(convertTime);
            t.setTime_end(convertTime);
            
            statement.setTimestamp(3,convertTime);
            statement.setTimestamp(4,convertTime);
            

            System.out.println("Executing command : " + query + "\n");
            statement.executeUpdate();
            statement.close();
            
            for (Answer answer : t.getAnswers()) {
                query = "INSERT INTO test_answer VALUES(?,?)";
                try (PreparedStatement st = connexion.prepareStatement(query)){
                    st.setInt(1,t.getId());
                    st.setInt(2,answer.getId());
                    System.out.println("Executing command : " + query + "\n");
                    st.executeUpdate();
                }
            }
            connexion.close();
        } catch(SQLException e) {
            Logger.getLogger(TestDAO.class.getName()).log(Level.SEVERE,null,e);           
        }
    }
    
    @Override
    public void update(Test t) {
        try {
            Connection connexion = null;

            try {
                Class.forName(DRIVER);
            } catch(ClassNotFoundException e) {
                Logger.getLogger(TestDAO.class.getName()).log(Level.SEVERE,null,e);
            }
            connexion = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            String query = "UPDATE tests SET score=?,user_id=?,form_id=?,time_end=? WHERE id=?;";
            PreparedStatement statement = connexion.prepareStatement(query);
            statement.setInt(1,t.score());
            statement.setString(2,t.getUser_id());
            statement.setInt(3,t.getForm_id());
            
            
            Calendar cal_end = Calendar.getInstance();
            cal_end.setTime(t.getTime_end());
            cal_end.set(Calendar.MILLISECOND,0);
            Timestamp convertTime = new Timestamp(cal_end.getTimeInMillis());
            statement.setTimestamp(4,convertTime);
            
            statement.setInt(5,t.getId());
            System.out.println("Executing command : " + query + "\n");
            statement.executeUpdate();
            connexion.close();
        } catch(SQLException e) {
            Logger.getLogger(TestDAO.class.getName()).log(Level.SEVERE,null,e);
        }
    }
    
     public void delete(Test t) {
        try {
            Connection connexion = null;

            try {
                Class.forName(DRIVER);
            } catch(ClassNotFoundException e) {
                Logger.getLogger(TestDAO.class.getName()).log(Level.SEVERE,null,e);
            }

            connexion = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            String query = "DELETE FROM tests WHERE id=?";
            try (PreparedStatement statement = connexion.prepareStatement(query)) {
                statement.setInt(1,t.getId());
                System.out.println("Executing command : " + query + "\n");
                statement.executeUpdate();
            }
            
            query = "DELETE FROM test_answer WHERE test_id = " + t.getId() + ";";
            try (PreparedStatement st = connexion.prepareStatement(query)) {
                System.out.println("Executing command : " + query + "\n");
                st.executeUpdate();
            }
            connexion.close();
        } catch (SQLException e) {
            Logger.getLogger(TestDAO.class.getName()).log(Level.SEVERE,null,e);            
        }
    }
     
     /* Retourne une liste de réponses d'un parcours donné en paramètre */
    public ArrayList<Answer> getAnswers(int id_test) {
        try {
            Connection connexion = null;
            Statement statement = null;
            
            try {
                Class.forName(DRIVER);
            } catch(ClassNotFoundException e) {
                Logger.getLogger(TestDAO.class.getName()).log(Level.SEVERE,null,e);
            }
            connexion = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            statement = connexion.createStatement();
            
            ArrayList<Answer> answers = new ArrayList<>();
            String query = "SELECT * FROM test_answer WHERE test_id= '" + id_test + "';";
            System.out.println("Executing command: " + query + "\n");
            ResultSet result = statement.executeQuery(query);
            while(result.next()) {
                int id_answer = result.getInt(2);
                AnswerDAO answerDAO = new AnswerDAO();
                answers.add(answerDAO.find(id_answer).get());
            }

            result.close();
            connexion.close();
            return answers;
        } catch(SQLException e) {
            Logger.getLogger(TestDAO.class.getName()).log(Level.SEVERE,null,e);
            return null;
        }
    }
    /* Créer un parcours et retourne l'id du parcours créé */
    public int create_ReturningId(Test t) {
        try {
            Connection connexion = null;
            ResultSet keys = null;

            try {
                Class.forName(DRIVER);
            } catch(ClassNotFoundException e) {
                Logger.getLogger(TestDAO.class.getName()).log(Level.SEVERE,null,e);
            }

            connexion = DriverManager.getConnection(URL,USERNAME,PASSWORD);

            String query = "INSERT INTO tests (user_id, form_id,time_start,time_end) VALUES ((SELECT email FROM users WHERE email=?),(SELECT id FROM forms WHERE id=?),?,?)";
            PreparedStatement statement = connexion.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            
            statement.setString(1,t.getUser_id());
            statement.setInt(2,t.getForm_id());
            // initialisation des champs time_start et time_end
            Date now = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(now);
            cal.set(Calendar.MILLISECOND,0);
            Timestamp convertTime = new Timestamp(cal.getTimeInMillis());
            t.setTime_start(convertTime);
            t.setTime_end(convertTime);
            
            statement.setTimestamp(3,convertTime);
            statement.setTimestamp(4,convertTime);
            

            System.out.println("Executing command : " + query + "\n");
            statement.executeUpdate();
            
            keys = statement.getGeneratedKeys();
            keys.next();
            int newKey = keys.getInt(1);
            t.setId(newKey);
            System.out.println("Id du test créé : " + newKey + "\n");
            if (keys!= null) {
                keys.close();
            }
            
            for (Answer answer : t.getAnswers()) {
                query = "INSERT INTO test_answer VALUES(?,?)";
                try (PreparedStatement st = connexion.prepareStatement(query)){
                    st.setInt(1,t.getId());
                    st.setInt(2,answer.getId());
                    System.out.println("Executing command : " + query + "\n");
                    st.executeUpdate();
                }
            }
            connexion.close();
            return newKey;
        } catch(SQLException e) {
            Logger.getLogger(TestDAO.class.getName()).log(Level.SEVERE,null,e);    
            return 0;
        }
    }
    /* Ajoute la réponse choisie par le stagiaire au parcours qu'il est en train de faire */
    public void addAnswer(int test_id,int answer_id) {
        try {
            Connection connexion = null;

            try {
                Class.forName(DRIVER);
            } catch(ClassNotFoundException e) {
                Logger.getLogger(TestDAO.class.getName()).log(Level.SEVERE,null,e);
            }

            connexion = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            String query = "INSERT INTO test_answer VALUES(?,?)";
            try (PreparedStatement statement = connexion.prepareStatement(query)) {
                statement.setInt(1, test_id);
                statement.setInt(2, answer_id);
                System.out.println("Executing command : " + query + "\n");
                statement.executeUpdate();
            }
            connexion.close();
        } catch(SQLException e) {
            Logger.getLogger(TestDAO.class.getName()).log(Level.SEVERE,null,e);           
        }
    }
    
    /* Retourne les parcours d'un utilisateur donné */
    public ArrayList<Test> getAllbyUser(String user_id) {
        try {
            Connection connexion = null;
            Statement statement = null;

            try {
                Class.forName(DRIVER);
            } catch(ClassNotFoundException e) {
                Logger.getLogger(TestDAO.class.getName()).log(Level.SEVERE,null,e);
            }
            connexion = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            statement = connexion.createStatement();

            String query = "SELECT * FROM tests WHERE user_id = '" + user_id +"';";
            System.out.println("Executing command : " + query + "\n");
            ArrayList<Test> ret;
            ResultSet result = statement.executeQuery(query);
            ret = new ArrayList<Test>();
            while(result.next()) {
                int id_test =result.getInt(1);
                int score=result.getInt(2);
                int form_id = result.getInt(4);
                Timestamp time_start = result.getTimestamp(5);
                Timestamp time_end = result.getTimestamp(6);
                TestDAO testDAO = new TestDAO();
                ArrayList<Answer> answers = testDAO.getAnswers(id_test);

                ret.add(new Test(id_test,score,user_id,form_id,time_start,time_end,answers));
            }
            result.close();
            connexion.close();
            return ret;
        } catch(SQLException e) {
            Logger.getLogger(TestDAO.class.getName()).log(Level.SEVERE,null,e);
            return null;
        }
    }
}
