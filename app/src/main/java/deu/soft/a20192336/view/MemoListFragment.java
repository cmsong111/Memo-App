package deu.soft.a20192336.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

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

        binding.listViewMemoList.setAdapter(new ArrayAdapter<String>(
                requireContext(),
                android.R.layout.simple_list_item_1,
                new String[]{"메모1", "메모2", "메모3", "메모4", "메모5", "메모6", "메모7", "메모8", "메모9", "메모10",
                        "메모11", "메모12", "메모13", "메모14", "메모15", "메모16", "메모17", "메모18", "메모19", "메모20",
                        "메모21", "메모22", "메모23", "메모24", "메모25", "메모26", "메모27", "메모28", "메모29", "메모30"}
        ));

        binding.listViewMemoList.setOnItemClickListener((parent, view1, position, id) -> {
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