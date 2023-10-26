package deu.soft.a20192336.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import deu.soft.a20192336.R;
import deu.soft.a20192336.databinding.FragmentNewMemoBinding;


public class new_memo extends Fragment {


    FragmentNewMemoBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNewMemoBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 홈 그래프까지 가는 코드
        binding.newMemoSave.setOnClickListener(v -> {
            Navigation.findNavController(v).popBackStack(R.id.nav_graph, true);
        });
    }


}