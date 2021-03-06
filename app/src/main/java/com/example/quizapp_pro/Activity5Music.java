package com.example.quizapp_pro;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Activity5Music extends AppCompatActivity {

    private Button BtnTrivia;
    private Button BtnRock;
    private Button BtnClasica;
    private Button BtnGeneral;
    private ImageView ImgTrvia;
    private ImageView ImgRock;
    private ImageView ImgClasica;
    private ImageView ImgGeneral;
    private Button BtnExit;
    private LinearLayout Activity;
    private MediaPlayer player1;
    private MediaPlayer player2;
    private MediaPlayer player3;
    private MediaPlayer player4;
    private MediaPlayer mp;

    private AnimatorSet animatorSetRotation;

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

    private int[] topicsChosen;
    private int cuantasPreguntas;
    private int dificultadPuntos;
    private boolean enabledPistas;
    private int cuantasPistas;

    private int botonGreyedOuyt = 0;
    private final String BUTTON_KEY = "BUTTON_KEY";
    private Bundle estado;

    private ObjectAnimator animatorRotation;

    //Reproduce un conjuto de ObjectAnimator en un orden en específico. Las animaciones, a su vez, pueden ser igual todas a las vez.
    private AnimatorSet animatorSet;

    private boolean mIsBound = false;
    private MusicService mServ;
    private ServiceConnection Scon = new ServiceConnection() {

        public void onServiceConnected(ComponentName name, IBinder
                binder) {
            mServ = ((MusicService.ServiceBinder) binder).getService();

        }

        public void onServiceDisconnected(ComponentName name) {
            mServ = null;
        }
    };

    void doBindService() {
        bindService(new Intent(this, MusicService.class),
                Scon, Context.BIND_AUTO_CREATE);
        mIsBound = true;
    }

    void doUnbindService() {
        if (mIsBound) {
            unbindService(Scon);
            mIsBound = false;
        }
    }

    private Intent music = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final MusicService mServ = new MusicService();

        music.setClass(Activity5Music.this, MusicService.class);


        setContentView(R.layout.activity_activity5_music);

        //stopPlaying();
        player1 = MediaPlayer.create(Activity5Music.this, R.raw.trividademo);
        player2 = MediaPlayer.create(Activity5Music.this, R.raw.rockdemo);
        player3 = MediaPlayer.create(Activity5Music.this, R.raw.generaldemo);
        player4 = MediaPlayer.create(Activity5Music.this, R.raw.clasicademo);

        BtnTrivia = findViewById(R.id.btnTrvia);
        BtnClasica = findViewById(R.id.btnClasica);
        BtnRock = findViewById(R.id.btnRock);
        BtnGeneral = findViewById(R.id.btnGeneral);
        BtnExit = findViewById(R.id.btnExit);
        ImgTrvia = findViewById(R.id.imageView1);
        ImgRock = findViewById(R.id.imageView2);
        ImgGeneral = findViewById(R.id.imageView3);
        ImgClasica = findViewById(R.id.imageView4);
        Activity = findViewById(R.id.activity);

        BtnExit.getBackground().setAlpha(60);
        BtnExit.setEnabled(false);
//        topicsChosen = getIntent().getIntArrayExtra("CUALES_TOPICS");
//        cuantasPreguntas = getIntent().getIntExtra("NO_PREGUNTAS", 5);
//        dificultadPuntos = getIntent().getIntExtra("DIFICULTAD_PUNTOS", 2);
//        enabledPistas = getIntent().getBooleanExtra("ENABLE_PISTAS", false);
//        cuantasPistas = getIntent().getIntExtra("NO_PISTAS", 0);
//
//        nicknames = getIntent().getStringArrayExtra(NICKNAME_ARRAY);
//        puntajes = getIntent().getIntArrayExtra(PUNTAJE_ARRAY);
//        gallinas = getIntent().getBooleanArrayExtra(GALLINA_ARRAY);

        if (estado != null) {
            savedInstanceState = estado;
        }

        if (savedInstanceState != null) {
            int aux = savedInstanceState.getInt(BUTTON_KEY);
            botonGreyedOuyt = aux;
//            topicsChosen = savedInstanceState.getIntArray(CUALES_TOPICS);
//            cuantasPreguntas = savedInstanceState.getInt(NO_PREGUNTAS);
//            dificultadPuntos = savedInstanceState.getInt(DIFICULTAD_PUNTOS);
//            enabledPistas = savedInstanceState.getBoolean(ENABLE_PISTAS);
//            cuantasPistas = savedInstanceState.getInt(NO_PISTAS);
//
//            nicknames = savedInstanceState.getStringArray(NICKNAME_ARRAY);
//            puntajes = savedInstanceState.getIntArray(PUNTAJE_ARRAY);
//            gallinas = savedInstanceState.getBooleanArray(GALLINA_ARRAY);
            BtnExit.setEnabled(false);
            estadosBotones(botonGreyedOuyt);


        }


        BtnTrivia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                stopPlaying();
//                mp = MediaPlayer.create(Activity5Music.this, R.raw.trividademo);
//                mp.start();
                BtnExit.setEnabled(true);
                stopService(music);
                music = new Intent();
                music.setClass(Activity5Music.this, MusicService.class);
                music.putExtra("DEMO_KEY", 1);
                startService(music);

                BtnTrivia.setBackgroundResource(R.drawable.opcionescogida1);
                Activity.setBackgroundResource(R.drawable.opcionescogidat);

                BtnRock.setBackgroundResource(R.drawable.boton_redondo_musica);
                BtnGeneral.setBackgroundResource(R.drawable.boton_redondo_musica);
                BtnClasica.setBackgroundResource(R.drawable.boton_redondo_musica);

                BtnRock.getBackground().setAlpha(60);
                BtnGeneral.getBackground().setAlpha(60);
                BtnClasica.getBackground().setAlpha(60);

                BtnExit.setBackgroundResource(R.drawable.boton_redondo_musica2);
                BtnExit.setText("A darle");
                BtnExit.setTextColor(Color.rgb(255, 255, 255));

                BtnTrivia.setClickable(false);
                BtnTrivia.setEnabled(false);

                BtnRock.setClickable(true);
                BtnRock.setEnabled(true);
                BtnGeneral.setClickable(true);
                BtnGeneral.setEnabled(true);
                BtnClasica.setClickable(true);
                BtnClasica.setEnabled(true);


                //if (player2.isPlaying()) {
                //    player2.stop();
                //} else if (player3.isPlaying()) {
                //    player3.stop();
                //} else if (player4.isPlaying()) {
                //    player4.stop();
                //}
                //player1.start();
                //final MediaPlayer mp = MediaPlayer.create(this, R.raw.music1);
                //mp.setLooping(true);
                //mp.start();
                botonGreyedOuyt = 1;
                animacion("rotation");
                //animacion("bucle");
            }
        });

        BtnRock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                stopPlaying();
//                mp = MediaPlayer.create(Activity5Music.this, R.raw.rockdemo);
//                mp.start();
                BtnExit.setEnabled(true);
                stopService(music);
                music = new Intent();
                music.setClass(Activity5Music.this, MusicService.class);
                music.putExtra("DEMO_KEY", 2);
                startService(music);

                BtnRock.setBackgroundResource(R.drawable.opcionescogida2);
                Activity.setBackgroundResource(R.drawable.opcionescogidar);

                BtnTrivia.setBackgroundResource(R.drawable.boton_redondo_musica);
                BtnGeneral.setBackgroundResource(R.drawable.boton_redondo_musica);
                BtnClasica.setBackgroundResource(R.drawable.boton_redondo_musica);

                BtnTrivia.getBackground().setAlpha(60);
                BtnGeneral.getBackground().setAlpha(60);
                BtnClasica.getBackground().setAlpha(60);

                BtnExit.setBackgroundResource(R.drawable.boton_redondo_musica2);
                BtnExit.setText("Demuestra quien es el que sabe");
                BtnExit.setTextColor(Color.rgb(255, 255, 255));

                BtnRock.setClickable(false);
                BtnRock.setEnabled(false);

                BtnTrivia.setEnabled(true);
                BtnTrivia.setClickable(true);
                BtnGeneral.setEnabled(true);
                BtnGeneral.setClickable(true);
                BtnClasica.setEnabled(true);
                BtnClasica.setClickable(true);

                //if (player1.isPlaying()) {
                //    player1.stop();
                //} else if (player4.isPlaying()) {
                //    player4.stop();
                //} else if (player3.isPlaying()) {
                //    player3.stop();
                //}
                //player2.start();
                botonGreyedOuyt = 2;
                animacion("rotation2");
            }
        });

        BtnGeneral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                stopPlaying();
//                mp = MediaPlayer.create(Activity5Music.this, R.raw.generaldemo);
//                mp.start();
                BtnExit.setEnabled(true);
                stopService(music);
                music = new Intent();
                music.setClass(Activity5Music.this, MusicService.class);
                music.putExtra("DEMO_KEY", 3);
                startService(music);

                BtnGeneral.setBackgroundResource(R.drawable.opcionescogida3);
                Activity.setBackgroundResource(R.drawable.fondomusic);

                BtnTrivia.setBackgroundResource(R.drawable.boton_redondo_musica);
                BtnRock.setBackgroundResource(R.drawable.boton_redondo_musica);
                BtnClasica.setBackgroundResource(R.drawable.boton_redondo_musica);

                BtnTrivia.getBackground().setAlpha(60);
                BtnRock.getBackground().setAlpha(60);
                BtnClasica.getBackground().setAlpha(60);

                BtnExit.setBackgroundResource(R.drawable.boton_redondo_musica2);
                BtnExit.setText("Bueno... si es que el quieres...");
                BtnExit.setTextColor(Color.rgb(255, 255, 255));

                BtnGeneral.setClickable(false);
                BtnGeneral.setEnabled(false);

                BtnTrivia.setEnabled(true);
                BtnTrivia.setClickable(true);
                BtnRock.setEnabled(true);
                BtnRock.setClickable(true);
                BtnClasica.setEnabled(true);
                BtnClasica.setClickable(true);

                //if (player1.isPlaying()) {
                //    player1.stop();
                //} else if (player2.isPlaying()) {
                //    player2.stop();
                //} else if (player4.isPlaying()) {
                //    player4.stop();
                //}
//
                //player3.start();
                botonGreyedOuyt = 3;
                animacion("rotation3");
            }
        });

        BtnClasica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                stopPlaying();
//                mp = MediaPlayer.create(Activity5Music.this, R.raw.clasicademo);
//                mp.start();
                BtnExit.setEnabled(true);
                stopService(music);
                music = new Intent();
                music.setClass(Activity5Music.this, MusicService.class);
                music.putExtra("DEMO_KEY", 4);
                startService(music);

                BtnClasica.setBackgroundResource(R.drawable.opcionescogida4);
                Activity.setBackgroundResource(R.drawable.opcionescogidac);

                BtnTrivia.setBackgroundResource(R.drawable.boton_redondo_musica);
                BtnRock.setBackgroundResource(R.drawable.boton_redondo_musica);
                BtnGeneral.setBackgroundResource(R.drawable.boton_redondo_musica);

                BtnTrivia.getBackground().setAlpha(60);
                BtnRock.getBackground().setAlpha(60);
                BtnGeneral.getBackground().setAlpha(60);

                BtnExit.setBackgroundResource(R.drawable.boton_redondo_musica2);
                BtnExit.setText("Levanta el meñique, Jaime");
                BtnExit.setTextColor(Color.rgb(255, 255, 255));

                BtnClasica.setClickable(false);
                BtnClasica.setEnabled(false);

                BtnTrivia.setEnabled(true);
                BtnTrivia.setClickable(true);
                BtnRock.setEnabled(true);
                BtnRock.setClickable(true);
                BtnGeneral.setEnabled(true);
                BtnGeneral.setClickable(true);

                //if (player1.isPlaying()) {
                //    player1.stop();
                //} else if (player2.isPlaying()) {
                //    player2.stop();
                //} else if (player3.isPlaying()) {
                //    player3.stop();
                //}
                //player4.start();
                botonGreyedOuyt = 4;
                animacion("bucle");
            }
        });
        BtnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                stopPlaying();

                //MediaPlayer playerX1 = MediaPlayer.create(Activity5Music.this, R.raw.trivia);
                //MediaPlayer playerX2 = MediaPlayer.create(Activity5Music.this, R.raw.rock);
                //MediaPlayer playerX3 = MediaPlayer.create(Activity5Music.this, R.raw.general);
                //MediaPlayer playerX4 = MediaPlayer.create(Activity5Music.this, R.raw.clasica);

                //if (player1.isPlaying()) {
                //    player1.stop();
                //} else if (player2.isPlaying()) {
                //    player2.stop();
                //} else if (player3.isPlaying()) {
                //    player3.stop();
                //} else if (player4.isPlaying()) {
                //    player4.stop();
                //}
//
                if (!BtnTrivia.isClickable() || !BtnTrivia.isEnabled()) {
                    stopService(music);
                    music = new Intent();
                    music.setClass(Activity5Music.this, MusicService.class);
                    music.putExtra("FULL_KEY", 1);
                    startService(music);

                } else if (!BtnRock.isClickable() || !BtnRock.isEnabled()) {
                    stopService(music);
                    music = new Intent();
                    music.setClass(Activity5Music.this, MusicService.class);
                    music.putExtra("FULL_KEY", 2);
                    startService(music);

                } else if (!BtnGeneral.isClickable() || !BtnGeneral.isEnabled()) {
                    stopService(music);
                    music = new Intent();
                    music.setClass(Activity5Music.this, MusicService.class);
                    music.putExtra("FULL_KEY", 3);
                    startService(music);

                } else if (!BtnClasica.isClickable() || !BtnClasica.isEnabled()) {
                    stopService(music);
                    music = new Intent();
                    music.setClass(Activity5Music.this, MusicService.class);
                    music.putExtra("FULL_KEY", 4);
                    startService(music);

                } else {
                    stopService(music);
                    music = new Intent();
                    music.setClass(Activity5Music.this, MusicService.class);
                    music.putExtra("FULL_KEY", 1);
                    startService(music);

                }

//                Intent intentBackMusic = new Intent(Activity5Music.this, MainActivity.class);
//                startActivity(intentBackMusic);
//                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
//                intentBackMusic.putExtra(CUALES_TOPICS, topicsChosen);
//                intentBackMusic.putExtra(NO_PREGUNTAS, cuantasPreguntas);
//                intentBackMusic.putExtra(DIFICULTAD_PUNTOS, dificultadPuntos);
//                intentBackMusic.putExtra(ENABLE_PISTAS, enabledPistas);
//                intentBackMusic.putExtra(NO_PISTAS, cuantasPistas);
//                intentBackMusic.putExtra(NICKNAME_ARRAY, nicknames);
//                intentBackMusic.putExtra(PUNTAJE_ARRAY, puntajes);
//                intentBackMusic.putExtra(GALLINA_ARRAY, gallinas);
//                startActivity(intentBackMusic);


                Toast.makeText(Activity5Music.this, "BUENA ELECCIÓN", Toast.LENGTH_LONG).show();
                finish();

            }

        });
    }


    private void animacion(String animacion) {
        switch (animacion) {
            case "rotation":


                animatorRotation = ObjectAnimator.ofFloat(ImgTrvia, "rotation", 0f, 1500f);
                animatorRotation.setDuration(25000);
                animatorSetRotation = new AnimatorSet();
                animatorSetRotation.play(animatorRotation);
                animatorSetRotation.start();


                break;

            case "rotation2":


                animatorRotation = ObjectAnimator.ofFloat(ImgRock, "rotation", 0f, 1500f);
                animatorRotation.setDuration(25000);
                animatorSetRotation = new AnimatorSet();
                animatorSetRotation.play(animatorRotation);
                animatorSetRotation.start();
                break;

            case "rotation3":


                animatorRotation = ObjectAnimator.ofFloat(ImgGeneral, "rotation", 0f, 1500f);
                animatorRotation.setDuration(25000);
                animatorSetRotation = new AnimatorSet();
                animatorSetRotation.play(animatorRotation);
                animatorSetRotation.start();
                break;

            case "bucle":
                animatorRotation = ObjectAnimator.ofFloat(ImgClasica, "rotation", 0f, 1500f);
                animatorRotation.setDuration(25000);
                animatorSetRotation = new AnimatorSet();
                animatorSetRotation.play(animatorRotation);
                animatorSetRotation.start();
                break;
        }
    }


//    @Override
//    public void onBackPressed() {
//
//        Intent intentBackMusic = new Intent(Activity5Music.this, MainActivity.class);
//        startActivity(intentBackMusic);
//        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
//        intentBackMusic.putExtra(CUALES_TOPICS, topicsChosen);
//        intentBackMusic.putExtra(NO_PREGUNTAS, cuantasPreguntas);
//        intentBackMusic.putExtra(DIFICULTAD_PUNTOS, dificultadPuntos);
//        intentBackMusic.putExtra(ENABLE_PISTAS, enabledPistas);
//        intentBackMusic.putExtra(NO_PISTAS, cuantasPistas);
//        intentBackMusic.putExtra(NICKNAME_ARRAY, nicknames);
//        intentBackMusic.putExtra(PUNTAJE_ARRAY, puntajes);
//        intentBackMusic.putExtra(GALLINA_ARRAY, gallinas);
//        startActivity(intentBackMusic);
//
//    }

    public void onSaveInstanceState(Bundle estado) {
//        estado.putIntArray(CUALES_TOPICS, topicsChosen);
//        estado.putInt(NO_PREGUNTAS, cuantasPreguntas);
//        estado.putInt(DIFICULTAD_PUNTOS, dificultadPuntos);
//        estado.putBoolean(ENABLE_PISTAS, enabledPistas);
//        estado.putInt(NO_PISTAS, cuantasPistas);
//        estado.putStringArray(NICKNAME_ARRAY, nicknames);
//        estado.putIntArray(PUNTAJE_ARRAY, puntajes);
//        estado.putBooleanArray(GALLINA_ARRAY, gallinas);
        estado.putInt(BUTTON_KEY, botonGreyedOuyt);
        super.onSaveInstanceState(estado);

    }

    private void estadosBotones(int btn) {
        switch (btn) {
            case 0:
                break;

            case 1:
                BtnTrivia.setBackgroundResource(R.drawable.opcionescogida1);
                Activity.setBackgroundResource(R.drawable.opcionescogidat);

                BtnRock.setBackgroundResource(R.drawable.boton_redondo_musica);
                BtnGeneral.setBackgroundResource(R.drawable.boton_redondo_musica);
                BtnClasica.setBackgroundResource(R.drawable.boton_redondo_musica);

                BtnRock.getBackground().setAlpha(60);
                BtnGeneral.getBackground().setAlpha(60);
                BtnClasica.getBackground().setAlpha(60);

                BtnExit.setEnabled(true);
                BtnExit.setBackgroundResource(R.drawable.boton_redondo_musica2);
                BtnExit.setText("A darle");
                BtnExit.setTextColor(Color.rgb(255, 255, 255));

                BtnTrivia.setClickable(false);
                BtnTrivia.setEnabled(false);

                BtnRock.setClickable(true);
                BtnGeneral.setClickable(true);
                BtnClasica.setClickable(true);
                animacion("rotation");
                break;

            case 2:
                BtnRock.setBackgroundResource(R.drawable.opcionescogida2);
                Activity.setBackgroundResource(R.drawable.opcionescogidar);

                BtnTrivia.setBackgroundResource(R.drawable.boton_redondo_musica);
                BtnGeneral.setBackgroundResource(R.drawable.boton_redondo_musica);
                BtnClasica.setBackgroundResource(R.drawable.boton_redondo_musica);

                BtnTrivia.getBackground().setAlpha(60);
                BtnGeneral.getBackground().setAlpha(60);
                BtnClasica.getBackground().setAlpha(60);

                BtnExit.setEnabled(true);
                BtnExit.setBackgroundResource(R.drawable.boton_redondo_musica2);
                BtnExit.setText("Demuestra quien es el que sabe");
                BtnExit.setTextColor(Color.rgb(255, 255, 255));

                BtnRock.setClickable(false);
                BtnRock.setEnabled(false);

                BtnTrivia.setClickable(true);
                BtnGeneral.setClickable(true);
                BtnClasica.setClickable(true);
                animacion("rotation2");
                break;

            case 3:
                BtnGeneral.setBackgroundResource(R.drawable.opcionescogida3);
                Activity.setBackgroundResource(R.drawable.fondomusic);

                BtnTrivia.setBackgroundResource(R.drawable.boton_redondo_musica);
                BtnRock.setBackgroundResource(R.drawable.boton_redondo_musica);
                BtnClasica.setBackgroundResource(R.drawable.boton_redondo_musica);

                BtnTrivia.getBackground().setAlpha(60);
                BtnRock.getBackground().setAlpha(60);
                BtnClasica.getBackground().setAlpha(60);

                BtnExit.setEnabled(true);
                BtnExit.setBackgroundResource(R.drawable.boton_redondo_musica2);
                BtnExit.setText("Bueno... si es que el quieres...");
                BtnExit.setTextColor(Color.rgb(255, 255, 255));

                BtnGeneral.setClickable(false);
                BtnGeneral.setEnabled(false);

                BtnTrivia.setClickable(true);
                BtnRock.setClickable(true);
                BtnClasica.setClickable(true);
                animacion("rotation3");
                break;

            case 4:
                BtnClasica.setBackgroundResource(R.drawable.opcionescogida4);
                Activity.setBackgroundResource(R.drawable.opcionescogidac);

                BtnTrivia.setBackgroundResource(R.drawable.boton_redondo_musica);
                BtnRock.setBackgroundResource(R.drawable.boton_redondo_musica);
                BtnGeneral.setBackgroundResource(R.drawable.boton_redondo_musica);

                BtnTrivia.getBackground().setAlpha(60);
                BtnRock.getBackground().setAlpha(60);
                BtnGeneral.getBackground().setAlpha(60);

                BtnExit.setEnabled(true);
                BtnExit.setBackgroundResource(R.drawable.boton_redondo_musica2);
                BtnExit.setText("Levanta el meñique, Jaime");
                BtnExit.setTextColor(Color.rgb(255, 255, 255));

                BtnClasica.setClickable(false);
                BtnClasica.setEnabled(false);

                BtnTrivia.setClickable(true);
                BtnRock.setClickable(true);
                BtnGeneral.setClickable(true);
                animacion("bucle");
                break;

            default:
                break;

        }
    }


    private void stopPlaying() {
        if (mp != null) {
            mp.stop();
            mp.release();
            mp = null;
        }
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(Activity5Music.this, "NO SE ELIGIÓ NINGUNA CANCIÓN", Toast.LENGTH_LONG).show();
        finish();
        super.onBackPressed();
    }
}
