package com.rafaapp.rafacel.ui.actualizar;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rafaapp.rafacel.Utils;
import com.rafaapp.rafacel.model.Persona;

import java.util.ArrayList;

public class ActualizarViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Persona>> listPersonas;

    public ActualizarViewModel() {
        listPersonas = new MutableLiveData<>();
    }

    public LiveData<ArrayList<Persona>> setActualizar(Context context) {
        listPersonas.setValue(Utils.getPersonasFromDatabase(Utils.getPersonasJsonArray(context)));
        return listPersonas;
    }
}