package com.example.quizapp_pro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnPlay;
    private ImageButton btnConfig;
    private ImageButton btnPoints;
    private ImageButton btnMusic;
    private int[] topicsChosen;
    private int cuantasPreguntas;
    private int dificultadPuntos;
    private boolean enabledPistas;
    private int cuantasPistas;
    private final String CUALES_TOPICS = "CUALES_TOPICS";
    private final String NO_PREGUNTAS = "NO_PREGUNTAS";
    private final String DIFICULTAD_PUNTOS = "DIFICULTAD_PUNTOS";
    private final String ENABLE_PISTAS = "ENABLE_PISTAS";
    private final String NO_PISTAS = "NO_PISTAS";
    private final String NICKNAME_ARRAY = "PLAYER_NICKNAME";
    private final String PUNTAJE_ARRAY = "PLAYER_POINTS";
    private final String GALLINA_ARRAY = "PLAYER_CHEATED";
    private String[] nicknames;
    private int[] puntajes;
    private boolean[] gallinas;

    //private final String ENVIA_PREGUNTAS = "XD";
    //private MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnPlay = findViewById(R.id.play_button);
        btnConfig = findViewById(R.id.options_button);
        btnPoints = findViewById(R.id.points_button);
        btnMusic = findViewById(R.id.music_button);

        if (getIntent().getExtras() != null) {
            //Toast.makeText(MainActivity.this, "ME DEVUELVE EL INTENT", Toast.LENGTH_SHORT).show();
            topicsChosen = getIntent().getIntArrayExtra("CUALES_TOPICS");
            cuantasPreguntas = getIntent().getIntExtra("NO_PREGUNTAS", 5);
            dificultadPuntos = getIntent().getIntExtra("DIFICULTAD_PUNTOS", 2);
            enabledPistas = getIntent().getBooleanExtra("ENABLE_PISTAS", false);
            cuantasPistas = getIntent().getIntExtra("NO_PISTAS", 0);

            nicknames = getIntent().getStringArrayExtra(NICKNAME_ARRAY);
            puntajes = getIntent().getIntArrayExtra(PUNTAJE_ARRAY);
            gallinas = getIntent().getBooleanArrayExtra(GALLINA_ARRAY);
        } else {
            Toast.makeText(MainActivity.this, "INICIO DE APP", Toast.LENGTH_SHORT).show();
            topicsChosen = new int[]{0};
            cuantasPreguntas = 5;
            dificultadPuntos = 2;
            enabledPistas = false;
            cuantasPistas = 0;

            nicknames = new String[]{"MAX"};
            puntajes = new int[]{0};
            gallinas = new boolean[]{false};

        }


        btnConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getIntent().getExtras() != null) {

                    Intent intentConfig = new Intent(MainActivity.this, OptionsActivity.class);
                    intentConfig.putExtra(CUALES_TOPICS, topicsChosen);
                    intentConfig.putExtra(NO_PREGUNTAS, cuantasPreguntas);
                    intentConfig.putExtra(DIFICULTAD_PUNTOS, dificultadPuntos);
                    intentConfig.putExtra(ENABLE_PISTAS, enabledPistas);
                    intentConfig.putExtra(NO_PISTAS, cuantasPistas);

                    intentConfig.putExtra(NICKNAME_ARRAY, nicknames);
                    intentConfig.putExtra(PUNTAJE_ARRAY, puntajes);
                    intentConfig.putExtra(GALLINA_ARRAY, gallinas);
                    startActivity(intentConfig);

                    startActivity(intentConfig);
                } else {

                    Intent intentConfig = new Intent(MainActivity.this, OptionsActivity.class);
                    startActivity(intentConfig);
                }

            }
        });

        btnPoints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPoints = new Intent(MainActivity.this, Activity4.class);
                intentPoints.putExtra(CUALES_TOPICS, topicsChosen);
                intentPoints.putExtra(NO_PREGUNTAS, cuantasPreguntas);
                intentPoints.putExtra(DIFICULTAD_PUNTOS, dificultadPuntos);
                intentPoints.putExtra(ENABLE_PISTAS, enabledPistas);
                intentPoints.putExtra(NO_PISTAS, cuantasPistas);

                intentPoints.putExtra(NICKNAME_ARRAY, nicknames);
                intentPoints.putExtra(PUNTAJE_ARRAY, puntajes);
                intentPoints.putExtra(GALLINA_ARRAY, gallinas);
                startActivity(intentPoints);


            }
        });

        btnMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(MainActivity.this, cuantasPreguntas + " - " + dificultadPuntos + " - " + cuantasPistas, Toast.LENGTH_SHORT).show();
                Intent intentMusic = new Intent(MainActivity.this, Activity5Music.class);
                intentMusic.putExtra(CUALES_TOPICS, topicsChosen);
                intentMusic.putExtra(NO_PREGUNTAS, cuantasPreguntas);
                intentMusic.putExtra(DIFICULTAD_PUNTOS, dificultadPuntos);
                intentMusic.putExtra(ENABLE_PISTAS, enabledPistas);
                intentMusic.putExtra(NO_PISTAS, cuantasPistas);

                intentMusic.putExtra(NICKNAME_ARRAY, nicknames);
                intentMusic.putExtra(PUNTAJE_ARRAY, puntajes);
                intentMusic.putExtra(GALLINA_ARRAY, gallinas);
                //Esto manda la informacion de config
                startActivity(intentMusic);

            }
        });

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "LA APP CRASHEA", Toast.LENGTH_SHORT).show();
                Intent intentPlay = new Intent(MainActivity.this, Activity3.class);
                intentPlay.putExtra(CUALES_TOPICS, topicsChosen);
                intentPlay.putExtra(NO_PREGUNTAS, cuantasPreguntas);
                intentPlay.putExtra(DIFICULTAD_PUNTOS, dificultadPuntos);
                intentPlay.putExtra(ENABLE_PISTAS, enabledPistas);
                intentPlay.putExtra(NO_PISTAS, cuantasPistas);

                intentPlay.putExtra(NICKNAME_ARRAY, nicknames);
                intentPlay.putExtra(PUNTAJE_ARRAY, puntajes);
                intentPlay.putExtra(GALLINA_ARRAY, gallinas);

                startActivity(intentPlay);

            }
        });

    }
}
