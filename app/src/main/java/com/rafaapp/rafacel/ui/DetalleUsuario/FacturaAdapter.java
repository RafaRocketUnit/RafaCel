package com.rafaapp.rafacel.ui.DetalleUsuario;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rafaapp.rafacel.R;
import com.rafaapp.rafacel.Utils;
import com.rafaapp.rafacel.model.Factura;
import com.rafaapp.rafacel.ui.actualizar.ItemViewHolder;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class FacturaAdapter extends RecyclerView.Adapter<FacturaViewHolder> {

    private ArrayList<Factura> facturas;

    public  FacturaAdapter(ArrayList<Factura> facturas) {
        this.facturas = facturas;
    }

    @NonNull
    @NotNull
    @Override
    public FacturaViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_facturas, parent, false);
        return new FacturaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull FacturaViewHolder holder, int position) {
        FacturaViewHolder facturaViewHolder = (FacturaViewHolder) holder;

        facturaViewHolder.getNumeroFac().setText("Factura #: "+facturas.get(position).getFacNumero());
        facturaViewHolder.getLineaNumero().setText("Linea Celular: " + facturas.get(position).getLinea().getLinumerolinea());
        facturaViewHolder.getLineaEstado().setText("Estado de la linea: "+facturas.get(position).getLinea().getLinEstado().name());
        facturaViewHolder.getEstadoFactura().setText("Estado de la factura: " + facturas.get(position).getEstadoFactura().name());
        facturaViewHolder.getFechaEmision().setText("Fecha de emisión: " + Utils.getDateStringFromDate(facturas.get(position).getFacFechaEmision()));
        facturaViewHolder.getValorFactura().setText("Valor a pagar: $" + facturas.get(position).getFacValor());
    }

    @Override
    public int getItemCount() {
        return facturas.size();
    }
}
