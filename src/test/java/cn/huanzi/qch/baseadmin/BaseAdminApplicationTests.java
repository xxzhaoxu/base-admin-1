package cn.huanzi.qch.baseadmin;

import cn.huanzi.qch.baseadmin.consumer.entity.SysConsumer;
import cn.huanzi.qch.baseadmin.consumer.service.SysConsumerService;
import cn.huanzi.qch.baseadmin.consumer.vo.SysConsumerVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BaseAdminApplicationTests {

    @Autowired
    private SysConsumerService sysConsumerService;
    @Test
    public void contextLoads() {
//        System.out.println("213132");
//        SysConsumerVO sysConsumer = new SysConsumerVO();
//        sysConsumer.setCName("哈哈哈");
////        sysConsumerService.save(sysConsumer);
//        sysConsumerService.save(sysConsumer);
    }

}
