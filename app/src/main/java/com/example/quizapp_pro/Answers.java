package com.example.quizapp_pro;

public class Answers {

    //Creación de variables

    private String answerText;
    private boolean answerChecker;

    //Métodos get para cada variable

    public String getAnswerText() {
        return answerText;
    }

    public boolean isAnswerChecker() {
        return answerChecker;
    }

    //Constructor

    public Answers(String answerText, boolean answerChecker) {
        this.answerText = answerText;
        this.answerChecker = answerChecker;
    }
}
