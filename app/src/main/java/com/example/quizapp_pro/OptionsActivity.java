package com.example.quizapp_pro;

import android.content.Intent;
import android.media.MediaPlayer;
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
    private boolean pistaBoolean;
    private Spinner pistasSpinner;
    private Topics[] topicsArray;
    private boolean[] topicsChosen;
    private int preguntasCuantas;
    private String auxText = "Fácil";
    private int dificultad;
    private int pistasCuantas;
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
    //private final String RECIBE_PREGUNTAS = "XD";
    private Bundle estado;
    private MediaPlayer playercheck;
    private MediaPlayer playercheat;

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

        playercheck = MediaPlayer.create(OptionsActivity.this, R.raw.fxburbuja);
        playercheat = MediaPlayer.create(OptionsActivity.this, R.raw.fxgallina);

        final CheckBox[] checkBoxes = {chkArte, chkGeografia, chkFrases, chkVideojuegos, chkHistoria, chkCultura};
        topicsArray = new Topics[]{
                new Topics(0, "Arte"),
                new Topics(1, "Geografía"),
                new Topics(2, "Frases célebres"),
                new Topics(3, "Videojuegos"),
                new Topics(4, "Historia"),
                new Topics(5, "Cultura general")
        };
//seccion para checar que temas se eligieron


        CompoundButton.OnCheckedChangeListener chkListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int i = 0;
                for (CheckBox chk : checkBoxes) {
                    if (chk.isChecked()) {
                        topicsChosen[i] = true;
                        playercheck.start();
                    } else {
                        topicsChosen[i] = false;
                    }
                    i++;
                }

            }
        };

        chkTodos.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkBoxes[0].setChecked(true);
                    checkBoxes[1].setChecked(true);
                    checkBoxes[2].setChecked(true);
                    checkBoxes[3].setChecked(true);
                    checkBoxes[4].setChecked(true);
                    checkBoxes[5].setChecked(true);
                    playercheck.start();

                    topicsChosen[0] = true;
                    topicsChosen[1] = true;
                    topicsChosen[2] = true;
                    topicsChosen[3] = true;
                    topicsChosen[4] = true;
                    topicsChosen[5] = true;
                }
            }
        });

        chkArte.setOnCheckedChangeListener(chkListener);
        chkGeografia.setOnCheckedChangeListener(chkListener);
        chkFrases.setOnCheckedChangeListener(chkListener);
        chkVideojuegos.setOnCheckedChangeListener(chkListener);
        chkHistoria.setOnCheckedChangeListener(chkListener);
        chkCultura.setOnCheckedChangeListener(chkListener);
//end seccion


//seccion para tomar el numero de preguntas del spinner

        pistasSpinner.setEnabled(false);
        pistasSpinner.getBackground().setAlpha(64);

        if (getIntent().getExtras() != null) {
            int noPreguntas = getIntent().getIntExtra("NO_PREGUNTAS", 5);
            int noDif = getIntent().getIntExtra("DIFICULTAD_PUNTOS", 2);
            boolean cheats = getIntent().getBooleanExtra("ENABLE_PISTAS", false);
            int noPistas = getIntent().getIntExtra("NO_PISTAS", 0);
            int[] temasId = getIntent().getIntArrayExtra("CUALES_TOPICS");

            nicknames = getIntent().getStringArrayExtra(NICKNAME_ARRAY);
            puntajes = getIntent().getIntArrayExtra(PUNTAJE_ARRAY);
            gallinas = getIntent().getBooleanArrayExtra(GALLINA_ARRAY);

            pistaBoolean = cheats;
            dificultad = noDif;
            preguntasCuantas = noPreguntas;
            pistasCuantas = noPistas;
            boolean[] auxTopics = recibeTemas(temasId);

            topicsChosen[0] = auxTopics[0];
            topicsChosen[1] = auxTopics[1];
            topicsChosen[2] = auxTopics[2];
            topicsChosen[3] = auxTopics[3];
            topicsChosen[4] = auxTopics[4];
            topicsChosen[5] = auxTopics[5];

            chkArte.setChecked(topicsChosen[0]);
            chkGeografia.setChecked(topicsChosen[1]);
            chkFrases.setChecked(topicsChosen[2]);
            chkVideojuegos.setChecked(topicsChosen[3]);
            chkHistoria.setChecked(topicsChosen[4]);
            chkCultura.setChecked(topicsChosen[5]);


            if (pistaBoolean) {
                pistasSpinner.setSelection(pistasCuantas - 1);
                pistasSpinner.setEnabled(true);
                pistasSpinner.getBackground().setAlpha(255);
            }

            switch (noPreguntas) {
                case 5:
                    preguntasSpinner.setSelection(0);
                    break;
                case 10:
                    preguntasSpinner.setSelection(1);
                    break;
                default:
                    preguntasSpinner.setSelection(0);
                    break;
            }

            switch (noDif) {
                case 2:
                    btnFacil.setChecked(true);
                    break;
                case 3:
                    btnMedio.setChecked(true);
                    break;
                case 4:
                    btnDificil.setChecked(true);
                    break;
                default:
                    btnFacil.setChecked(true);
                    break;
            }

            if (cheats) {
                pistaSwitch.setChecked(true);
            } else {
                pistaSwitch.setChecked(false);
            }

        } else {
            pistaBoolean = false;
            dificultad = 2;
            preguntasCuantas = 5;
            pistasCuantas = 0;
            topicsChosen = new boolean[]{false, true, false, false, false, false};
        }

        if (estado != null) {
            savedInstanceState = estado;
        }

        if (savedInstanceState != null) {
            topicsChosen = recibeTemas(savedInstanceState.getIntArray(CUALES_TOPICS));
            dificultad = savedInstanceState.getInt(DIFICULTAD_PUNTOS);
            preguntasCuantas = savedInstanceState.getInt(NO_PREGUNTAS);
            pistasCuantas = savedInstanceState.getInt(NO_PISTAS);
            pistaBoolean = savedInstanceState.getBoolean(ENABLE_PISTAS);
            nicknames = savedInstanceState.getStringArray(NICKNAME_ARRAY);
            puntajes = savedInstanceState.getIntArray(PUNTAJE_ARRAY);
            gallinas = savedInstanceState.getBooleanArray(GALLINA_ARRAY);


            chkArte.setChecked(topicsChosen[0]);
            chkGeografia.setChecked(topicsChosen[1]);
            chkFrases.setChecked(topicsChosen[2]);
            chkVideojuegos.setChecked(topicsChosen[3]);
            chkHistoria.setChecked(topicsChosen[4]);
            chkCultura.setChecked(topicsChosen[5]);


            if (pistaBoolean) {
                pistasSpinner.setSelection(pistasCuantas - 1);
                pistasSpinner.setEnabled(true);
                pistasSpinner.getBackground().setAlpha(255);
            }

            switch (preguntasCuantas) {
                case 5:
                    preguntasSpinner.setSelection(0);
                    break;
                case 10:
                    preguntasSpinner.setSelection(1);
                    break;
                default:
                    preguntasSpinner.setSelection(0);
                    break;
            }

            switch (dificultad) {
                case 2:
                    btnFacil.setChecked(true);
                    break;
                case 3:
                    btnMedio.setChecked(true);
                    break;
                case 4:
                    btnDificil.setChecked(true);
                    break;
                default:
                    btnFacil.setChecked(true);
                    break;
            }

            if (pistaBoolean) {
                pistaSwitch.setChecked(true);
            } else {
                pistaSwitch.setChecked(false);
            }
        }

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


        pistaSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (pistaSwitch.isChecked()) {
                    playercheat.start();
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

        pistaBoolean = pistaSwitch.isChecked();
        if (!pistaBoolean) {
            pistasCuantas = 0;
        }

        Intent intent = new Intent(OptionsActivity.this, MainActivity.class);
        intent.putExtra(CUALES_TOPICS, ArregloTemasId(topicsChosen));
        intent.putExtra(NO_PREGUNTAS, preguntasCuantas);
        intent.putExtra(DIFICULTAD_PUNTOS, dificultad);
        intent.putExtra(ENABLE_PISTAS, pistaBoolean);
        intent.putExtra(NO_PISTAS, pistasCuantas);
        intent.putExtra(NICKNAME_ARRAY, nicknames);
        intent.putExtra(PUNTAJE_ARRAY, puntajes);
        intent.putExtra(GALLINA_ARRAY, gallinas);
        startActivity(intent);


    }

    public int[] ArregloTemasId(boolean[] b) {
        List<Integer> list = new ArrayList<>();
        int i = 0;
        for (boolean x : b) {
            if (x) {
                list.add(i);
            }
            i++;
        }


        int[] arreglo = new int[list.size()];
        int i2 = 0;
        for (Integer y : list) {
            arreglo[i2] = list.get(i2);
            i2++;
        }
        return arreglo;
    }

    public void onSaveInstanceState(Bundle estado) {
        estado.putIntArray(CUALES_TOPICS, ArregloTemasId(topicsChosen));
        estado.putInt(NO_PREGUNTAS, preguntasCuantas);
        estado.putInt(DIFICULTAD_PUNTOS, dificultad);
        estado.putBoolean(ENABLE_PISTAS, pistaBoolean);
        estado.putInt(NO_PISTAS, pistasCuantas);
        estado.putStringArray(NICKNAME_ARRAY, nicknames);
        estado.putIntArray(PUNTAJE_ARRAY, puntajes);
        estado.putBooleanArray(GALLINA_ARRAY, gallinas);
        super.onSaveInstanceState(estado);

    }

    public boolean[] recibeTemas(int[] temasId) {
        boolean[] aux = new boolean[]{false, false, false, false, false, false};
        for (int x : temasId) {
            switch (x) {
                case 0:
                    aux[0] = true;
                    break;
                case 1:
                    aux[1] = true;
                    break;
                case 2:
                    aux[2] = true;
                    break;
                case 3:
                    aux[3] = true;
                    break;
                case 4:
                    aux[4] = true;
                    break;
                case 5:
                    aux[5] = true;
                    break;
                default:
                    break;
            }
        }
        return aux;
    }
}

