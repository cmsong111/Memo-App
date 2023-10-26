package deu.soft.a20192336.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import deu.soft.a20192336.databinding.FragmentMemoDetailBinding;


public class MemoDetailFragment extends Fragment {

    FragmentMemoDetailBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMemoDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 수정
        binding.buttonMemoEdit.setOnClickListener(v -> {
            NavHostFragment.findNavController(this).navigate(
                    MemoDetailFragmentDirections.actionMemoViewToMemoForm2()
            );
        });

        // 삭제
        binding.buttonMemoDelete.setOnClickListener(v -> {
            NavHostFragment.findNavController(this).navigateUp();
        });

        // 뒤로가기
        binding.buttonMemoBack.setOnClickListener(v -> {
            NavHostFragment.findNavController(this).navigateUp();
        });
    }
}