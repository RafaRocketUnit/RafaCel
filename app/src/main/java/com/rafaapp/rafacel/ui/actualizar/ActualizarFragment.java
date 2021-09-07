package com.rafaapp.rafacel.ui.actualizar;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.rafaapp.rafacel.R;
import com.rafaapp.rafacel.databinding.FragmentActualizarBinding;
import com.rafaapp.rafacel.model.Persona;

public class ActualizarFragment extends Fragment {

    private ActualizarViewModel actualizarViewModel;
    private FragmentActualizarBinding binding;
    private ActualizarAdapter actualizarAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        actualizarViewModel =
                new ViewModelProvider(this).get(ActualizarViewModel.class);

        binding = FragmentActualizarBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        actualizarViewModel.setActualizar(requireContext()).observe(getViewLifecycleOwner(), personas -> {
            actualizarAdapter = new ActualizarAdapter(personas, msg -> {
                Bundle bundle = new Bundle();
                bundle.putString("persona", new Gson().toJson((Persona) msg.obj));
                Navigation.findNavController(this.getView()).navigate(R.id.action_actualizar_to_detalle, bundle);
                return true;
            });
            binding.recycler.setAdapter(actualizarAdapter);
            actualizarAdapter.notifyDataSetChanged();
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}