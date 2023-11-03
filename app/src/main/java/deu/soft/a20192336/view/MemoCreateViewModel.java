/**
 * 개인 프로젝트 - MemoCreateViewModel.java
 *
 * @Author : 컴퓨터소프트웨어공학 김남주
 * @Email : cmsong111@naver.com
 */
package deu.soft.a20192336.view;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.sql.Timestamp;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import deu.soft.a20192336.data.MemoDao;

@HiltViewModel
public class MemoCreateViewModel extends ViewModel {

    @Inject
    public MemoCreateViewModel(MemoDao memoDao) {
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
