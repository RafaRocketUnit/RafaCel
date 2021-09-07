package com.rafaapp.rafacel.ui.DetalleUsuario;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rafaapp.rafacel.R;

import org.jetbrains.annotations.NotNull;

public class EquipoViewHolder extends RecyclerView.ViewHolder {

    private TextView equSerial;
    private TextView lineaNumero;
    private TextView marca_celular;
    private TextView descripcion_celular;
    private TextView estado_celular;

    public EquipoViewHolder(@NonNull @NotNull View itemView) {

        super(itemView);
        equSerial = itemView.findViewById(R.id.equSerial);
        lineaNumero = itemView.findViewById(R.id.lineaNumero);
        marca_celular = itemView.findViewById(R.id.marca_celular);
        descripcion_celular = itemView.findViewById(R.id.descripcion_celular);
        estado_celular = itemView.findViewById(R.id.estado_celular);
    }

    public TextView getEquSerial() {
        return equSerial;
    }

    public void setEquSerial(TextView equSerial) {
        this.equSerial = equSerial;
    }

    public TextView getLineaNumero() {
        return lineaNumero;
    }

    public void setLineaNumero(TextView lineaNumero) {
        this.lineaNumero = lineaNumero;
    }

    public TextView getMarca_celular() {
        return marca_celular;
    }

    public void setMarca_celular(TextView marca_celular) {
        this.marca_celular = marca_celular;
    }

    public TextView getDescripcion_celular() {
        return descripcion_celular;
    }

    public void setDescripcion_celular(TextView descripcion_celular) {
        this.descripcion_celular = descripcion_celular;
    }

    public TextView getEstado_celular() {
        return estado_celular;
    }

    public void setEstado_celular(TextView estado_celular) {
        this.estado_celular = estado_celular;
    }

}
