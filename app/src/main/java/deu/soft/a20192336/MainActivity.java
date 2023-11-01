/**
 * 개인 프로젝트 - MainActivity.java
 *
 * @Author : 컴퓨터소프트웨어공학 김남주
 * @Email : cmsong111@naver.com
 */
package deu.soft.a20192336;

import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import dagger.hilt.android.AndroidEntryPoint;
import deu.soft.a20192336.data.Memo;
import deu.soft.a20192336.databinding.ActivityMainBinding;
import deu.soft.a20192336.view.ListViewAdaptor;
import deu.soft.a20192336.view.MemoCreateActivity;
import deu.soft.a20192336.view.MemoDetailActivity;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    MainActivityViewModel viewModel;
    ActivityResultLauncher<Intent> memoCreateActivityLauncher;
    ActivityResultLauncher<Intent> memoDetailActivityLauncher;
    SearchManager searchManager;
    SearchView searchView;

    long backKeyPressedTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);



        searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView = (SearchView) binding.toolbarMemoList.getMenu().getItem(0).getActionView();
        if (searchManager != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        }

        // 검색어 입력시
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // 검색어 입력 완료시
            @Override
            public boolean onQueryTextSubmit(String query) {
                binding.getMemoAdapter().setSearchResult(viewModel.findMemoList(query));
                if (binding.getMemoAdapter().getCount() == 0) {
                    Toast.makeText(MainActivity.this, "검색 결과가 없습니다.", Toast.LENGTH_SHORT).show();
                }
                return false;
            }

            // 검색어 입력시
            @Override
            public boolean onQueryTextChange(String newText) {
                binding.getMemoAdapter().setSearchResult(viewModel.findMemoList(newText));
                if (newText.equals("")) {
                    Toast.makeText(MainActivity.this, "검색 단어가 없습니다.", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
        // 메모 View 어탭터 설정
        binding.setMemoAdapter(new ListViewAdaptor(viewModel.getMemoList()));


        // 메모 추가 Activity 실행
        memoCreateActivityLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Toast.makeText(this, "메모가 추가되었습니다.", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        memoDetailActivityLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                }
        );


        binding.toolbarMemoList.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.developer_info) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("개발자 정보");
                builder.setMessage(Html.fromHtml("동의대 컴퓨터소프트웨어공학과 김남주<br><a href='cmsong111@naver.com'>cmsong111@naver.com</a>"));
                builder.setPositiveButton("확인", (dialog, which) -> {
                    dialog.dismiss();
                });
                builder.show();
                return true;
            } else if (item.getItemId() == R.id.add_memo) {
                Intent intent = new Intent(this, MemoCreateActivity.class);
                memoCreateActivityLauncher.launch(intent);
                return true;
            }
            return false;
        });

        binding.toolbarMemoList.getMenu().getItem(0).setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(@NonNull MenuItem item) {
                return true;
            }

            // 검색창 닫을 때
            @Override
            public boolean onMenuItemActionCollapse(@NonNull MenuItem item) {
                binding.setMemoAdapter(new ListViewAdaptor(viewModel.getMemoList()));
                return true;
            }
        });

        binding.listViewMemoList.setOnItemLongClickListener((parent, view12, position, id) -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
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

        binding.listViewMemoList.setOnItemClickListener((parent, view1, position, id) -> {
            Memo memo = (Memo) binding.listViewMemoList.getItemAtPosition(position);
            Intent intent = new Intent(this, MemoDetailActivity.class);
            intent.putExtra("memoId", memo.id);
            memoDetailActivityLauncher.launch(intent);
        });

        binding.floatingActionButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, MemoCreateActivity.class);
            memoCreateActivityLauncher.launch(intent);
        });

    }

    /**
     * 뒤로가기 버튼을 두번 누르면 종료되도록 설정
     */
    @Override
    public void onBackPressed() {

        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            Toast.makeText(this, "뒤로가기 버튼을 한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            finish();
        }
        super.onBackPressed();
    }
}