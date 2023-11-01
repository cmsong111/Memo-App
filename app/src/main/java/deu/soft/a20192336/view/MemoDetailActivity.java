package deu.soft.a20192336.view;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import deu.soft.a20192336.data.MemoDao;
import deu.soft.a20192336.databinding.ActivityMemoDetailBinding;

@AndroidEntryPoint
public class MemoDetailActivity extends AppCompatActivity {

    ActivityMemoDetailBinding binding;

    ActivityResultLauncher<Intent> memoEditActivityLauncher;

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

        memoEditActivityLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    switch (result.getResultCode()) {
                        case RESULT_OK:
                            Toast.makeText(this, "메모가 수정되었습니다.", Toast.LENGTH_SHORT).show();
                            break;
                        case RESULT_CANCELED:
                            break;
                    }
                }
        );

        // 수정
        binding.buttonMemoEdit.setOnClickListener(v -> {
            Intent intent = new Intent(this, MemoEditActivity.class);
            intent.putExtra("memoId", memoId);
            intent.putExtra("memoTitle", binding.getMemo().title);
            intent.putExtra("memoContent", binding.getMemo().content);
            memoEditActivityLauncher.launch(intent);
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