package com.example.quizapp_pro;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class Activity4 extends AppCompatActivity {

    private TextView txtName1;
    private TextView txtName2;
    private TextView txtName3;
    private TextView txtName4;
    private TextView txtName5;
    private TextView txtName6;
    private TextView txtPuntaje1;
    private TextView txtPuntaje2;
    private TextView txtPuntaje3;
    private TextView txtPuntaje4;
    private TextView txtPuntaje5;
    private TextView txtPuntaje6;
    private ImageView btnGallina;

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
    private String nicknameActual;
    private int[] puntajes;
    private int puntajeActual;
    private boolean[] gallinas;
    private boolean gallinaActual;
    private Bundle estado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);

        txtName1 = findViewById(R.id.nombre1);
        txtName2 = findViewById(R.id.nombre2);
        txtName3 = findViewById(R.id.nombre3);
        txtName4 = findViewById(R.id.nombre4);
        txtName5 = findViewById(R.id.nombre5);
        txtName6 = findViewById(R.id.nombre6);
        txtPuntaje1 = findViewById(R.id.puntaje1);
        txtPuntaje2 = findViewById(R.id.puntaje2);
        txtPuntaje3 = findViewById(R.id.puntaje3);
        txtPuntaje4 = findViewById(R.id.puntaje4);
        txtPuntaje5 = findViewById(R.id.puntaje5);
        txtPuntaje6 = findViewById(R.id.puntaje6);
        btnGallina = findViewById(R.id.findeljuego_imagen);

        Intent intent04 = getIntent();
        estado = intent04.getBundleExtra("PARTIDA");//gallinaActual = intent04.getBooleanExtra("CHECADOR_TRAMPAS",false);
        nicknameActual = intent04.getStringExtra("NICKNAME");
        puntajeActual = intent04.getIntExtra("PUNTAJE", 0);


        topicsChosen = getIntent().getIntArrayExtra("CUALES_TOPICS");
        //cuantasPreguntas = getIntent().getIntExtra("NO_PREGUNTAS", 5);
        //dificultadPuntos = getIntent().getIntExtra("DIFICULTAD_PUNTOS", 2);
        //enabledPistas = getIntent().getBooleanExtra("ENABLE_PISTAS", false);
        //cuantasPistas = getIntent().getIntExtra("NO_PISTAS", 0);
        //nicknames = getIntent().getStringArrayExtra(NICKNAME_ARRAY);
        //puntajes = getIntent().getIntArrayExtra(PUNTAJE_ARRAY);
        //gallinas = getIntent().getBooleanArrayExtra(GALLINA_ARRAY);

        btnGallina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer playerGallina = MediaPlayer.create(Activity4.this, R.raw.gallina);
                playerGallina.start();
            }
        });


    }

    @Override
    public void onBackPressed() {
        EnviarInfoBack();
    }

    public void EnviarInfoBack() {
        Intent intentBacking = new Intent(Activity4.this, Activity3.class);
        intentBacking.putExtra("PARTIDA_REGRESO", estado);
        startActivity(intentBacking);
    }
}
