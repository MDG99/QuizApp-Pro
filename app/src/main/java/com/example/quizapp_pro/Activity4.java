package com.example.quizapp_pro;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


public class Activity4 extends AppCompatActivity {

    private final String CUALES_TOPICS = "CUALES_TOPICS";
    private final String NO_PREGUNTAS = "NO_PREGUNTAS";
    private final String DIFICULTAD_PUNTOS = "DIFICULTAD_PUNTOS";
    private final String ENABLE_PISTAS = "ENABLE_PISTAS";
    private final String NO_PISTAS = "NO_PISTAS";

    private final String NICKNAME_ARRAY = "PLAYER_NICKNAME";
    private final String PUNTAJE_ARRAY = "PLAYER_POINTS";
    private final String GALLINA_ARRAY = "PLAYER_CHEATED";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);



    }
}
