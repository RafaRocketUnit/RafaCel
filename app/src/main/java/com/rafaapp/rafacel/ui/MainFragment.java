package com.rafaapp.rafacel.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.rafaapp.rafacel.R;

import org.jetbrains.annotations.NotNull;

public class MainFragment extends Fragment {

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.consultar).setOnClickListener( v -> {
            Navigation.findNavController(v).navigate(R.id.action_main_to_consultar);
        });

        view.findViewById(R.id.registrar_usuario).setOnClickListener( v -> {
            Navigation.findNavController(v).navigate(R.id.action_main_to_registrar_usuario);
        });

        view.findViewById(R.id.registrar_celular).setOnClickListener( v -> {
            Navigation.findNavController(v).navigate(R.id.action_main_to_registrar_equipo);
        });

        view.findViewById(R.id.actualizar).setOnClickListener( v -> {
            Navigation.findNavController(v).navigate(R.id.action_main_to_actualizar);
        });

    }
}
