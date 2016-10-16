package com.liuwan.search;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;

import com.liuwan.search.util.SearchAdapter;


public class MainActivity extends Activity implements View.OnClickListener {

    private LinearLayout empty;
    private AutoCompleteTextView search;
    private String[] str = {"大大大", "大大小", "大小大", "大小小", "小大大", "小大小", "小大小", "小小小"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        empty = (LinearLayout) findViewById(R.id.empty);
        empty.setOnClickListener(this);
        search = (AutoCompleteTextView) findViewById(R.id.search);
        // 自动提示适配器
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, str);
        // 支持拼音检索
        SearchAdapter<String> adapter = new SearchAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, str, SearchAdapter.ALL);
        search.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.empty:
                search.setText("");
                break;
        }
    }

}
