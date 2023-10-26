package deu.soft.a20192336.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import deu.soft.a20192336.databinding.FragmentMemoListBinding;


public class MemoListFragment extends Fragment {

    FragmentMemoListBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMemoListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 메모 자세히 보기
        binding.buttonMemoView.setOnClickListener(v -> {
            NavHostFragment.findNavController(this).navigate(
                    MemoListFragmentDirections.actionMenoListToMemoView()
            );
        });

        // 새로 만들기
        binding.floatingActionButton.setOnClickListener(v -> {
            NavHostFragment.findNavController(this).navigate(
                    MemoListFragmentDirections.actionMenoListToNewMemo()
            );
        });
    }

}