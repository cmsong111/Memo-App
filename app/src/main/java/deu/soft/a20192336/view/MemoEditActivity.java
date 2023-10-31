package deu.soft.a20192336.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import dagger.hilt.android.AndroidEntryPoint;
import deu.soft.a20192336.databinding.ActivityMemoEditBinding;

@AndroidEntryPoint
public class MemoEditActivity extends AppCompatActivity {

    ActivityMemoEditBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMemoEditBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }


}