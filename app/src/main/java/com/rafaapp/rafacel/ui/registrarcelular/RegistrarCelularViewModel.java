package com.rafaapp.rafacel.ui.registrarcelular;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rafaapp.rafacel.Utils;
import com.rafaapp.rafacel.model.EquipoCelular;
import com.rafaapp.rafacel.model.Linea;

public class RegistrarCelularViewModel extends ViewModel {

    private MutableLiveData<EquipoCelular> personaMutableLiveData;

    public RegistrarCelularViewModel() {
        personaMutableLiveData = new MutableLiveData<>();
    }

    public LiveData<EquipoCelular> createEquipo(@Nullable int perId,
                                                @Nullable Linea linea,
                                                @Nullable long serial,
                                                @Nullable String marca,
                                                @Nullable String descripcion,
                                                @Nullable EquipoCelular.EquEstado equEstado) {

        personaMutableLiveData = new MutableLiveData<>();
        personaMutableLiveData.setValue(Utils.createEquipoCelular(perId, linea, serial, marca, descripcion, equEstado));
        return personaMutableLiveData;
    }
}