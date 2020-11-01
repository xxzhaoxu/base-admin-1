package cn.huanzi.qch.baseadmin.consumer.repository.impl;

import cn.huanzi.qch.baseadmin.consumer.entity.UserEntity;
import cn.huanzi.qch.baseadmin.consumer.repository.userDao;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import org.springframework.stereotype.Service;

@Service
public class UserDaoImpl implements userDao {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Override
    public void saveUser(UserEntity user) {
        mongoTemplate.save(user);
    }

    @Override
    public UserEntity findUserByUserName(String userName) {

        Query query=new Query(Criteria.where("userName").is(userName)).addCriteria(Criteria.where("id").is("1"));
        UserEntity user =  mongoTemplate.findOne(query , UserEntity.class);
        return user;
    }

    @Override
    public int updateUser(UserEntity user) {
        Query query=new Query(Criteria.where("id").is(user.getId()));
        Update update= new Update().set("userName", user.getUserName()).set("passWord", user.getPassWord()).set("tags",user.getTags());
        //更新查询返回结果集的第一条
        UpdateResult result =mongoTemplate.updateFirst(query,update,UserEntity.class);
        //更新查询返回结果集的所有
        // mongoTemplate.updateMulti(query,update,UserEntity.class);
        if(result!=null)
            return Integer.parseInt(result.getModifiedCount()+"");
        else{
            return 0;
        }

    }

    @Override
    public void deleteUserById(Long id) {
        Query query=new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query,UserEntity.class);
    }
}
