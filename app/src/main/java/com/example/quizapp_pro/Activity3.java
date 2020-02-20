package com.example.quizapp_pro;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Activity3 extends AppCompatActivity {

    //TextViews: contador, trampas y pregunta
    private TextView questionsFollower;
    private TextView cheatsFollower;
    private TextView questionsText;
    private TextView cheatsButton;


    //Colores a usar
    private int COLOR_NORMAL = Color.rgb(55,134,197);
    private int COLOR_CORRECTO = Color.rgb(55,197,62);
    private int COLOR_INCORRECTO = Color.rgb(197,55,72);
    private int COLOR_TRAMPA = Color.rgb(46,55,58);

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


    private int difficult;
    private int currentQuestion = 0;
    private int questionsQuantity;
    private int questionsForTopic;
    private int residueQuestionsForTopic;
    private int cheatsQuantity;
    private int Puntaje = 0;
    private int[] topicsToAsk;
    private int[][] colorsAnswers;

    private boolean cheatsEnable;
    private boolean cheatRecorder = false;
    private boolean cheatsFollowerEnable[];
    private boolean[][] habilitadorDeRespuestas;
    private boolean[] habilitadorDeCheats;

    //Nombres de los Intents de donde se recaudará información del OptionsActivity
    public static final String DIFFICULT_INTENT = "DIFICULTAD_PUNTOS";
    public static final String QUANTITY_QUESTIONS_INTENT = "NO_PREGUNTAS";
    public static final String CHEATS_ENABLE_INTENT = "ENABLE_PISTAS";
    public static final String CHEATS_QUANTITY_INTENT = "NO_PISTAS";
    public static final String TOPICS_ID_INTENT = "CUALES_TOPICS";

    private List<Questions> questionsToShow;
    private Questions[] questionsToShowSaved;

    private Answers[][] answersToShowSaved;
    private Answers[][] answersToShow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        //#region Inicialización de variables

        questionsFollower = findViewById(R.id.questionsQuantity);
        cheatsFollower = findViewById(R.id.cheatsQuantity);
        questionsText = findViewById(R.id.questionText);
        respuesta01 = findViewById(R.id.respuesta01);
        respuesta02 = findViewById(R.id.respuesta02);
        respuesta03 = findViewById(R.id.respuesta03);
        respuesta04 = findViewById(R.id.respuesta04);
        prevButton = findViewById(R.id.prevButton);
        nextButton = findViewById(R.id.nextButton);
        cheatsButton = findViewById(R.id.cheatsQuantity);

        Intent intent = getIntent();
        difficult = intent.getIntExtra(DIFFICULT_INTENT, 4); //Correcto
        questionsQuantity = intent.getIntExtra(QUANTITY_QUESTIONS_INTENT, 60); //Me da valores de 0 o 1
        cheatsEnable = intent.getBooleanExtra(CHEATS_ENABLE_INTENT, false); //Correcto
        cheatsQuantity = intent.getIntExtra(CHEATS_QUANTITY_INTENT, 0); //Correcto
        //topicsToAsk = intent.getIntArrayExtra(TOPICS_ID_INTENT);
        topicsToAsk = new int[3];
        topicsToAsk[0] = 0;
        topicsToAsk[1] = 1;
        topicsToAsk[2] = 2;

        colorsAnswers = new int[questionsQuantity][difficult];

        for (int y = 0; y < questionsQuantity; y++) {
            for (int x = 0; x < difficult; x++) {
                colorsAnswers[y][x] = Color.rgb(0, 0, 100);
            }
        }

        habilitadorDeRespuestas = new boolean[questionsQuantity][difficult];

        for (int y = 0; y < questionsQuantity; y++) {
            for (int x = 0; x < difficult; x++) {
                habilitadorDeRespuestas[y][x] = true;
            }
        }

        MainActivityViewModel model = new MainActivityViewModel();

        //#endregion

        //#region Llenado de información Random
        //Llenado de las preguntas por tópico

        questionsToShow = new ArrayList<>();
        questionsToShow.addAll(model.questionsByTopicRandom(questionsQuantity, topicsToAsk));
        questionsToShowSaved = new Questions[questionsQuantity];
        questionsToShow.toArray(questionsToShowSaved);


        answersToShow = new Answers[questionsQuantity][difficult];

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
        answersToShowSaved = new Answers[questionsQuantity][difficult];

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

        //#region Actividades iniciales de la apliación
        ShowQuestionsFollower(currentQuestion);
        InicializacionBotones();
        HabilitacionBotones(difficult);
        CheatsDeshabilitador(cheatsEnable, cheatsQuantity);
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
            }
        });
        //#endregion

        cheatsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cheatsQuantity--;
                CheatsDeshabilitador(cheatsEnable, cheatsQuantity);
                cheatRecorder = true;
                if (difficult > 2) {
                    Trampa();
                } else {

                }
                GetAnswersColors();
            }
        });


        //# region  Click respuesta 01

        respuesta01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AnswerChecker(0)) {
                    colorsAnswers[currentQuestion][0] = Color.rgb(0, 100, 0);
                    Puntaje += difficult;

                } else {
                    colorsAnswers[currentQuestion][0] = Color.rgb(100, 0, 0);
                }
                RespuestasHabilitador(false);
                GetAnswersColors();
            }
        });


        //# endregion

        //# region  Click respuesta 04
        respuesta02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AnswerChecker(1)) {
                    colorsAnswers[currentQuestion][1] = Color.rgb(0, 100, 0);
                    Puntaje += difficult;

                } else {
                    colorsAnswers[currentQuestion][1] = Color.rgb(100, 0, 0);
                }
                RespuestasHabilitador(false);
                GetAnswersColors();
            }
        });


        //# endregion

        //# region  Click respuesta 04
        respuesta03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AnswerChecker(2)) {
                    colorsAnswers[currentQuestion][2] = Color.rgb(0, 100, 0);
                    Puntaje += difficult;

                } else {
                    colorsAnswers[currentQuestion][2] = Color.rgb(100, 0, 0);
                }
                RespuestasHabilitador(false);
                GetAnswersColors();
            }
        });

        //# endregion

        //# region  Click respuesta 04
        respuesta04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AnswerChecker(3)) {
                    colorsAnswers[currentQuestion][3] = Color.rgb(0, 100, 0);
                    Puntaje += difficult;

                } else {
                    colorsAnswers[currentQuestion][3] = Color.rgb(100, 0, 0);
                }
                RespuestasHabilitador(false);
                GetAnswersColors();
            }
        });

        //# endregion
    }


/*

//Leer los temas de los que se va a preguntar, vienen de un arreglo

// Revisar que las preguntas estén siendo seleccionadas al azar realmente
//Las trampas no funcionan bien

//Game checker para terminar el juego

        //Mensaje al salir sin terminar la partida: SnackBar

        //Acción al presionar back();


        //Guardado de la partida

        //Enviar información al siguiente activity: Puntaje, si hizo trampa y todas las respuestas

//Cambiar los colores utilizados en las respuestas por las variables de los colores declarads
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

    public void ShowCheatsQuantity(int quantity) {
        cheatsButton.setText(Integer.toString(quantity));
        if (cheatsQuantity == 0)
            cheatsButton.setEnabled(false);
        else{
            for(int t=0;t<cheatsQuantity;t++){
                habilitadorDeCheats[t]=true;
            }
        }
    }

    //#endregion

    //#region Métodos por probar
    public void BotonTrampa(int indice) {
        respuestas[currentQuestion][indice].setEnabled(false);
    }


    public void CheatsDeshabilitador(boolean cheats, int quantity) {
        if (!cheats) {
            cheatsButton.setEnabled(false);
        } else
            ShowCheatsQuantity(quantity);
    }






    public void BotonesRespuestaDeshabilitacion(int quantity, int aux) {
        for (int i = 0; i < quantity; i++) {
            if (i != aux) {
                respuestas[currentQuestion][i].setEnabled(true);
            }
        }
    }


    public void Trampa() {
        Random rand = new Random();
        boolean finish = true;
        while (finish) {
            int aleatorio = rand.nextInt(respuestas.length) - 1;
            if (respuestas[currentQuestion][aleatorio].isEnabled()) {
                if (respuestas[currentQuestion][aleatorio].getText() != answersToShow[currentQuestion][0].getAnswerText()) {
                    habilitadorDeRespuestas[currentQuestion][aleatorio] = false;
                    colorsAnswers[currentQuestion][aleatorio] = COLOR_TRAMPA;
                    finish = false;
                }

            }
        }
    }



    //#endregion

}



