/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import Model.Answer;
import Model.AnswerDAO;
import Model.Question;
import Model.QuestionDAO;
import Model.Test;
import Model.TestDAO;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Junior
 */
public class TestActionNext extends ActionSupport{
    private int questionRank;
    private Question question;
    private ArrayList<Answer> answers = new ArrayList<>();
    private int test_id;
    private int answer_id;
    private Test test = new Test();
    private int score=0;
    
    public String next() throws Exception {
        QuestionDAO questiondao = new QuestionDAO();
        TestDAO testdao = new TestDAO();
        setQuestionRank(getQuestionRank()+1);
        // TROUVER UNE AUTRE SOLUTION POUR EVITER DE RECUP A CHAQUE FOIS TOUTES LES REPONSES ET QUESTIONS
        ArrayList<Question> questions = questiondao.getAll(testdao.find(getTest_id()).get().getForm_id());
        // ajout de la réponse dans le parcours
        testdao.addAnswer(test_id, answer_id);
        // On regarde si c'était la dernière question
        if (questionRank > questions.size()) {
            setTest(testdao.find(test_id).get());
            setScore(getTest().score());

            // MODIFICATION du temps de la fin du test
            Date now = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(now);
            cal.set(Calendar.MILLISECOND,0);
            Timestamp convertTime = new Timestamp(cal.getTimeInMillis());
            getTest().setTime_end(convertTime);
            // ** UPDATE TEST avec le score ** /
            testdao.update(getTest());

            System.out.println("le score est de " + getScore() + "\n");
            return "result";
        }
        // On affiche la question suivante
        for (Question this_question : questions) {
            if (this_question.getRank() == getQuestionRank()) {
                setQuestion(this_question);
            }
        }
        // On affiche les réponses associées à cette question
        AnswerDAO answerdao = new AnswerDAO();
        setAnswers(answerdao.getAll(getQuestion().getId()));
        return "success";
        
    }
    
    // *** GETTER & SETTER **** //

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

    public int getTest_id() {
        return test_id;
    }

    public void setTest_id(int test_id) {
        this.test_id = test_id;
    }

    public int getAnswer_id() {
        return answer_id;
    }

    public void setAnswer_id(int answer_id) {
        this.answer_id = answer_id;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
    
    
}
