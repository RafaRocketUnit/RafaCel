package com.rafaapp.rafacel.ui.consultar;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rafaapp.rafacel.Utils;
import com.rafaapp.rafacel.model.Factura;
import com.rafaapp.rafacel.model.Persona;

import java.util.ArrayList;
import java.util.Date;

public class ConsultarViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Factura>> facturas;

    public ConsultarViewModel() {
        facturas = new MutableLiveData<>();

    }

    public LiveData<ArrayList<Factura>> getFacturas(Persona persona, Date input, Date output) {
        facturas.setValue(Utils.getFacturasPerPerson(persona, input, output));
        return facturas;
    }

    public LiveData<ArrayList<Factura>> getFacturas(Context context, Date input, Date output) {
        facturas.setValue(Utils.getTodasFacturas(context, input, output));
        return facturas;
    }
}