package com.alias.custompulltorefresh;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.alias.custompulltorefresh.widget.RefreshableView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RefreshableView refreshableView;
    private ListView listView;
    private String[] list = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        refreshableView = findViewById(R.id.refresh_main);
        listView = findViewById(R.id.lv_main);
        MyAdapter myAdapter = new MyAdapter(list);
        listView.setAdapter(myAdapter);
        refreshableView.setOnRefreshListener(new RefreshableView.PullToRefreshListener() {
            @Override
            public void onRefresh() {
                SystemClock.sleep(3000);
                refreshableView.finishRefreshing();
            }
        }, 0);
    }

    class MyAdapter extends BaseAdapter {
        private String[] list;

        public MyAdapter(String[] list) {
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.length;
        }

        @Override
        public Object getItem(int i) {
            return list[i];
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder holder;
            if (view == null) {
                holder = new ViewHolder();
                view = View.inflate(MainActivity.this, R.layout.item_layout, null);
                holder.textView = view.findViewById(R.id.tv_item);
                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }
            holder.textView.setText(list[i].toString());
            return view;
        }
    }

    class ViewHolder {
        TextView textView;
    }
}
