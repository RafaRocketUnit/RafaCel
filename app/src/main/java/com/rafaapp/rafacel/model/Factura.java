package com.rafaapp.rafacel.model;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.rafaapp.rafacel.Utils;

import org.jetbrains.annotations.NotNull;

import java.util.Date;

public class Factura {

    int perId;
    int facNumero = -1;
    EstadoFactura estadoFactura = EstadoFactura.Pendiente;
    Linea linea = new Linea();
    Date facFechaEmision;
    Double facValor = 0.0;

    public int getPerId() {
        return perId;
    }
    public void setPerId(int perId) {
        this.perId = perId;
    }

    public int getFacNumero() {
        return facNumero;
    }
    public void setFacNumero(int facNumero) {
        this.facNumero = facNumero;
    }

    public Linea getLinea() {
        return linea;
    }
    public void setLinea(Linea linea) {
        this.linea = linea;
    }

    public Date getFacFechaEmision() {
        return facFechaEmision;
    }
    public void setFacFechaEmision(Date facFechaEmision) {
        this.facFechaEmision = facFechaEmision;
    }

    public Double getFacValor() {
        return facValor;
    }
    public void setFacValor(Double facValor) {
        this.facValor = facValor;
    }

    public EstadoFactura getEstadoFactura() {
        return estadoFactura;
    }

    public void setEstadoFactura(EstadoFactura estadoFactura) {
        this.estadoFactura = estadoFactura;
    }

    public enum EstadoFactura {
        Pendiente,
        Pagada,
        Vencida
    }


    @NonNull
    @NotNull
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
