package com.example.quizapp_pro;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

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

    private UsuariosViewModel usuarios;
    private EstadoViewModel estado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);


        estado = new ViewModelProvider(this).get(EstadoViewModel.class);
        usuarios = new ViewModelProvider(this).get(UsuariosViewModel.class);

        Intent intent = getIntent();
        estado.setNickname(intent.getStringExtra("NICKNAME"));
        estado.setPuntaje(intent.getIntExtra("PUNTAJE", 0));
        estado.setCheatRecorder(intent.getBooleanExtra("TRAMPAS", false));

        String[] s = intent.getStringArrayExtra("BEST_USERS_NICKNAME");
        int[] p = intent.getIntArrayExtra("BEST_USERS_POINTS");
        boolean[] g = intent.getBooleanArrayExtra("BEST_USERS_CHEATS");
        usuarios.setUsuarios(usuarios.Construir(s, p, g));


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


        if (estado.getNickname() == null) {
            MostrarNombres(usuarios.getUsuarios());
        } else {
            Usuario NuevoUsuario = new Usuario(estado.getNickname(), estado.getPuntaje(), estado.isCheatRecorder());
            String msg = estado.getNickname() + ", " + estado.getPuntaje() + " " + estado.isCheatRecorder();
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
            MostrarNombres(usuariosToShow(usuarios.getUsuarios(), NuevoUsuario));

            if (estado.isCheatRecorder()) {
                btnGallina.setBackgroundResource(R.drawable.trampa);
            } else {
                if (usuarios.getUsuarios()[0].getNickname() == estado.getNickname() && usuarios.getUsuarios()[0].getPuntaje() == estado.getPuntaje()) {
                    btnGallina.setBackgroundResource(R.drawable.oro);
                    Toast.makeText(this, "¡Felicidades!" + "¡Quedaste en primer lugar!", Toast.LENGTH_SHORT).show();
                } else {
                    if (usuarios.getUsuarios()[1].getNickname() == estado.getNickname() && usuarios.getUsuarios()[1].getPuntaje() == estado.getPuntaje()) {
                        btnGallina.setBackgroundResource(R.drawable.plata);
                        Toast.makeText(this, "¡Felicidades!" + "¡Quedaste en segundo lugar!", Toast.LENGTH_SHORT).show();
                    } else {
                        if (usuarios.getUsuarios()[2].getNickname() == estado.getNickname() && usuarios.getUsuarios()[2].getPuntaje() == estado.getPuntaje()) {
                            btnGallina.setBackgroundResource(R.drawable.bronce);
                            Toast.makeText(this, "¡Nada mal!" + "¡Quedaste en tercer lugar!", Toast.LENGTH_SHORT).show();
                        } else {
                            if ((usuarios.getUsuarios()[3].getNickname() == estado.getNickname() && usuarios.getUsuarios()[3].getPuntaje() == estado.getPuntaje())
                                    || (usuarios.getUsuarios()[4].getNickname() == estado.getNickname() && usuarios.getUsuarios()[4].getPuntaje() == estado.getPuntaje())
                                    || (usuarios.getUsuarios()[5].getNickname() == estado.getNickname() && usuarios.getUsuarios()[5].getPuntaje() == estado.getPuntaje())) {
                                btnGallina.setBackgroundResource(R.drawable.nube);
                                Toast.makeText(this, "¡Wow!" + "¡Estas entre los mejores!", Toast.LENGTH_SHORT).show();
                            } else {
                                btnGallina.setBackgroundResource(R.drawable.nube);
                                Toast.makeText(this, estado.getNickname() + " " + estado.getPuntaje() + " Mejor suerte para la próxima", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
            }
        }


        //Usuario UsuarioPartida = new Usuario(nicknameActual, puntajeActual, gallinaActual);

        //Usuario[] UsuariosAMostrar = new Usuario[6];
        //UsuariosAMostrar = usuariosToShow(U.getUsuarios(), UsuarioPartida);

        //MostrarNombres(UsuariosAMostrar);


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

        usuarios.setUsuarios(arregloOrdenado);
        return arregloOrdenado;
    }

    @Override
    public void onBackPressed() {
        if(estado.getNickname() == null){
            super.onBackPressed();
        }else{
            super.onBackPressed();
        }
    }
}


class SortByPoints implements Comparator<Usuario> {
    public int compare(Usuario a, Usuario b) {
        return -(a.getPuntaje() - b.getPuntaje());
    }
}

