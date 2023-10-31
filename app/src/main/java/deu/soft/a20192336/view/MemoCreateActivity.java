package deu.soft.a20192336.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import dagger.hilt.android.AndroidEntryPoint;
import deu.soft.a20192336.databinding.ActivityMemoCreateBinding;


@AndroidEntryPoint
public class MemoCreateActivity extends AppCompatActivity {
    ActivityMemoCreateBinding binding;

    MemoCreateViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMemoCreateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(MemoCreateViewModel.class);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);


        binding.buttonSaveMemo.setOnClickListener(v -> {
            viewModel.saveMemo();
            setResult(RESULT_OK);
            finish();
        });

        binding.buttonCancelMemo.setOnClickListener(v -> {
            setResult(RESULT_CANCELED);
            finish();
        });
    }
}