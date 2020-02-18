package com.example.quizapp_pro;

public class Questions {

    //Creación de variables

    private String questionText;
    private int topicId;
    private int questionId;
    private Answers correctAnswer;
    private Answers fakeAnswer1;
    private Answers fakeAnswer2;
    private Answers fakeAnswer3;



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

    public Answers getCorrectAnswer() {
        return correctAnswer;
    }

    public Answers getFakeAnswer1() {
        return fakeAnswer1;
    }

    public Answers getFakeAnswer2() {
        return fakeAnswer2;
    }

    public Answers getFakeAnswer3() {
        return fakeAnswer3;
    }
//Constructor


    public Questions(String questionText, int topicId, int questionId, Answers correctAnswer, Answers fakeAnswer1, Answers fakeAnswer2, Answers fakeAnswer3) {
        this.questionText = questionText;
        this.topicId = topicId;
        this.questionId = questionId;
        this.correctAnswer = correctAnswer;
        this.fakeAnswer1 = fakeAnswer1;
        this.fakeAnswer2 = fakeAnswer2;
        this.fakeAnswer3 = fakeAnswer3;
    }
}
