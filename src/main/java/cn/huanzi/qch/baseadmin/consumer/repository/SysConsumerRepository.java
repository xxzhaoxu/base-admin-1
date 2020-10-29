package cn.huanzi.qch.baseadmin.consumer.repository;

import cn.huanzi.qch.baseadmin.common.repository.CommonRepository;
import cn.huanzi.qch.baseadmin.consumer.entity.SysConsumer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author create by zhaoxu
 * @create 2020/10/20
 */
public interface SysConsumerRepository extends CommonRepository<SysConsumer, String>, JpaSpecificationExecutor<SysConsumer> {
    @Query(value = "select * from  sys_consumer where phone = ?1   order by cid desc",nativeQuery=true)
    List<SysConsumer> SelelctSysConsumerbyPhone(String phone);
    @Modifying
    @Query(value = "update sys_consumer set stats = '0' where  phone = ?1 and c_group = ?2",nativeQuery = true)
    int updateSysConsumerStatus(String phone,String group);

    @Modifying
    @Transactional
    @Query(value = "delete from sys_consumer where phone = ?1 and c_group = ?2",nativeQuery = true)
    int deleteAllByPhone(String phone,String group);

    @Query(value = "select * from  sys_consumer limit ?1,?2  ",
            nativeQuery=true
    )
    List<SysConsumer> findAllByParam(int start,int size);


}
