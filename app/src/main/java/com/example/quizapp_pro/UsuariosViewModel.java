package com.example.quizapp_pro;

import androidx.lifecycle.ViewModel;

import java.util.Arrays;

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
                new Usuario("CLX", 20, false),
                new Usuario("MAX", 15, false),
                new Usuario("IGN", 10, false),
                new Usuario("QWE", 8, false),
                new Usuario("ASD", 12, true),
                new Usuario("ZXC", 15, true),
        };


        Arrays.sort(usuarios, new SortByPoints());
    }

    public Usuario[] Construir(String[] s, int[] p, boolean[] g) {
        Usuario[] UsuarioAux = new Usuario[6];

        for (int i = 0; i < 6; i++) {
            UsuarioAux[i] = new Usuario(s[i], p[i], g[i]);
        }

        return UsuarioAux;
    }


}