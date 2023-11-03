/**
 * 개인 프로젝트 - MemoCreateActivity.java
 *
 * @Author : 컴퓨터소프트웨어공학 김남주
 * @Email : cmsong111@naver.com
 */
package deu.soft.a20192336.view;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.Toast;

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
            if (viewModel.title.getValue() == null || viewModel.title.getValue().isEmpty()) {
                Toast.makeText(this, "메모의 '제목'란이 비워졌습니다.", Toast.LENGTH_SHORT).show();
                return;
            }
            if (viewModel.content.getValue() == null || viewModel.content.getValue().isEmpty()) {
                Toast.makeText(this, "메모의 '내용'란이 비워졌습니다.", Toast.LENGTH_SHORT).show();
                return;
            }
            viewModel.saveMemo();
            setResult(RESULT_OK);
            finish();
        });

        binding.buttonCancelMemo.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("메모 작성을 취소하시겠습니까?");
            builder.setPositiveButton("예", (dialog, which) -> {
                setResult(RESULT_CANCELED);
                finish();
            });
            builder.setNegativeButton("아니오", (dialog, which) -> {
            });
            builder.show();
        });
    }
}