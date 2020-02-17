package com.example.quizapp_pro;

public class Player {
    private String Nickname;
    private int Puntaje;
    private boolean Cheat;

    public String getNickname() {
        return Nickname;
    }

    public void setNickname(String nickname) {
        Nickname = nickname;
    }

    public int getPuntaje() {
        return Puntaje;
    }

    public void setPuntaje(int puntaje) {
        Puntaje = puntaje;
    }

    public boolean isCheat() {
        return Cheat;
    }

    public void setCheat(boolean cheat) {
        Cheat = cheat;
    }

    public Player(String nickname, int puntaje, boolean cheat) {
        Nickname = nickname;
        Puntaje = puntaje;
        Cheat = cheat;
    }
}
