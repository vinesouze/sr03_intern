/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import Model.Form;
import Model.FormDAO;
import Model.FormResult;
import Model.Test;
import Model.TestDAO;
import Model.Theme;
import Model.ThemeDAO;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.inject.Context;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author Junior
 */
public class TraineeAction extends ActionSupport implements SessionAware{
    private ArrayList<Form> forms = new ArrayList<>();
    private ArrayList<Theme> themes = new ArrayList<>();
    private ArrayList<FormResult> results = new ArrayList<>();
    Map<String, Object> map;
    
    public String listFormTrainee() throws Exception {
        ThemeDAO themeDAO = new ThemeDAO();
        setThemes(themeDAO.getAll());
        return "success";
    }
    
    public String seeResult() throws Exception {
        Map<String, Object> currentSession = ActionContext.getContext().getSession();
        TestDAO testdao = new TestDAO();
        FormDAO formdao = new FormDAO();
        ArrayList<Test> tests = testdao.getAllbyUser(currentSession.get("userMail").toString());
        
        for (Test test : tests) {
            Form form = formdao.find(test.getForm_id()).get();
            Date start = test.getTime_start();
            System.out.println("durée : " + test.getTime_start() + "\n");
            Date end = test.getTime_end();
            Timestamp timestamp1 = new Timestamp(start.getTime());
            Timestamp timestamp2 = new Timestamp(end.getTime());
            
            // différence de temps en secondes
            long milliseconds = timestamp2.getTime() - timestamp1.getTime();
            int seconds = (int) milliseconds / 1000;
            int duree = seconds;

            // calcul en heures, minutes et secondes
            int hours = seconds / 3600;
            int minutes = (seconds % 3600) / 60;
            seconds = (seconds % 3600) % 60;
            System.out.println("durée : " + hours + " heures " + minutes + " minutes " + seconds + "secondes \n");
                    
            getResults().add(new FormResult(form.getTheme(),form.getSubject(),test.getScore(),duree));
        }
        return "success";
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

    @Override
    public void setSession(Map<String, Object> map) {
        this.map = map;
    }

    public ArrayList<Theme> getThemes() {
        return themes;
    }

    public void setThemes(ArrayList<Theme> themes) {
        this.themes = themes;
    }
    
    
}
