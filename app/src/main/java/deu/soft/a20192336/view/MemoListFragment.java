package deu.soft.a20192336.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import dagger.hilt.android.AndroidEntryPoint;
import deu.soft.a20192336.R;
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


        // 새로 만들기
        binding.floatingActionButton.setOnClickListener(v -> {
            NavHostFragment.findNavController(this).navigate(
                    MemoListFragmentDirections.actionMenoListToNewMemo()
            );
        });

        // 메모 리스트 클릭
        binding.listViewMemoList.setOnItemClickListener((parent, view1, position, id) -> {
            NavHostFragment.findNavController(this).navigate(
                    MemoListFragmentDirections.actionMenoListToMemoView()
            );
        });

        // 메모 리스트 아이템 롱 클릭
        binding.listViewMemoList.setOnItemLongClickListener((parent, view12, position, id) -> {
            Toast.makeText(getContext(), "롱클릭", Toast.LENGTH_SHORT).show();
            return true;
        });

    }


}