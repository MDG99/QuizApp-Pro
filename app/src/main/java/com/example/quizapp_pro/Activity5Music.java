package com.example.quizapp_pro;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class Activity5Music extends AppCompatActivity {

    private Button BtnTrivia;
    private Button BtnRock;
    private Button BtnClascia;
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

    private ObjectAnimator animatorRotation;

    //Reproduce un conjuto de ObjectAnimator en un orden en específico. Las animaciones, a su vez, pueden ser igual todas a las vez.
    private AnimatorSet animatorSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity5_music);

        player1 = MediaPlayer.create(Activity5Music.this, R.raw.trividademo);
        player2 = MediaPlayer.create(Activity5Music.this, R.raw.rockdemo);
        player3 = MediaPlayer.create(Activity5Music.this, R.raw.generaldemo);
        player4 = MediaPlayer.create(Activity5Music.this, R.raw.clasicademo);

        BtnTrivia = findViewById(R.id.btnTrvia);
        BtnClascia = findViewById(R.id.btnClasica);
        BtnRock = findViewById(R.id.btnRock);
        BtnGeneral = findViewById(R.id.btnGeneral);
        BtnExit = findViewById(R.id.btnExit);
        ImgTrvia = findViewById(R.id.imageView1);
        ImgRock = findViewById(R.id.imageView2);
        ImgGeneral = findViewById(R.id.imageView3);
        ImgClasica = findViewById(R.id.imageView4);
        Activity = findViewById(R.id.activity);

        BtnExit.getBackground().setAlpha(60);

        BtnTrivia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BtnTrivia.setBackgroundResource(R.drawable.opcionescogida1);
                Activity.setBackgroundResource(R.drawable.opcionescogidat);

                BtnRock.setBackgroundResource(R.drawable.boton_redondo_musica);
                BtnGeneral.setBackgroundResource(R.drawable.boton_redondo_musica);
                BtnClascia.setBackgroundResource(R.drawable.boton_redondo_musica);

                BtnRock.getBackground().setAlpha(60);
                BtnGeneral.getBackground().setAlpha(60);
                BtnClascia.getBackground().setAlpha(60);

                BtnExit.setBackgroundResource(R.drawable.boton_redondo_musica2);
                BtnExit.setText("A darle");
                BtnExit.setTextColor(Color.rgb(255, 255, 255));

                BtnTrivia.setClickable(false);

                BtnRock.setClickable(true);
                BtnGeneral.setClickable(true);
                BtnClascia.setClickable(true);


                if (player2.isPlaying()) {
                    player2.stop();
                } else if (player3.isPlaying()) {
                    player3.stop();
                } else if (player4.isPlaying()) {
                    player4.stop();
                }
                player1.start();
                //final MediaPlayer mp = MediaPlayer.create(this, R.raw.music1);
                //mp.setLooping(true);
                //mp.start();

                animacion("rotation");
                //animacion("bucle");
            }
        });

        BtnRock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BtnRock.setBackgroundResource(R.drawable.opcionescogida2);
                Activity.setBackgroundResource(R.drawable.opcionescogidar);

                BtnTrivia.setBackgroundResource(R.drawable.boton_redondo_musica);
                BtnGeneral.setBackgroundResource(R.drawable.boton_redondo_musica);
                BtnClascia.setBackgroundResource(R.drawable.boton_redondo_musica);

                BtnTrivia.getBackground().setAlpha(60);
                BtnGeneral.getBackground().setAlpha(60);
                BtnClascia.getBackground().setAlpha(60);

                BtnExit.setBackgroundResource(R.drawable.boton_redondo_musica2);
                BtnExit.setText("Demuestra quien es el que sabe");
                BtnExit.setTextColor(Color.rgb(255, 255, 255));

                BtnRock.setClickable(false);

                BtnTrivia.setClickable(true);
                BtnGeneral.setClickable(true);
                BtnClascia.setClickable(true);

                if (player1.isPlaying()) {
                    player1.stop();
                } else if (player4.isPlaying()) {
                    player4.stop();
                } else if (player3.isPlaying()) {
                    player3.stop();
                }
                player2.start();
                animacion("rotation2");
            }
        });

        BtnGeneral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BtnGeneral.setBackgroundResource(R.drawable.opcionescogida3);
                Activity.setBackgroundResource(R.drawable.fondomusic);

                BtnTrivia.setBackgroundResource(R.drawable.boton_redondo_musica);
                BtnRock.setBackgroundResource(R.drawable.boton_redondo_musica);
                BtnClascia.setBackgroundResource(R.drawable.boton_redondo_musica);

                BtnTrivia.getBackground().setAlpha(60);
                BtnRock.getBackground().setAlpha(60);
                BtnClascia.getBackground().setAlpha(60);

                BtnExit.setBackgroundResource(R.drawable.boton_redondo_musica2);
                BtnExit.setText("Bueno... si es que el quieres...");
                BtnExit.setTextColor(Color.rgb(255, 255, 255));

                BtnGeneral.setClickable(false);

                BtnTrivia.setClickable(true);
                BtnRock.setClickable(true);
                BtnClascia.setClickable(true);

                if (player1.isPlaying()) {
                    player1.stop();
                } else if (player2.isPlaying()) {
                    player2.stop();
                } else if (player4.isPlaying()) {
                    player4.stop();
                }

                player3.start();
                animacion("rotation3");
            }
        });

        BtnClascia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BtnClascia.setBackgroundResource(R.drawable.opcionescogida4);
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

                BtnClascia.setClickable(false);

                BtnTrivia.setClickable(true);
                BtnRock.setClickable(true);
                BtnGeneral.setClickable(true);

                if (player1.isPlaying()) {
                    player1.stop();
                } else if (player2.isPlaying()) {
                    player2.stop();
                } else if (player3.isPlaying()) {
                    player3.stop();
                }
                player4.start();
                animacion("bucle");
            }
        });
    }

    private void animacion(String animacion) {
        switch (animacion) {
            case "rotation":
                animatorRotation = ObjectAnimator.ofFloat(ImgTrvia, "rotation", 0f, 360f);
                animatorRotation.setDuration(30000);
                AnimatorSet animatorSetRotation = new AnimatorSet();
                animatorSetRotation.play(animatorRotation);
                animatorSetRotation.start();
                break;

            case "rotation2":
                animatorRotation = ObjectAnimator.ofFloat(ImgRock, "rotation", 0f, 360f);
                animatorRotation.setDuration(30000);
                AnimatorSet animatorSetRotation2 = new AnimatorSet();
                animatorSetRotation2.play(animatorRotation);
                animatorSetRotation2.start();
                break;

            case "rotation3":
                animatorRotation = ObjectAnimator.ofFloat(ImgGeneral, "rotation", 0f, 360f);
                animatorRotation.setDuration(30000);
                AnimatorSet animatorSetRotation3 = new AnimatorSet();
                animatorSetRotation3.play(animatorRotation);
                animatorSetRotation3.start();
                break;

            case "bucle":
                animatorRotation = ObjectAnimator.ofFloat(ImgClasica, "rotation", 0f, 360f);
                animatorRotation.setDuration(30000);
                AnimatorSet animatorSetBucle = new AnimatorSet();
                animatorSetBucle.play(animatorRotation);
                animatorSetBucle.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        animation.start();
                    }
                });
                animatorSetBucle.start();
                break;
        }
    }
}
