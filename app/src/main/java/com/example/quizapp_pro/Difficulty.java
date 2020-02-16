package com.example.quizapp_pro;

public class Difficulty {
    private int puntosPreguntas;
    private String difficultyText;

    public Difficulty(int v) {
        switch (v) {
            case 2:
                this.puntosPreguntas = 2;
                this.difficultyText = "Fácil";
                break;
            case 3:
                this.puntosPreguntas = 3;
                this.difficultyText = "Medio";
                break;
            case 4:
                this.puntosPreguntas = 4;
                this.difficultyText = "Difícil";
                break;
            default:
                break;
        }

    }
}
