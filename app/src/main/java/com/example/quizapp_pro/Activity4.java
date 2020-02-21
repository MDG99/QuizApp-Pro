package com.example.quizapp_pro;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


public class Activity4 extends AppCompatActivity {

    private final String CUALES_TOPICS = "CUALES_TOPICS";
    private final String NO_PREGUNTAS = "NO_PREGUNTAS";
    private final String DIFICULTAD_PUNTOS = "DIFICULTAD_PUNTOS";
    private final String ENABLE_PISTAS = "ENABLE_PISTAS";
    private final String NO_PISTAS = "NO_PISTAS";

    private int[] topicsChosen;
    private int cuantasPreguntas;
    private int dificultadPuntos;
    private boolean enabledPistas;
    private int cuantasPistas;

    private final String NICKNAME_ARRAY = "PLAYER_NICKNAME";
    private final String PUNTAJE_ARRAY = "PLAYER_POINTS";
    private final String GALLINA_ARRAY = "PLAYER_CHEATED";
    private String[] nicknames;
    private int[] puntajes;
    private boolean[] gallinas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);

        topicsChosen = getIntent().getIntArrayExtra("CUALES_TOPICS");
        cuantasPreguntas = getIntent().getIntExtra("NO_PREGUNTAS", 5);
        dificultadPuntos = getIntent().getIntExtra("DIFICULTAD_PUNTOS", 2);
        enabledPistas = getIntent().getBooleanExtra("ENABLE_PISTAS", false);
        cuantasPistas = getIntent().getIntExtra("NO_PISTAS", 0);
        nicknames = getIntent().getStringArrayExtra(NICKNAME_ARRAY);
        puntajes = getIntent().getIntArrayExtra(PUNTAJE_ARRAY);
        gallinas = getIntent().getBooleanArrayExtra(GALLINA_ARRAY);

    }
}
