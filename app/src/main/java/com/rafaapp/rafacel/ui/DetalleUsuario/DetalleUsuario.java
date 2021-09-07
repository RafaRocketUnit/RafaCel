package com.rafaapp.rafacel.ui.DetalleUsuario;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import com.google.gson.Gson;
import com.rafaapp.rafacel.R;
import com.rafaapp.rafacel.Utils;
import com.rafaapp.rafacel.controller.OperacionesImpl;
import com.rafaapp.rafacel.model.Persona;

public class DetalleUsuario extends Fragment {
    private Persona persona;
    OperacionesImpl operaciones = new OperacionesImpl();

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.detalle_usuario, container, false);
    }

    @Override
    public void onViewCreated(View rootView, Bundle savedInstanceState) {
        super.onViewCreated(rootView, savedInstanceState);
        TextView nombre = (TextView) rootView.findViewById(R.id.nombre);
        TextView cedula = (TextView) rootView.findViewById(R.id.cedula);
        TextView telefonoFijo = (TextView) rootView.findViewById(R.id.telefonoFijo);
        TextView fechaNacimiento = (TextView) rootView.findViewById(R.id.fechaNacimiento);

        Button eliminar = (Button) rootView.findViewById(R.id.eliminarUsuario);
        Button verEquipos = (Button) rootView.findViewById(R.id.verEquipos);
        Button verFacturas = (Button) rootView.findViewById(R.id.verFacturas);

        if (persona != null) {
            nombre.setText(persona.getPerNombre() + " " + persona.getPerApellido());
            cedula.setText(persona.getPerCedula());
            telefonoFijo.setText(persona.getPerTelefonoFijo());
            fechaNacimiento.setText(persona.getPerFechaNacimiento());

            eliminar.setOnClickListener( v -> {
                Utils.showDialog(requireContext(), "¿Desea eliminar el usuario " + persona.toString() + "?", new Handler.Callback() {
                    @Override
                    public boolean handleMessage(@NonNull Message msg) {
                        if ((boolean)msg.obj) {
                            String texto;
                            if (operaciones.eliminarUsuario(requireContext(), persona)) {
                                texto = "Usuario "+ persona.toString() + " eliminado";
                                Navigation.findNavController(v).navigate(R.id.action_detalle_to_actualizar);
                            } else {
                                texto = "Error al eliminar";
                            }
                            Toast.makeText(requireContext(),texto, Toast.LENGTH_LONG).show();
                        }
                        return true;
                    }
                });
            });

            verEquipos.setOnClickListener( v -> {
                Bundle bundle = new Bundle();
                bundle.putString("persona", new Gson().toJson(persona));
                Navigation.findNavController(v).navigate(R.id.action_detalle_to_equipos, bundle);
            });

            verFacturas.setOnClickListener( v -> {
                Bundle bundle = new Bundle();
                bundle.putString("persona", new Gson().toJson(persona));
                Navigation.findNavController(v).navigate(R.id.action_detalle_to_facturas, bundle);
            });
        }
    }
}
