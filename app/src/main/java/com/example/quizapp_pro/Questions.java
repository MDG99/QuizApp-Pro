package com.example.quizapp_pro;

public class Questions {

    //Creación de variables

    private String questionText;
    private int topicId;
    private int questionId;

    //Métodos get para cada variable

    public String getQuestionText() {
        return questionText;
    }

    public int getTopicId() {
        return topicId;
    }

    public int getQuestionId() {
        return questionId;
    }

    //Constructor

    Questions(String questionText, int topicId, int questionId) {
        this.questionText = questionText;
        this.topicId = topicId;
        this.questionId = questionId;
    }

}
