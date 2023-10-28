package deu.soft.a20192336.view;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import dagger.hilt.android.AndroidEntryPoint;
import deu.soft.a20192336.R;
import deu.soft.a20192336.data.Memo;
import deu.soft.a20192336.databinding.FragmentMemoListBinding;

@AndroidEntryPoint
public class MemoListFragment extends Fragment {

    FragmentMemoListBinding binding;
    MemoListViewModel viewModel;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMemoListBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(MemoListViewModel.class);

        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);

        // 메모 View 어탭터 설정
        binding.setMemoAdapter(new ListViewAdaptor(viewModel.getMemoList()));

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        binding.toolbarMemoList.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.developer_info) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("개발자 정보");
                builder.setMessage(Html.fromHtml("동의대 컴퓨터소프트웨어공학과 김남주<br><a href='cmsong111@naver.com'>cmsong111@naver.com</a>"));
                builder.setPositiveButton("확인", (dialog, which) -> {
                    dialog.dismiss();
                });
                builder.show();
                return true;
            } else if (item.getItemId() == R.id.add_memo) {
                NavHostFragment.findNavController(this).navigate(
                        MemoListFragmentDirections.actionMenoListToNewMemo()
                );
            }
            return false;
        });

        binding.toolbarMemoList.getMenu().getItem(0).setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                Toast.makeText(getContext(), "Action View Expanded...", Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                Toast.makeText(getContext(), "Action View Collapsed...", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        // Floating Action Button 클릭 - 새 메모 작성
        binding.floatingActionButton.setOnClickListener(v -> {
            NavHostFragment.findNavController(this).navigate(
                    MemoListFragmentDirections.actionMenoListToNewMemo()
            );
        });

        // ListView Item 클릭 - 메모 보기
        binding.listViewMemoList.setOnItemClickListener((parent, view1, position, id) -> {
            Memo memo = (Memo) binding.listViewMemoList.getItemAtPosition(position);
            MemoListFragmentDirections.ActionMenoListToMemoView action = MemoListFragmentDirections.actionMenoListToMemoView();
            action.setMemoIdx(memo.id);
            NavHostFragment.findNavController(this).navigate(action);
        });

        // ListView Item 클릭 - 삭제
        binding.listViewMemoList.setOnItemLongClickListener((parent, view12, position, id) -> {

            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("메모 삭제");
            builder.setMessage("메모를 삭제하시겠습니까?");
            builder.setPositiveButton("확인", (dialog, which) -> {
                Memo memo = (Memo) binding.listViewMemoList.getItemAtPosition(position);
                viewModel.deleteMemo(memo.id);
                dialog.dismiss();
            });
            builder.setNegativeButton("취소", (dialog, which) -> {
                dialog.dismiss();
            });
            builder.show();


            return true;
        });

    }
}