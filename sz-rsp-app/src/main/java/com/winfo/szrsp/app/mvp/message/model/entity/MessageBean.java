package com.winfo.szrsp.app.mvp.message.model.entity;

/**
 * Created by Guan on 2017-10-18.
 */

public class MessageBean {

    private  String title;
    private  boolean isVoice;
    private boolean isRead;
    private String content;
    private String time;

    private String voiceLong;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isVoice() {
        return isVoice;
    }

    public void setVoice(boolean voice) {
        isVoice = voice;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getVoiceLong() {
        return voiceLong;
    }

    public void setVoiceLong(String voiceLong) {
        this.voiceLong = voiceLong;
    }
}
