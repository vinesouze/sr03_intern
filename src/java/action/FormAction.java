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
import Model.Theme;
import Model.ThemeDAO;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Junior
 */
public class FormAction extends ActionSupport {
    public Form form = new Form();
    public Question question = new Question();
    public Answer answer = new Answer();
    
    public ArrayList<Form> forms = new ArrayList<>();
    public Theme theme = new Theme();
    public ArrayList<Theme> themes = new ArrayList<>();
    public ArrayList<Question> questions = new ArrayList<>();
    public int modifyId;
    public String adminMail;
    
    public String addForm() throws SQLException {
        FormDAO formDAO = new FormDAO();
        System.out.println("Form subject : " + form.getSubject() + "\n");
        System.out.println("Form theme : " + form.getTheme() + "\n");
        getForm().setStatus("inactif");

        QuestionDAO questionDAO = new QuestionDAO();
        System.out.println("Statement question: " + question.getStatement() + "\n");
        System.out.println("Statement answer: " + answer.getStatement() + "\n");

        formDAO.create(getForm(),getQuestion(),getAnswer());
        setForms(formDAO.getAll());
        return "success";
    }
    
    public String listForm() throws SQLException {
        FormDAO formDAO = new FormDAO();
        setForms(formDAO.getAll());
        return "success";
    }
    
    public String activateForm() throws SQLException {
        FormDAO formdao = new FormDAO();
        form = formdao.find(modifyId).get();
        System.out.println("actif : " + form.getStatus() + "\n");
        if((form.getStatus()).equals("actif")) 
            form.setStatus("inactif"); 
        else 
            form.setStatus("actif");
        formdao.update(getForm());
        setForms(formdao.getAll());
        return "success";
    }
    
    public String modifyForm() throws Exception {
        String themes_ok = listTheme();
        String questions = listQuestion();
        System.out.println("id modifié : " + modifyId + "\n");
        FormDAO formdao = new FormDAO();
        form = formdao.find(modifyId).get();
        return "success";
    }
    
    public String modifyFormSubmit() throws Exception {
        FormDAO formdao = new FormDAO();
        System.out.println("id modifié : " + form.getId() + "\n");
        formdao.update(getForm());
        setForms(formdao.getAll());
        return "success";
    }
    
    public String addTheme() throws SQLException {
        ThemeDAO themeDAO = new ThemeDAO();
        System.out.println("Theme : " + theme.getSubject() + "\n");
        themeDAO.create(getTheme());
        setThemes(themeDAO.getAll());
        FormDAO formDAO = new FormDAO();
        setForms(formDAO.getAll());
        return "success";
    }
    
    public String listTheme() throws SQLException {
        ThemeDAO themeDAO = new ThemeDAO();
        setThemes(themeDAO.getAll());
        return "success";
    }

    public String listQuestion() throws SQLException {
        QuestionDAO questionDAO = new QuestionDAO();
        setQuestions(questionDAO.getAll(modifyId));
        return "success";
    }

    // *** GETTER & SETTER ***** 
    
    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public ArrayList<Form> getForms() {
        return forms;
    }

    public void setForms(ArrayList<Form> forms) {
        this.forms = forms;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public ArrayList<Theme> getThemes() {
        return themes;
    }

    public void setThemes(ArrayList<Theme> themes) {
        this.themes = themes;
    }

    public int getModifyId() {
        return modifyId;
    }

    public void setModifyId(int modifyId) {
        this.modifyId = modifyId;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public String getAdminMail() {
        return adminMail;
    }

    public void setAdminMail(String adminMail) {
        this.adminMail = adminMail;
    }    

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }
    
}
