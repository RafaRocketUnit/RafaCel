package com.rafaapp.rafacel.ui.DetalleUsuario;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rafaapp.rafacel.R;
import com.rafaapp.rafacel.Utils;
import com.rafaapp.rafacel.model.EquipoCelular;
import com.rafaapp.rafacel.model.Factura;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class EquipoAdapter extends RecyclerView.Adapter<EquipoViewHolder> {

    private ArrayList<EquipoCelular> equipos = new ArrayList<>();

    public EquipoAdapter(ArrayList<EquipoCelular> equipos) {
        this.equipos = equipos;
    }

    @NonNull
    @NotNull
    @Override
    public EquipoViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_equipos, parent, false);
        return new EquipoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull EquipoViewHolder holder, int position) {
        EquipoViewHolder equipoViewHolder = (EquipoViewHolder) holder;

        equipoViewHolder.getEquSerial().setText("# Serial: " + equipos.get(position).getEquSerial());
        equipoViewHolder.getLineaNumero().setText("Numero de equipo: " + equipos.get(position).getLinea().getLinumerolinea());
        equipoViewHolder.getMarca_celular().setText("Marca Celular: " + equipos.get(position).getEquMarca());
        equipoViewHolder.getDescripcion_celular().setText("Descripción: " + equipos.get(position).getEquDescripcion());
        equipoViewHolder.getEstado_celular().setText("Estado del equipo: " + equipos.get(position).getEquEstado().name());
    }

    @Override
    public int getItemCount() {
        return equipos.size();
    }
}
