package com.scujcc.videoplayer;

import android.net.Uri;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;


public class ExoPlayerActivity extends AppCompatActivity {
    private SimpleExoPlayer player;
    private PlayerView playerView;
    private DataSource.Factory factory;
    private String userAgent;
    private MediaSource mediaSource;
    private String TAG = "videoPlayer";

    private String tvUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exo_player_view);
        userAgent = Util.getUserAgent(this, "videoPlayer");
        playerView =findViewById(R.id.playerView);

        tvUrl = getIntent().getStringExtra("tvUrl");
        Log.i("收到的URL",tvUrl);
    }
    @Override
    protected void onStart() {
        super.onStart();
        initPlayer();
        if (playerView != null) {
            playerView.onResume();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (player == null) {
            initPlayer();
            if (playerView != null) {
                playerView.onResume();
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (playerView != null) {
            playerView.onPause();
        }
        releasePlayer();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (playerView != null) {
            playerView.onPause();
        }
        releasePlayer();
    }

    private void initPlayer(){
        if (player == null){
            player = ExoPlayerFactory.newSimpleInstance(this);
            player.addListener(new MyEventListener());
            player.setPlayWhenReady(true);
            playerView.setPlayer(player);
            factory = new DefaultDataSourceFactory(this, userAgent);



            mediaSource = new HlsMediaSource.Factory(factory)
                    .createMediaSource(Uri.parse(tvUrl));
        }

        player.prepare(mediaSource);

    }
    private void releasePlayer() {
        if (player != null) {
            player.release();
            player = null;
            mediaSource = null;
        }
    }

    class MyEventListener implements Player.EventListener {
        @Override
        public void onLoadingChanged(boolean isLoading) {
            Log.d(TAG, "onLoadingChanged isLoading=" + isLoading);
        }

        @Override
        public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
            switch (playbackState) {
                case Player.STATE_BUFFERING:
                    Log.d(TAG, "正在缓冲...");
                    break;
                case Player.STATE_READY:
                    Log.d(TAG, "缓冲完成，可以播放了...");
                    break;
                case Player.STATE_IDLE:
                    Log.d(TAG, "闲置状态，无事可干...");
                    break;
                case Player.STATE_ENDED:
                    Log.d(TAG, "已经结束了。");
                    break;
                default:
                    Log.d(TAG, "无效状态:" + playbackState);
            }
            Log.d(TAG, "onPlayerStateChanged playWhenReady=" + playWhenReady);
        }

        @Override
        public void onPlayerError(ExoPlaybackException error) {
            Log.e(TAG, "onPlayerError 出错了，再次准备播放" + error);
            initPlayer();
        }

        @Override
        public void onPositionDiscontinuity(int reason) {
            Log.d(TAG, "onPositionDiscontinuity reason=" + reason);
        }

        @Override
        public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
            Log.d(TAG, "onPlaybackParametersChanged playbackParameters=" + playbackParameters);
        }
    }
}
