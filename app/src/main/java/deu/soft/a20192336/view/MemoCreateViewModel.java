package deu.soft.a20192336.view;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import dagger.hilt.android.qualifiers.ApplicationContext;
import deu.soft.a20192336.data.Memo;
import deu.soft.a20192336.data.MemoDao;

@HiltViewModel
public class MemoCreateViewModel extends ViewModel {

    @Inject
    public MemoCreateViewModel(MemoDao memoDao) {
        Log.d("MemoCreateViewModel", "MemoCreateViewModel was created");
        this.memoDao = memoDao;
    }

    MemoDao memoDao;

    public MutableLiveData<String> title = new MutableLiveData<>();
    public MutableLiveData<String> content = new MutableLiveData<>();

    public void setTitle(String title) {
        this.title.setValue(title);
    }

    public void setContent(String content) {
        this.content.setValue(content);
    }

    public void saveMemo() {
        String now = new Timestamp(System.currentTimeMillis()).toString();
        // Use new Thread to save memo
        new Thread(() -> {
            memoDao.save(title.getValue(), content.getValue(), now, now);
        }).start();

    }
}
