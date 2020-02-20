package com.example.quizapp_pro;

import android.content.Intent;
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
    private Button cheatsButton;
    private Button[][] respuestas;


    private int difficult;
    private int currentQuestion = 0;
    private int questionsQuantity;
    private int questionsForTopic;
    private int residueQuestionsForTopic;
    private int cheatsQuantity;
    private int[] topicsToAsk;

    private boolean cheatsEnable;
    private boolean cheatRecorder = false;
    private boolean cheatsFollowerEnable[];

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


        //Inicialización de las preguntas, recaudar la información de otros activities
        MainActivityViewModel model = new MainActivityViewModel();


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

        //Desplegar preguntas con sus debidas respuestas de manera aleatoria Tienes las que se van a mostrar, hay que cambiar el orden

        ShowQuestionsFollower(currentQuestion);
        InicializacionBotones();
        HabilitacionBotones(difficult);
        CheatsDeshabilitador(cheatsEnable, cheatsQuantity);
        questionsText.setText(questionsToShowSaved[currentQuestion].getQuestionText());

        DesplegarRespuestas(currentQuestion);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NextQuestionIndex();
                ShowQuestionsFollower(currentQuestion);
                questionsText.setText(questionsToShowSaved[currentQuestion].getQuestionText());
                DesplegarRespuestas(currentQuestion);
            }
        });

        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PrevQuestionIndex();
                ShowQuestionsFollower(currentQuestion);
                questionsText.setText(questionsToShowSaved[currentQuestion].getQuestionText());
                DesplegarRespuestas(currentQuestion);
            }
        });
/*
        cheatsFollower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cheatsQuantity--;
                CheatsDeshabilitador(cheatsEnable, cheatsQuantity);
                //Falta guardar las habilitaciones del TextView de trampas
                cheatRecorder = true;
                //Hay que guardar los botones deshabilitados
                if (difficult > 2) {
                    Trampa();
                } else {
                    //Poner el método para responder
                }


            }
        });

        //Estoy deshabilitando todos los botones, para que ya no puedas volver a dar click
        //Pero la respuesta se guarda

        respuesta01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AnswerChecker(0)) {
                    respuestas[currentQuestion][0].setTextColor(0x0000ff);
                    BotonesRespuestaDeshabilitacion(difficult, 0);
                    NotEnableButtons();

                } else {
                    respuestas[currentQuestion][0].setTextColor(0xff0000);
                    BotonesRespuestaDeshabilitacion(difficult, 0);
                    NotEnableButtons();
                }
            }
        });

        respuesta02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AnswerChecker(0)) {
                    respuestas[currentQuestion][1].setTextColor(0x0000ff);
                    BotonesRespuestaDeshabilitacion(difficult, 0);
                    NotEnableButtons();
                } else {
                    respuestas[currentQuestion][1].setTextColor(0xff0000);
                    BotonesRespuestaDeshabilitacion(difficult, 0);
                    NotEnableButtons();
                }
            }
        });

        respuesta03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AnswerChecker(0)) {
                    respuestas[currentQuestion][2].setTextColor(0x0000ff);
                    BotonesRespuestaDeshabilitacion(difficult, 0);
                    NotEnableButtons();
                } else {
                    respuestas[currentQuestion][2].setTextColor(0xff0000);
                    BotonesRespuestaDeshabilitacion(difficult, 0);
                    NotEnableButtons();
                }
            }
        });

        respuesta04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AnswerChecker(0)) {
                    respuestas[currentQuestion][3].setTextColor(0x0000ff);
                    BotonesRespuestaDeshabilitacion(difficult, 0);
                    NotEnableButtons();
                } else {
                    respuestas[currentQuestion][3].setTextColor(0xff0000);
                    BotonesRespuestaDeshabilitacion(difficult, 0);
                    NotEnableButtons();
                }
            }
        });

*/
    }
/*
        //Mensaje al salir sin terminar la partida: SnackBar

        //Acción al presionar back();

        //Cambiar color de la pregunta según se haya contestato: Bien, Mal, Bien con trampa

        //Guardado de la partida

        //Enviar información al siguiente activity: Puntaje, si hizo trampa y todas las respuestas


    }
*/

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

/*
    public void BotonTrampa(int indice) {
        respuestas[currentQuestion][indice].setEnabled(false);
    }
*/

    public void CheatsDeshabilitador(boolean cheats, int quantity) {
        if (!cheats) {
            cheatsButton.setEnabled(false);
            cheatsButton.setVisibility(View.INVISIBLE);
        } else
            ShowCheatsQuantity(quantity);
    }

    public void ShowQuestionsFollower(int c) {
        String Contador = Integer.toString(c + 1) + "/" + Integer.toString(questionsQuantity);
        questionsFollower.setText(Contador);
    }

    public void ShowCheatsQuantity(int quantity) {
        cheatsButton.setText(Integer.toString(quantity));
        if (cheatsQuantity == 0)
            cheatsButton.setEnabled(false);
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

/*
    public void BotonesRespuestaDeshabilitacion(int quantity, int aux) {
        for (int i = 0; i < quantity; i++) {
            if (i != aux) {
                respuestas[currentQuestion][i].setEnabled(true);
            }
        }
    }
*/
/*
    public void Trampa() {
        Random rand = new Random();
        boolean finish = true;
        while (finish) {
            int aleatorio = rand.nextInt(respuestas.length) - 1;
            if (respuestas[currentQuestion][aleatorio].isEnabled()) {
                if (respuestas[currentQuestion][aleatorio].getText() != answersToShow[currentQuestion][0].getAnswerText()) {
                    respuestas[currentQuestion][aleatorio].setEnabled(false);
                    finish = false;
                }

            }
        }
    }
*/
/*
    public boolean AnswerChecker(int i) {
        boolean a;
        if (respuestas[currentQuestion][i].getText() == answersToShow[currentQuestion][0].getAnswerText())
            a = true;
        else
            a = false;
        return a;
    }
*/
/*
    public void NotEnableButtons() {
        for (int h = 0; h <= 3; h++) {
            if (respuestas[currentQuestion][h].isEnabled()) ;
        }
    }
*/
}



