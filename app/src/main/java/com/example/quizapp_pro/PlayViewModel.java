package com.example.quizapp_pro;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayViewModel extends ViewModel {
    private int cuantasPistas;
    private int[] topicsChosen;
    private int cuantasPreguntas;
    private int dificultadPuntos;
    private boolean enabledPistas;
    private boolean aux = true;
    private boolean[] IndicesCkb = new boolean[6];

    public boolean[] getIndicesCkb() {
        return IndicesCkb;
    }

    public void setIndicesCkb(boolean[] indicesCkb) {
        IndicesCkb = indicesCkb;
    }

    public void IndicesTrue(int i){
        IndicesCkb[i] = true;
    }

    public void IndicesFalse(int i){
        IndicesCkb[i] = false;
    }

    public boolean isAux() {
        return aux;
    }

    public void setAux(boolean aux) {
        this.aux = aux;
    }

    public int getCuantasPistas() {
        return cuantasPistas;
    }

    public void setCuantasPistas(int cuantasPistas) {
        this.cuantasPistas = cuantasPistas;
    }

    public int[] getTopicsChosen() {
        Arrays.sort(topicsChosen);
        return topicsChosen;
    }

    public void setTopicsChosen(int[] topicsChosen) {
        this.topicsChosen = topicsChosen;
    }

    public int getCuantasPreguntas() {
        return cuantasPreguntas;
    }

    public void setCuantasPreguntas(int cuantasPreguntas) {
        this.cuantasPreguntas = cuantasPreguntas;
    }

    public int getDificultadPuntos() {
        return dificultadPuntos;
    }

    public void setDificultadPuntos(int dificultadPuntos) {
        this.dificultadPuntos = dificultadPuntos;
    }

    public boolean isEnabledPistas() {
        return enabledPistas;
    }

    public void setEnabledPistas(boolean enabledPistas) {
        this.enabledPistas = enabledPistas;
    }


    public void Predeterminado() {
        int[] arrayAux = new int[]{0, 1, 2, 3, 4, 5};
        cuantasPistas = 0;
        topicsChosen = arrayAux;
        cuantasPreguntas = 5;
        dificultadPuntos = 2;
        enabledPistas = false;
    }
}
