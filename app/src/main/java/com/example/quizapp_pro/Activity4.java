package com.example.quizapp_pro;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

public class Activity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);

        ImageView ViewImage = findViewById(R.id.imageViewFondo);

        //Añadir fondo a la pantalla
        Drawable fondo = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            fondo = getResources().getDrawable(R.drawable.fondo4, null);
        }
        ViewImage.setImageDrawable(fondo);

    }
}
