/**
 * 개인 프로젝트 - MemoEditViewModel.java
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
public class MemoEditViewModel extends ViewModel {
    @Inject
    public MemoEditViewModel(MemoDao memoDao) {
        this.memoDao = memoDao;
    }

    MemoDao memoDao;

    public int memoId;
    public MutableLiveData<String> title = new MutableLiveData<>();
    public MutableLiveData<String> content = new MutableLiveData<>();


    public void saveMemo() {
        String now = new Timestamp(System.currentTimeMillis()).toString();
        // Use new Thread to save memo
        new Thread(() -> {
            memoDao.save(memoId, title.getValue(), content.getValue(), now);
        }).start();
    }
}
