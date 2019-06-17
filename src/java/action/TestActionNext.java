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
import Model.Question;
import Model.QuestionAnswer;
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
    private FormResult result;
    private ArrayList<QuestionAnswer> questions_trainee = new ArrayList<>();
    
    public String next() throws Exception {
        QuestionDAO questiondao = new QuestionDAO();
        FormDAO formDAO = new FormDAO();
        TestDAO testdao = new TestDAO();
        AnswerDAO answerdao = new AnswerDAO();
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

            // ** MAJ du score et de la durée du test
            Date start=test.getTime_start();
            Timestamp timestamp1 = new Timestamp(start.getTime());

            long milliseconds = convertTime.getTime() - timestamp1.getTime();
            int seconds = (int) milliseconds/1000;
            int duree = seconds;
            Form form = formDAO.find(getTest().getForm_id()).get();
            setResult(new FormResult(form.getTheme(),form.getSubject(),getScore(),duree,test_id));
            
            // *** MAJ des questions et réponses du stagiaire pour ce test
            for (Answer answer : testdao.getAnswers(test_id)) {
                getQuestions_trainee().add(new QuestionAnswer(questiondao.find(answer.getQuestion_id()).get(),answer,answerdao.findGoodAnswer(answer.getQuestion_id()).get()));
            }
            
            return "result";
        }
        // On affiche la question suivante
        for (Question this_question : questions) {
            if (this_question.getRank() == getQuestionRank()) {
                setQuestion(this_question);
            }
        }
        // On affiche les réponses associées à cette question
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
