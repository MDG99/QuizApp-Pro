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
import androidx.lifecycle.ViewModelProvider;

public class OptionsActivity extends AppCompatActivity {

    private CheckBox chkTodos;
    private CheckBox chkArte;
    private CheckBox chkGeografia;
    private CheckBox chkFrases;
    private CheckBox chkVideojuegos;
    private CheckBox chkHistoria;
    private CheckBox chkCultura;
    private CheckBox[] checkBoxes;

    private Spinner preguntasSpinner;
    private RadioButton btnFacil;
    private RadioButton btnMedio;
    private RadioButton btnDificil;
    private Switch pistaSwitch;

    private PlayViewModel play;
    private MainActivityViewModel model;

    private boolean pistaBoolean;
    private Spinner pistasSpinner;
    private Topics[] topicsArray;
    private boolean[] topicsChosen;
    private int preguntasCuantas;
    private String auxText = "Fácil";
    private int dificultad;
    private int pistasCuantas;
    private final String CUALES_TOPICS = "TOPICS_TO_ASK";
    private final String NO_PREGUNTAS = "QUESTIONS_QUANTITY";
    private final String DIFICULTAD_PUNTOS = "DIFFICULT";
    private final String ENABLE_PISTAS = "CHEATS_ENABLE";
    private final String NO_PISTAS = "CHEATS_QUANTITY";

    private UsuariosViewModel bestPlayers;
    private String[] s = new String[6];
    private int[] p = new int[6];
    private boolean[] g = new boolean[6];

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

//seccion de datos de usuarios
        bestPlayers = new ViewModelProvider(this).get(UsuariosViewModel.class);
        if (getIntent().getSerializableExtra("BEST_USERS_NICKNAME") != null) {
            s = getIntent().getStringArrayExtra("BEST_USERS_NICKNAME");
            p = getIntent().getIntArrayExtra("BEST_USERS_POINTS");
            g = getIntent().getBooleanArrayExtra("BEST_USERS_CHEATS");
            bestPlayers.setUsuarios(bestPlayers.Construir(s, p, g));
        }
        for (int i = 0; i < 6; i++) {
            s[i] = bestPlayers.getUsuarios()[i].getNickname();
            p[i] = bestPlayers.getUsuarios()[i].getPuntaje();
            g[i] = bestPlayers.getUsuarios()[i].isCheat();
        }
        //end seccion

        checkBoxes = new CheckBox[]{chkArte, chkGeografia, chkFrases, chkVideojuegos, chkHistoria, chkCultura};
        play = new ViewModelProvider(this).get(PlayViewModel.class);
//seccion para checar que temas se eligieron

        if (play.isAux()) {
            play.setCuantasPreguntas(getIntent().getIntExtra("QUESTIONS_QUANTITY", 5));
            play.setDificultadPuntos(getIntent().getIntExtra("DIFFICULT", 2));
            play.setEnabledPistas(getIntent().getBooleanExtra("CHEATS_ENABLE", false));
            play.setCuantasPistas(getIntent().getIntExtra("CHEATS_QUANTITY", 0));
            play.setTopicsChosen(getIntent().getIntArrayExtra("TOPICS_TO_ASK"));
            play.setAux(false);
        }

        play.setIndicesCkb(ArregloBooleanos(play.getTopicsChosen()));

        for (int i = 0; i < checkBoxes.length; i++) {
            for (int j = 0; j < play.getTopicsChosen().length; j++) {
                if (i == play.getTopicsChosen()[j]) {
                    switch (i) {
                        case 0:
                            checkBoxes[0].setChecked(true);
                            break;
                        case 1:
                            checkBoxes[1].setChecked(true);
                            break;
                        case 2:
                            checkBoxes[2].setChecked(true);
                            break;
                        case 3:
                            checkBoxes[3].setChecked(true);
                            break;
                        case 4:
                            checkBoxes[4].setChecked(true);
                            break;
                        case 5:
                            checkBoxes[5].setChecked(true);
                            break;
                    }
                }
            }
        }

        if (play.getCuantasPreguntas() == 5) {
            preguntasSpinner.setSelection(0);
        } else {
            preguntasSpinner.setSelection(1);
        }

        if (play.getDificultadPuntos() == 2) {
            btnFacil.setChecked(true);
            btnMedio.setChecked(false);
            btnDificil.setChecked(false);
        } else {
            if (play.getDificultadPuntos() == 3) {
                btnFacil.setChecked(false);
                btnMedio.setChecked(true);
                btnDificil.setChecked(false);
            } else {
                btnFacil.setChecked(false);
                btnMedio.setChecked(false);
                btnDificil.setChecked(true);
            }
        }

        if (play.isEnabledPistas()) {
            pistaSwitch.setChecked(true);
            pistasSpinner.setEnabled(true);
            switch (play.getCuantasPistas()) {
                case 1:
                    pistasSpinner.setSelection(0);
                    break;
                case 2:
                    pistasSpinner.setSelection(1);
                    break;
                case 3:
                    pistasSpinner.setSelection(2);
                    break;
                case 4:
                    pistasSpinner.setSelection(3);
                    break;
                case 5:
                    pistasSpinner.setSelection(4);
                    break;
            }

            pistasSpinner.getBackground().setAlpha(255);
        } else {
            pistasSpinner.setEnabled(false);
            pistasSpinner.getBackground().setAlpha(64);
            play.setCuantasPistas(0);
        }


        CompoundButton.OnCheckedChangeListener chkListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                for (int i = 0; i < checkBoxes.length; i++) {
                    if (checkBoxes[i].isChecked()) {
                        play.IndicesTrue(i);
                    } else {
                        play.IndicesFalse(i);
                    }
                }
                play.setTopicsChosen(ArregloEnteros(play.getIndicesCkb()));
                playercheck.start();
            }
        };

        chkTodos.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    for (int i = 0; i < checkBoxes.length; i++) {
                        checkBoxes[i].setChecked(true);
                        play.IndicesTrue(i);
                    }
                    play.setTopicsChosen(ArregloEnteros(play.getIndicesCkb()));
                    playercheck.start();
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


        preguntasSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                play.setCuantasPreguntas(Integer.parseInt(parent.getItemAtPosition(position).toString()));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                play.setCuantasPreguntas(5);
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
                        play.setDificultadPuntos(2);
                        break;
                    case "Medio":
                        play.setDificultadPuntos(3);
                        break;
                    case "Difícil":
                        play.setDificultadPuntos(4);
                        break;
                    default:
                        play.setDificultadPuntos(2);
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
                    play.setEnabledPistas(true);
                    pistasSpinner.setEnabled(true);
                    play.setCuantasPistas(Integer.parseInt(pistasSpinner.getSelectedItem().toString()));
                    pistasSpinner.getBackground().setAlpha(255);
                } else {
                    pistasSpinner.setEnabled(false);
                    play.setEnabledPistas(false);
                    pistasSpinner.getBackground().setAlpha(64);
                    play.setCuantasPistas(0);
                }
            }
        });

        pistasSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                play.setCuantasPistas(Integer.parseInt(parent.getItemAtPosition(position).toString()));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                if (pistaSwitch.isEnabled()) {
                    play.setCuantasPistas(1);
                } else {
                    play.setCuantasPistas(0);
                }
            }
        });
        //end seccion


    }

    //ESTO ABRE LA MAIN ACTIVITY Y LE MANDA UN INTENT
    @Override
    public void onBackPressed() {

        if (play.getTopicsChosen().length == 0) {
            int[] aux = new int[]{0, 3, 2};
            play.setTopicsChosen(aux);
            Toast.makeText(this, "Se han seleccionado los temas predeterminados", Toast.LENGTH_SHORT).show();
        }
        Intent intent = new Intent(OptionsActivity.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        intent.putExtra(CUALES_TOPICS, play.getTopicsChosen());
        intent.putExtra(NO_PREGUNTAS, play.getCuantasPreguntas());
        intent.putExtra(DIFICULTAD_PUNTOS, play.getDificultadPuntos());
        intent.putExtra(ENABLE_PISTAS, play.isEnabledPistas());
        intent.putExtra(NO_PISTAS, play.getCuantasPistas());
        intent.putExtra("BEST_USERS_NICKNAME", s);
        intent.putExtra("BEST_USERS_POINTS", p);
        intent.putExtra("BEST_USERS_CHEATS", g);
        startActivity(intent);

    }

    public int[] ArregloEnteros(boolean[] arregloBooleanos) {
        int size = 0;
        for (int i = 0; i < arregloBooleanos.length; i++) {
            if (arregloBooleanos[i]) {
                size++;
            }
        }
        int[] arregloEnteros = new int[size];
        int indice = 0;
        for (int i = 0; i < arregloBooleanos.length; i++) {
            if (arregloBooleanos[i]) {
                arregloEnteros[indice] = i;
                indice++;
            }
        }
        return arregloEnteros;
    }

    public boolean[] ArregloBooleanos(int[] arregloEnteros) {
        boolean[] arregloBooleanos = new boolean[6];
        for (int y = 0; y < 6; y++) {
            arregloBooleanos[y] = false;
        }
        for (int i = 0; i < arregloEnteros.length; i++) {
            arregloBooleanos[arregloEnteros[i]] = true;
        }
        return arregloBooleanos;
    }
}
