package cn.huanzi.qch.baseadmin.consumer.repository;

import cn.huanzi.qch.baseadmin.consumer.entity.UserEntity;

public interface userDao {

      void saveUser(UserEntity user);

      UserEntity findUserByUserName(String userName);

      int updateUser(UserEntity user);

      void deleteUserById(Long id);
}
