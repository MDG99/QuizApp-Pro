package com.example.quizapp_pro;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


public class Activity4 extends AppCompatActivity {


    private TextView txtName1;
    private TextView txtName2;
    private TextView txtName3;
    private TextView txtName4;
    private TextView txtName5;
    private TextView txtName6;
    private TextView txtPuntaje1;
    private TextView txtPuntaje2;
    private TextView txtPuntaje3;
    private TextView txtPuntaje4;
    private TextView txtPuntaje5;
    private TextView txtPuntaje6;
    private ImageView btnGallina;
    private TextView[] Nombres;
    private TextView[] Puntajes;

    private final String CUALES_TOPICS = "CUALES_TOPICS";
    private final String NO_PREGUNTAS = "NO_PREGUNTAS";
    private final String DIFICULTAD_PUNTOS = "DIFICULTAD_PUNTOS";
    private final String ENABLE_PISTAS = "ENABLE_PISTAS";
    private final String NO_PISTAS = "NO_PISTAS";

    private int[] topicsChosen;
    private int cuantasPreguntas;
    private int dificultadPuntos;
    private boolean enabledPistas;
    private int cuantasPistas;

    private final String NICKNAME_ARRAY = "PLAYER_NICKNAME";
    private final String PUNTAJE_ARRAY = "PLAYER_POINTS";
    private final String GALLINA_ARRAY = "PLAYER_CHEATED";
    private String[] nicknames;
    private String nicknameActual;
    private int[] puntajes;
    private int puntajeActual;
    private boolean[] gallinas;
    private boolean gallinaActual;
    private Bundle estado;
    private Usuario[] UsuariosPoints;

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

    private boolean cheatsEnable; //guardar
    private boolean cheatRecorder; //guardar
    private boolean cheatsFollowerEnable[];
    private boolean[][] habilitadorDeRespuestas;
    private boolean[] puntajeCheats;
    private boolean[] habilitadorDeCheats;
    private boolean[] Respondido;

    private Questions[] questionsToShowSaved;

    private Answers[][] answersToShowSaved;
    private Answers[][] answersToShow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);

        Toast.makeText(Activity4.this, "Bienvenido del Activity 3", Toast.LENGTH_SHORT).show();


        txtName1 = findViewById(R.id.nombre1);
        txtName2 = findViewById(R.id.nombre2);
        txtName3 = findViewById(R.id.nombre3);
        txtName4 = findViewById(R.id.nombre4);
        txtName5 = findViewById(R.id.nombre5);
        txtName6 = findViewById(R.id.nombre6);
        txtPuntaje1 = findViewById(R.id.puntaje1);
        txtPuntaje2 = findViewById(R.id.puntaje2);
        txtPuntaje3 = findViewById(R.id.puntaje3);
        txtPuntaje4 = findViewById(R.id.puntaje4);
        txtPuntaje5 = findViewById(R.id.puntaje5);
        txtPuntaje6 = findViewById(R.id.puntaje6);
        btnGallina = findViewById(R.id.findeljuego_imagen);


        Nombres = new TextView[]{
                txtName1, txtName2, txtName3, txtName4, txtName5, txtName6
        };

        Puntajes = new TextView[]{
                txtPuntaje1, txtPuntaje2, txtPuntaje3, txtPuntaje4, txtPuntaje5, txtPuntaje6
        };

        Intent intent04 = getIntent();
        nicknameActual = intent04.getStringExtra("NICKNAME");
        puntajeActual = intent04.getIntExtra("PUNTAJE", 0);
        gallinaActual = intent04.getBooleanExtra("CHECADOR_TRAMPAS", false);


        if (nicknameActual == null) {
            topicsChosen = getIntent().getIntArrayExtra("CUALES_TOPICS");
            cuantasPreguntas = getIntent().getIntExtra("NO_PREGUNTAS", 5);
            dificultadPuntos = getIntent().getIntExtra("DIFICULTAD_PUNTOS", 2);
            enabledPistas = getIntent().getBooleanExtra("ENABLE_PISTAS", false);
            cuantasPistas = getIntent().getIntExtra("NO_PISTAS", 0);
        } else {

          /*
          difficult = intent04.getIntExtra("DIFICULTAD",2);
          questionsQuantity = intent04.getIntExtra("QUESTIONS_QUANTITY",5 );

          cheatsCounterByQuestion = new int[questionsQuantity];
          habilitadorDeCheats = new boolean[questionsQuantity];
          colorsAnswers = new int[questionsQuantity][difficult];
          Respondido = new boolean[questionsQuantity];
          habilitadorDeRespuestas = new boolean[questionsQuantity][difficult];
          puntajeCheats = new boolean[questionsQuantity];

          questionsToShowSaved = new Questions[questionsQuantity];
          answersToShow = new Answers[questionsQuantity][difficult];
          answersToShowSaved = new Answers[questionsQuantity][difficult];

          currentQuestion = intent04.getIntExtra("CURRENT_QUESTIONS",4);
          questionsToShowSaved = (Questions[]) intent04.getSerializableExtra("QUESTIONS_TO_SHOW_SAVED" );
          cheatsCounterByQuestion =(int[]) intent04.getSerializableExtra("CHEATS_COUNTER_BY_QUESTION");
          cheatsQuantity = intent04.getIntExtra("CHEATS_QUANTITY",0 );
          Puntaje = intent04.getIntExtra("PUNTAJE",0);
          topicsToAsk = intent04.getIntArrayExtra("TOPICs_TO_ASK");
          colorsAnswers = (int[][]) intent04.getSerializableExtra("COLOR_ANSWERS");
          cheatsEnable = intent04.getBooleanExtra("CHEATS_ENABLE",false);
          cheatRecorder = intent04.getBooleanExtra("CHEATS_RECORDER",false);
          cheatsFollowerEnable = intent04.getBooleanArrayExtra("CHEATS_FOLLOWER_ENABLE");
          habilitadorDeRespuestas = (boolean[][]) intent04.getSerializableExtra("HABILITADOR_DE_RESPUESTAS");
          puntajeCheats =intent04.getBooleanArrayExtra("PUNTAJE_CHEATS");
          habilitadorDeCheats = intent04.getBooleanArrayExtra("HABILITADOR_DE_CHEATS");
          Respondido = intent04.getBooleanArrayExtra("RESPONDIDO");
          answersToShow =(Answers[][]) intent04.getSerializableExtra("ANSWERS_TO_SHOW");
          answersToShowSaved = (Answers[][]) intent04.getSerializableExtra("ANSWERS_TO_SHOW_SAVED");*/
        }

        //nicknames = getIntent().getStringArrayExtra(NICKNAME_ARRAY);
        //puntajes = getIntent().getIntArrayExtra(PUNTAJE_ARRAY);
        //gallinas = getIntent().getBooleanArrayExtra(GALLINA_ARRAY);

        btnGallina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer playerGallina = MediaPlayer.create(Activity4.this, R.raw.gallina);
                playerGallina.start();
            }
        });


        if (nicknameActual == null) {
            UsuariosViewModel U = new UsuariosViewModel();
            UsuariosPoints = new Usuario[6];
            UsuariosPoints = U.getUsuarios();
            MostrarNombres(UsuariosPoints);
        } else {
            UsuariosViewModel U = new UsuariosViewModel();
            UsuariosPoints = new Usuario[6];
            UsuariosPoints = U.getUsuarios();
            Usuario NuevoUsuario = new Usuario(nicknameActual, puntajeActual, gallinaActual);
            String msg = nicknameActual + ", " + puntajeActual + " " + gallinaActual;
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
            MostrarNombres(usuariosToShow(UsuariosPoints, NuevoUsuario));
        }


        //Usuario UsuarioPartida = new Usuario(nicknameActual, puntajeActual, gallinaActual);

        //Usuario[] UsuariosAMostrar = new Usuario[6];
        //UsuariosAMostrar = usuariosToShow(U.getUsuarios(), UsuarioPartida);

        //MostrarNombres(UsuariosAMostrar);


    }


//    @Override
//    public void onBackPressed() {
//        EnviarInfoBack();
//    }

    public void EnviarInfoBack() {
        if (nicknameActual == null) {
            Intent mainintent = new Intent(Activity4.this, MainActivity.class);
            mainintent.putExtra(CUALES_TOPICS, topicsChosen);
            mainintent.putExtra(NO_PREGUNTAS, cuantasPreguntas);
            mainintent.putExtra(DIFICULTAD_PUNTOS, dificultadPuntos);
            mainintent.putExtra(ENABLE_PISTAS, enabledPistas);
            mainintent.putExtra(NO_PISTAS, cuantasPistas);

            startActivity(mainintent);

        } else {
            //Intent playintent = new Intent(Activity4.this, Activity3.class);
            Intent playintent = new Intent(Activity4.this, Activity3.class);//(Activity4.this, OptionsActivity.class);
            playintent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            playintent.putExtra(CUALES_TOPICS, topicsChosen);
            playintent.putExtra(NO_PREGUNTAS, cuantasPreguntas);
            playintent.putExtra(DIFICULTAD_PUNTOS, dificultadPuntos);
            playintent.putExtra(ENABLE_PISTAS, enabledPistas);
            playintent.putExtra(NO_PISTAS, cuantasPistas);

            playintent.putExtra(NICKNAME_ARRAY, nicknames);
            playintent.putExtra(PUNTAJE_ARRAY, puntajes);
            playintent.putExtra(GALLINA_ARRAY, gallinas);
            startActivity(playintent);
            //startActivity(playintent);

            //onBackPressed();
        }
    }


    public void MostrarNombres(Usuario[] UAux) {
        for (int i = 0; i < Nombres.length; i++) {
            Nombres[i].setText(UAux[i].getNickname());
            String msg = " " + UAux[i].getPuntaje() + " ";
            Puntajes[i].setText(msg);
        }
    }


    public Usuario[] usuariosToShow(Usuario[] arregloPrevio, Usuario nuevoUsuario) {
        List<Usuario> ordenador = new ArrayList<Usuario>();

        for (Usuario u : arregloPrevio
        ) {
            ordenador.add(u);

        }

        ordenador.add(nuevoUsuario);

        Usuario[] AUX = new Usuario[ordenador.size()];
        ordenador.toArray(AUX);
        Usuario[] arregloOrdenado = new Usuario[6];
        Arrays.sort(AUX, new SortByPoints());
        for (int h = 0; h < 6; h++) {
            arregloOrdenado[h] = AUX[h];
        }

        return arregloOrdenado;
    }


}


class SortByPoints implements Comparator<Usuario> {
    public int compare(Usuario a, Usuario b) {
        return -(a.getPuntaje() - b.getPuntaje());
    }
}
