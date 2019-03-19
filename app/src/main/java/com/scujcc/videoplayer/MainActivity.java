package com.scujcc.videoplayer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private String[] TVNames;
    private String[] TVDetial;
    private String[] TVUrl;
    private List<TV> mTVList = new ArrayList<TV>();
    private MyRecyclerViewAdapter adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        recyclerView = findViewById(R.id.recyclerView);

        initVideoDatas();


        //recyclerView的适配器 adapter
        adapter = new MyRecyclerViewAdapter(mTVList);
        //item点击
        adapter.setOnItemClickListener(new MyRecyclerViewAdapter.OnItemClickListener() {
            //单击
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getApplicationContext(),
                        "click: " + position, Toast.LENGTH_SHORT).show();

                String tvUrl =mTVList.get(position).getTvUrl();
                Log.i("传递的视频url",tvUrl);

                Intent intent = new Intent(MainActivity.this, ExoPlayerActivity.class);
                intent.putExtra("tvUrl",tvUrl);
                startActivity(intent);
            }
            //长按
            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(getApplicationContext(),
                        "long click: " + position, Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(adapter);
        //recyclerView布局管理
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

    }
    private void initVideoDatas(){
        //视频信息集合
        TVNames = getResources().getStringArray(R.array.TVNames);
        TVDetial =getResources().getStringArray(R.array.TVDetail);
        TVUrl = getResources().getStringArray(R.array.TVUrl);
        TV cctv1 = new TV(R.drawable.cctv1,TVNames[0],TVDetial[0],TVUrl[0]);
        mTVList.add(cctv1);
        TV ccvt2 = new TV(R.drawable.cctv2,TVNames[1],TVDetial[1],TVUrl[1]);
        mTVList.add(ccvt2);
        TV cctv3 = new TV(R.drawable.cctv3,TVNames[2],TVDetial[2],TVUrl[2]);
        mTVList.add(cctv3);
        TV cctv4 = new TV(R.drawable.cctv4,TVNames[3],TVDetial[3],TVUrl[3]);
        mTVList.add(cctv4);
        TV cctv5 = new TV(R.drawable.cctv5,TVNames[4],TVDetial[4],TVUrl[4]);
        mTVList.add(cctv5);
        TV cctv6 = new TV(R.drawable.cctv6,TVNames[5],TVDetial[5],TVUrl[5]);
        mTVList.add(cctv6);
        TV gameTV = new TV(R.drawable.game,TVNames[6],TVDetial[6],TVUrl[6]);
        mTVList.add(gameTV);
        TV gongfuTV= new TV(R.drawable.gongfu,TVNames[7],TVDetial[7],TVUrl[7]);
        mTVList.add(gongfuTV);

    }


}
