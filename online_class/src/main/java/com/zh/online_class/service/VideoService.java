package com.zh.online_class.service;

import com.zh.online_class.model.entity.Video;
import com.zh.online_class.model.entity.VideoBanner;

import java.util.List;

public interface VideoService {

    List<Video> listVideo();

    List<VideoBanner> listVideoBanner();

    Video findDetailById(int videoId);
}
