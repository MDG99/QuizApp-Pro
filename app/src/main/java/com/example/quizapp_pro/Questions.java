package com.example.quizapp_pro;

public class Questions {

    //Creación de variables

    private String questionText;
    private String answerText;
    private int topicId;
    private int questionId;

    //Métodos get para cada variable

    public String getQuestionText() {
        return questionText;
    }

    public String getAnswerText() {
        return answerText;
    }

    public int getTopicId() {
        return topicId;
    }

    public int getQuestionId() {
        return questionId;
    }

    //Constructor

    Questions(String questionText, String answerText, int topicId, int questionId) {
        this.questionText = questionText;
        this.answerText = answerText;
        this.topicId = topicId;
        this.questionId = questionId;
    }

}
