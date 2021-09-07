package com.rafaapp.rafacel.ui.registrar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.rafaapp.rafacel.Utils;
import com.rafaapp.rafacel.controller.OperacionesImpl;
import com.rafaapp.rafacel.databinding.FragmentRegistrarBinding;

import java.util.Calendar;

public class RegistrarFragment extends Fragment {

    private RegistrarViewModel registrarViewModel;
    private FragmentRegistrarBinding binding;

    private final  OperacionesImpl operaciones = new OperacionesImpl();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        registrarViewModel =
                new ViewModelProvider(this).get(RegistrarViewModel.class);

        binding = FragmentRegistrarBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.fechaNacimiento.setMinDate(-888641999000L);
        binding.fechaNacimiento.setMaxDate(973278001000L);

        binding.buttonGuardar.setOnClickListener(v -> {
            registrarViewModel.createPersona(requireContext(),
                    binding.nombre.getText().toString(),
                    binding.apellido.getText().toString(),
                    binding.Cedula.getText().toString(),
                    binding.telefonoFijo.getText().toString(),
                    Utils.getDateFromDatePicker(binding.fechaNacimiento),
                    "Motorola",
                    "2021").observe(getViewLifecycleOwner(), persona -> {
                Toast.makeText(requireContext(), operaciones.registro(requireContext(), persona) ? "Exitoso": "No Exitoso",Toast.LENGTH_LONG).show();
            });
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}