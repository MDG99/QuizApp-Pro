package com.example.quizapp_pro;

public class Questions {

    private String questionText;
    private String answerText;
    private String topicText;

    public String getQuestionText() {
        return questionText;
    }

    public String getAnswerText() {
        return answerText;
    }

    public String getTopicText() {
        return topicText;
    }

    Questions (String questionText, String answerText, String topicText){
        this.questionText = questionText;
        this.answerText = answerText;
        this.topicText = answerText;
    }

}
