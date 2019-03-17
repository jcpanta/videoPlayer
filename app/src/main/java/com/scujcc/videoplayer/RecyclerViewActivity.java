package com.scujcc.videoplayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.scujcc.videoplayer.R.drawable.tv1;

public class RecyclerViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private String[] TVNames;
    private List<TV> mTVList = new ArrayList<TV>();
    private MyRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        recyclerView = findViewById(R.id.recyclerView);



        //视频信息集合
        TVNames = getResources().getStringArray(R.array.TVNames);

        for (int i = 0;i<100;i++)
        {
            TV tv1 = new TV(R.drawable.tv1,TVNames[0]);
            mTVList.add(tv1);

            TV tv2 = new TV(R.drawable.tv1,TVNames[1]);
            mTVList.add(tv2);

            TV tv3 = new TV(R.drawable.tv1,TVNames[2]);
            mTVList.add(tv3);

            TV tv4 = new TV(R.drawable.tv1,TVNames[3]);
            mTVList.add(tv4);
        }



        //recyclerView的适配器 adapter
        adapter = new MyRecyclerViewAdapter(mTVList);
        adapter.setOnItemClickListener(new MyRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getApplicationContext(),
                        "click: " + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(getApplicationContext(),
                        "long click: " + position, Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));




        //LayoutMAnager

    }
}
