package com.hyn.swiperefreshlayout;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends ActionBarActivity implements SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView listView;
    private SimpleAdapter adapter;
    private List<HashMap<String, String>> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        swipeRefreshLayout.setOnRefreshListener(this);
//        swipeRefreshLayout.setColorScheme(android.R.color.holo_green_light,
//                android.R.color.holo_blue_bright);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright);
        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(android.R.color.holo_orange_light);
        list = new ArrayList<HashMap<String, String>>();
        listView = (ListView) findViewById(R.id.listview);
        for (int i = 0; i < 10; i++) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("name", "第" + i + "个item");
            list.add(map);
        }
        adapter = new SimpleAdapter(this, list, R.layout.layout_item, new String[]{"name"}, new int[]{R.id.list_item});
        listView.setAdapter(adapter);
    }

    @Override
    public void onRefresh() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put("name", "刷新第" + i + "个item");
                    list.add(0, map);
                }
                adapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 2000);
    }
}
