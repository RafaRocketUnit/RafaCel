package com.rafaapp.rafacel.ui.DetalleUsuario;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rafaapp.rafacel.R;

import org.jetbrains.annotations.NotNull;

public class FacturaViewHolder extends RecyclerView.ViewHolder {

    private TextView numeroFac;
    private TextView lineaNumero;
    private TextView lineaEstado;
    private TextView fechaEmision;
    private TextView valorFactura;
    private TextView estadoFactura;

    public FacturaViewHolder(@NonNull @NotNull View itemView) {

        super(itemView);
        numeroFac = itemView.findViewById(R.id.numeroFac);
        lineaNumero = itemView.findViewById(R.id.lineaNumero);
        lineaEstado = itemView.findViewById(R.id.lineaEstado);
        fechaEmision = itemView.findViewById(R.id.fechaEmision);
        valorFactura = itemView.findViewById(R.id.valorFactura);
        estadoFactura = itemView.findViewById(R.id.estadoFactura);
    }


    public TextView getNumeroFac() {
        return numeroFac;
    }

    public TextView getLineaNumero() {
        return lineaNumero;
    }

    public TextView getLineaEstado() {
        return lineaEstado;
    }

    public TextView getFechaEmision() {
        return fechaEmision;
    }

    public TextView getValorFactura() {
        return valorFactura;
    }

    public TextView getEstadoFactura() {
        return estadoFactura;
    }
}
