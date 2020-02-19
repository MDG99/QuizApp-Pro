package com.example.quizapp_pro;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Activity5Music extends AppCompatActivity {

    private Button btnTrivia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity5_music);

        btnTrivia = findViewById(R.id.button2);

        btnTrivia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer player = MediaPlayer.create(Activity5Music.this, R.raw.music1);
                player.start();
                //final MediaPlayer mp = MediaPlayer.create(this, R.raw.music1);
                //mp.setLooping(true);
                //mp.start();


            }
        });

    }


}
