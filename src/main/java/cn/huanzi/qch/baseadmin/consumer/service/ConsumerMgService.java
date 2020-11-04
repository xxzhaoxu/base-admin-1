package cn.huanzi.qch.baseadmin.consumer.service;

import cn.huanzi.qch.baseadmin.common.pojo.Result;
import cn.huanzi.qch.baseadmin.consumer.entity.ConsumerMg;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface ConsumerMgService {
    ConsumerMg findConsumerMgByPhone(String phone);
    ConsumerMg findConsumerMgById(String id);
    void saveConsumerMg(ConsumerMg consumerMg);
    int updateConsumerMg(ConsumerMg consumerMg);

    JSONObject findConsumerMgByEntity(ConsumerMg consumerMg, Integer start, Integer end);
    List<ConsumerMg> findConsumerMgByEntity(ConsumerMg consumerMg);
    void delete(String phone);
}
