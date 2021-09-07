package com.rafaapp.rafacel.ui.registrar;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rafaapp.rafacel.Utils;
import com.rafaapp.rafacel.model.Persona;

import java.util.Date;

public class RegistrarViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<Persona> personaMutableLiveData;

    public RegistrarViewModel() {
        personaMutableLiveData = new MutableLiveData<>();
    }

    public LiveData<Persona> createPersona(Context context,
                                        String nombre,
                                        String apellido,
                                        String cedula,
                                        String telefonoFijo,
                                        Date fechaNacimiento,
                                        String marca,
                                        String descripcion) {

        personaMutableLiveData = new MutableLiveData<>();
        personaMutableLiveData.setValue(Utils.createPersona(context, nombre, apellido, cedula, telefonoFijo, fechaNacimiento, marca, descripcion));
        return personaMutableLiveData;
    }
}