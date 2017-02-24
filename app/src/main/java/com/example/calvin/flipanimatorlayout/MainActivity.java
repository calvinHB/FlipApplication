package com.example.calvin.flipanimatorlayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnAdd, btnDel;
    private ListView mListView;
    private MyAdapter mAdapter;

    private List<String> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = (Button) findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(this);
        btnDel = (Button) findViewById(R.id.btn_del);
        btnDel.setOnClickListener(this);

        mListView = (ListView) findViewById(R.id.lv_content);
        initList();
        mAdapter = new MyAdapter(this, mList);
        mListView.setAdapter(mAdapter);
    }

    private void initList() {
        mList = new ArrayList<String>();
        for(int i=0;i<2; i++)
        {
            mList.add("第"+i+"个item");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_add:
                addItem();
                break;

            case R.id.btn_del:
                delItem();
                break;

            default:
                break;
        }
    }

    private void delItem() {
        if(mList.size()>0)
        {
            mList.remove(mList.size()-1);
            mAdapter.setList(mList);
        }
    }

    private void addItem() {
        mList.add("第"+mList.size()+"个item");
        mAdapter.setList(mList);
    }
}
