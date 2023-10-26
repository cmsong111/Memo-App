package deu.soft.a20192336.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import deu.soft.a20192336.databinding.FragmentMemoCreateBinding;


public class MemoCreateFragment extends Fragment {


    FragmentMemoCreateBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMemoCreateBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        binding.buttonSaveMemo.setOnClickListener(v -> {
            NavHostFragment.findNavController(this).navigateUp();
        });

        binding.buttonCancelMemo.setOnClickListener(v -> {
            NavHostFragment.findNavController(this).navigateUp();
        });
    }


}