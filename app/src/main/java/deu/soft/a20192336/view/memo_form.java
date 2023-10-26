package deu.soft.a20192336.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import deu.soft.a20192336.R;
import deu.soft.a20192336.databinding.FragmentEditMemoBinding;

public class memo_form extends Fragment {

    FragmentEditMemoBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEditMemoBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonMemoSave.setOnClickListener(v -> {
            Navigation.findNavController(v).popBackStack(R.id.nav_graph, true);
        });
    }
}