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
public class Question {
    private int id;
    private String statement;
    private String status;
    private int rank;
    private int id_form;
    private ArrayList<Answer> answers;
    
    public Question(){}

    public Question(int id, String statement, String status, int rank, int id_form, ArrayList<Answer> answers) {
        this.id = id;
        this.statement = statement;
        this.status = status;
        this.answers = answers;
        this.rank = rank;
        this.id_form = id_form;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<Answer> answers) {
        this.answers = answers;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getId_form() {
        return id_form;
    }

    public void setId_form(int id_form) {
        this.id_form = id_form;
    }
    
    @Override   
    public String toString() {
        return "Question(" + "id= " + id + ", statement= " + statement + ", status= " + status + ")" ;
    }
}
