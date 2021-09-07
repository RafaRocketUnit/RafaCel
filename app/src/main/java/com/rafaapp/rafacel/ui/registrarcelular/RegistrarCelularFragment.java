package com.rafaapp.rafacel.ui.registrarcelular;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.rafaapp.rafacel.Utils;
import com.rafaapp.rafacel.controller.OperacionesImpl;
import com.rafaapp.rafacel.databinding.RegistrarCelularBinding;
import com.rafaapp.rafacel.model.EquipoCelular;
import com.rafaapp.rafacel.model.Linea;
import com.rafaapp.rafacel.model.Persona;

import java.util.ArrayList;

public class RegistrarCelularFragment extends Fragment {

    private RegistrarCelularViewModel registrarViewModel;
    private RegistrarCelularBinding binding;

    private Persona persona;
    private Linea linea;
    private long serial = 0L;
    private EquipoCelular.EquEstado equEstado;
    private OperacionesImpl operaciones = new OperacionesImpl();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        registrarViewModel =
                new ViewModelProvider(this).get(RegistrarCelularViewModel.class);

        binding = RegistrarCelularBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        binding.perIdCelular.setOnClickListener(v -> {
            Utils.showUsuarios(requireContext(),
                    "Seleccione un usuario",
                    null,
                    Utils.getPersonasFromDatabase(Utils.getPersonasJsonArray(requireContext())),
                    msg -> {
                        persona = (Persona) msg.obj;
                        binding.perIdCelular.setText(persona.getPerNombre() + " " +persona.getPerApellido());
                        return true;
                    });
        });

        binding.autogenerarLinea.setOnClickListener(v -> {
            if (persona != null) {
                linea = Utils.createLinea(requireContext(), persona);
                binding.lineaCelular.setText(linea.getLinumerolinea()+"");
            } else {
                Toast.makeText(requireContext(),"Debe elegir a un usuario primero", Toast.LENGTH_LONG).show();
            }
        });

        binding.autogenerarSerial.setOnClickListener(v -> {
            serial = Utils.nuevoSerial(requireContext());
            binding.equSerial.setText(serial + "");
        });

        binding.estadoCelular.setOnClickListener( v -> {
            ArrayList<EquipoCelular.EquEstado> lineaEstados = new ArrayList<>();
            lineaEstados.add(EquipoCelular.EquEstado.No_Reportado);
            lineaEstados.add(EquipoCelular.EquEstado.Reportado);
            Utils.showEstados(requireContext(), "Seleccione un estado", lineaEstados, new Handler.Callback() {
                @Override
                public boolean handleMessage(@NonNull Message msg) {
                    equEstado = (EquipoCelular.EquEstado)msg.obj;
                    binding.estadoCelular.setText(equEstado.name());
                    return true;
                }
            });
        });

        binding.registrarCelular.setOnClickListener(v -> {

            if (!Utils.existNumeroCelularInDataBase(requireContext(), linea.getLinumerolinea())
                    && !Utils.existSerialInDataBase(requireContext(), serial)) {
                registrarViewModel.createEquipo(persona.perId,
                        linea,
                        serial,
                        binding.marcaCelular.getText().toString(),
                        binding.descripcionCelular.getText().toString(),
                        equEstado
                ).observe(getViewLifecycleOwner(), equipoCelular -> {

                    Toast.makeText(requireContext(), operaciones.registroCelular(requireContext(), persona, equipoCelular)
                            ? "Exitoso": "No Exitoso",Toast.LENGTH_LONG).show();
                });
            } else {
                Toast.makeText(requireContext(), "Ese equipo ya fue registrado en el sistema",Toast.LENGTH_LONG).show();
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}