package com.zh.online_class.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VideoOrderRequest {

    @JsonProperty("video_id")
    private int videoId;

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }

    public int getVideoId() {
        return videoId;
    }
}
