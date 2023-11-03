/**
 * 개인 프로젝트 - ListViewAdaptor.java
 *
 * @Author : 컴퓨터소프트웨어공학 김남주
 * @Email : cmsong111@naver.com
 */
package deu.soft.a20192336.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.lifecycle.LiveData;

import java.util.Collections;
import java.util.List;

import deu.soft.a20192336.R;
import deu.soft.a20192336.data.Memo;

public class ListViewAdaptor extends BaseAdapter {
    private List<Memo> memoList = Collections.emptyList();

    // LiveData를 Observe하면서 데이터가 변경될 때마다 ListViewAdaptor의 memoList를 갱신한다.
    public ListViewAdaptor(LiveData<List<Memo>> memoListLiveData) {
        memoListLiveData.observeForever(memos -> {
            memoList = memos;
            notifyDataSetChanged();
        });
    }

    public void setSearchResult(LiveData<List<Memo>> memoListLiveData) {
        memoListLiveData.observeForever(memos -> {
            memoList = memos;
            notifyDataSetChanged();
        });
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return memoList.size();
    }

    @Override
    public Object getItem(int position) {
        return memoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return memoList.get(position).id;
    }

    // R.layout.list_tile
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_tile, parent, false);
        }
        TextView memoTitle = convertView.findViewById(R.id.textView_memo_title);
        TextView memoCreatedAt = convertView.findViewById(R.id.textView_memo_create_at);

        memoTitle.setText(memoList.get(position).title);
        memoCreatedAt.setText(memoList.get(position).createdAt);

        return convertView;

    }
}
