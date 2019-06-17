/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import Model.Answer;
import Model.AnswerDAO;
import Model.Form;
import Model.FormDAO;
import Model.Question;
import Model.QuestionDAO;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Junior
 */
public class QuestionAction extends ActionSupport {
    public Question question = new Question();
    public ArrayList<Question> questions = new ArrayList<>();
    public Answer answer = new Answer();
    public int modifyIdForm;
    public int modifyIdQuestion;
    
    public String addQuestion() throws SQLException {
        System.out.println("id modifié : " + modifyIdForm + "\n");
        return "success";
    }
    
    public String addQuestionSubmit() throws SQLException {
        QuestionDAO questionDAO = new QuestionDAO();
        System.out.println("Statement : " + question.getStatement() + "\n");
        System.out.println("modifyIdForm : " + question.getId_form() + "\n");
        questions= questionDAO.getAll(question.getId_form());
        int rang = 1;
        for (Question question : questions) {
            if(rang <= question.getRank()) {
                rang++;
            }
        }
        question.setRank(rang);
        
        AnswerDAO answerDAO = new AnswerDAO();
        System.out.println("Statement reponse : " + answer.getStatement() + "\n");
   
        questionDAO.create(getQuestion(), getAnswer());
        questions = questionDAO.getAll(question.getId_form());
        return "success";
    }

    public String listQuestion() throws Exception {
        QuestionDAO questionDAO = new QuestionDAO();
        setQuestions(questionDAO.getAll(getModifyIdForm()));
        return "success";
    }

    public String activateQuestion() throws SQLException{
        QuestionDAO questiondao = new QuestionDAO();
        question = questiondao.find(modifyIdQuestion).get();
        System.out.println("id question : " + question.getStatus() + "\n");
        if((question.getStatus()).equals("actif")) 
            question.setStatus("inactif"); 
        else 
            question.setStatus("actif");
        questiondao.update(getQuestion());
        
        // On actualise toutes les listes questions
        setQuestions(questiondao.getAll(getModifyIdForm()));
        /*
        Questions_actif = new ArrayList<User>();
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

    public String modifyQuestion() throws Exception {
        System.out.println("id modifié : " + modifyIdQuestion + "\n");
        QuestionDAO questiondao = new QuestionDAO();
        question = questiondao.find(modifyIdQuestion).get();
        return "success";
    }

    public String modifyQuestionSubmit() throws Exception {
        QuestionDAO questiondao = new QuestionDAO();
        questiondao.update(getQuestion());
        
        // On actualise toutes les listes questions
        setQuestions(questiondao.getAll(getQuestion().getId_form()));
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

    public String down() throws Exception {
        QuestionDAO questionDAO = new QuestionDAO();
        Question question_this = questionDAO.find(getModifyIdQuestion()).get();
        
        setQuestions(questionDAO.getAll(getModifyIdForm()));
        
        if (question_this.getRank() < getQuestions().size()) {
            Question question_change = getQuestions().get(question_this.getRank()); // la question changé est celle qui suit la question actuelle
            int temp_rank = question_this.getRank();
            question_this.setRank(question_change.getRank());
            question_change.setRank(temp_rank);
            
            questionDAO.update(question_this);
            questionDAO.update(question_change);
        }
        
        setQuestions(questionDAO.getAll(getModifyIdForm()));
        return "success";
    }
    
    public String up() throws Exception {
        QuestionDAO questionDAO = new QuestionDAO();
        Question question_this = questionDAO.find(getModifyIdQuestion()).get();
        
        setQuestions(questionDAO.getAll(getModifyIdForm()));
        
        if (question_this.getRank() > 1) {
            Question question_change = getQuestions().get(question_this.getRank()-2); // la question changé est celle qui précède la question actuelle
            int temp_rank = question_this.getRank();
            question_this.setRank(question_change.getRank());
            question_change.setRank(temp_rank);
            
            questionDAO.update(question_this);
            questionDAO.update(question_change);
        }
        
        setQuestions(questionDAO.getAll(getModifyIdForm()));
        return "success";
    }

    // *** GETTER & SETTER ****

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public int getModifyIdForm() {
        return modifyIdForm;
    }

    public void setModifyIdForm(int modifyIdForm) {
        this.modifyIdForm = modifyIdForm;
    }

    public int getModifyIdQuestion() {
        return modifyIdQuestion;
    }

    public void setModifyIdQuestion(int modifyIdQuestion) {
        this.modifyIdQuestion = modifyIdQuestion;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }
    
}
