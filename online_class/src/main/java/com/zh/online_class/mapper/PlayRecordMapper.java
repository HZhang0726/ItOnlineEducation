package com.zh.online_class.mapper;

import com.zh.online_class.model.entity.PlayRecord;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayRecordMapper {

    int saveRecord(PlayRecord playRecord);
}
