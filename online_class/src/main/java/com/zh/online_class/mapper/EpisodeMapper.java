package com.zh.online_class.mapper;

import com.zh.online_class.model.entity.Episode;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EpisodeMapper {

    Episode findFirstEpisodeByVideoId(@Param("video_id") int videoId);

}
