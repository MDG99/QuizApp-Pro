package com.example.quizapp_pro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

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
    private Button[] respuestas;


    private int difficult;
    private int currentQuestion;
    private int questionsQuantity;
    private int questionsForTopic;
    int questionCurrent = 0;
    private int residueQuestionsForTopic;
    private int cheatsQuantity;
    private int[] topicsToAsk;

    private boolean cheatsEnable;
    private boolean cheatRecorder = false;

    //Nombres de los Intents de donde se recaudará información del OptionsActivity
    public static final String DIFFICULT_INTENT = "DIFICULTAD_PUNTOS";
    public static final String QUANTITY_QUESTIONS_INTENT = "NO_PREGUNTAS";
    public static final String CHEATS_ENABLE_INTENT = "ENABLE_PISTAS";
    public static final String CHEATS_QUANTITY_INTENT = "NO_PISTAS";
    public static final String TOPICS_ID_INTENT = "CUALES_TOPICS";

    private List<Questions> questionsToShow;
    private Questions[] questionsToShowSaved;

    private Answers[][] answersToShowSaved;


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

        Intent intent = getIntent();
        difficult = intent.getIntExtra(DIFFICULT_INTENT, 4);
        questionsQuantity = intent.getIntExtra(QUANTITY_QUESTIONS_INTENT, 60);
        cheatsEnable = intent.getBooleanExtra(CHEATS_ENABLE_INTENT, false);
        cheatsQuantity = intent.getIntExtra(CHEATS_QUANTITY_INTENT, 0);
        topicsToAsk = intent.getIntArrayExtra(TOPICS_ID_INTENT);


        //Inicialización de las preguntas, recaudar la información de otros activities
        MainActivityViewModel model = new MainActivityViewModel();


        //ANSWERSTOSHOWSAVED NO ESTÁ EN RANDOM, ESTO ES BUENO, PUES PERMITE LA VALIDACIÓN DE LA RESPUESTA

        //#region Llenado de información Random
        //Llenado de las preguntas por tópico
        questionsToShow.addAll(model.questionsByTopicRandom(questionsQuantity, topicsToAsk));
        Questions[] questionsToShowSaved = new Questions[questionsToShow.toArray().length];
        questionsToShow.toArray(questionsToShowSaved);

        Answers[][] answersToShow = new Answers[questionsQuantity][difficult];

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
        final Answers[][] answersToShowSaved = new Answers[questionsQuantity][difficult];

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

        ShowQuestionsFollower(questionCurrent);
        InicializacionBotones();
        HabilitacionBotones(difficult);
        CheatsDeshabilitador(cheatsEnable, cheatsQuantity);
        DesplegarPregunta_Respuestas(questionCurrent);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionCurrent = NextQuestionIndex(questionCurrent);
                ShowQuestionsFollower(questionCurrent);
                DesplegarPregunta_Respuestas(questionCurrent);
            }
        });

        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionCurrent = PrevQuestionIndex(questionCurrent);
                ShowQuestionsFollower(questionCurrent);
                DesplegarPregunta_Respuestas(questionCurrent);
            }
        });

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

        respuesta01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(AnswerChecker(0)){
                    respuesta01.setTextColor("blue"); //Ver formato de colores
                    BotonesRespuestaDeshabilitacion(difficult,0);
                }else{
                    respuesta01.setTextColor("red"); //Ver formato de colores
                    BotonesRespuestaDeshabilitacion(difficult,0);
                }
            }
        });

        respuesta02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(AnswerChecker(0)){
                    respuesta02.setTextColor("blue"); //Ver formato de colores
                    BotonesRespuestaDeshabilitacion(difficult,0);
                }else{
                    respuesta02.setTextColor("red"); //Ver formato de colores
                    BotonesRespuestaDeshabilitacion(difficult,0);
                }
            }
        });

        respuesta03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(AnswerChecker(0)){
                    respuesta03.setTextColor("blue"); //Ver formato de colores
                    BotonesRespuestaDeshabilitacion(difficult,0);
                }else{
                    respuesta03.setTextColor("red"); //Ver formato de colores
                    BotonesRespuestaDeshabilitacion(difficult,0);
                }
            }
        });

        respuesta04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(AnswerChecker(0)){
                    respuesta04.setTextColor("blue"); //Ver formato de colores
                    BotonesRespuestaDeshabilitacion(difficult,0);
                }else{
                    respuesta04.setTextColor("red"); //Ver formato de colores
                    BotonesRespuestaDeshabilitacion(difficult,0);
                }
            }
        });


        //Mensaje al salir sin terminar la partida: SnackBar

        //Acción al presionar back();

        //Cambiar color de la pregunta según se haya contestato: Bien, Mal, Bien con trampa

        //Guardado de la partida

        //Enviar información al siguiente activity


    }

    public void DesplegarPregunta_Respuestas(int current) {

        questionsText.setText(questionsToShowSaved[current].getQuestionText());

        switch (difficult) {
            case 2:
                respuesta01.setText(answersToShowSaved[current][0].getAnswerText());
                respuesta02.setText(answersToShowSaved[current][1].getAnswerText());
                respuesta03.setEnabled(false);
                respuesta03.setVisibility(View.INVISIBLE);
                respuesta04.setEnabled(false);
                respuesta04.setVisibility(View.INVISIBLE);
                break;
            case 3:
                respuesta01.setText(answersToShowSaved[current][0].getAnswerText());
                respuesta02.setText(answersToShowSaved[current][1].getAnswerText());
                respuesta03.setText(answersToShowSaved[current][2].getAnswerText());
                respuesta04.setEnabled(false);
                respuesta04.setVisibility(View.INVISIBLE);
                break;
            case 4:
                respuesta01.setText(answersToShowSaved[current][0].getAnswerText());
                respuesta02.setText(answersToShowSaved[current][1].getAnswerText());
                respuesta03.setText(answersToShowSaved[current][2].getAnswerText());
                respuesta04.setText(answersToShowSaved[current][3].getAnswerText());
                break;
        }
    }

    public int NextQuestionIndex(int current) {
        if (current == questionsQuantity - 1)
            return 0;
        else
            return current++;
    }

    public int PrevQuestionIndex(int current) {
        if (current == 0)
            return questionsQuantity - 1;
        else
            return current--;
    }

    public void BotonTrampa(int indice) {
        respuestas[indice].setEnabled(false);
    }

    public void CheatsDeshabilitador(boolean cheats, int quantity) {
        if (!cheats) {
            cheatsImage.setEnabled(false);
            cheatsFollower.setEnabled(false);
        } else
            ShowCheatsQuantity(quantity);
    }

    public void ShowQuestionsFollower(int current) {
        String Contador = Integer.toString(current + 1) + "/" + Integer.toString(questionsQuantity);
        questionsFollower.setText(Contador);
    }

    public void ShowCheatsQuantity(int quantity) {
        cheatsFollower.setText(Integer.toString(quantity));
        if (cheatsQuantity == 0)
            cheatsFollower.setEnabled(false);
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

        respuestas = new Button[]{respuesta01, respuesta02, respuesta03, respuesta04};
    }

    public void HabilitacionBotones(int quantity) {
        for (int i = 0; i < quantity; i++) {
            respuestas[i].setEnabled(true);
            respuestas[i].setVisibility(View.VISIBLE);
        }
    }


    public void BotonesRespuestaDeshabilitacion(int quantity, int aux) {
        for (int i = 0; i < quantity; i++) {
            if(i!=aux){
                respuestas[i].setEnabled(true);
            }
        }
    }

    public void Trampa() {
        Random rand = new Random();
        boolean finish = true;
        while (finish) {
            int aleatorio = rand.nextInt(respuestas.length) - 1;
            if (respuestas[aleatorio].isEnabled()) {
                if (respuestas[aleatorio].getText() != answersToShowSaved[currentQuestion][0].getAnswerText()) {
                    respuestas[aleatorio].setEnabled(false);
                    finish = false;
                }

            }
        }
    }

    public boolean AnswerChecker(int i) {
        boolean a;
        if (respuestas[i].getText() == answersToShowSaved[currentQuestion][0].getAnswerText())
            a = true;
        else
            a = false;
        return a;
    }

}
