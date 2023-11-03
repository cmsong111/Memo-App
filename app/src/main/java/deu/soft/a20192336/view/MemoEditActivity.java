/**
 * 개인 프로젝트 - MemoEditActivity.java
 *
 * @Author : 컴퓨터소프트웨어공학 김남주
 * @Email : cmsong111@naver.com
 */
package deu.soft.a20192336.view;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
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
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("현재 수정하는 메모를 취소하시겠습니까?");
            builder.setPositiveButton("예", (dialog, which) -> {
                setResult(RESULT_CANCELED);
                finish();
            });
            builder.setNegativeButton("아니오", (dialog, which) -> {
            });
            builder.show();
        });

        binding.buttonSaveMemo.setOnClickListener(v -> {
            if (viewModel.title.getValue() == null || viewModel.title.getValue().isEmpty()) {
                Toast.makeText(this, "메모의 '제목'란이 비워졌습니다.", Toast.LENGTH_SHORT).show();
                return;
            }
            if (viewModel.content.getValue() == null || viewModel.content.getValue().isEmpty()) {
                Toast.makeText(this, "메모의 '내용'란이 비워졌습니다.", Toast.LENGTH_SHORT).show();
                return;
            }

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("정말 " + String.valueOf(viewModel.memoId) + "번 메모를 수정하시겠습니까?");
            builder.setPositiveButton("예", (dialog, which) -> {
                viewModel.saveMemo();
                setResult(RESULT_OK);
                finish();
            });
            builder.setNegativeButton("아니오", (dialog, which) -> {
            });
            builder.show();
        });
    }

    long backKeyPressedTime = 0L;
    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            Toast.makeText(this, "메모 수정을 취소 하시려면 한번 더 눌러주세요.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            setResult(RESULT_CANCELED);
            finish();
        }
    }
}