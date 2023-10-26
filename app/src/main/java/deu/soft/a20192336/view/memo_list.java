package deu.soft.a20192336.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import deu.soft.a20192336.R;
import deu.soft.a20192336.databinding.FragmentMenoListBinding;


public class memo_list extends Fragment {

    FragmentMenoListBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMenoListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonMemoView.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_meno_list_to_memo_view);
        });

        binding.buttonNewMemo.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_meno_list_to_new_memo);
        });
    }
}