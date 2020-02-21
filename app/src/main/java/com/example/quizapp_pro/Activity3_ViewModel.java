package com.example.quizapp_pro;

import android.graphics.Color;

import androidx.lifecycle.ViewModel;

import java.util.List;

public class Activity3_ViewModel extends ViewModel {
    private String nickname;
    private int COLOR_NORMAL = Color.rgb(55, 134, 197);
    private int COLOR_CORRECTO = Color.rgb(55, 197, 62);
    private int COLOR_INCORRECTO = Color.rgb(197, 55, 72);
    private int COLOR_TRAMPA = Color.rgb(46, 55, 58);
    private int difficult = 4;
    private int currentQuestion = 0;
    private int questionsQuantity;
    private int[] cheatsCounterByQuestion;
    private int questionsForTopic;
    private int residueQuestionsForTopic;
    private int cheatsQuantity = 5;
    private int Puntaje = 0;
    private int[] topicsToAsk;
    private int[][] colorsAnswers;

    private boolean cheatsEnable = true;
    private boolean cheatRecorder = false;
    private boolean cheatsFollowerEnable[];
    private boolean[][] habilitadorDeRespuestas;
    private boolean[] puntajeCheats;
    private boolean[] habilitadorDeCheats;
    private boolean[] Respondido;

    private List<Questions> questionsToShow;
    private Questions[] questionsToShowSaved;

    private Answers[][] answersToShowSaved;
    private Answers[][] answersToShow;

    public String getNickname() {
        return nickname;
    }

    public int getCOLOR_NORMAL() {
        return COLOR_NORMAL;
    }

    public int getCOLOR_CORRECTO() {
        return COLOR_CORRECTO;
    }

    public int getCOLOR_INCORRECTO() {
        return COLOR_INCORRECTO;
    }

    public int getCOLOR_TRAMPA() {
        return COLOR_TRAMPA;
    }

    public int getDifficult() {
        return difficult;
    }

    public int getCurrentQuestion() {
        return currentQuestion;
    }

    public int getQuestionsQuantity() {
        return questionsQuantity;
    }

    public int[] getCheatsCounterByQuestion() {
        return cheatsCounterByQuestion;
    }

    public int getQuestionsForTopic() {
        return questionsForTopic;
    }

    public int getResidueQuestionsForTopic() {
        return residueQuestionsForTopic;
    }

    public int getCheatsQuantity() {
        return cheatsQuantity;
    }

    public int getPuntaje() {
        return Puntaje;
    }

    public int[] getTopicsToAsk() {
        return topicsToAsk;
    }

    public int[][] getColorsAnswers() {
        return colorsAnswers;
    }

    public boolean isCheatsEnable() {
        return cheatsEnable;
    }

    public boolean isCheatRecorder() {
        return cheatRecorder;
    }

    public boolean[] getCheatsFollowerEnable() {
        return cheatsFollowerEnable;
    }

    public boolean[][] getHabilitadorDeRespuestas() {
        return habilitadorDeRespuestas;
    }

    public boolean[] getPuntajeCheats() {
        return puntajeCheats;
    }

    public boolean[] getHabilitadorDeCheats() {
        return habilitadorDeCheats;
    }

    public boolean[] getRespondido() {
        return Respondido;
    }

    public List<Questions> getQuestionsToShow() {
        return questionsToShow;
    }

    public Questions[] getQuestionsToShowSaved() {
        return questionsToShowSaved;
    }

    public Answers[][] getAnswersToShowSaved() {
        return answersToShowSaved;
    }

    public Answers[][] getAnswersToShow() {
        return answersToShow;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setCOLOR_NORMAL(int COLOR_NORMAL) {
        this.COLOR_NORMAL = COLOR_NORMAL;
    }

    public void setCOLOR_CORRECTO(int COLOR_CORRECTO) {
        this.COLOR_CORRECTO = COLOR_CORRECTO;
    }

    public void setCOLOR_INCORRECTO(int COLOR_INCORRECTO) {
        this.COLOR_INCORRECTO = COLOR_INCORRECTO;
    }

    public void setCOLOR_TRAMPA(int COLOR_TRAMPA) {
        this.COLOR_TRAMPA = COLOR_TRAMPA;
    }

    public void setDifficult(int difficult) {
        this.difficult = difficult;
    }

    public void setCurrentQuestion(int currentQuestion) {
        this.currentQuestion = currentQuestion;
    }

    public void setQuestionsQuantity(int questionsQuantity) {
        this.questionsQuantity = questionsQuantity;
    }

    public void setCheatsCounterByQuestion(int[] cheatsCounterByQuestion) {
        this.cheatsCounterByQuestion = cheatsCounterByQuestion;
    }

    public void setQuestionsForTopic(int questionsForTopic) {
        this.questionsForTopic = questionsForTopic;
    }

    public void setResidueQuestionsForTopic(int residueQuestionsForTopic) {
        this.residueQuestionsForTopic = residueQuestionsForTopic;
    }

    public void setCheatsQuantity(int cheatsQuantity) {
        this.cheatsQuantity = cheatsQuantity;
    }

    public void setPuntaje(int puntaje) {
        Puntaje = puntaje;
    }

    public void setTopicsToAsk(int[] topicsToAsk) {
        this.topicsToAsk = topicsToAsk;
    }

    public void setColorsAnswers(int[][] colorsAnswers) {
        this.colorsAnswers = colorsAnswers;
    }

    public void setCheatsEnable(boolean cheatsEnable) {
        this.cheatsEnable = cheatsEnable;
    }

    public void setCheatRecorder(boolean cheatRecorder) {
        this.cheatRecorder = cheatRecorder;
    }

    public void setCheatsFollowerEnable(boolean[] cheatsFollowerEnable) {
        this.cheatsFollowerEnable = cheatsFollowerEnable;
    }

    public void setHabilitadorDeRespuestas(boolean[][] habilitadorDeRespuestas) {
        this.habilitadorDeRespuestas = habilitadorDeRespuestas;
    }

    public void setPuntajeCheats(boolean[] puntajeCheats) {
        this.puntajeCheats = puntajeCheats;
    }

    public void setHabilitadorDeCheats(boolean[] habilitadorDeCheats) {
        this.habilitadorDeCheats = habilitadorDeCheats;
    }

    public void setRespondido(boolean[] respondido) {
        Respondido = respondido;
    }

    public void setQuestionsToShow(List<Questions> questionsToShow) {
        this.questionsToShow = questionsToShow;
    }

    public void setQuestionsToShowSaved(Questions[] questionsToShowSaved) {
        this.questionsToShowSaved = questionsToShowSaved;
    }

    public void setAnswersToShowSaved(Answers[][] answersToShowSaved) {
        this.answersToShowSaved = answersToShowSaved;
    }

    public void setAnswersToShow(Answers[][] answersToShow) {
        this.answersToShow = answersToShow;
    }

    public Activity3_ViewModel() {
        habilitadorDeCheats = new boolean[questionsQuantity];
        cheatsCounterByQuestion = new int[questionsQuantity];
        colorsAnswers = new int[questionsQuantity][difficult];
        Respondido = new boolean[questionsQuantity];
        habilitadorDeRespuestas = new boolean[questionsQuantity][difficult];
        puntajeCheats = new boolean[questionsQuantity];
        questionsToShowSaved = new Questions[questionsQuantity];
        answersToShow = new Answers[questionsQuantity][difficult];
        answersToShowSaved = new Answers[questionsQuantity][difficult];

    }
}
