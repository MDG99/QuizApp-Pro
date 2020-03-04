package com.example.quizapp_pro;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class UsuariosViewModel extends ViewModel {

    private Usuario[] usuarios;

    public Usuario[] getUsuarios() {
        return usuarios;
    }


    public void setUsuarios(Usuario[] usuarios) {
        this.usuarios = usuarios;
    }

    public UsuariosViewModel() {

        usuarios = new Usuario[]{
                new Usuario("MDG", 9, false),
                new Usuario("CLT", 8, false),
                new Usuario("MAX", 7, false),
                new Usuario("IGN", 6, false),
                new Usuario("EDS", 3, true),
                new Usuario("999", 5, true),
        };


        Arrays.sort(usuarios, new SortByPoints());
    }

    public Usuario[] Construir(String[] s, int[] p, boolean[] g) {
        Usuario[] UsuarioAux = new Usuario[6];

        for (int i = 0; i < 6; i++) {
            UsuarioAux[i]= new Usuario(s[i],p[i],g[i]);
        }

        return UsuarioAux;
    }

}