package deu.soft.a20192336.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import dagger.hilt.android.HiltAndroidApp;
import deu.soft.a20192336.R;
import deu.soft.a20192336.databinding.FragmentMemoViewBinding;


public class memo_view extends Fragment {

    FragmentMemoViewBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMemoViewBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonMemoEdit.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_memo_view_to_memo_form2);
        });
    }
}