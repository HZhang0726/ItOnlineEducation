package com.zh.online_class.mapper;

import com.zh.online_class.model.entity.Video;
import com.zh.online_class.model.entity.VideoBanner;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface VideoMapper {


    /**
     * 查询所有视频列表
     * @return
     */
    List<Video> listVideo();


    /**
     * 首页轮播图列表
     * @return
     */
    List<VideoBanner> listVideoBanner();


    /**
     * 视频详情
     * @param videoId
     * @return
     */
    Video findDetailById(@Param("video_id") int videoId);


    /**
     * 简单查询视频详情
     * @param videoId
     * @return
     */
    Video findById(@Param("video_id") int videoId);
}
