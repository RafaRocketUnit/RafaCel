package com.rafaapp.rafacel.ui.consultar;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.rafaapp.rafacel.Utils;
import com.rafaapp.rafacel.databinding.FragmentConsultarBinding;
import com.rafaapp.rafacel.model.Persona;
import com.rafaapp.rafacel.ui.DetalleUsuario.FacturaAdapter;

import java.util.Calendar;
import java.util.Date;

public class ConsultarFragment extends Fragment {

    private ConsultarViewModel consultarViewModel;
    private FragmentConsultarBinding binding;
    private Persona persona;
    private Persona personaExtra = new Persona();
    private Date input;
    private Date output;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        consultarViewModel =
                new ViewModelProvider(this).get(ConsultarViewModel.class);

        binding = FragmentConsultarBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        personaExtra.setPerNombre("Todos");

        binding.usuario.setOnClickListener( v -> {
            Utils.showUsuarios(requireContext(),
                    "Seleccione un usuario",
                    personaExtra,
                    Utils.getPersonasFromDatabase(Utils.getPersonasJsonArray(requireContext())),
                    msg -> {
                        persona = (Persona) msg.obj;
                        if (persona.getPerNombre().equals(personaExtra.getPerNombre())) {
                            persona = null;
                            binding.usuario.setText(personaExtra.toString());
                        } else {
                            binding.usuario.setText(persona.toString());
                        }
                        return true;
                    });
        });

        binding.dateInput.setOnClickListener( v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(requireContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(year, month, dayOfMonth);
                    input = calendar.getTime();
                    binding.dateInput.setText(dayOfMonth+"-"+month+"-"+year);
                }
            }, 0,0,0);

            datePickerDialog.getDatePicker().setMinDate(1502536000000L);
            datePickerDialog.getDatePicker().setMaxDate(Calendar.getInstance().getTimeInMillis());
            datePickerDialog.getDatePicker().setCalendarViewShown(false);
            datePickerDialog.setTitle("Fecha de inicio");
            datePickerDialog.show();
        });

        binding.dateOutput.setOnClickListener( v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(requireContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(year, month, dayOfMonth);
                    output = calendar.getTime();
                    binding.dateOutput.setText(dayOfMonth+"-"+month+"-"+year);
                }
            }, 0,0,0);

            datePickerDialog.setTitle("Fecha de final");
            if ( input != null) {
                datePickerDialog.getDatePicker().setMinDate(input.getTime());
            }
            datePickerDialog.getDatePicker().setMaxDate(Calendar.getInstance().getTimeInMillis());
            datePickerDialog.show();
        });

        binding.consultarEjecutar.setOnClickListener( v -> {
            if (input != null && output != null) {
                if (persona != null) {
                    consultarViewModel.getFacturas(persona, input, output).observe(getViewLifecycleOwner(), facturas -> {
                        if (facturas.isEmpty()) {
                            Toast.makeText(requireContext(), "No hay facturas en ese rango de fechas", Toast.LENGTH_LONG).show();
                        } else {
                            binding.recyclerConsultar.setLayoutManager(new LinearLayoutManager(requireContext()));
                            FacturaAdapter facturaAdapter = new FacturaAdapter(facturas);
                            binding.recyclerConsultar.setAdapter(facturaAdapter);
                            facturaAdapter.notifyDataSetChanged();
                        }
                    });
                } else {
                    consultarViewModel.getFacturas(requireContext(), input, output).observe(getViewLifecycleOwner(), facturas -> {
                        if (facturas.isEmpty()) {
                            Toast.makeText(requireContext(), "No hay facturas en ese rango de fechas", Toast.LENGTH_LONG).show();
                        } else {
                            binding.recyclerConsultar.setLayoutManager(new LinearLayoutManager(requireContext()));
                            FacturaAdapter facturaAdapter = new FacturaAdapter(facturas);
                            binding.recyclerConsultar.setAdapter(facturaAdapter);
                            facturaAdapter.notifyDataSetChanged();
                        }
                    });
                }
            } else {
                Toast.makeText(requireContext(), "Debe elegir las fechas a consultar", Toast.LENGTH_LONG).show();
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