/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Junior
 */
public class FormResult {
    private String theme;
    private String subject;
    private int score;
    private int duree;
    private int test_id;

    public FormResult(String theme, String subject, int score, int duree, int test_id) {
        this.theme = theme;
        this.subject = subject;
        this.score = score;
        this.duree = duree;
        this.test_id = test_id;
    }

    public int hours() {
        return duree/3600;
    }

    public int minutes() {
        return (duree % 3600) / 60 ;
    }

    public int seconds() {
        return (duree % 3600) % 60;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    } 

    public long getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public int getTest_id() {
        return test_id;
    }

    public void setTest_id(int test_id) {
        this.test_id = test_id;
    }
    
}
