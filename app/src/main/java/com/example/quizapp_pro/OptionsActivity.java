package com.example.quizapp_pro;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

public class OptionsActivity extends AppCompatActivity {

    private CheckBox chkTodos;
    private CheckBox chkArte;
    private CheckBox chkGeografia;
    private CheckBox chkFrases;
    private CheckBox chkVideojuegos;
    private CheckBox chkHistoria;
    private CheckBox chkCultura;
    private Spinner preguntasSpinner;
    private RadioButton btnFacil;
    private RadioButton btnMedio;
    private RadioButton btnDificil;
    private Switch pistaSwitch;
    private Spinner pistasSpinner;
    private Topics[] topicsArray;
    private boolean[] topicsChosen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        chkTodos = findViewById(R.id.todos_checkbox);
        chkArte = findViewById(R.id.arte_checkbox);
        chkGeografia = findViewById(R.id.geografia_checkbox);
        chkFrases = findViewById(R.id.frases_checkbox);
        chkVideojuegos = findViewById(R.id.videojuegos_checkbox);
        chkHistoria = findViewById(R.id.historia_checkbox);
        chkCultura = findViewById(R.id.cultura_checkbox);

        preguntasSpinner = findViewById(R.id.preguntas_spinner);
        btnFacil = findViewById(R.id.facil_radio);
        btnMedio = findViewById(R.id.medio_radio);
        btnDificil = findViewById(R.id.dificil_radio);
        pistaSwitch = findViewById(R.id.pistas_button);
        pistasSpinner = findViewById(R.id.pistas_spinner);


        topicsArray = new Topics[]{
                new Topics(0, "Arte"),
                new Topics(1, "Geografía"),
                new Topics(2, "Frases célebres"),
                new Topics(3, "Videojuegos"),
                new Topics(4, "Historia"),
                new Topics(5, "Cultura general")
        };

        final CheckBox[] checkBoxes = {chkArte, chkGeografia, chkFrases, chkVideojuegos, chkHistoria, chkCultura};

        topicsChosen = new boolean[]{false, false, false, false, false, false};

        CompoundButton.OnCheckedChangeListener chkListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int i = 0;
                for (CheckBox chk : checkBoxes) {
                    if (chk.isChecked()) {
                        topicsChosen[i] = true;
                    }
                    i++;
                }

            }
        };

        chkArte.setOnCheckedChangeListener(chkListener);
        chkGeografia.setOnCheckedChangeListener(chkListener);
        chkFrases.setOnCheckedChangeListener(chkListener);
        chkVideojuegos.setOnCheckedChangeListener(chkListener);
        chkHistoria.setOnCheckedChangeListener(chkListener);
        chkCultura.setOnCheckedChangeListener(chkListener);

    }
}
