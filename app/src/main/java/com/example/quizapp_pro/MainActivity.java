package com.example.quizapp_pro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnPlay;
    private Button btnConfig;
    private Button btnPoints;
    private Button btnMusic;
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
            Toast.makeText(MainActivity.this, "ME DEVUELVE EL INTENT", Toast.LENGTH_SHORT).show();
            topicsChosen = getIntent().getIntArrayExtra("CUALES_TOPICS");
            cuantasPreguntas = getIntent().getIntExtra("NO_PREGUNTAS", 5);
            dificultadPuntos = getIntent().getIntExtra("DIFICULTAD_PUNTOS", 2);
            enabledPistas = getIntent().getBooleanExtra("ENABLE_PISTAS", false);
            cuantasPistas = getIntent().getIntExtra("NO_PISTAS", 0);
        } else {
            Toast.makeText(MainActivity.this, "INICIO DE APP", Toast.LENGTH_SHORT).show();
            topicsChosen = new int[]{0};
            cuantasPreguntas = 5;
            dificultadPuntos = 2;
            enabledPistas = false;
            cuantasPistas = 0;
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
                    startActivity(intentConfig);

                    startActivity(intentConfig);
                } else {

                    Intent intent = new Intent(MainActivity.this, OptionsActivity.class);
                    startActivity(intent);
                }

            }
        });

        btnPoints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Activity4.class);
                startActivity(intent);


            }
        });

        btnMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this, cuantasPreguntas + " - " + dificultadPuntos + " - " + cuantasPistas, Toast.LENGTH_SHORT).show();
                //Intent intent = new Intent(MainActivity.this, Activity5Music.class);
                //startActivity(intent);

            }
        });

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "LA APP CRASHEA", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, Activity3.class);
                intent.putExtra(CUALES_TOPICS, topicsChosen);
                intent.putExtra(NO_PREGUNTAS, cuantasPreguntas);
                intent.putExtra(DIFICULTAD_PUNTOS, dificultadPuntos);
                intent.putExtra(ENABLE_PISTAS, enabledPistas);
                intent.putExtra(NO_PISTAS, cuantasPistas);
                startActivity(intent);

            }
        });

    }
}
