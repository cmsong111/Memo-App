package deu.soft.a20192336.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import dagger.hilt.android.AndroidEntryPoint;
import deu.soft.a20192336.databinding.FragmentMemoCreateBinding;


@AndroidEntryPoint
public class MemoCreateFragment extends Fragment {
    FragmentMemoCreateBinding binding;
    MemoCreateViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(this).get(MemoCreateViewModel.class);
        binding = FragmentMemoCreateBinding.inflate(inflater, container, false);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);

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