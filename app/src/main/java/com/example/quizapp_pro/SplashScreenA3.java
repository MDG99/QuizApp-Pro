package com.example.quizapp_pro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreenA3 extends AppCompatActivity {

    private PlayViewModel opcionesDeJuego;
    private UsuariosViewModel bestPlayers;
    private String[] s = new String[6];
    private int[] p = new int[6];
    private boolean[] g = new boolean[6];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen_a3);

        final Intent intent = getIntent();

        opcionesDeJuego = new ViewModelProvider(this).get(PlayViewModel.class);
        bestPlayers = new ViewModelProvider(this).get(UsuariosViewModel.class);


        if (intent.getSerializableExtra("BEST_USERS_NICKNAME") != null) {
            s = intent.getStringArrayExtra("BEST_USERS_NICKNAME");
            p = intent.getIntArrayExtra("BEST_USERS_POINTS");
            g = intent.getBooleanArrayExtra("BEST_USERS_CHEATS");
            bestPlayers.setUsuarios(bestPlayers.Construir(s, p, g));
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

        for (int i = 0; i < 6; i++) {
            s[i] = bestPlayers.getUsuarios()[i].getNickname();
            p[i] = bestPlayers.getUsuarios()[i].getPuntaje();
            g[i] = bestPlayers.getUsuarios()[i].isCheat();
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intentPlay = new Intent(SplashScreenA3.this, Activity3.class);
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
                finish();
            }
        }, 3000);
    }
}
