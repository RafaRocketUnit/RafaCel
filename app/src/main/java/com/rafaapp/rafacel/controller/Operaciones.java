package com.rafaapp.rafacel.controller;

import android.content.Context;

import com.rafaapp.rafacel.model.EquipoCelular;
import com.rafaapp.rafacel.model.Persona;

public interface Operaciones {

    public boolean registro(Context context, Persona persona);
    public boolean registroCelular(Context context, Persona persona, EquipoCelular equipoCelular);
    boolean eliminarUsuario(Context context, Persona persona);

}
