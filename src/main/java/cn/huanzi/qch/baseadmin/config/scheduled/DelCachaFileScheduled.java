package cn.huanzi.qch.baseadmin.config.scheduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class DelCachaFileScheduled {
    @Scheduled(cron = "0 20 5 * * *")
    public void queryPayStatus() {
        File file = new File("D:\\cache");
        if (file.exists()){
            file.delete();
        }
        System.err.println("删除缓存文件成功");
    }
}
