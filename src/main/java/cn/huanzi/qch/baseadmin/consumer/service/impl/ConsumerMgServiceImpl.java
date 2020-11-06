package cn.huanzi.qch.baseadmin.consumer.service.impl;

import cn.huanzi.qch.baseadmin.consumer.entity.ConsumerMg;
import cn.huanzi.qch.baseadmin.consumer.service.ConsumerMgService;
import cn.huanzi.qch.baseadmin.sys.sysauthority.pojo.SysAuthority;
import cn.huanzi.qch.baseadmin.sys.sysauthority.repository.SysAuthorityRepository;
import cn.huanzi.qch.baseadmin.util.SecurityUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Service
public class ConsumerMgServiceImpl implements ConsumerMgService {

    @Autowired
    private MongoTemplate mongoTemplate;
    @Resource
    private SysAuthorityRepository sysAuthorityRepository;

    @Override
    public ConsumerMg findConsumerMgByPhone(String phone) {
        Query query=new Query(Criteria.where("phone").is(phone));
        ConsumerMg consumerMg =  mongoTemplate.findOne(query , ConsumerMg.class);
        return consumerMg;
    }

    @Override
    public ConsumerMg findConsumerMgById(String id) {
        Query query=new Query(Criteria.where("id").is(id));
        ConsumerMg consumerMg =  mongoTemplate.findOne(query , ConsumerMg.class);
        return consumerMg;
    }

    @Override
    public void saveConsumerMg(ConsumerMg consumerMg) {
        mongoTemplate.save(consumerMg);

    }

    @Override
    public int updateConsumerMg(ConsumerMg consumerMg) {
        Query query=new Query(Criteria.where("id").is(consumerMg.getId()));
        Update update= new Update();

        Field[] field = consumerMg.getClass().getDeclaredFields();
        try {
            for (Field f : field) {
                f.setAccessible(true);
                if (f.get(consumerMg)==null){
                    continue;
                }
                List<String> list = new ArrayList<>();
                //集合
                if(f.getType() == java.util.List.class){
                    Class<?> clzz = f.get(consumerMg).getClass();
                    Method sizeMethod = clzz.getDeclaredMethod("size");
                    if(!sizeMethod.isAccessible()){
                        sizeMethod.setAccessible(true);
                    }
                    //集合长度
                    int size = (int) sizeMethod.invoke(f.get(consumerMg));
                    for (int i = 0; i < size; i++) {
                        //反射获取到list的get方法
                        Method getMethod = clzz.getDeclaredMethod("get", int.class);
                        //调用get方法获取数据
                        if(!getMethod.isAccessible()){
                            getMethod.setAccessible(true);
                        }
                        String str = (String) getMethod.invoke(f.get(consumerMg), i);
                        if (StrUtil.isNotEmpty(str)){
                            list.add(str);
                        }
                    }
                    update.set(f.getName(),list);
                }else {
                    String value =  (String) f.get(consumerMg);
                    if (StrUtil.isNotEmpty(value)){
                        update.set(f.getName(),value);
                    }
                }
            }
            UpdateResult result =mongoTemplate.updateFirst(query,update,ConsumerMg.class);
            return (int)result.getModifiedCount();
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public JSONObject findConsumerMgByEntity(ConsumerMg consumerMg, Integer start, Integer end) {
        Query query= createQuery(consumerMg);
        Pageable pageable = PageRequest.of(start,end);
        query.with(pageable);
        List<ConsumerMg> consumerMgList = mongoTemplate.find(query,ConsumerMg.class);
        Long count = mongoTemplate.count(query,ConsumerMg.class);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("list",consumerMgList);
        jsonObject.put("count",count);
        return jsonObject;
    }

    @Override
    public List<ConsumerMg> findConsumerMgByEntity(ConsumerMg consumerMg, Long start, Long end) {
        Query query= createQuery(consumerMg);
        Pageable pageable = PageRequest.of(start.intValue(),end.intValue());
        query.with(pageable);
        List<ConsumerMg> consumerMgList = mongoTemplate.find(query,ConsumerMg.class);
        return consumerMgList;
    }


    @Override
    public List<ConsumerMg> findConsumerMgByEntity(ConsumerMg consumerMg) {
        Query query=createQuery(consumerMg);
        List<ConsumerMg> consumerMgList = mongoTemplate.find(query,ConsumerMg.class);
        return consumerMgList;
    }

    @Override
    public Long findCount(ConsumerMg consumerMg) {
        Query query= createQuery(consumerMg);
        Long count = mongoTemplate.count(query,ConsumerMg.class);
        return count;
    }

    @Override
    public void delete(String phone) {
        Query query=new Query(Criteria.where("id").is(phone));
        mongoTemplate.remove(query,ConsumerMg.class);
    }

    private Query createQuery(ConsumerMg consumerMg){
        Query query=new Query();
        if (consumerMg.getPhone()!=null){
            query.addCriteria(Criteria.where("phone").regex("^.*"+consumerMg.getPhone()+".*$"));
        }
        if (consumerMg.getLevel()!=null){
            query.addCriteria(Criteria.where("level").regex("^.*"+consumerMg.getLevel()+".*$"));
        }
        if (consumerMg.getIsNew()!=null){
            query.addCriteria(Criteria.where("isNew").regex("^.*"+consumerMg.getIsNew()+".*$"));
        }
        if (consumerMg.getCType()!=null){
            query.addCriteria(Criteria.where("cType").regex("^.*"+consumerMg.getCType()+".*$"));
        }
        if (consumerMg.getMenu()!=null){
            query.addCriteria(Criteria.where("menu").regex("^.*"+consumerMg.getMenu()+".*$"));
        }
        if (consumerMg.getCompany()!=null){
            query.addCriteria(Criteria.where("company").regex("^.*"+consumerMg.getCompany()+".*$"));
        }
        if (consumerMg.getCName()!=null){
            query.addCriteria(Criteria.where("cName").regex("^.*"+consumerMg.getCName()+".*$"));
        }
        if (consumerMg.getProduct()!=null){
            query.addCriteria(Criteria.where("product").regex("^.*"+consumerMg.getProduct()+".*$"));
        }
        if (consumerMg.getSalesman()!=null){
            query.addCriteria(Criteria.where("salesman").regex("^.*"+consumerMg.getSalesman()+".*$"));
        }
        if (consumerMg.getWxNick()!=null){
            query.addCriteria(Criteria.where("wxNick").regex("^.*"+consumerMg.getWxNick()+".*$"));
        }
        if (consumerMg.getWxPhone()!=null){
            query.addCriteria(Criteria.where("wxPhone").regex("^.*"+consumerMg.getWxPhone()+".*$"));
        }
        if (consumerMg.getProvince()!=null){
            query.addCriteria(Criteria.where("province").regex("^.*"+consumerMg.getProvince()+".*$"));
        }
        if (consumerMg.getCity()!=null){
            query.addCriteria(Criteria.where("city").regex("^.*"+consumerMg.getCity()+".*$"));
        }
        if (consumerMg.getArea()!=null){
            query.addCriteria(Criteria.where("area").regex("^.*"+consumerMg.getArea()+".*$"));
        }
        if (consumerMg.getCountry()!=null){
            query.addCriteria(Criteria.where("country").regex("^.*"+consumerMg.getCountry()+".*$"));
        }
        if (consumerMg.getPhone2()!=null){
            query.addCriteria(Criteria.where("phone2").regex("^.*"+consumerMg.getPhone2()+".*$"));
        }
        if (consumerMg.getSalesList()!=null){
            query.addCriteria(Criteria.where("salesList").regex("^.*"+consumerMg.getSalesList()+".*$"));
        }
        if (consumerMg.getPromise()!=null){
            query.addCriteria(Criteria.where("promise").regex("^.*"+consumerMg.getPromise()+".*$"));
        }
        if (consumerMg.getPhoneContent()!=null){
            query.addCriteria(Criteria.where("phoneContent").regex("^.*"+consumerMg.getPhoneContent()+".*$"));
        }
        if (consumerMg.getKs()!=null){
            query.addCriteria(Criteria.where("ks").regex("^.*"+consumerMg.getKs()+".*$"));
        }
        if (consumerMg.getKsContent()!=null){
            query.addCriteria(Criteria.where("ksContent").regex("^.*"+consumerMg.getKsContent()+".*$"));
        }
        if (consumerMg.getDy()!=null){
            query.addCriteria(Criteria.where("dy").regex("^.*"+consumerMg.getDy()+".*$"));
        }
        if (consumerMg.getDyContent()!=null){
            query.addCriteria(Criteria.where("dyContent").regex("^.*"+consumerMg.getDyContent()+".*$"));
        }
        if (consumerMg.getPdd()!=null){
            query.addCriteria(Criteria.where("pdd").regex("^.*"+consumerMg.getPdd()+".*$"));
        }
        if (consumerMg.getPddContent()!=null){
            query.addCriteria(Criteria.where("pddContent").regex("^.*"+consumerMg.getPddContent()+".*$"));
        }
        if (consumerMg.getQq()!=null){
            query.addCriteria(Criteria.where("qq").regex("^.*"+consumerMg.getQq()+".*$"));
        }
        if (consumerMg.getQqContent()!=null){
            query.addCriteria(Criteria.where("qqContent").regex("^.*"+consumerMg.getQqContent()+".*$"));
        }
        if (consumerMg.getTbww()!=null){
            query.addCriteria(Criteria.where("tbww").regex("^.*"+consumerMg.getTbww()+".*$"));
        }
        if (consumerMg.getTbwwContent()!=null){
            query.addCriteria(Criteria.where("tbwwContent").regex("^.*"+consumerMg.getTbwwContent()+".*$"));
        }
        if (consumerMg.getEmail()!=null){
            query.addCriteria(Criteria.where("email").regex("^.*"+consumerMg.getEmail()+".*$"));
        }
        if (consumerMg.getBaidu()!=null){
            query.addCriteria(Criteria.where("baidu").regex("^.*"+consumerMg.getBaidu()+".*$"));
        }
        if (consumerMg.getBaiduContent()!=null){
            query.addCriteria(Criteria.where("baiduContent").regex("^.*"+consumerMg.getBaiduContent()+".*$"));
        }
        if (consumerMg.getIndexPage()!=null){
            query.addCriteria(Criteria.where("indexPage").regex("^.*"+consumerMg.getIndexPage()+".*$"));
        }
        if (consumerMg.getRemark()!=null){
            query.addCriteria(Criteria.where("remark").regex("^.*"+consumerMg.getRemark()+".*$"));
        }
        if (consumerMg.getConnectList()!=null){
            query.addCriteria(Criteria.where("connectList").regex("^.*"+consumerMg.getConnectList()+".*$"));
        }
        if (consumerMg.getAddress()!=null){
            query.addCriteria(Criteria.where("address").regex("^.*"+consumerMg.getAddress()+".*$"));
        }
        if (consumerMg.getLogistialAddress()!=null){
            query.addCriteria(Criteria.where("logistialAddress").regex("^.*"+consumerMg.getLogistialAddress()+".*$"));
        }
        //查询角色是否有查询全部权限
        Collection<GrantedAuthority> collection = SecurityUtil.getLoginUser().getAuthorities();
        boolean b = false;
        for (GrantedAuthority grantedAuthority : collection) {
            String c =   grantedAuthority.getAuthority();
            SysAuthority sysAuthority =  sysAuthorityRepository.findAllByAuthorityName(c);
            if (sysAuthority!=null&&sysAuthority.getIsFindAll()!=null&&sysAuthority.getIsFindAll().equals("on")){
                b= true;
                break;
            }
        }
        if (!b){
            query.addCriteria(new Criteria().orOperator(Criteria.where("group").is(consumerMg.getGroup()),Criteria.where("group").is(null),Criteria.where("group").is("")));
        }

        return query;
    }
}
