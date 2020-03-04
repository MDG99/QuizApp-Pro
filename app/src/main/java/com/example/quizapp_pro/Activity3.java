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
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Activity3 extends AppCompatActivity {

    private EstadoViewModel estado;
    private PlayViewModel play;
    private UsuariosViewModel bestPlayers;

    private AlertDialog Dialogo;

    private boolean[] habilitadorDeCheats;
    //TextViews: contador, trampas y pregunta
    private TextView questionsFollower;
    private TextView questionsText;
    private TextView cheatsButton;


    //ImageView: ícono de trampas
    private ImageView cheatsImage;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        playercorrect = MediaPlayer.create(Activity3.this, R.raw.fxacierto);
        playercheat = MediaPlayer.create(Activity3.this, R.raw.fxgallina);
        playerincorrect = MediaPlayer.create(Activity3.this, R.raw.fxincorrecto);

        //estado = intent.getBundleExtra("PARTIDA_REGRESO");


        questionsFollower = findViewById(R.id.questionsQuantity);
        questionsText = findViewById(R.id.questionText);
        respuesta01 = findViewById(R.id.respuesta01);
        respuesta02 = findViewById(R.id.respuesta02);
        respuesta03 = findViewById(R.id.respuesta03);
        respuesta04 = findViewById(R.id.respuesta04);
        //Botones: controles de preguntas
        Button prevButton = findViewById(R.id.prevButton);
        Button nextButton = findViewById(R.id.nextButton);
        cheatsButton = findViewById(R.id.cheatsQuantity);
        TopicPhoto = findViewById(R.id.ImageTopic);

        MainActivityViewModel model = new ViewModelProvider(this).get(MainActivityViewModel.class);
        estado = new ViewModelProvider(this).get(EstadoViewModel.class);
        play = new ViewModelProvider(this).get(PlayViewModel.class);
        bestPlayers = new ViewModelProvider(this).get(UsuariosViewModel.class);
        Intent intent = getIntent();


        if(estado.isAux()){
            String[] s = intent.getStringArrayExtra("BEST_USERS_NICKNAME");
            int[] p = intent.getIntArrayExtra("BEST_USERS_POINTS");
            boolean[] g = intent.getBooleanArrayExtra("BEST_USERS_CHEATS");
            bestPlayers.setUsuarios(bestPlayers.Construir(s, p, g));
            play.setCuantasPistas(intent.getIntExtra("CHEATS_QUANTITY", 0));
            play.setCuantasPreguntas(intent.getIntExtra("QUESTIONS_QUANTITY", 5));
            play.setDificultadPuntos(intent.getIntExtra("DIFFICULT", 2));
            play.setEnabledPistas(intent.getBooleanExtra("CHEATS_ENABLE", false));
            play.setTopicsChosen((int[]) intent.getSerializableExtra("TOPICS_TO_ASK"));
            estado.PlayDefault(play.getCuantasPreguntas(), play.getDificultadPuntos());
            estado.setQuestionsToShow(model.questionsByTopicRandom(play.getCuantasPreguntas(), play.getTopicsChosen()));
            estado.setQuestionsToShowSaved(estado.ListToArrayQuestion(estado.getQuestionsToShow()));
            estado.PlayRandomAnswers(play.getCuantasPreguntas(), play.getDificultadPuntos());
            estado.setAux(false);
        }

        ShowQuestionsFollower(estado.getCurrentQuestion());

        InicializacionBotones();

        HabilitacionBotones(play.getDificultadPuntos());

        CheckerCheatsButton(play.isEnabledPistas());

        GetAnswersColors();
        questionsText.setText(estado.getQuestionsToShowSaved()[estado.getCurrentQuestion()].getQuestionText());


        DesplegarRespuestas(estado.getCurrentQuestion());
        //#endregion

        switch (estado.getQuestionsToShowSaved()[estado.getCurrentQuestion()].

                getTopicId()) {
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
        }

        //#region Click botón Next
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                estado.NextQuestionIndex(play.getCuantasPreguntas());
                ShowQuestionsFollower(estado.getCurrentQuestion());
                questionsText.setText(estado.getQuestionsToShowSaved()[estado.getCurrentQuestion()].getQuestionText());
                DesplegarRespuestas(estado.getCurrentQuestion());
                GetAnswersColors();
                CheatsButtonByQuestion();
                switch (estado.getQuestionsToShowSaved()[estado.getCurrentQuestion()].getTopicId()) {
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
                estado.PrevQuestionIndex(play.getCuantasPreguntas());
                ShowQuestionsFollower(estado.getCurrentQuestion());
                questionsText.setText(estado.getQuestionsToShowSaved()[estado.getCurrentQuestion()].getQuestionText());
                DesplegarRespuestas(estado.getCurrentQuestion());
                GetAnswersColors();
                CheatsButtonByQuestion();
                switch (estado.getQuestionsToShowSaved()[estado.getCurrentQuestion()].getTopicId()) {
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

                play.setCuantasPistas(play.getCuantasPistas() - 1);
                ShowCheatsQuantity();
                estado.setCheatRecorder(true);

                if (estado.getCheatsCounterByQuestion()[estado.getCurrentQuestion()] > 2) {
                    Trampa();
                    playercheat.start();
                    estado.cheatsButtonClick01();
                } else {
                    Trampa();
                    playercorrect.start();
                    ShowAnswerByCheats();
                    estado.cheatsButtonClick02();
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
                    estado.RespuestaClick01(0);
                    playercorrect.start();
                    if (!estado.getPuntajeCheats()[estado.getCurrentQuestion()])
                        estado.setPuntaje(estado.getPuntaje() + play.getDificultadPuntos());

                } else {
                    estado.RespuestaClick02(0);
                    playerincorrect.start();
                }
                RespuestasHabilitador(false);
                estado.RespuestaClick();
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
                    estado.RespuestaClick01(1);
                    playercorrect.start();
                    if (!estado.getPuntajeCheats()[estado.getCurrentQuestion()])
                        estado.setPuntaje(estado.getPuntaje() + play.getDificultadPuntos());

                } else {
                    estado.RespuestaClick02(1);
                    playerincorrect.start();
                }
                RespuestasHabilitador(false);
                estado.RespuestaClick();
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
                    estado.RespuestaClick01(2);
                    playercorrect.start();
                    if (!estado.getPuntajeCheats()[estado.getCurrentQuestion()])
                        estado.setPuntaje(estado.getPuntaje() + play.getDificultadPuntos());

                } else {
                    estado.RespuestaClick02(2);
                    playerincorrect.start();
                }
                RespuestasHabilitador(false);
                estado.RespuestaClick();
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
                    estado.RespuestaClick01(3);
                    playercorrect.start();
                    if (!estado.getPuntajeCheats()[estado.getCurrentQuestion()])
                        estado.setPuntaje(estado.getPuntaje() + play.getDificultadPuntos());

                } else {
                    estado.RespuestaClick02(3);
                    playerincorrect.start();
                }
                RespuestasHabilitador(false);
                estado.RespuestaClick();
                CheatsButtonByQuestion();
                GameChecker();
                GetAnswersColors();
            }
        });

        //# endregion
    }


    //#region métodos que funcionana
    public void GetAnswersColors() {

        switch (play.getDificultadPuntos()) {
            case 2:
                respuesta01.setBackgroundColor(estado.getColorsAnswers()[estado.getCurrentQuestion()][0]);
                respuesta02.setBackgroundColor(estado.getColorsAnswers()[estado.getCurrentQuestion()][1]);

                respuesta01.setEnabled(estado.getHabilitadorDeRespuestas()[estado.getCurrentQuestion()][0]);
                respuesta02.setEnabled(estado.getHabilitadorDeRespuestas()[estado.getCurrentQuestion()][1]);

                break;
            case 3:
                respuesta01.setBackgroundColor(estado.getColorsAnswers()[estado.getCurrentQuestion()][0]);
                respuesta02.setBackgroundColor(estado.getColorsAnswers()[estado.getCurrentQuestion()][1]);
                respuesta03.setBackgroundColor(estado.getColorsAnswers()[estado.getCurrentQuestion()][2]);

                respuesta01.setEnabled(estado.getHabilitadorDeRespuestas()[estado.getCurrentQuestion()][0]);
                respuesta02.setEnabled(estado.getHabilitadorDeRespuestas()[estado.getCurrentQuestion()][1]);
                respuesta03.setEnabled(estado.getHabilitadorDeRespuestas()[estado.getCurrentQuestion()][2]);


                break;
            case 4:
                respuesta01.setBackgroundColor(estado.getColorsAnswers()[estado.getCurrentQuestion()][0]);
                respuesta02.setBackgroundColor(estado.getColorsAnswers()[estado.getCurrentQuestion()][1]);
                respuesta03.setBackgroundColor(estado.getColorsAnswers()[estado.getCurrentQuestion()][2]);
                respuesta04.setBackgroundColor(estado.getColorsAnswers()[estado.getCurrentQuestion()][3]);

                respuesta01.setEnabled(estado.getHabilitadorDeRespuestas()[estado.getCurrentQuestion()][0]);
                respuesta02.setEnabled(estado.getHabilitadorDeRespuestas()[estado.getCurrentQuestion()][1]);
                respuesta03.setEnabled(estado.getHabilitadorDeRespuestas()[estado.getCurrentQuestion()][2]);
                respuesta04.setEnabled(estado.getHabilitadorDeRespuestas()[estado.getCurrentQuestion()][3]);

                break;


        }

    }

    public void DesplegarRespuestas(int current) {

        switch (play.getDificultadPuntos()) {
            case 2:
                respuesta01.setText(estado.getAnswersToShowSaved()[current][0].getAnswerText());
                respuesta02.setText(estado.getAnswersToShowSaved()[current][1].getAnswerText());

                break;
            case 3:
                respuesta01.setText(estado.getAnswersToShowSaved()[current][0].getAnswerText());
                respuesta02.setText(estado.getAnswersToShowSaved()[current][1].getAnswerText());
                respuesta03.setText(estado.getAnswersToShowSaved()[current][2].getAnswerText());

                break;
            case 4:
                respuesta01.setText(estado.getAnswersToShowSaved()[current][0].getAnswerText());
                respuesta02.setText(estado.getAnswersToShowSaved()[current][1].getAnswerText());
                respuesta03.setText(estado.getAnswersToShowSaved()[current][2].getAnswerText());
                respuesta04.setText(estado.getAnswersToShowSaved()[current][3].getAnswerText());
                break;
        }
    }


    public void ShowQuestionsFollower(int c) {
        String Contador = (c + 1) + "/" + play.getCuantasPreguntas();
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

        respuestas = new Button[play.getCuantasPreguntas()][4];
        for (int i = 0; i < play.getCuantasPreguntas(); i++) {
            respuestas[i][0] = respuesta01;
            respuestas[i][1] = respuesta02;
            respuestas[i][2] = respuesta03;
            respuestas[i][3] = respuesta04;
        }
    }

    public void HabilitacionBotones(int quantity) {
        for (int i = 0; i < quantity; i++) {
            respuestas[estado.getCurrentQuestion()][i].setEnabled(true);
            respuestas[estado.getCurrentQuestion()][i].setVisibility(View.VISIBLE);

        }
    }

    public boolean AnswerChecker(int i) {
        boolean a;
        if (respuestas[estado.getCurrentQuestion()][i].getText() == estado.getAnswersToShow()[estado.getCurrentQuestion()][0].getAnswerText())
            a = true;
        else
            a = false;
        return a;
    }

    public void NotEnableButtons() {
        for (int h = 0; h < play.getDificultadPuntos(); h++) {
            estado.getHabilitadorDeRespuestas()[estado.getCurrentQuestion()][h] = false;
        }
    }

    public void RespuestasHabilitador(boolean habilitador) {
        if (!habilitador) {
            NotEnableButtons();
        }
    }

    public void CheatsButtonByQuestion() {
        cheatsButton.setEnabled(estado.getHabilitadorDeCheats()[estado.getCurrentQuestion()]);
    }

    public void CheckerCheatsButton(boolean checker) {
        if (checker) {
            if(play.getCuantasPistas() == 0){
                cheatsButton.setEnabled(false);
            }else{
                cheatsButton.setEnabled(true); //Aquí tienes que cheacar si te quedan chafas
                habilitadorDeCheats = new boolean[play.getCuantasPreguntas()];
                for (int t = 0; t < play.getCuantasPreguntas(); t++) {
                    habilitadorDeCheats[t] = true;

                }
                estado.setHabilitadorDeCheats(habilitadorDeCheats);
            }
            ShowCheatsQuantity();
        } else {
            cheatsButton.setEnabled(false);
            cheatsButton.setVisibility(View.INVISIBLE);
        }
    }

    public void ShowCheatsQuantity() {
        cheatsButton.setText(Integer.toString(play.getCuantasPistas()));
    }


    public void ShowAnswerByCheats() {
        for (int o = 0; o < 4; o++) {
            if (respuestas[estado.getCurrentQuestion()][o].isEnabled()) {
                estado.ShowAnswerByCheats(o);
                NotEnableButtons();
            }
        }
    }

    public void GameChecker() {
        int finish = 0;
        for (int d = 0; d < play.getCuantasPreguntas(); d++) {
            if (estado.getRespondido()[d])
                finish++;
        }
        if (finish == play.getCuantasPreguntas())
            CreacionDialogo();
    }

    public void Trampa() {
        Random rand = new Random();
        boolean finish = true;
        while (finish) {
            int aleatorio = rand.nextInt(4);
            if (respuestas[estado.getCurrentQuestion()][aleatorio].isEnabled()) {
                if (respuestas[estado.getCurrentQuestion()][aleatorio].getText() != estado.getAnswersToShow()[estado.getCurrentQuestion()][0].getAnswerText()) {
                    estado.Trampa(aleatorio);
                    respuestas[estado.getCurrentQuestion()][aleatorio].setEnabled(estado.getHabilitadorDeRespuestas()[estado.getCurrentQuestion()][aleatorio]);
                    finish = false;
                }

            }
        }
        if (play.getCuantasPistas() == 0) {
            estado.TrampaCero(play.getCuantasPreguntas());
            if (estado.getRespondido()[estado.getCurrentQuestion()])
                NotEnableButtons();
        }
    }

    public void SnackTrampa() {
        View Contenido = findViewById(R.id.content);
        Snackbar.make(Contenido, "Haz hecho trampa", Snackbar.LENGTH_SHORT).show();
    }
    /////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////    HASTA AQUÍ HE CHECADO LOS MÉTODOS    ////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////


    //#endregion


    public void CreacionDialogo() {

        final AlertDialog.Builder DialogoNickname = new AlertDialog.Builder(this);
        final EditText input = new EditText(Activity3.this);
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
                estado.setNickname(input.getText().toString());
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
                Intent intent = new Intent(Activity3.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }
        }).show();
    }

    public void EnviarInfo() {
        Intent intent03 = new Intent(Activity3.this, Activity4.class);

        String[] s = new String[6];
        for (int i = 0; i<6;i++){
            s[i] = bestPlayers.getUsuarios()[i].getNickname();
        }
        int[] p = new int[6];
        for (int i = 0; i<6;i++){
            p[i] = bestPlayers.getUsuarios()[i].getPuntaje();
        }
        boolean[] g = new boolean[6];
        for (int i = 0; i<6;i++){
            g[i] = bestPlayers.getUsuarios()[i].isCheat();
        }

        intent03.putExtra("BEST_USERS_NICKNAME", s);
        intent03.putExtra("BEST_USERS_POINTS", p);
        intent03.putExtra("BEST_USERS_CHEATS", g);

        intent03.putExtra("NICKNAME",estado.getNickname());
        intent03.putExtra("PUNTAJE",estado.getPuntaje());
        intent03.putExtra("TRAMPAS", estado.isCheatRecorder());
        startActivity(intent03);
    }

}



