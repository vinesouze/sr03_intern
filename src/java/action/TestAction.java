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
import Model.Test;
import Model.TestDAO;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author Junior
 */
public class TestAction extends ActionSupport implements SessionAware{
    private int questionRank = 1;
    private Question question;
    private ArrayList<Answer> answers = new ArrayList<>();
    private Test test = new Test();
    private int test_id;
    private String theme;
    Map<String, Object> map;
    
    public String FormTrainee() throws Exception {
        Map<String, Object> currentSession = ActionContext.getContext().getSession();
        getTest().setUser_id(currentSession.get("userMail").toString());

        System.out.println("sujet sélectionné : " + theme + "\n");

        // **** Choix aléatoire du formulaire parmi le thème sélectionné ***
        FormDAO formDAO = new FormDAO();
        ArrayList<Form> forms = formDAO.getAllbyTheme(theme);
        Random randomGenerator = new Random();
        int random_index = randomGenerator.nextInt(forms.size());
        Form selected_form = forms.get(random_index);
        System.out.println("id du form random : " + selected_form.getId() + "\n");
        getTest().setForm_id(selected_form.getId());

        QuestionDAO questiondao = new QuestionDAO();
        ArrayList<Question> questions = questiondao.getAll(test.getForm_id());
        
        for (Question this_question : questions) {
            if(this_question.getRank()== getQuestionRank()) {
                setQuestion(this_question);
                System.out.println("id de la question  : " + this_question.getId() + "\n");
            }
        }
        AnswerDAO answerdao = new AnswerDAO();
        System.out.println("id de la question  : " + getQuestion().getId() + "\n");
        setAnswers(answerdao.getAll(getQuestion().getId()));
        TestDAO testdao = new TestDAO();
        setTest_id(testdao.create_ReturningId(test));
        getTest().setId(test_id);
        System.out.println("user id : "  + test_id + "\n");
        System.out.println("Parcours id : "  + test_id + "\n");
        return "success";
    }
            
    // **** SETTER & GETTER

    public int getQuestionRank() {
        return questionRank;
    }

    public void setQuestionRank(int questionRank) {
        this.questionRank = questionRank;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<Answer> answers) {
        this.answers = answers;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public int getTest_id() {
        return test_id;
    }

    public void setTest_id(int test_id) {
        this.test_id = test_id;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.map=map;
    }
    
}
