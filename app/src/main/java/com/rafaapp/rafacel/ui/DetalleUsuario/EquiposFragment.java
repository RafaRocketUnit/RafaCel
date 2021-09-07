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
import com.rafaapp.rafacel.model.Persona;

import org.jetbrains.annotations.NotNull;

public class EquiposFragment extends Fragment {

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
        return inflater.inflate(R.layout.equipos, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerEquipos = (RecyclerView) view.findViewById(R.id.recyclerEquipos);

        EquipoAdapter equipoAdapter = new EquipoAdapter(persona.getEquipoCelular());
        recyclerEquipos.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerEquipos.setAdapter(equipoAdapter);
        equipoAdapter.notifyDataSetChanged();
    }
}
