package com.example.quizapp_pro;

import androidx.lifecycle.ViewModel;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class UsuariosViewModel extends ViewModel {

    private Usuario[] usuarios;

    public Usuario[] getUsuarios() {
        return usuarios;
    }

    public UsuariosViewModel() {

        usuarios = new Usuario[]{
                new Usuario("MDG",9,false),
                new Usuario("CLT",8,true),
                new Usuario("MAX",1,true),
                new Usuario("IGN",7,false),
                new Usuario("EDS",2,false),
                new Usuario("999",5,false),
        };
        Arrays.sort(usuarios, new SortByPoints());
    }
}