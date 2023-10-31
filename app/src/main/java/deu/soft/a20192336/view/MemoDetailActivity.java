package deu.soft.a20192336.view;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import deu.soft.a20192336.data.MemoDao;
import deu.soft.a20192336.databinding.ActivityMemoDetailBinding;

@AndroidEntryPoint
public class MemoDetailActivity extends AppCompatActivity {

    ActivityMemoDetailBinding binding;

    @Inject
    MemoDao memoDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMemoDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int memoId = getIntent().getIntExtra("memoId", 0);
        // 메모 불러오기
        memoDao.findById(memoId).observe(this, memo -> {
            binding.setMemo(memo);
        });

        // 삭제
        binding.buttonMemoDelete.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("메모 삭제");
            builder.setMessage("메모를 삭제하시겠습니까?");
            builder.setPositiveButton("삭제", (dialog, which) -> {
                new Thread(() -> {
                    memoDao.delete(memoId);
                }).start();
                dialog.dismiss();
                finish();
            });
            builder.setNegativeButton("취소", (dialog, which) -> {
                dialog.dismiss();
            });
            builder.show();
        });

        // 돌아가기
        binding.buttonMemoBack.setOnClickListener(v -> {
            finish();
        });
    }
}