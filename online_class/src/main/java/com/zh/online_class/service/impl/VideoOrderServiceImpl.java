package com.zh.online_class.service.impl;

import com.zh.online_class.exception.XDException;
import com.zh.online_class.mapper.*;
import com.zh.online_class.model.entity.Episode;
import com.zh.online_class.model.entity.PlayRecord;
import com.zh.online_class.model.entity.Video;
import com.zh.online_class.model.entity.VideoOrder;
import com.zh.online_class.service.VideoOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;


@Service
public class VideoOrderServiceImpl implements VideoOrderService {


    @Autowired
    private VideoOrderMapper videoOrderMapper;



    @Autowired
    private VideoMapper videoMapper;


    @Autowired
    private EpisodeMapper episodeMapper;
    
    
    @Autowired
    private PlayRecordMapper playRecordMapper;


    /**
     * 下单操作
     * 未来版本：优惠券抵扣，风控用户检查，生成订单基础信息，生成支付信息
     * @param userId
     * @param videoId
     * @return
     */
    @Override
    @Transactional  //针对需要事务的方法，添加注解
    public int save(int userId, int videoId) {
        //判断是否已经购买
        VideoOrder videoOrder = videoOrderMapper.findByUserIdAndVideoIdAndState(userId,videoId,1);

        if(videoOrder!=null){return  0;}

        Video video = videoMapper.findById(videoId);

        VideoOrder newVideoOrder = new VideoOrder();
        newVideoOrder.setCreateTime(new Date());
        newVideoOrder.setOutTradeNo(UUID.randomUUID().toString());
        newVideoOrder.setState(1);
        newVideoOrder.setTotalFee(video.getPrice());
        newVideoOrder.setUserId(userId);

        newVideoOrder.setVideoId(videoId);
        newVideoOrder.setVideoImg(video.getCoverImg());
        newVideoOrder.setVideoTitle(video.getTitle());

        int rows = videoOrderMapper.saveOrder(newVideoOrder);

//        int i = 1/0;
        //生成播放记录
        if (rows == 1){
            Episode episode = episodeMapper.findFirstEpisodeByVideoId(videoId);
            if (episode == null){
                throw new XDException(-1,"视频没有集信息，请运营人员检测");
            }

            PlayRecord playRecord = new PlayRecord();

            playRecord.setCreateTime(new Date());
            playRecord.setEpisodeId(episode.getId());
            playRecord.setCurrentNum(episode.getNum());
            playRecord.setUserId(userId);
            playRecord.setVideoId(videoId);


            playRecordMapper.saveRecord(playRecord);
        }

        return rows;
    }



    @Override
    public List<VideoOrder> listOrderByUserId(Integer userId) {

        return videoOrderMapper.listOrderByUserId(userId);
    }
}
