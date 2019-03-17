package com.scujcc.videoplayer;

public class TV {
    private int iconId;
    private String tvTitle;


    public TV(int iconId, String tvTitle) {
        this.iconId = iconId;
        this.tvTitle = tvTitle;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public String getTvTitle() {
        return tvTitle;
    }

    public void setTvTitle(String tvTitle) {
        this.tvTitle = tvTitle;
    }

    public int setIconId() {
        return 0;
    }
}
