package cn.huanzi.qch.baseadmin.consumer.service.impl;

import cn.huanzi.qch.baseadmin.common.service.CommonServiceImpl;
import cn.huanzi.qch.baseadmin.consumer.entity.SysConsumer;
import cn.huanzi.qch.baseadmin.consumer.service.SysConsumerService;
import cn.huanzi.qch.baseadmin.consumer.vo.SysConsumerVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author create by zhaoxu
 * @create 2020/10/20
 */
@Service
@Transactional
public class SysConsumerServiceImpl extends CommonServiceImpl<SysConsumerVO, SysConsumer, String> implements SysConsumerService {

}
