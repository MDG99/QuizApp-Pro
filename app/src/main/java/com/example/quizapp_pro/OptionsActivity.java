package com.example.quizapp_pro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

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
    private int preguntasCuantas = 5;
    private String auxText = "Fácil";
    private int dificultad = 2;
    private int pistasCuantas = 0;
    private final String CUALES_TOPICS = "CUALES_TOPICS";
    private final String NO_PREGUNTAS = "NO_PREGUNTAS";
    private final String DIFICULTAD_PUNTOS = "DIFICULTAD_PUNTOS";
    private final String ENABLE_PISTAS = "ENABLE_PISTAS";
    private final String NO_PISTAS = "NO_PISTAS";

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
//seccion para checar que temas se eligieron
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
//end seccion

//seccion para tomar el numero de preguntas del spinner
        preguntasSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                preguntasCuantas = Integer.parseInt(parent.getItemAtPosition(position).toString());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                preguntasCuantas = 5;
            }
        });

//end seccion

        //inicio seccion para elegir dificultad
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton src = (RadioButton) v;
                auxText = src.getText().toString();

                switch (auxText) {
                    case "Fácil":
                        dificultad = 2;
                        break;
                    case "Medio":
                        dificultad = 3;
                        break;
                    case "Difícil":
                        dificultad = 4;
                        break;
                    default:
                        dificultad = 2;
                        break;
                }
            }
        };
        btnFacil.setOnClickListener(listener);
        btnMedio.setOnClickListener(listener);
        btnDificil.setOnClickListener(listener);
        //end seccion

        //inicio seccion para elegir pistas
        pistasSpinner.setEnabled(false);
        pistasSpinner.getBackground().setAlpha(64);

        pistaSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (pistaSwitch.isChecked()) {
                    pistasSpinner.setEnabled(true);
                    pistasCuantas = Integer.parseInt(pistasSpinner.getSelectedItem().toString());
                    pistasSpinner.getBackground().setAlpha(255);
                    Toast.makeText(OptionsActivity.this, preguntasCuantas + " - " + dificultad + " - " + pistasCuantas, Toast.LENGTH_SHORT).show();
                } else {
                    pistasSpinner.setEnabled(false);
                    pistasSpinner.getBackground().setAlpha(64);
                    pistasCuantas = 0;
                }
            }
        });

        pistasSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pistasCuantas = Integer.parseInt(parent.getItemAtPosition(position).toString());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                pistasCuantas = 0;
            }
        });
        //end seccion


    }

    //ESTO ABRE LA MAIN ACTIVITY Y LE MANDA UN INTENT
    @Override
    public void onBackPressed() {


        Intent intent = new Intent(OptionsActivity.this, MainActivity.class);
        intent.putExtra(CUALES_TOPICS, ArregloTemasId(topicsChosen));
        intent.putExtra(NO_PREGUNTAS, preguntasCuantas);
        intent.putExtra(DIFICULTAD_PUNTOS, dificultad);
        intent.putExtra(ENABLE_PISTAS, pistaSwitch.isChecked());
        intent.putExtra(NO_PISTAS, pistasCuantas);
        startActivity(intent);
    }

    public int[] ArregloTemasId(boolean[] b) {
        List<Integer> list = new ArrayList<>();
        int i = 0;
        for (boolean x : b) {
            if (x == true) {
                list.add(i);
            }
            i++;
        }


        int[] arreglo = new int[i - 1];
        int i2 = 0;
        for (Integer y : list) {
            arreglo[i2] = list.get(i2);
            i2++;
        }
        return arreglo;
    }
}

