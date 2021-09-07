package com.rafaapp.rafacel.model;


import com.google.gson.Gson;

import java.util.ArrayList;

public class EquipoCelular {

    int perId;
    Long equSerial = 0L;
    Linea linea = new Linea();
    String equMarca = "";
    String equDescripcion = "";
    ArrayList<Factura> facturas;
    EquEstado equEstado = EquEstado.No_Reportado;

    public enum EquEstado {
        Reportado,
        No_Reportado
    }

    public int getPerId() {
        return perId;
    }

    public void setPerId(int perId) {
        this.perId = perId;
    }

    public Long getEquSerial() {
        return equSerial;
    }

    public void setEquSerial(Long equSerial) {
        this.equSerial = equSerial;
    }

    public Linea getLinea() {
        return linea;
    }

    public void setLinea(Linea linea) {
        this.linea = linea;
    }

    public String getEquMarca() {
        return equMarca;
    }

    public void setEquMarca(String equMarca) {
        this.equMarca = equMarca;
    }

    public ArrayList<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(ArrayList<Factura> facturas) {
        this.facturas = facturas;
    }

    public String getEquDescripcion() {
        return equDescripcion;
    }

    public void setEquDescripcion(String equDescripcion) {
        this.equDescripcion = equDescripcion;
    }

    public EquEstado getEquEstado() {
        return equEstado;
    }

    public void setEquEstado(EquEstado equEstado) {
        this.equEstado = equEstado;
    }

    public boolean isFullFilled() {
        return equSerial != 0L && linea.isFullfilled() &&
                (equMarca != null && !equMarca.isEmpty()) &&
                (equDescripcion != null && !equDescripcion.isEmpty());
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
