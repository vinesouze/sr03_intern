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
import Model.FormResult;
import Model.QuestionAnswer;
import Model.QuestionDAO;
import Model.Test;
import Model.TestDAO;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;

/**
 *
 * @author Junior
 */
public class ShowAction extends ActionSupport{
    private String traineeMail;
    private ArrayList<Form> forms = new ArrayList<>();
    private ArrayList<FormResult> results = new ArrayList<>();

    private int testId;
    private FormResult result;
    private ArrayList<QuestionAnswer> questions_trainee = new ArrayList<>();
    
    public String show() throws Exception {
        System.out.println("traineeMail : " + traineeMail + "\n");
        TestDAO testdao = new TestDAO();
        FormDAO formdao = new FormDAO();
        ArrayList<Test> tests = testdao.getAllbyUser(traineeMail);
        
        int i=0;
        for (Test test : tests) {
            Form form = formdao.find(test.getForm_id()).get();
            System.out.println(" ..... : " + form.getTheme() + " ; " + test.score() +  "\n");
            getResults().add(new FormResult(form.getTheme(),form.getSubject(),test.score(),1,test.getId()));
            System.out.println("result : " + i + " ; " + getResults().get(i).getSubject() + "\n");
            i++;

        }
        return "success";
    }

    public String showOne() throws Exception {
        QuestionDAO questiondao = new QuestionDAO();
        FormDAO formDAO = new FormDAO();
        TestDAO testdao = new TestDAO();
        AnswerDAO answerdao = new AnswerDAO();

        // *** MAJ des questions et réponses du stagiaire pour ce test
        for (Answer answer : testdao.getAnswers(testId)) {
            getQuestions_trainee().add(new QuestionAnswer(questiondao.find(answer.getQuestion_id()).get(),answer,answerdao.findGoodAnswer(answer.getQuestion_id()).get()));
        }

        return "success";
    }
    
    // *** GETTER & SETTER ***

    public String getTraineeMail() {
        return traineeMail;
    }

    public void setTraineeMail(String traineeMail) {
        this.traineeMail = traineeMail;
    }

    public ArrayList<Form> getForms() {
        return forms;
    }

    public void setForms(ArrayList<Form> forms) {
        this.forms = forms;
    }

    public ArrayList<FormResult> getResults() {
        return results;
    }

    public void setResults(ArrayList<FormResult> results) {
        this.results = results;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public FormResult getResult() {
        return result;
    }

    public void setResult(FormResult result) {
        this.result = result;
    }

    public ArrayList<QuestionAnswer> getQuestions_trainee() {
        return questions_trainee;
    }

    public void setQuestions_trainee(ArrayList<QuestionAnswer> questions_trainee) {
        this.questions_trainee = questions_trainee;
    }
    
    
}
