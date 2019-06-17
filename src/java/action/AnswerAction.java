/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import Model.Answer;
import Model.AnswerDAO;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;
import java.util.ArrayList;
import static javafx.scene.input.KeyCode.O;

/**
 *
 * @author Junior
 */
public class AnswerAction extends ActionSupport {
    public Answer answer = new Answer();
    public ArrayList<Answer> answers = new ArrayList<>();
    public int modifyIdQuestion;
    public int modifyIdForm;
    public int modifyIdAnswer;
    
    public String addAnswer() throws SQLException {
        System.out.println("id modifié : " + modifyIdQuestion + "\n");
        return "success";
    }
    
    public String addAnswerSubmit() throws SQLException {
        AnswerDAO answerDAO = new AnswerDAO();
        System.out.println("Statement : " + answer.getStatement() + "\n");
        System.out.println("modifyIdQuestion : " + answer.getQuestion_id() + "\n");
        answers= answerDAO.getAll(answer.getQuestion_id());
        int rank = 1;
        for (Answer answer : answers) {
            if(rank <= answer.getRank()) {
                rank++;
            }
        }
        answer.setRank(rank);
        answerDAO.create(getAnswer());
        answers = answerDAO.getAll(answer.getQuestion_id());
        return "success";
    }

    public String modifyAnswer() throws Exception {
        System.out.println("id modifié : " + modifyIdAnswer + "\n");
        AnswerDAO answerdao = new AnswerDAO();
        answer = answerdao.find(modifyIdAnswer).get();
        return "success";
    }

    public String modifyAnswerSubmit() throws Exception {
        AnswerDAO answerdao = new AnswerDAO();
        answerdao.update(getAnswer());
        
        // On actualise toutes les listes Answers
        setAnswers(answerdao.getAll(getAnswer().getQuestion_id()));
        /*
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
        */
        return "success";
    }

    public String activateAnswer() throws SQLException{
        AnswerDAO answerdao = new AnswerDAO();
        answer = answerdao.find(modifyIdAnswer).get();
        System.out.println("id answer : " + answer.getStatus() + "\n");
        if((answer.getStatus()).equals("actif")) 
            answer.setStatus("inactif"); 
        else 
            answer.setStatus("actif");
        answerdao.update(getAnswer());
        
        // On actualise toutes les listes answers
        setAnswers(answerdao.getAll(getModifyIdQuestion()));
        /*
        answers_actif = new ArrayList<User>();
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
        */
        return "success";
    }

    public String listAnswer() throws Exception {
        AnswerDAO answerDAO = new AnswerDAO();
        setAnswers(answerDAO.getAll(getModifyIdQuestion()));
        return "success";
    }

    public String down() throws Exception {
        AnswerDAO answerDAO = new AnswerDAO();
        Answer answer_this = answerDAO.find(getModifyIdAnswer()).get();
        
        setAnswers(answerDAO.getAll(getModifyIdQuestion()));
        
        if (answer_this.getRank() < getAnswers().size()) {
            Answer answer_change = getAnswers().get(answer_this.getRank()); // la réponse changée est celle qui suit la réponse actuelle
            int temp_rank = answer_this.getRank();
            answer_this.setRank(answer_change.getRank());
            answer_change.setRank(temp_rank);
            
            answerDAO.update(answer_this);
            answerDAO.update(answer_change);
        }
        
        setAnswers(answerDAO.getAll(getModifyIdQuestion()));
        return "success";
    }
    
    public String up() throws Exception {
        AnswerDAO answerDAO = new AnswerDAO();
        Answer answer_this = answerDAO.find(getModifyIdAnswer()).get();
        
        setAnswers(answerDAO.getAll(getModifyIdQuestion()));
        
        if (answer_this.getRank() > 1) {
            Answer answer_change = getAnswers().get(answer_this.getRank()-2); // la réponse changée est celle qui précède la réponse actuelle
            int temp_rank = answer_this.getRank();
            answer_this.setRank(answer_change.getRank());
            answer_change.setRank(temp_rank);
            
            answerDAO.update(answer_this);
            answerDAO.update(answer_change);
        }
        
        setAnswers(answerDAO.getAll(getModifyIdQuestion()));
        return "success";
    }

    // *** GETTER & SETTER *** 

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<Answer> answers) {
        this.answers = answers;
    }

    public int getModifyIdQuestion() {
        return modifyIdQuestion;
    }

    public void setModifyIdQuestion(int modifyIdQuestion) {
        this.modifyIdQuestion = modifyIdQuestion;
    }

    public int getModifyIdAnswer() {
        return modifyIdAnswer;
    }

    public void setModifyIdAnswer(int modifyIdAnswer) {
        this.modifyIdAnswer = modifyIdAnswer;
    }

    public int getModifyIdForm() {
        return modifyIdForm;
    }

    public void setModifyIdForm(int modifyIdForm) {
        this.modifyIdForm = modifyIdForm;
    }
    
}
