package com.example.quizapp_pro;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btnPlay;
    private ImageButton btnConfig;
    private ImageButton btnPoints;
    private ImageButton btnMusic;
    private ImageView ImagenMundo;
    private PlayViewModel opcionesDeJuego;
    private UsuariosViewModel bestPlayers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inicialización de las variables del Activity

        btnPlay = findViewById(R.id.play_button);
        btnConfig = findViewById(R.id.options_button);
        btnPoints = findViewById(R.id.points_button);
        btnMusic = findViewById(R.id.music_button);
        ImagenMundo = findViewById(R.id.imagen_portada);

        final Intent intent = getIntent();

        opcionesDeJuego = new ViewModelProvider(this).get(PlayViewModel.class);
        bestPlayers = new ViewModelProvider(this).get(UsuariosViewModel.class);


        if (intent.getSerializableExtra("LISTA_USUARIOS") != null) {
            bestPlayers.setUsuarios((Usuario[]) intent.getSerializableExtra("LISTA_USUARIOS"));
        }


        if (intent.getSerializableExtra("TOPICS_TO_ASK") == null) {
            opcionesDeJuego.Predeterminado();
        } else {
            opcionesDeJuego.setCuantasPistas(intent.getIntExtra("CHEATS_QUANTITY", 0));
            opcionesDeJuego.setCuantasPreguntas(intent.getIntExtra("QUESTIONS_QUANTITY", 5));
            opcionesDeJuego.setDificultadPuntos(intent.getIntExtra("DIFFICULT", 2));
            opcionesDeJuego.setEnabledPistas(intent.getBooleanExtra("CHEATS_ENABLE", false));
            opcionesDeJuego.setTopicsChosen((int[]) intent.getSerializableExtra("TOPICS_TO_ASK"));
        }


        ImagenMundo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Elaborado por: Ignacio Isaac, Maximiliano Segura y Aarón Calixto", Toast.LENGTH_LONG).show();
            }
        });

        btnConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentConfig = new Intent(MainActivity.this, OptionsActivity.class);
                startActivity(intentConfig);
                intentConfig.putExtra("QUESTIONS_QUANTITY", opcionesDeJuego.getCuantasPreguntas());
                intentConfig.putExtra("CHEATS_QUANTITY", opcionesDeJuego.getCuantasPistas());
                intentConfig.putExtra("DIFFICULT", opcionesDeJuego.getDificultadPuntos());
                intentConfig.putExtra("TOPICS_TO_ASK", opcionesDeJuego.getTopicsChosen());
                intentConfig.putExtra("CHEATS_ENABLE", opcionesDeJuego.isEnabledPistas());
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                startActivity(intentConfig);


            }
        });

        btnPoints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPoints = new Intent(MainActivity.this, Activity4.class);
                String[] s = new String[6];
                for (int i = 0; i<6;i++){
                    s[i] = bestPlayers.getUsuarios()[i].getNickname();
                }
                int[] p = new int[6];
                for (int i = 0; i<6;i++){
                    p[i] = bestPlayers.getUsuarios()[i].getPuntaje();
                }
                boolean[] g = new boolean[6];
                for (int i = 0; i<6;i++){
                    g[i] = bestPlayers.getUsuarios()[i].isCheat();
                }


                intentPoints.putExtra("BEST_USERS_NICKNAME", s);
                intentPoints.putExtra("BEST_USERS_POINTS", p);
                intentPoints.putExtra("BEST_USERS_CHEATS", g);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                startActivity(intentPoints);
            }
        });

        btnMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(MainActivity.this, cuantasPreguntas + " - " + dificultadPuntos + " - " + cuantasPistas, Toast.LENGTH_SHORT).show();
                Intent intentMusic = new Intent(MainActivity.this, Activity5Music.class);
                intentMusic.putExtra("QUESTIONS_QUANTITY", opcionesDeJuego.getCuantasPreguntas());
                intentMusic.putExtra("CHEATS_QUANTITY", opcionesDeJuego.getCuantasPistas());
                intentMusic.putExtra("DIFFICULT", opcionesDeJuego.getDificultadPuntos());
                intentMusic.putExtra("TOPICS_TO_ASK", opcionesDeJuego.getTopicsChosen());
                intentMusic.putExtra("CHEATS_ENABLE", opcionesDeJuego.isEnabledPistas());
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                startActivity(intentMusic);

            }
        });

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPlay = new Intent(MainActivity.this, Activity3.class);

                String[] s = new String[6];
                for (int i = 0; i<6;i++){
                    s[i] = bestPlayers.getUsuarios()[i].getNickname();
                }
                int[] p = new int[6];
                for (int i = 0; i<6;i++){
                    p[i] = bestPlayers.getUsuarios()[i].getPuntaje();
                }
                boolean[] g = new boolean[6];
                for (int i = 0; i<6;i++){
                    g[i] = bestPlayers.getUsuarios()[i].isCheat();
                }


                intentPlay.putExtra("BEST_USERS_NICKNAME", s);
                intentPlay.putExtra("BEST_USERS_POINTS", p);
                intentPlay.putExtra("BEST_USERS_CHEATS", g);


                intentPlay.putExtra("QUESTIONS_QUANTITY", opcionesDeJuego.getCuantasPreguntas());
                intentPlay.putExtra("CHEATS_QUANTITY", opcionesDeJuego.getCuantasPistas());
                intentPlay.putExtra("DIFFICULT", opcionesDeJuego.getDificultadPuntos());
                intentPlay.putExtra("TOPICS_TO_ASK", opcionesDeJuego.getTopicsChosen());
                intentPlay.putExtra("CHEATS_ENABLE", opcionesDeJuego.isEnabledPistas());
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                startActivity(intentPlay);
            }
        });
    }

}
