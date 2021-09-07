package com.rafaapp.rafacel.model;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

public class Linea {

    long liNumeroLinea = 0L;
    int perId = -1;
    LineaEstado linEstado = LineaEstado.Suspendida;

    public enum LineaEstado {
        Activa,
        Suspendida
    }

    public long getLinumerolinea() {
        return liNumeroLinea;
    }

    public void setLinumerolinea(long liNumeroLinea) {
        this.liNumeroLinea = liNumeroLinea;
    }

    public int getPerid() {
        return perId;
    }

    public void setPerid(int perId) {
        this.perId = perId;
    }

    public LineaEstado getLinEstado() {
        return linEstado;
    }

    public void setLinEstado(LineaEstado linEstado) {
        this.linEstado = linEstado;
    }

    public boolean isFullfilled() {
        return (liNumeroLinea != 0L);
    }

//    liNumeroLinea + "," + perId + "," + linEstado.name();

    @NonNull
    @NotNull
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
