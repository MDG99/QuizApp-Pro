package com.example.quizapp_pro;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ImageView imgTema;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //imgTema = findViewById(R.id.imagen_portada);

        //imgTema.setImageResource(R.drawable.ic_launcher_foreground);

        Toast.makeText(MainActivity.this, "Ejemplo", Toast.LENGTH_SHORT).show();
    }
}
