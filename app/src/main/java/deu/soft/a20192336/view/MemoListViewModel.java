package deu.soft.a20192336.view;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import deu.soft.a20192336.data.Memo;
import deu.soft.a20192336.data.MemoDao;

@HiltViewModel
public class MemoListViewModel extends ViewModel {
    MemoDao memoDao;

    @Inject
    public MemoListViewModel(MemoDao memoDao) {
        this.memoDao = memoDao;
    }

    public LiveData<List<Memo>> getMemoList() {
        return memoDao.getAll();
    }

    public void deleteMemo(int memoId) {
        new Thread(() -> {
            memoDao.delete(memoId);
        }).start();
    }
}
