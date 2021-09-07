package com.rafaapp.rafacel.ui.DetalleUsuario;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.rafaapp.rafacel.R;
import com.rafaapp.rafacel.model.EquipoCelular;
import com.rafaapp.rafacel.model.Factura;
import com.rafaapp.rafacel.model.Persona;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class FacturasFragment extends Fragment {

    Persona persona;

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null && getArguments().containsKey("persona")) {
            String personaJsonString = getArguments().getString("persona", "");
            persona = new Gson().fromJson(personaJsonString, Persona.class);
        }
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.facturas, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerFacturas = (RecyclerView) view.findViewById(R.id.recyclerFacturas);
        FacturaAdapter facturaAdapter = new FacturaAdapter(getFacturas(persona.getEquipoCelular()));
        recyclerFacturas.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerFacturas.setAdapter(facturaAdapter);
        facturaAdapter.notifyDataSetChanged();
    }

    private ArrayList<Factura> getFacturas(ArrayList<EquipoCelular> equipos) {
        ArrayList<Factura> result = new ArrayList<>();
        for (EquipoCelular equipo : equipos) {
            if (equipo.getFacturas() != null) {
                result.addAll(equipo.getFacturas());
            }
        }
        return result;
    }
}
