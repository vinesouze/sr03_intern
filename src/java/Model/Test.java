/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Junior
 */
public class Test {
    private int id;
    private String user_id;
    private int form_id;
    private Timestamp time_start;
    private Timestamp time_end;
    private ArrayList<Answer> answers = new ArrayList<>();
    private int score;

    public Test() {
    }

    public Test(int id, int score, String user_id, int form_id, Timestamp time_start, Timestamp time_end,ArrayList<Answer> answers) {
        this.id = id;
        this.user_id = user_id;
        this.form_id = form_id;
        this.time_start = time_start;
        this.time_end = time_end;
        this.answers = answers;
        this.score = score;
    }
    
    public int score(){
        TestDAO testdao = new TestDAO();
        ArrayList<Answer> answers = testdao.getAnswers(id);
        int score=0;
        for (Answer answer : answers) {
            if(answer.Is_right()) {
                score++;
            }
        }
        return score;
    }
    
    // ** GETTER & SETTER ** //

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getForm_id() {
        return form_id;
    }

    public void setForm_id(int form_id) {
        this.form_id = form_id;
    }

    public Timestamp getTime_start() {
        return time_start;
    }

    public void setTime_start(Timestamp time_start) {
        this.time_start = time_start;
    }

    public Timestamp getTime_end() {
        return time_end;
    }

    public void setTime_end(Timestamp time_end) {
        this.time_end = time_end;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<Answer> answers) {
        this.answers = answers;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
}
