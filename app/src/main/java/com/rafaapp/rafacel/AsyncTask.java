package com.rafaapp.rafacel;

import android.content.Context;

import com.rafaapp.rafacel.controller.OperacionesImpl;
import com.rafaapp.rafacel.model.Persona;

public class AsyncTask extends android.os.AsyncTask<Persona, Void, Void> {

    OperacionesImpl operaciones = new OperacionesImpl();

    @Override
    protected Void doInBackground(Persona... personas) {

//        operaciones.registro()
        return null;
    }
}