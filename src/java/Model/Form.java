/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author Junior
 */
public class Form {
    private int id;
    private String theme;
    private String subject;
    private String status;
    private int nb_question;
    private int nb_answer;
    private ArrayList<Question> questions;

    public Form() {
    }

    public Form(int id, String theme, String subject, String status,ArrayList<Question> questions) {
        this.id = id;
        this.theme = theme;
        this.subject = subject;
        this.status = status;
        this.questions = questions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public int getNb_question() {
        return nb_question;
    }

    public void setNb_question(int nb_question) {
        this.nb_question = nb_question;
    }

    public int getNb_answer() {
        return nb_answer;
    }

    public void setNb_answer(int nb_answer) {
        this.nb_answer = nb_answer;
    }
    
    
}
