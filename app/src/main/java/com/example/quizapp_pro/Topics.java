package com.example.quizapp_pro;

public class Topics {

    //Creación de variables

    private int topicId;
    private String topicText;

    //Métodos get para cada variable

    public int getTopicId() {
        return topicId;
    }

    public String getTopicText() {
        return topicText;
    }

    //Constructor

    public Topics(int topicId, String topicText) {
        this.topicId = topicId;
        this.topicText = topicText;
    }
}
