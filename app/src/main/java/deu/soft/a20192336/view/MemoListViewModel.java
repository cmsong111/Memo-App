package deu.soft.a20192336.view;

import android.util.Log;
import android.widget.ArrayAdapter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
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
