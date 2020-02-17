package com.example.quizapp_pro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

public class Activity3 extends AppCompatActivity {

    private TextView questionsCounter;
    private TextView cheatsCounter;
    private TextView questionsText;

    private CheckBox respuesta01;
    private CheckBox respuesta02;
    private CheckBox respuesta03;
    private CheckBox respuesta04;

    private Button prevButton;
    private Button nextButton;

    private int difficult;
    private int questionsQuantity;
    private int quantityAnswers;
    private int questionsForTopic;
    private int residueQuestionsForTopic;
    private int[] topicsToAsk;

    private boolean cheatsEnable;
    private boolean cheatRecorder;

    private List<Questions> questionsToShow;
    private List<Answers> answersToShow;
    private List<Answers> fakeAnswers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        questionsCounter = findViewById(R.id.questionsCounter);
        cheatsCounter = findViewById(R.id.cheatsCounter);
        questionsText = findViewById(R.id.questionText);
        respuesta01 = findViewById(R.id.respuesta01Text);
        respuesta02 = findViewById(R.id.respuesta02Text);
        respuesta03 = findViewById(R.id.respuesta03Text);
        respuesta04 = findViewById(R.id.respuesta04Text);
        prevButton = findViewById(R.id.previousButton);
        nextButton = findViewById(R.id.nextButton);

        //Inicialización de las preguntas, recaudar la información de otros activities
        MainActivityViewModel model = new MainActivityViewModel();
        cheatRecorder = false;
        quantityAnswers = 4; //DEPENDE DE LA DIFICULTAD


        //Preguntas por tópico YA LO HACE OTRA CLASE


        //Llenado de las preguntas por tópico
        questionsToShow.addAll(model.questionsByTopicRandom(questionsQuantity,topicsToAsk));


        //Desplegar preguntas con sus debidas respuestas de manera aleatoria

        //Cambiar de pregunta

        //Habilitar o deshabilitar el contador de trampas

        //Trampa: deshabilitar una respuesta incorrecta de manera aleatoria

        //Mensaje al salir sin terminar la partida: SnackBar

        //Acción al presionar back();

        //Cambiar color de la pregunta según se haya contestato: Bien, Mal, Bien con trampa

        //Guardado de la partida

        //Enviar información al siguiente activity


    }
}
