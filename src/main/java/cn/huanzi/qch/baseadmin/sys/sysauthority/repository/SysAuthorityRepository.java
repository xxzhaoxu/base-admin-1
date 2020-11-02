package cn.huanzi.qch.baseadmin.sys.sysauthority.repository;

import cn.huanzi.qch.baseadmin.common.repository.*;
import cn.huanzi.qch.baseadmin.consumer.entity.SysConsumer;
import cn.huanzi.qch.baseadmin.sys.sysauthority.pojo.SysAuthority;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysAuthorityRepository extends CommonRepository<SysAuthority, String> {
    @Query(value = "select * from  sys_authority where authority_name = ?1",nativeQuery=true)
    SysAuthority findAllByAuthorityName(String name);
}
