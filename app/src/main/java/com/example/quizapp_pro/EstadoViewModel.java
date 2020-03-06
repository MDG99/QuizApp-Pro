package com.example.quizapp_pro;

import android.graphics.Color;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EstadoViewModel extends ViewModel {
    private String nickname;
    private int currentQuestion; //guardar
    private int[] cheatsCounterByQuestion; //guardar
    private int Puntaje; //guardar
    private int[][] colorsAnswers; //guardar

    private boolean cheatRecorder; //guardar
    private boolean aux = true;
    private boolean[] cheatsFollowerEnable;
    private boolean[][] habilitadorDeRespuestas;
    private boolean[] puntajeCheats;
    private boolean[] habilitadorDeCheats;
    private boolean[] Respondido;

    private List<Questions> questionsToShow;
    private Questions[] questionsToShowSaved;

    private Answers[][] answersToShowSaved;
    private Answers[][] answersToShow;

    private int COLOR_NORMAL = Color.rgb(55, 134, 197);
    private int COLOR_CORRECTO = Color.rgb(55, 197, 62);
    private int COLOR_INCORRECTO = Color.rgb(197, 55, 72);
    private int COLOR_TRAMPA = Color.rgb(46, 55, 58);

    private boolean isGameFinished = false;
    private boolean DialogRepeat = true;

    public boolean isAux() {
        return aux;
    }

    public void setAux(boolean aux) {
        this.aux = aux;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(int currentQuestion) {
        this.currentQuestion = currentQuestion;
    }

    public int[] getCheatsCounterByQuestion() {
        return cheatsCounterByQuestion;
    }

    public void setCheatsCounterByQuestion(int[] cheatsCounterByQuestion) {
        this.cheatsCounterByQuestion = cheatsCounterByQuestion;
    }

    public int getPuntaje() {
        return Puntaje;
    }

    public void setPuntaje(int puntaje) {
        Puntaje = puntaje;
    }

    public int[][] getColorsAnswers() {
        return colorsAnswers;
    }

    public void setColorsAnswers(int[][] colorsAnswers) {
        this.colorsAnswers = colorsAnswers;
    }

    public boolean isCheatRecorder() {
        return cheatRecorder;
    }

    public void setCheatRecorder(boolean cheatRecorder) {
        this.cheatRecorder = cheatRecorder;
    }

    public boolean[] getCheatsFollowerEnable() {
        return cheatsFollowerEnable;
    }

    public void setCheatsFollowerEnable(boolean[] cheatsFollowerEnable) {
        this.cheatsFollowerEnable = cheatsFollowerEnable;
    }

    public boolean[][] getHabilitadorDeRespuestas() {
        return habilitadorDeRespuestas;
    }

    public void setHabilitadorDeRespuestas(boolean[][] habilitadorDeRespuestas) {
        this.habilitadorDeRespuestas = habilitadorDeRespuestas;
    }

    public boolean[] getPuntajeCheats() {
        return puntajeCheats;
    }

    public void setPuntajeCheats(boolean[] puntajeCheats) {
        this.puntajeCheats = puntajeCheats;
    }

    public boolean[] getHabilitadorDeCheats() {
        return habilitadorDeCheats;
    }

    public void setHabilitadorDeCheats(boolean[] habilitadorDeCheats) {
        this.habilitadorDeCheats = habilitadorDeCheats;
    }

    public boolean[] getRespondido() {
        return Respondido;
    }

    public void setRespondido(boolean[] respondido) {
        Respondido = respondido;
    }

    public List<Questions> getQuestionsToShow() {
        return questionsToShow;
    }

    public void setQuestionsToShow(List<Questions> questionsToShow) {
        this.questionsToShow = questionsToShow;
    }

    public Questions[] getQuestionsToShowSaved() {
        return questionsToShowSaved;
    }

    public void setQuestionsToShowSaved(Questions[] questionsToShowSaved) {
        this.questionsToShowSaved = questionsToShowSaved;
    }

    public Answers[][] getAnswersToShowSaved() {
        return answersToShowSaved;
    }

    public void setAnswersToShowSaved(Answers[][] answersToShowSaved) {
        this.answersToShowSaved = answersToShowSaved;
    }

    public Answers[][] getAnswersToShow() {
        return answersToShow;
    }

    public void setAnswersToShow(Answers[][] answersToShow) {
        this.answersToShow = answersToShow;
    }

    public Questions[] ListToArrayQuestion(List<Questions> LQ) {
        Questions[] Q = new Questions[LQ.size()];
        LQ.toArray(Q);
        return Q;
    }

    public void PlayDefault(int questionsQuantity, int difficult) {

        currentQuestion = 0;
        cheatRecorder = false;
        Puntaje = 0;

        habilitadorDeCheats = new boolean[questionsQuantity];
        cheatsCounterByQuestion = new int[questionsQuantity];
        colorsAnswers = new int[questionsQuantity][difficult];
        Respondido = new boolean[questionsQuantity];
        habilitadorDeRespuestas = new boolean[questionsQuantity][difficult];
        puntajeCheats = new boolean[questionsQuantity];

        questionsToShow = new ArrayList<>();
        questionsToShowSaved = new Questions[questionsQuantity];
        answersToShow = new Answers[questionsQuantity][difficult];
        answersToShowSaved = new Answers[questionsQuantity][difficult];


        for (int x = 0; x < questionsQuantity; x++) {
            Respondido[x] = false;
        }

        for (int x = 0; x < questionsQuantity; x++) {
            cheatsCounterByQuestion[x] = difficult;
        }

        for (int y = 0; y < questionsQuantity; y++) {
            for (int x = 0; x < difficult; x++) {
                colorsAnswers[y][x] = Color.rgb(0, 0, 100);
            }
        }

        for (boolean b : puntajeCheats) {
            b = false;
        }

        for (int y = 0; y < questionsQuantity; y++) {
            for (int x = 0; x < difficult; x++) {
                habilitadorDeRespuestas[y][x] = true;
            }
        }

    }

    public void PlayRandomAnswers(int questionsQuantity, int difficult) {
        //Llenado de respuestas a mostrar aleatoriamente.- Te aseguras que esté la respuesta correcta
        for (int i = 0; i < questionsQuantity; i++) {
            List<Answers> fakeAnswers = new ArrayList<>();
            //Agregamos las respuestas falsas
            fakeAnswers.add(questionsToShowSaved[i].getFakeAnswer1());
            fakeAnswers.add(questionsToShowSaved[i].getFakeAnswer2());
            fakeAnswers.add(questionsToShowSaved[i].getFakeAnswer3());
            for (int d = 0; d < difficult; d++) {
                if (d == 0)
                    answersToShow[i][d] = questionsToShowSaved[i].getCorrectAnswer();
                else {
                    Random rand = new Random();
                    int aleatorio = rand.nextInt(fakeAnswers.size());
                    answersToShow[i][d] = fakeAnswers.get(aleatorio);
                    fakeAnswers.remove(aleatorio);
                }
            }
        }

        //Respuestas aleatorias
        for (int i = 0; i < questionsQuantity; i++) {
            List<Answers> auxAns = new ArrayList<>();
            for (int h = 0; h < difficult; h++) {
                auxAns.add(answersToShow[i][h]);
            }
            for (int d = 0; d < difficult; d++) {
                Random rand = new Random();
                int aleatorio = rand.nextInt(auxAns.size());
                answersToShowSaved[i][d] = auxAns.get(aleatorio);
                auxAns.remove(aleatorio);
            }
        }

    }


    public void NextQuestionIndex(int questionsQuantity) {
        currentQuestion = (currentQuestion + 1) % questionsQuantity;
    }

    public void PrevQuestionIndex(int questionsQuantity) {
        currentQuestion = (currentQuestion + questionsQuantity - 1) % questionsQuantity;
    }

    public void ShowAnswerByCheats(int o) {
        colorsAnswers[currentQuestion][o] = COLOR_CORRECTO;
        Respondido[currentQuestion] = true;


    }

    public void Trampa(int aleatorio) {
        habilitadorDeRespuestas[currentQuestion][aleatorio] = false;
        colorsAnswers[currentQuestion][aleatorio] = COLOR_TRAMPA;
    }

    public void TrampaCero(int questionsQuantity) {
        for (int t = 0; t < questionsQuantity; t++) {
            habilitadorDeCheats[t] = false;
        }
    }

    public void cheatsButtonClick01() {
        puntajeCheats[currentQuestion] = true;
        cheatsCounterByQuestion[currentQuestion]--;
    }

    public void cheatsButtonClick02() {
        habilitadorDeCheats[currentQuestion] = false;
    }

    public void RespuestaClick() {
        Respondido[currentQuestion] = true;
        habilitadorDeCheats[currentQuestion] = false;
    }

    public void RespuestaClick01(int o) {
        colorsAnswers[currentQuestion][o] = COLOR_CORRECTO;

    }

    public void RespuestaClick02(int o) {
        colorsAnswers[currentQuestion][o] = COLOR_INCORRECTO;
    }

    public void setGameFinished() {
        isGameFinished = true;
    }

    public boolean getGameFinished() {
        return isGameFinished;
    }

    public void setDialogRepeat() {
        DialogRepeat = false;
    }

    public boolean getDialogRepeat() {
        return DialogRepeat;
    }

}

