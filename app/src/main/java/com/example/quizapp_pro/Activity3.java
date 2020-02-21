package com.example.quizapp_pro;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Activity3 extends AppCompatActivity {

    private String nickname; //Al final se guarda y se pssa
    private int difficult = 4; //guardar
    private int currentQuestion = 0; //guardar
    private int questionsQuantity; //guardar
    private int[] cheatsCounterByQuestion; //guardar
    private int questionsForTopic;
    private int residueQuestionsForTopic;
    private int cheatsQuantity = 5; //guardar
    private int Puntaje; //guardar
    private int[] topicsToAsk; //guardar
    private int[][] colorsAnswers; //guardar
    private EditText Nickname_Input;

    private boolean cheatsEnable = true; //guardar
    private boolean cheatRecorder = false; //guardar
    private boolean cheatsFollowerEnable[];
    private boolean[][] habilitadorDeRespuestas;
    private boolean[] puntajeCheats;
    private boolean[] habilitadorDeCheats;
    private boolean[] Respondido;

    private AlertDialog Dialogo;
    private List<Questions> questionsToShow;
    private Questions[] questionsToShowSaved;

    private Answers[][] answersToShowSaved;
    private Answers[][] answersToShow;




    //TextViews: contador, trampas y pregunta
    private TextView questionsFollower;
    private TextView cheatsFollower;
    private TextView questionsText;
    private TextView cheatsButton;

    private EditText NickName;


    //Colores a usar
    private int COLOR_NORMAL = Color.rgb(55, 134, 197);
    private int COLOR_CORRECTO = Color.rgb(55, 197, 62);
    private int COLOR_INCORRECTO = Color.rgb(197, 55, 72);
    private int COLOR_TRAMPA = Color.rgb(46, 55, 58);

    //ImageView: ícono de trampas
    private ImageView cheatsImage;

    //Botones: controles de preguntas
    private Button prevButton;
    private Button nextButton;

    //Botones: respuestas
    private Button respuesta01;
    private Button respuesta02;
    private Button respuesta03;
    private Button respuesta04;
    private Button[][] respuestas;




    //Nombres de los Intents de donde se recaudará información del OptionsActivity
    public static final String DIFFICULT_INTENT = "DIFICULTAD_PUNTOS";
    public static final String QUANTITY_QUESTIONS_INTENT = "NO_PREGUNTAS";
    public static final String CHEATS_ENABLE_INTENT = "ENABLE_PISTAS";
    public static final String CHEATS_QUANTITY_INTENT = "NO_PISTAS";
    public static final String TOPICS_ID_INTENT = "CUALES_TOPICS";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        Intent intent = getIntent();
        //difficult = intent.getIntExtra(DIFFICULT_INTENT, 4); //Correcto
        questionsQuantity = intent.getIntExtra(QUANTITY_QUESTIONS_INTENT, 60); //Me da valores de 0 o 1
        //cheatsEnable = intent.getBooleanExtra(CHEATS_ENABLE_INTENT, false); //Correcto
        //cheatsQuantity = intent.getIntExtra(CHEATS_QUANTITY_INTENT, 0); //Correcto
        //topicsToAsk = intent.getIntArrayExtra(TOPICS_ID_INTENT);
        topicsToAsk = new int[3];
        topicsToAsk[0] = 0;
        topicsToAsk[1] = 1;
        topicsToAsk[2] = 2;


        habilitadorDeCheats = new boolean[questionsQuantity];
        cheatsCounterByQuestion = new int[questionsQuantity];
        colorsAnswers = new int[questionsQuantity][difficult];
        Respondido = new boolean[questionsQuantity];
        habilitadorDeRespuestas = new boolean[questionsQuantity][difficult];
        puntajeCheats = new boolean[questionsQuantity];


        questionsFollower = findViewById(R.id.questionsQuantity);
        questionsText = findViewById(R.id.questionText);
        respuesta01 = findViewById(R.id.respuesta01);
        respuesta02 = findViewById(R.id.respuesta02);
        respuesta03 = findViewById(R.id.respuesta03);
        respuesta04 = findViewById(R.id.respuesta04);
        prevButton = findViewById(R.id.prevButton);
        nextButton = findViewById(R.id.nextButton);
        cheatsButton = findViewById(R.id.cheatsQuantity);
        NickName = findViewById(R.id.dialogo);
        Nickname_Input = findViewById(R.id.dialogo);


        MainActivityViewModel model = new MainActivityViewModel();

        questionsToShow = new ArrayList<>();
        questionsToShowSaved = new Questions[questionsQuantity];
        answersToShow = new Answers[questionsQuantity][difficult];
        answersToShowSaved = new Answers[questionsQuantity][difficult];



        if(savedInstanceState != null){
            currentQuestion = savedInstanceState.getInt("CURRENT_QUESTIONS");
            difficult = savedInstanceState.getInt("DIFICULTAD");
            questionsToShowSaved = (Questions[]) savedInstanceState.getSerializable("QUESTIONS_TO_SHOW_SAVED");
            questionsQuantity = savedInstanceState.getInt("QUESTIONS_QUANTITY");
            cheatsCounterByQuestion = savedInstanceState.getIntArray("CHEATS_COUNTER_BY_QUESTION");
            cheatsQuantity = savedInstanceState.getInt("CHEATS_QUANTITY");
            Puntaje = savedInstanceState.getInt("PUNTAJE");
            topicsToAsk = savedInstanceState.getIntArray("TOPICs_TO_ASK");
            colorsAnswers = (int[][]) savedInstanceState.getSerializable("COLOR_ANSWERS");
            cheatsEnable = savedInstanceState.getBoolean("CHEATS_ENABLE");
            cheatRecorder = savedInstanceState.getBoolean("CHEATS_RECORDER");
            cheatsFollowerEnable = savedInstanceState.getBooleanArray("CHEATS_FOLLOWER_ENABLE");
            habilitadorDeRespuestas = (boolean[][]) savedInstanceState.getSerializable("HABILITADOR_DE_RESPUESTAS");
            puntajeCheats =  savedInstanceState.getBooleanArray("PUNTAJE_CHEATS");
            habilitadorDeCheats = savedInstanceState.getBooleanArray("HABILITADOR_DE_CHEATS");
            Respondido = savedInstanceState.getBooleanArray("RESPONDIDO");
            answersToShow = (Answers[][]) savedInstanceState.getSerializable("ANSWERS_TO_SHOW");
            answersToShowSaved = (Answers[][]) savedInstanceState.getSerializable("ANSWERS_TO_SHOW_SAVED");
        }else{

            //#region Inicialización de variables





            for (int x = 0; x < questionsQuantity; x++) {
                Respondido[x] = false;
            }

            for (int x = 0; x < questionsQuantity; x++) {
                cheatsCounterByQuestion[x] = difficult;
            }

            for (int y = 0; y < questionsQuantity; y++) {
                for (int x = 0; x < difficult; x++) {
                    colorsAnswers[y][x] = Color.rgb(0, 0, 100);
                }
            }

            for (boolean b : puntajeCheats) {
                b = false;
            }

            for (int y = 0; y < questionsQuantity; y++) {
                for (int x = 0; x < difficult; x++) {
                    habilitadorDeRespuestas[y][x] = true;
                }
            }


            Puntaje = 0;


            //#endregion

            //#region Llenado de información Random

            questionsToShow.addAll(model.questionsByTopicRandom(questionsQuantity, topicsToAsk));
            questionsToShow.toArray(questionsToShowSaved);


            //Llenado de respuestas a mostrar aleatoriamente.- Te aseguras que esté la respuesta correcta
            for (int i = 0; i < questionsQuantity; i++) {
                List<Answers> fakeAnswers = new ArrayList<>();
                //Agregamos las respuestas falsas
                fakeAnswers.add(questionsToShowSaved[i].getFakeAnswer1());
                fakeAnswers.add(questionsToShowSaved[i].getFakeAnswer2());
                fakeAnswers.add(questionsToShowSaved[i].getFakeAnswer3());
                for (int d = 0; d < difficult; d++) {
                    if (d == 0)
                        answersToShow[i][d] = questionsToShowSaved[i].getCorrectAnswer();
                    else {
                        Random rand = new Random();
                        int aleatorio = rand.nextInt(fakeAnswers.size());
                        answersToShow[i][d] = fakeAnswers.get(aleatorio);
                        fakeAnswers.remove(aleatorio);
                    }
                }
            }


            //Respuestas aleatorias

            for (int i = 0; i < questionsQuantity; i++) {
                List<Answers> auxAns = new ArrayList<>();
                for (int h = 0; h < difficult; h++) {
                    auxAns.add(answersToShow[i][h]);
                }
                for (int d = 0; d < difficult; d++) {
                    Random rand = new Random();
                    int aleatorio = rand.nextInt(auxAns.size());
                    answersToShowSaved[i][d] = auxAns.get(aleatorio);
                    auxAns.remove(aleatorio);
                }
            }


            //#endregion

        }

        //#region Actividades iniciales de la apliación
        ShowQuestionsFollower(currentQuestion);
        InicializacionBotones();
        HabilitacionBotones(difficult);
        CheckerCheatsButton(cheatsEnable);
        GetAnswersColors();
        questionsText.setText(questionsToShowSaved[currentQuestion].getQuestionText());
        DesplegarRespuestas(currentQuestion);
        //#endregion

        //#region Click botón Next
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NextQuestionIndex();
                ShowQuestionsFollower(currentQuestion);
                questionsText.setText(questionsToShowSaved[currentQuestion].getQuestionText());
                DesplegarRespuestas(currentQuestion);
                GetAnswersColors();
                CheatsButtonByQuestion();
            }
        });
        //#endregion

        //#region Click botón Prev
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PrevQuestionIndex();
                ShowQuestionsFollower(currentQuestion);
                questionsText.setText(questionsToShowSaved[currentQuestion].getQuestionText());
                DesplegarRespuestas(currentQuestion);
                GetAnswersColors();
                CheatsButtonByQuestion();
            }
        });
        //#endregion

        cheatsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(Activity3.this, "Tramposo", Toast.LENGTH_SHORT).show();
                cheatsQuantity--;
                ShowCheatsQuantity();
                cheatRecorder = true;

                if (cheatsCounterByQuestion[currentQuestion] > 2) {
                    Trampa();
                    puntajeCheats[currentQuestion] = true;
                    cheatsCounterByQuestion[currentQuestion]--;
                } else {
                    Trampa();
                    ShowAnswerByCheats();
                }
                SnackTrampa();
                CheatsButtonByQuestion();
                GameChecker();
                GetAnswersColors();
            }
        });


        //# region  Click respuesta 01

        respuesta01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AnswerChecker(0)) {
                    colorsAnswers[currentQuestion][0] = COLOR_CORRECTO;
                    if (!puntajeCheats[currentQuestion])
                        Puntaje += difficult;

                } else {
                    colorsAnswers[currentQuestion][0] = COLOR_INCORRECTO;
                }
                RespuestasHabilitador(false);
                Respondido[currentQuestion] = true;
                GameChecker();
                GetAnswersColors();
            }
        });


        //# endregion

        //# region  Click respuesta 02
        respuesta02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AnswerChecker(1)) {
                    colorsAnswers[currentQuestion][1] = COLOR_CORRECTO;
                    if (!puntajeCheats[currentQuestion])
                        Puntaje += difficult;

                } else {
                    colorsAnswers[currentQuestion][1] = COLOR_INCORRECTO;
                }
                RespuestasHabilitador(false);
                Respondido[currentQuestion] = true;
                GameChecker();
                GetAnswersColors();
            }
        });


        //# endregion

        //# region  Click respuesta 03
        respuesta03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AnswerChecker(2)) {
                    colorsAnswers[currentQuestion][2] = COLOR_CORRECTO;
                    if (!puntajeCheats[currentQuestion])
                        Puntaje += difficult;

                } else {
                    colorsAnswers[currentQuestion][2] = COLOR_INCORRECTO;
                }
                RespuestasHabilitador(false);
                Respondido[currentQuestion] = true;
                GameChecker();
                GetAnswersColors();
            }
        });

        //# endregion

        //# region  Click respuesta 04
        respuesta04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AnswerChecker(3)) {
                    colorsAnswers[currentQuestion][3] = COLOR_CORRECTO;
                    if (!puntajeCheats[currentQuestion])
                        Puntaje += difficult;

                } else {
                    colorsAnswers[currentQuestion][3] = COLOR_INCORRECTO;
                }
                RespuestasHabilitador(false);
                Respondido[currentQuestion] = true;
                GameChecker();
                GetAnswersColors();
            }
        });

        //# endregion
    }


    public void onSaveInstanceState(Bundle estado) {
        estado.putInt("CURRENT_QUESTIONS",currentQuestion);
        estado.putInt("DIFICULTAD",difficult);
        estado.putSerializable("QUESTIONS_TO_SHOW_SAVED",questionsToShowSaved);
        estado.putInt("QUESTIONS_QUANTITY",questionsQuantity);
        estado.putIntArray("CHEATS_COUNTER_BY_QUESTION",cheatsCounterByQuestion);
        estado.putInt("CHEATS_QUANTITY",cheatsQuantity);
        estado.putInt("PUNTAJE",Puntaje);
        estado.putIntArray("TOPICs_TO_ASK",topicsToAsk);
        estado.putSerializable("COLOR_ANSWERS",colorsAnswers);
        estado.putBoolean("CHEATS_ENABLE",cheatsEnable);
        estado.putBoolean("CHEATS_RECORDER",cheatRecorder);
        estado.putBooleanArray("CHEATS_FOLLOWER_ENABLE",cheatsFollowerEnable);
        estado.putSerializable("HABILITADOR_DE_RESPUESTAS",habilitadorDeRespuestas);
        estado.putBooleanArray("PUNTAJE_CHEATS",puntajeCheats);
        estado.putBooleanArray("HABILITADOR_DE_CHEATS",habilitadorDeCheats);
        estado.putBooleanArray("RESPONDIDO",Respondido);
        estado.putSerializable("ANSWERS_TO_SHOW",answersToShow);
        estado.putSerializable("ANSWERS_TO_SHOW_SAVED",answersToShowSaved);
        super.onSaveInstanceState(estado);

    }


/*

//Leer los temas de los que se va a preguntar, vienen de un arreglo



//Falra que el botón de trampas se deshabilite cuando ya no sea necesario hacer trampas en la pregunta

    }

*/


    //#region métodos que funcionana
    public void GetAnswersColors() {

        switch (difficult) {
            case 2:
                respuesta01.setBackgroundColor(colorsAnswers[currentQuestion][0]);
                respuesta02.setBackgroundColor(colorsAnswers[currentQuestion][1]);

                respuesta01.setEnabled(habilitadorDeRespuestas[currentQuestion][0]);
                respuesta02.setEnabled(habilitadorDeRespuestas[currentQuestion][1]);

                break;
            case 3:
                respuesta01.setBackgroundColor(colorsAnswers[currentQuestion][0]);
                respuesta02.setBackgroundColor(colorsAnswers[currentQuestion][1]);
                respuesta03.setBackgroundColor(colorsAnswers[currentQuestion][2]);

                respuesta01.setEnabled(habilitadorDeRespuestas[currentQuestion][0]);
                respuesta02.setEnabled(habilitadorDeRespuestas[currentQuestion][1]);
                respuesta03.setEnabled(habilitadorDeRespuestas[currentQuestion][2]);


                break;
            case 4:
                respuesta01.setBackgroundColor(colorsAnswers[currentQuestion][0]);
                respuesta02.setBackgroundColor(colorsAnswers[currentQuestion][1]);
                respuesta03.setBackgroundColor(colorsAnswers[currentQuestion][2]);
                respuesta04.setBackgroundColor(colorsAnswers[currentQuestion][3]);

                respuesta01.setEnabled(habilitadorDeRespuestas[currentQuestion][0]);
                respuesta02.setEnabled(habilitadorDeRespuestas[currentQuestion][1]);
                respuesta03.setEnabled(habilitadorDeRespuestas[currentQuestion][2]);
                respuesta04.setEnabled(habilitadorDeRespuestas[currentQuestion][3]);

                break;


        }

    }

    public void DesplegarRespuestas(int current) {

        switch (difficult) {
            case 2:
                respuesta01.setText(answersToShowSaved[current][0].getAnswerText());
                respuesta02.setText(answersToShowSaved[current][1].getAnswerText());


                break;
            case 3:
                respuesta01.setText(answersToShowSaved[current][0].getAnswerText());
                respuesta02.setText(answersToShowSaved[current][1].getAnswerText());
                respuesta03.setText(answersToShowSaved[current][2].getAnswerText());

                break;
            case 4:
                respuesta01.setText(answersToShowSaved[current][0].getAnswerText());
                respuesta02.setText(answersToShowSaved[current][1].getAnswerText());
                respuesta03.setText(answersToShowSaved[current][2].getAnswerText());
                respuesta04.setText(answersToShowSaved[current][3].getAnswerText());
                break;
        }
    }

    public void NextQuestionIndex() {
        currentQuestion = (currentQuestion + 1) % questionsQuantity;
    }

    public void PrevQuestionIndex() {
        currentQuestion = (currentQuestion + questionsQuantity - 1) % questionsQuantity;
    }

    public void ShowQuestionsFollower(int c) {
        String Contador = Integer.toString(c + 1) + "/" + Integer.toString(questionsQuantity);
        questionsFollower.setText(Contador);
    }

    public void InicializacionBotones() {
        respuesta01.setEnabled(false);
        respuesta01.setVisibility(View.INVISIBLE);
        respuesta02.setEnabled(false);
        respuesta02.setVisibility(View.INVISIBLE);
        respuesta03.setEnabled(false);
        respuesta03.setVisibility(View.INVISIBLE);
        respuesta04.setEnabled(false);
        respuesta04.setVisibility(View.INVISIBLE);

        respuestas = new Button[questionsQuantity][4];
        for (int i = 0; i < questionsQuantity; i++) {
            respuestas[i][0] = respuesta01;
            respuestas[i][1] = respuesta02;
            respuestas[i][2] = respuesta03;
            respuestas[i][3] = respuesta04;
        }
    }

    public void HabilitacionBotones(int quantity) {
        for (int i = 0; i < quantity; i++) {
            respuestas[currentQuestion][i].setEnabled(true);
            respuestas[currentQuestion][i].setVisibility(View.VISIBLE);

        }
    }

    public boolean AnswerChecker(int i) {
        boolean a;
        if (respuestas[currentQuestion][i].getText() == answersToShow[currentQuestion][0].getAnswerText())
            a = true;
        else
            a = false;
        return a;
    }

    public void NotEnableButtons() {
        for (int h = 0; h < difficult; h++) {
            habilitadorDeRespuestas[currentQuestion][h] = false;
        }
    }

    public void RespuestasHabilitador(boolean habilitador) {
        if (!habilitador) {
            NotEnableButtons();
        }
    }

    public void CheckerCheatsButton(boolean checker) {
        if (checker) {
            cheatsButton.setEnabled(true);
            for (int t = 0; t < cheatsQuantity; t++) {
                habilitadorDeCheats[t] = true;
            }
            ShowCheatsQuantity();
        } else {
            cheatsButton.setEnabled(false);
            cheatsButton.setVisibility(View.INVISIBLE);
        }
    }

    public void ShowCheatsQuantity() {
        cheatsButton.setText(Integer.toString(cheatsQuantity));
    }

    public void ShowAnswerByCheats() {
        for (int o = 0; o < 4; o++) {
            if (respuestas[currentQuestion][o].isEnabled()) {
                colorsAnswers[currentQuestion][o] = COLOR_CORRECTO;
                NotEnableButtons();
                Respondido[currentQuestion] = true;
            }
        }
    }

    public void Trampa() {
        Random rand = new Random();
        boolean finish = true;
        while (finish) {
            int aleatorio = rand.nextInt(4);
            if (respuestas[currentQuestion][aleatorio].isEnabled()) {
                if (respuestas[currentQuestion][aleatorio].getText() != answersToShow[currentQuestion][0].getAnswerText()) {
                    habilitadorDeRespuestas[currentQuestion][aleatorio] = false;
                    colorsAnswers[currentQuestion][aleatorio] = COLOR_TRAMPA;
                    respuestas[currentQuestion][aleatorio].setEnabled(habilitadorDeRespuestas[currentQuestion][aleatorio]);
                    finish = false;
                }

            }
        }
        if (cheatsQuantity == 0) {
            for (int t = 0; t < questionsQuantity; t++) {
                habilitadorDeCheats[t] = false;
            }
        }
    }

    public void CheatsButtonByQuestion() {
        cheatsButton.setEnabled(habilitadorDeCheats[currentQuestion]);
    }

    //#endregion

    public void GameChecker() {
        int finish = 0;
        for (int d = 0; d < questionsQuantity; d++) {
            if (Respondido[d])
                finish++;
        }
        if (finish == questionsQuantity)
            CreacionDialogo();
    }

    public void CreacionDialogo() {
        final AlertDialog.Builder DialogoNickname = new AlertDialog.Builder(this);
        Nickname_Input = new EditText(this);
        DialogoNickname.setView(R.layout.dialogo_personalizado);

        DialogoNickname.setTitle("Juego terminado");
        DialogoNickname.setMessage("Ingrese su Nickname (Solo 3 carácteres)\n\n" +
                "Aceptar para guardar \n\nCancelar para descartar (se perderá la partida)");
        DialogoNickname.setCancelable(false);
        DialogoNickname.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                nickname = NickName.getText().toString();
                String msg = nickname + ": " + Integer.toString(Puntaje) + " puntos";
                EnviarInfo();
            }
        });
        DialogoNickname.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(Activity3.this, "Partida descartada", Toast.LENGTH_SHORT).show();
            }
        });
        Dialogo = DialogoNickname.create();
        Dialogo.show();

        Dialogo.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);


    }

    public void onBackPressed() {
        View Contenido = findViewById(R.id.content);
        Snackbar.make(Contenido, "Al salir se perderá tu partida", Snackbar.LENGTH_LONG).setAction("Aceptar", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity3.this, MainActivity.class);
                startActivity(intent);
            }
        }).show();
    }

    public void SnackTrampa() {
        View Contenido = findViewById(R.id.content);
        Snackbar.make(Contenido, "Haz hecho trampa", Snackbar.LENGTH_SHORT).show();
    }

    public void EnviarInfo(){
        Intent intent = new Intent(Activity3.this,Activity4.class);
        intent.putExtra("NICKNAME", nickname);
        intent.putExtra("PUNTAJE", Puntaje);
        intent.putExtra("CHECADOR_TRAMPAS",cheatsEnable);
        startActivity(intent);

    }

}



