package com.example.quizapp_pro;

public class Answers {

    //Creación de variables

    private int questionId;
    private String answerText;
    private boolean answerChecker;

    //Métodos get para cada variable

    public int getQuestionId() {
        return questionId;
    }

    public String getAnswerText() {
        return answerText;
    }

    public boolean isAnswerChecker() {
        return answerChecker;
    }

    //Constructor

    public Answers(int questionId, String answerText, boolean answerChecker) {
        this.questionId = questionId;
        this.answerText = answerText;
        this.answerChecker = answerChecker;
    }
}
