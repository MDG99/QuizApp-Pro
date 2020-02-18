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


    private int difficult;
    private int questionsQuantity;
    private int quantityAnswers;
    private int questionsForTopic;
    private int residueQuestionsForTopic;
    private int cheatsQuantity;
    private int[] topicsToAsk;

    private boolean cheatsEnable;
    private boolean cheatRecorder;

    //Nombres de los Intents de donde se recaudará información del OptionsActivity
    public static final String DIFFICULT_INTENT = "DIFICULTAD_PUNTOS";
    public static final String QUANTITY_QUESTIONS_INTENT = "NO_PREGUNTAS";
    public static final String CHEATS_ENABLE_INTENT = "ENABLE_PISTAS";
    public static final String CHEATS_QUANTITY_INTENT ="NO_PISTAS";

    private List<Questions> questionsToShow;

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
        cheatsEnable = intent.getBooleanExtra(CHEATS_ENABLE_INTENT,false);
        cheatsQuantity = intent.getIntExtra(CHEATS_QUANTITY_INTENT,0);


                /*
                *         Intent intent = new Intent(OptionsActivity.this, MainActivity.class);
        intent.putExtra(CUALES_TOPICS, topicsChosen);
        startActivity(intent);
                * */


        //Inicialización de las preguntas, recaudar la información de otros activities
        MainActivityViewModel model = new MainActivityViewModel();
        cheatRecorder = false;
        quantityAnswers = 4; //DEPENDE DE LA DIFICULTAD
        int questionCurrent = 0;


        //Preguntas por tópico YA LO HACE OTRA CLASE


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
        Answers[][] answersToShowSaved = new Answers[questionsQuantity][difficult];

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

        //Desplegar preguntas con sus debidas respuestas de manera aleatoria Tienes las que se van a mostrar, hay que cambiar el orden

        questionsText.setText(questionsToShowSaved[questionCurrent].getQuestionText());

        //Hay que hacer que se repita cuando se presiona un botón
        switch (difficult) {
            case 2:
                respuesta01.setText(answersToShowSaved[questionCurrent][0].getAnswerText());
                respuesta02.setText(answersToShowSaved[questionCurrent][1].getAnswerText());
                respuesta03.setEnabled(false);
                respuesta03.setVisibility(View.INVISIBLE);
                respuesta04.setEnabled(false);
                respuesta04.setVisibility(View.INVISIBLE);
                break;
            case 3:
                respuesta01.setText(answersToShowSaved[questionCurrent][0].getAnswerText());
                respuesta02.setText(answersToShowSaved[questionCurrent][1].getAnswerText());
                respuesta03.setText(answersToShowSaved[questionCurrent][2].getAnswerText());
                respuesta04.setEnabled(false);
                respuesta04.setVisibility(View.INVISIBLE);
                break;
            case 4:
                respuesta01.setText(answersToShowSaved[questionCurrent][0].getAnswerText());
                respuesta02.setText(answersToShowSaved[questionCurrent][1].getAnswerText());
                respuesta03.setText(answersToShowSaved[questionCurrent][2].getAnswerText());
                respuesta04.setText(answersToShowSaved[questionCurrent][3].getAnswerText());
                break;
        }


        //Cambiar de pregunta

        //Habilitar o deshabilitar el contador de trampas
        if (cheatsEnable) {
            cheatsCounter.setText(String.valueOf(cheatsQuantity));
        } else {
            cheatsCounter.setEnabled(false);
            cheatsCounter.setVisibility(View.INVISIBLE);
        }

        //Trampa: deshabilitar una respuesta incorrecta de manera aleatoria

        //Mensaje al salir sin terminar la partida: SnackBar

        //Acción al presionar back();

        //Cambiar color de la pregunta según se haya contestato: Bien, Mal, Bien con trampa

        //Guardado de la partida

        //Enviar información al siguiente activity


    }
}
