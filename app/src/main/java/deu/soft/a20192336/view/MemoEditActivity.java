package deu.soft.a20192336.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import dagger.hilt.android.AndroidEntryPoint;
import deu.soft.a20192336.databinding.ActivityMemoEditBinding;

@AndroidEntryPoint
public class MemoEditActivity extends AppCompatActivity {

    ActivityMemoEditBinding binding;
    MemoEditViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMemoEditBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(MemoEditViewModel.class);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);

        viewModel.memoId = getIntent().getIntExtra("memoId", 0);
        viewModel.title.setValue(getIntent().getStringExtra("memoTitle"));
        viewModel.content.setValue(getIntent().getStringExtra("memoContent"));


        binding.buttonCancelMemo.setOnClickListener(v -> {
            setResult(RESULT_CANCELED);
            finish();
        });

        binding.buttonSaveMemo.setOnClickListener(v -> {
            viewModel.saveMemo();
            setResult(RESULT_OK);
            finish();
        });
    }




}