package com.rafaapp.rafacel.ui.actualizar;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.rafaapp.rafacel.R;
import com.rafaapp.rafacel.Utils;
import com.rafaapp.rafacel.model.EquipoCelular;
import com.rafaapp.rafacel.model.Factura;
import com.rafaapp.rafacel.model.Persona;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ActualizarAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final ArrayList<Persona> personas;
    private final Handler.Callback callback;

    public ActualizarAdapter(ArrayList<Persona> personas, Handler.Callback callback) {
        this.personas = personas;
        this.callback = callback;
    }



    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemview, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        Persona persona = personas.get(position);
        itemViewHolder.getNombre().setText(persona.getPerNombre());
        itemViewHolder.getApellido().setText(persona.getPerApellido());
        itemViewHolder.getCedula().setText(persona.getPerCedula());
        itemViewHolder.getEquipoCelular().setText(hayEquipos(persona.getEquipoCelular()) ? "Si":"No");
        itemViewHolder.getFechaNacimiento().setText(persona.getPerFechaNacimiento());
        itemViewHolder.getTelefonoFijo().setText(persona.getPerTelefonoFijo());
        itemViewHolder.getFactura().setText(hayFacturas(persona.getEquipoCelular()) ? "Si":"No");

        itemViewHolder.itemView.setOnClickListener( v -> {
            Message message = new Message();
            message.obj = persona;
            callback.handleMessage(message);
        });
    }

    @Override
    public int getItemCount() {
        return personas.size();
    }

    private Boolean hayEquipos(ArrayList<EquipoCelular> equipoCelulars) {
        return equipoCelulars.size() > 0;
    }

    private Boolean hayFacturas(ArrayList<EquipoCelular> equipoCelulars) {
        for (EquipoCelular equipoCelular : equipoCelulars) {
            return equipoCelular.getFacturas().size() > 0;
        }
        return false;
    }
}
