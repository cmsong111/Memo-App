package deu.soft.a20192336.view;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import deu.soft.a20192336.data.Memo;
import deu.soft.a20192336.data.MemoDao;
import deu.soft.a20192336.databinding.FragmentMemoDetailBinding;

@AndroidEntryPoint
public class MemoDetailFragment extends Fragment {

    FragmentMemoDetailBinding binding;

    @Inject
    MemoDao memoDao;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMemoDetailBinding.inflate(inflater, container, false);
        binding.setLifecycleOwner(this);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 메모 불러오기
        int memoId = MemoDetailFragmentArgs.fromBundle(getArguments()).getMemoIdx();
        memoDao.findById(memoId).observe(getViewLifecycleOwner(), memo1 -> {
            binding.setMemo(memo1);
        });

        // 수정
        binding.buttonMemoEdit.setOnClickListener(v -> {
            NavHostFragment.findNavController(this).navigate(
                    MemoDetailFragmentDirections.actionMemoViewToMemoForm2()
            );
        });

        // 삭제
        binding.buttonMemoDelete.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("메모 삭제");
            builder.setMessage("메모를 삭제하시겠습니까?");
            builder.setPositiveButton("확인", (dialog, which) -> {
                new Thread(() -> {
                    memoDao.delete(memoId);
                }).start();
                dialog.dismiss();
                NavHostFragment.findNavController(this).navigateUp();
            });
            builder.setNegativeButton("취소", (dialog, which) -> {
                dialog.dismiss();
            });
            builder.show();


        });

        // 뒤로가기
        binding.buttonMemoBack.setOnClickListener(v -> {
            NavHostFragment.findNavController(this).navigateUp();
        });
    }
}