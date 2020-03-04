package com.example.quizapp_pro;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class ReviewActivity extends AppCompatActivity {

    private String nickname = "CALISTO"; //Al final se guarda y se pssa
    private int difficult; //guardar
    private int currentQuestion; //guardar
    private int questionsQuantity; //guardar
    private int[] cheatsCounterByQuestion; //guardar
    private int questionsForTopic;
    private int residueQuestionsForTopic;
    private int cheatsQuantity; //guardar
    private int Puntaje; //guardar
    private int[] topicsToAsk; //guardar
    private int[][] colorsAnswers; //guardar
    private int a = 0;
    private Bundle extadoAux;

    private String[] nicknames;
    private int[] puntajes;
    private boolean[] gallinas;

    private boolean cheatsEnable; //guardar
    private boolean cheatRecorder; //guardar
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

    private Bundle estado;


    //TextViews: contador, trampas y pregunta
    private TextView questionsFollower;
    private TextView cheatsFollower;
    private TextView questionsText;
    private TextView cheatsButton;


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

    private MediaPlayer playercorrect;
    private MediaPlayer playercheat;
    private MediaPlayer playerincorrect;

    private ImageView TopicPhoto;

    //Nombres de los Intents de donde se recaudará información del OptionsActivity
    public static final String DIFFICULT_INTENT = "DIFICULTAD_PUNTOS";
    public static final String QUANTITY_QUESTIONS_INTENT = "NO_PREGUNTAS";
    public static final String CHEATS_ENABLE_INTENT = "ENABLE_PISTAS";
    public static final String CHEATS_QUANTITY_INTENT = "NO_PISTAS";
    public static final String TOPICS_ID_INTENT = "CUALES_TOPICS";

    final int START_POINTS_FOR_RESULT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        playercorrect = MediaPlayer.create(ReviewActivity.this, R.raw.fxacierto);
        playercheat = MediaPlayer.create(ReviewActivity.this, R.raw.fxgallina);
        playerincorrect = MediaPlayer.create(ReviewActivity.this, R.raw.fxincorrecto);

        Intent intent = getIntent();
        extadoAux = new Bundle();
        //estado = intent.getBundleExtra("PARTIDA_REGRESO");

        questionsFollower = findViewById(R.id.questionsQuantity);
        questionsText = findViewById(R.id.questionText);
        respuesta01 = findViewById(R.id.respuesta01);
        respuesta02 = findViewById(R.id.respuesta02);
        respuesta03 = findViewById(R.id.respuesta03);
        respuesta04 = findViewById(R.id.respuesta04);
        prevButton = findViewById(R.id.prevButton);
        nextButton = findViewById(R.id.nextButton);
        cheatsButton = findViewById(R.id.cheatsQuantity);
        TopicPhoto = findViewById(R.id.ImageTopic);

        MainActivityViewModel model = new MainActivityViewModel();

        if (estado != null) {
            savedInstanceState = estado;
            difficult = savedInstanceState.getInt("DIFICULTAD");
            questionsQuantity = savedInstanceState.getInt("QUESTIONS_QUANTITY");
            cheatsEnable = savedInstanceState.getBoolean("CHEATS_ENABLE");
            cheatsQuantity = savedInstanceState.getInt("CHEATS_QUANTITY");
            topicsToAsk = savedInstanceState.getIntArray("TOPICs_TO_ASK");

            nicknames = getIntent().getStringArrayExtra("PLAYER_NICKNAME");
            puntajes = getIntent().getIntArrayExtra("PLAYER_POINTS");
            gallinas = getIntent().getBooleanArrayExtra("PLAYER_CHEATED");


        } else {
            difficult = intent.getIntExtra(DIFFICULT_INTENT, 4); //Correcto
            questionsQuantity = intent.getIntExtra(QUANTITY_QUESTIONS_INTENT, 60); //Me da valores de 0 o 1
            cheatsEnable = intent.getBooleanExtra(CHEATS_ENABLE_INTENT, false); //Correcto
            cheatsQuantity = intent.getIntExtra(CHEATS_QUANTITY_INTENT, 0); //Correcto
            topicsToAsk = intent.getIntArrayExtra(TOPICS_ID_INTENT);

            nicknames = getIntent().getStringArrayExtra("PLAYER_NICKNAME");
            puntajes = getIntent().getIntArrayExtra("PLAYER_POINTS");
            gallinas = getIntent().getBooleanArrayExtra("PLAYER_CHEATED");

        }

        habilitadorDeCheats = new boolean[questionsQuantity];
        cheatsCounterByQuestion = new int[questionsQuantity];
        colorsAnswers = new int[questionsQuantity][difficult];
        Respondido = new boolean[questionsQuantity];
        habilitadorDeRespuestas = new boolean[questionsQuantity][difficult];
        puntajeCheats = new boolean[questionsQuantity];

        questionsToShow = new ArrayList<>();
        questionsToShowSaved = new Questions[questionsQuantity];
        answersToShow = new Answers[questionsQuantity][difficult];
        answersToShowSaved = new Answers[questionsQuantity][difficult];


        if (savedInstanceState != null) {
            currentQuestion = savedInstanceState.getInt("CURRENT_QUESTIONS");
            questionsToShowSaved = (Questions[]) savedInstanceState.getSerializable("QUESTIONS_TO_SHOW_SAVED");
            cheatsCounterByQuestion = savedInstanceState.getIntArray("CHEATS_COUNTER_BY_QUESTION");
            Puntaje = savedInstanceState.getInt("PUNTAJE");
            colorsAnswers = (int[][]) savedInstanceState.getSerializable("COLOR_ANSWERS");
            cheatRecorder = savedInstanceState.getBoolean("CHEATS_RECORDER");
            cheatsFollowerEnable = savedInstanceState.getBooleanArray("CHEATS_FOLLOWER_ENABLE");
            habilitadorDeRespuestas = (boolean[][]) savedInstanceState.getSerializable("HABILITADOR_DE_RESPUESTAS");
            puntajeCheats = savedInstanceState.getBooleanArray("PUNTAJE_CHEATS");
            habilitadorDeCheats = savedInstanceState.getBooleanArray("HABILITADOR_DE_CHEATS");
            Respondido = savedInstanceState.getBooleanArray("RESPONDIDO");
            answersToShow = (Answers[][]) savedInstanceState.getSerializable("ANSWERS_TO_SHOW");
            answersToShowSaved = (Answers[][]) savedInstanceState.getSerializable("ANSWERS_TO_SHOW_SAVED");


        } else {

            currentQuestion = 0;
            cheatRecorder = false;

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

        switch (questionsToShowSaved[currentQuestion].getTopicId()) {
            case 0:
                TopicPhoto.setBackgroundResource(R.drawable.arteicono);
                break;
            case 1:
                TopicPhoto.setBackgroundResource(R.drawable.geografiaicono);
                break;
            case 2:
                TopicPhoto.setBackgroundResource(R.drawable.frasesicono);
                break;
            case 3:
                TopicPhoto.setBackgroundResource(R.drawable.juegosicono);
                break;
            case 4:
                TopicPhoto.setBackgroundResource(R.drawable.historiaicono);
                break;
            case 5:
                TopicPhoto.setBackgroundResource(R.drawable.mainphoto);
                break;
            default:
                TopicPhoto.setBackgroundResource(R.drawable.mainphoto);

        }

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
                switch (questionsToShowSaved[currentQuestion].getTopicId()) {
                    case 0:
                        TopicPhoto.setBackgroundResource(R.drawable.arteicono);
                        break;
                    case 1:
                        TopicPhoto.setBackgroundResource(R.drawable.geografiaicono);
                        break;
                    case 2:
                        TopicPhoto.setBackgroundResource(R.drawable.frasesicono);
                        break;
                    case 3:
                        TopicPhoto.setBackgroundResource(R.drawable.juegosicono);
                        break;
                    case 4:
                        TopicPhoto.setBackgroundResource(R.drawable.historiaicono);
                        break;
                    case 5:
                        TopicPhoto.setBackgroundResource(R.drawable.mainphoto);
                        break;
                    default:
                        TopicPhoto.setBackgroundResource(R.drawable.mainphoto);

                }
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
                switch (questionsToShowSaved[currentQuestion].getTopicId()) {
                    case 0:
                        TopicPhoto.setBackgroundResource(R.drawable.arteicono);
                        break;
                    case 1:
                        TopicPhoto.setBackgroundResource(R.drawable.geografiaicono);
                        break;
                    case 2:
                        TopicPhoto.setBackgroundResource(R.drawable.frasesicono);
                        break;
                    case 3:
                        TopicPhoto.setBackgroundResource(R.drawable.juegosicono);
                        break;
                    case 4:
                        TopicPhoto.setBackgroundResource(R.drawable.historiaicono);
                        break;
                    case 5:
                        TopicPhoto.setBackgroundResource(R.drawable.mainphoto);
                        break;
                    default:
                        TopicPhoto.setBackgroundResource(R.drawable.mainphoto);

                }
            }
        });
        //#endregion

        cheatsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cheatsQuantity--;
                ShowCheatsQuantity();
                cheatRecorder = true;

                if (cheatsCounterByQuestion[currentQuestion] > 2) {
                    Trampa();
                    playercheat.start();
                    puntajeCheats[currentQuestion] = true;
                    cheatsCounterByQuestion[currentQuestion]--;
                } else {
                    Trampa();
                    playercorrect.start();
                    ShowAnswerByCheats();
                    habilitadorDeCheats[currentQuestion] = false;
                }
                CheatsButtonByQuestion();
                SnackTrampa();
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
                    playercorrect.start();
                    if (!puntajeCheats[currentQuestion])
                        Puntaje += difficult;

                } else {
                    colorsAnswers[currentQuestion][0] = COLOR_INCORRECTO;
                    playerincorrect.start();
                }
                RespuestasHabilitador(false);
                Respondido[currentQuestion] = true;
                habilitadorDeCheats[currentQuestion] = false;
                CheatsButtonByQuestion();
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
                    playercorrect.start();
                    if (!puntajeCheats[currentQuestion])
                        Puntaje += difficult;

                } else {
                    colorsAnswers[currentQuestion][1] = COLOR_INCORRECTO;
                    playerincorrect.start();
                }
                RespuestasHabilitador(false);
                Respondido[currentQuestion] = true;
                habilitadorDeCheats[currentQuestion] = false;
                CheatsButtonByQuestion();
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
                    playercorrect.start();
                    if (!puntajeCheats[currentQuestion])
                        Puntaje += difficult;

                } else {
                    colorsAnswers[currentQuestion][2] = COLOR_INCORRECTO;
                    playerincorrect.start();
                }
                RespuestasHabilitador(false);
                Respondido[currentQuestion] = true;
                habilitadorDeCheats[currentQuestion] = false;
                CheatsButtonByQuestion();
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
                    playercorrect.start();
                    if (!puntajeCheats[currentQuestion])
                        Puntaje += difficult;

                } else {
                    colorsAnswers[currentQuestion][3] = COLOR_INCORRECTO;
                    playerincorrect.start();
                }
                RespuestasHabilitador(false);
                Respondido[currentQuestion] = true;
                habilitadorDeCheats[currentQuestion] = false;
                CheatsButtonByQuestion();
                GameChecker();
                GetAnswersColors();
            }
        });

        //# endregion
    }


    public void onSaveInstanceState(Bundle estado) {
        estado.putInt("CURRENT_QUESTIONS", currentQuestion);
        estado.putInt("DIFICULTAD", difficult);
        estado.putSerializable("QUESTIONS_TO_SHOW_SAVED", questionsToShowSaved);
        estado.putInt("QUESTIONS_QUANTITY", questionsQuantity);
        estado.putIntArray("CHEATS_COUNTER_BY_QUESTION", cheatsCounterByQuestion);
        estado.putInt("CHEATS_QUANTITY", cheatsQuantity);
        estado.putInt("PUNTAJE", Puntaje);
        estado.putIntArray("TOPICs_TO_ASK", topicsToAsk);
        estado.putSerializable("COLOR_ANSWERS", colorsAnswers);
        estado.putBoolean("CHEATS_ENABLE", cheatsEnable);
        estado.putBoolean("CHEATS_RECORDER", cheatRecorder);
        estado.putBooleanArray("CHEATS_FOLLOWER_ENABLE", cheatsFollowerEnable);
        estado.putSerializable("HABILITADOR_DE_RESPUESTAS", habilitadorDeRespuestas);
        estado.putBooleanArray("PUNTAJE_CHEATS", puntajeCheats);
        estado.putBooleanArray("HABILITADOR_DE_CHEATS", habilitadorDeCheats);
        estado.putBooleanArray("RESPONDIDO", Respondido);
        estado.putSerializable("ANSWERS_TO_SHOW", answersToShow);
        estado.putSerializable("ANSWERS_TO_SHOW_SAVED", answersToShowSaved);
        estado.putStringArray("PLAYER_NICKNAME", nicknames);
        estado.putIntArray("PLAYER_POINTS", puntajes);
        estado.putBooleanArray("PLAYER_CHEATED", gallinas);
        super.onSaveInstanceState(estado);

    }


/*

//Leer los temas de los que se va a preguntar, vienen de un arreglo


//El dialogo no funciona bien: No guarda el texto y tampoco se deshabilita cuando quiero el botón aceptar
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
        if (finish == questionsQuantity) {
            CreacionDialogo();
        }
    }

    public void CreacionDialogo() {

        final AlertDialog.Builder DialogoNickname = new AlertDialog.Builder(ReviewActivity.this);
        final EditText input = new EditText(ReviewActivity.this);
        LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(layout);
        input.setMaxEms(3);
        DialogoNickname.setView(input);
        DialogoNickname.setTitle("Juego terminado");
        DialogoNickname.setMessage("Ingrese su Nickname (Solo 3 carácteres)\n\n" +
                "Aceptar para guardar \n\nCancelar para descartar (se perderá la partida)");
        DialogoNickname.setCancelable(false);
        DialogoNickname.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(DialogInterface dialog, int which) {
                nickname = input.getText().toString();
                EnviarInfo();
            }
        });
        DialogoNickname.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(ReviewActivity.this, "Partida descartada", Toast.LENGTH_SHORT).show();
            }
        });
        Dialogo = DialogoNickname.create();
        Dialogo.show();

        Dialogo.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);

        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (input.getText().toString().length() == 3) {
                    Dialogo.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
                } else {
                    Dialogo.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                }
            }
        });


    }

    public void onBackPressed() {
        View Contenido = findViewById(R.id.content);
        Snackbar.make(Contenido, "Al salir se perderá tu partida", Snackbar.LENGTH_LONG).setAction("Aceptar", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReviewActivity.this, MainActivity.class);
                intent.putExtra("CUALES_TOPICS", topicsToAsk);
                intent.putExtra("NO_PREGUNTAS", questionsQuantity);
                intent.putExtra("DIFICULTAD_PUNTOS", difficult);
                intent.putExtra("ENABLE_PISTAS", cheatsEnable);
                intent.putExtra("NO_PISTAS", cheatsQuantity);
                intent.putExtra("PLAYER_NICKNAME", nickname);
                intent.putExtra("PLAYER_POINTS", puntajes);
                intent.putExtra("PLAYER_CHEATED", gallinas);
                startActivity(intent);
                finish();
            }
        }).show();
    }

    public void SnackTrampa() {
        View Contenido = findViewById(R.id.content);
        Snackbar.make(Contenido, "Haz hecho trampa", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == START_POINTS_FOR_RESULT) {
            if (resultCode == Activity4.RESULT_OK) {
                Toast.makeText(ReviewActivity.this, "REVISIÓN DE RESULTADOS", Toast.LENGTH_LONG).show();
            }
            if (resultCode == Activity4.RESULT_CANCELED) {
                Toast.makeText(ReviewActivity.this, "MENSAJE SECRETO", Toast.LENGTH_LONG).show();
            }
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void EnviarInfo() {
        Intent intent03 = new Intent(ReviewActivity.this, Activity4.class);
        //intent03.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        intent03.putExtra("NICKNAME", nickname);
        intent03.putExtra("PUNTAJE", Puntaje);
        intent03.putExtra("CHECADOR_TRAMPAS", cheatsEnable);

        intent03.putExtra("PLAYER_NICKNAME", nicknames);
        intent03.putExtra("PLAYER_POINTS", puntajes);
        intent03.putExtra("PLAYER_CHEATED", gallinas);

        intent03.putExtra(DIFFICULT_INTENT, difficult);
        intent03.putExtra(QUANTITY_QUESTIONS_INTENT, questionsQuantity);
        intent03.putExtra(CHEATS_ENABLE_INTENT, cheatsEnable);
        intent03.putExtra(CHEATS_QUANTITY_INTENT, cheatsQuantity);
        intent03.putExtra(TOPICS_ID_INTENT, topicsToAsk);




/*estado = new Bundle();
            estado.putInt("CURRENT_QUESTIONS", currentQuestion);
            estado.putInt("DIFICULTAD", difficult);
            estado.putSerializable("QUESTIONS_TO_SHOW_SAVED", questionsToShowSaved);
            estado.putInt("QUESTIONS_QUANTITY", questionsQuantity);
            estado.putIntArray("CHEATS_COUNTER_BY_QUESTION", cheatsCounterByQuestion);
            estado.putInt("CHEATS_QUANTITY", cheatsQuantity);
            estado.putInt("PUNTAJE", Puntaje);
            estado.putIntArray("TOPICs_TO_ASK", topicsToAsk);
            estado.putSerializable("COLOR_ANSWERS", colorsAnswers);
            estado.putBoolean("CHEATS_ENABLE", cheatsEnable);
            estado.putBoolean("CHEATS_RECORDER", cheatRecorder);
            estado.putBooleanArray("CHEATS_FOLLOWER_ENABLE", cheatsFollowerEnable);
            estado.putSerializable("HABILITADOR_DE_RESPUESTAS", habilitadorDeRespuestas);
            estado.putBooleanArray("PUNTAJE_CHEATS", puntajeCheats);
            estado.putBooleanArray("HABILITADOR_DE_CHEATS", habilitadorDeCheats);
            estado.putBooleanArray("RESPONDIDO", Respondido);
            estado.putSerializable("ANSWERS_TO_SHOW", answersToShow);
            estado.putSerializable("ANSWERS_TO_SHOW_SAVED", answersToShowSaved);
            intent03.putExtra("ESTADO",estado);
            Toast.makeText(this, "Estado vacío", Toast.LENGTH_SHORT).show();

*/
        /*
        intent03.putExtra("DIFICULTAD", difficult);
        intent03.putExtra("CURRENT_QUESTIONS", currentQuestion);
        intent03.putExtra("QUESTIONS_TO_SHOW_SAVED", questionsToShowSaved);
        intent03.putExtra("QUESTIONS_QUANTITY", questionsQuantity);
        intent03.putExtra("CHEATS_COUNTER_BY_QUESTION", cheatsCounterByQuestion);
        intent03.putExtra("CHEATS_QUANTITY", cheatsQuantity);
        intent03.putExtra("PUNTAJE", Puntaje);
        intent03.putExtra("TOPICs_TO_ASK", topicsToAsk);
        intent03.putExtra("COLOR_ANSWERS", colorsAnswers);
        intent03.putExtra("CHEATS_ENABLE", cheatsEnable);
        intent03.putExtra("CHEATS_RECORDER", cheatRecorder);
        intent03.putExtra("CHEATS_FOLLOWER_ENABLE", cheatsFollowerEnable);
        intent03.putExtra("HABILITADOR_DE_RESPUESTAS", habilitadorDeRespuestas);
        intent03.putExtra("PUNTAJE_CHEATS", puntajeCheats);
        intent03.putExtra("HABILITADOR_DE_CHEATS", habilitadorDeCheats);
        intent03.putExtra("RESPONDIDO", Respondido);
        intent03.putExtra("ANSWERS_TO_SHOW", answersToShow);
        intent03.putExtra("ANSWERS_TO_SHOW_SAVED", answersToShowSaved);*/

        Toast.makeText(ReviewActivity.this, "Se pasó la informacion", Toast.LENGTH_SHORT).show();
        startActivity(intent03);
        finish();

    }

}