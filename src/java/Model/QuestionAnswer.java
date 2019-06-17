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
public class QuestionAnswer {
    private Question question;
    private Answer answer;
    private Answer answer_good;

    public QuestionAnswer(Question question, Answer answer, Answer answer_good) {
        this.question = question;
        this.answer = answer;
        this.answer_good = answer_good;
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

    public Answer getAnswer_good() {
        return answer_good;
    }

    public void setAnswer_good(Answer answer_good) {
        this.answer_good = answer_good;
    }
    
    
}
