package com.example.quizapp_pro;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Usuario
{
    private String nickname;
    private int puntaje;
    private boolean cheat;

    public String getNickname() {
        return nickname;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public boolean isCheat() {
        return cheat;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public void setCheat(boolean cheat) {
        this.cheat = cheat;
    }

    public Usuario(String nickname, int puntaje, boolean cheat) {
        this.nickname = nickname;
        this.puntaje = puntaje;
        this.cheat = cheat;
    }

}
