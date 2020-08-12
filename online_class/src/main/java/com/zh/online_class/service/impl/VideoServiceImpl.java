package com.zh.online_class.service.impl;

import com.zh.online_class.config.CacheKeyManager;
import com.zh.online_class.model.entity.Video;
import com.zh.online_class.model.entity.VideoBanner;
import com.zh.online_class.mapper.VideoMapper;
import com.zh.online_class.service.VideoService;
import com.zh.online_class.utils.BaseCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class VideoServiceImpl implements VideoService {


    @Autowired
    private VideoMapper videoMapper;


    @Autowired
    private BaseCache baseCache;


    @Override
    public List<Video> listVideo() {

        try {
            Object cacheObj = baseCache.getTenMinuteCache().get(CacheKeyManager.INDEX_VIDEO_LIST, () -> {

                List<Video> videoList = videoMapper.listVideo();

                System.out.println("从数据库中找视频列表");
                return videoList;
            });

            if (cacheObj instanceof List){
                List<Video> videoList = (List<Video>) cacheObj;
                return videoList;
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        //可以返回兜底数据，业务系统降级 --> SpringCloud课程
        return null;
    }


    @Override
    public List<VideoBanner> listVideoBanner() {

        try {
            Object cacheObj = baseCache.getTenMinuteCache().get(CacheKeyManager.INDEX_BANNER_KEY, () -> {

                List<VideoBanner> bannerList = videoMapper.listVideoBanner();

                System.out.println("从数据库中找轮播图列表");
                return bannerList;
            });

            if (cacheObj instanceof List){
                List<VideoBanner> bannerList = (List<VideoBanner>) cacheObj;

                return bannerList;
            }

        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    public Video findDetailById(int videoId) {

        String videoCacheKey = String.format(CacheKeyManager.VIDEO_DETAIL, videoId);

        try {
            Object cacheObj = baseCache.getOneHourCache().get(videoCacheKey, () -> {

                Video video = videoMapper.findDetailById(videoId);
                System.out.println("从数据库中找视频详情列表");

                return video;
            });

            if (cacheObj instanceof Video){
                Video video = (Video) cacheObj;

                return video;
            }

        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return null;
    }
}
