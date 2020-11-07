package cn.huanzi.qch.baseadmin.consumer.controller;

import cn.huanzi.qch.baseadmin.common.pojo.Result;
import cn.huanzi.qch.baseadmin.consumer.entity.ConsumerMg;
import cn.huanzi.qch.baseadmin.consumer.entity.SysConsumer;
import cn.huanzi.qch.baseadmin.consumer.repository.SysConsumerRepository;
import cn.huanzi.qch.baseadmin.consumer.service.ConsumerMgService;
import cn.huanzi.qch.baseadmin.sys.sysauthority.pojo.SysAuthority;
import cn.huanzi.qch.baseadmin.sys.sysauthority.repository.SysAuthorityRepository;
import cn.huanzi.qch.baseadmin.util.SecurityUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.ZipUtil;
import cn.hutool.poi.excel.BigExcelWriter;
import cn.hutool.poi.excel.ExcelUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author create by zhaoxu
 * @create 2020/10/20
 */
@RestController
public class consumerController {
    @Autowired
    private SysConsumerRepository sysConsumerRepository;
    @Autowired
    private ConsumerMgService consumerMgService;
    @Autowired
    private SysAuthorityRepository sysAuthorityRepository;

    String filepath = "D:/picture/";
    String cachePath = "D:/cache/";
//    String filepath = "E:\\File\\HTML5_yonghudenglu\\HTML5响应式用户登录界面模板\\css\\";


    private List<String> StrToList(String str){
        if (StrUtil.isEmpty(str)){
            return new ArrayList<>();
        }
        String arr[]  = str.split("\n");
        return  Arrays.asList(arr);
    }

    @GetMapping("updateConusmer")
    public Result updateConusmer(
            @RequestParam(required = true,name = "id")String id,
            @RequestParam(required = false,name = "level")String level,
            @RequestParam(required = false,name = "isNew")String isNew,
            @RequestParam(required = false,name = "cType")String cType,
            @RequestParam(required = false,name = "menu")String menu,
            @RequestParam(required = false,name = "company")String company,
            @RequestParam(required = false,name = "cName")String cName,
            @RequestParam(required = false,name = "phone")String phone,
            @RequestParam(required = false,name = "product")String product,
            @RequestParam(required = false,name = "salesman")String salesman,
            @RequestParam(required = false,name = "wxNick")String wxNick,
            @RequestParam(required = false,name = "wxPhone")String wxPhone,
            @RequestParam(required = false,name = "province")String province,
            @RequestParam(required = false,name = "city")String city,
            @RequestParam(required = false,name = "area")String area,
            @RequestParam(required = false,name = "country")String country,
            @RequestParam(required = false,name = "village")String village,
            @RequestParam(required = false,name = "agency")String agency,
            @RequestParam(required = false,name = "salesList")String salesList,
            @RequestParam(required = false,name = "promise")String promise,
            @RequestParam(required = false,name = "phoneContent")String phoneContent,
            @RequestParam(required = false,name = "ks")String ks,
            @RequestParam(required = false,name = "ksContent")String ksContent,
            @RequestParam(required = false,name = "dy")String dy,
            @RequestParam(required = false,name = "dyContent")String dyContent,
            @RequestParam(required = false,name = "pdd")String pdd,
            @RequestParam(required = false,name = "pddContent")String pddContent,
            @RequestParam(required = false,name = "qq")String qq,
            @RequestParam(required = false,name = "qqContent")String qqContent,
            @RequestParam(required = false,name = "tbww")String tbww,
            @RequestParam(required = false,name = "tbwwContent")String tbwwContent,
            @RequestParam(required = false,name = "email")String email,
            @RequestParam(required = false,name = "baidu")String baidu,
            @RequestParam(required = false,name = "baiduContent")String baiduContent,
            @RequestParam(required = false,name = "indexPage")String indexPage,
            @RequestParam(required = false,name = "remark")String remark,
            @RequestParam(required = false,name = "connectList")String connectList,
            @RequestParam(required = false,name = "address")String address,
            @RequestParam(required = false,name = "logistialAddress")String logistialAddress,
            @RequestParam(required = false,name = "group")String group,
            @RequestParam(required = false,name = "text1")String text1,
            @RequestParam(required = false,name = "text2")String text2,
            @RequestParam(required = false,name = "text3")String text3,
            @RequestParam(required = false,name = "text4")String text4,
            @RequestParam(required = false,name = "text5")String text5,
            @RequestParam(required = false,name = "text6")String text6
    ){
        ConsumerMg consumerMg = consumerMgService.findConsumerMgById(id);
        if (consumerMg==null){
            return Result.of("数据异常");
        }
        consumerMg.setLevel(level);
        consumerMg.setIsNew(isNew);
        consumerMg.setCType(cType);
        consumerMg.setPhone(phone);
        if (StrUtil.isNotEmpty(menu)) {
            List<String> menuList = StrToList(menu);
            consumerMg.setMenu(menuList);
        }

        if (StrUtil.isNotEmpty(company) ) {
            List<String> companyList = StrToList(company);
            consumerMg.setCompany(companyList);
        }

        if (StrUtil.isNotEmpty(cName)) {
            List<String> cNameList =StrToList(cName);
            consumerMg.setCName(cNameList);
        }

        if (StrUtil.isNotEmpty(product)) {
            List<String> productList = StrToList(product);
            consumerMg.setProduct(productList);
        }

        if (StrUtil.isNotEmpty(salesman)) {
            List<String> salesmanList = StrToList(salesman);
            consumerMg.setSalesman(salesmanList);
        }

        if (StrUtil.isNotEmpty(wxNick)) {
            List<String> wxNickList = StrToList(wxNick);
            consumerMg.setWxNick(wxNickList);
        }

        if (StrUtil.isNotEmpty(wxPhone)) {
            List<String> wxPhoneList = StrToList(wxPhone);
            consumerMg.setWxPhone(wxPhoneList);
        }

        consumerMg.setProvince(province);
        consumerMg.setCity(city);

        if (StrUtil.isNotEmpty(salesList)) {
            List<String> saleList = StrToList(salesList);
            consumerMg.setSalesList(saleList);
        }

        if (StrUtil.isNotEmpty(promise)) {
            List<String> promiseList = StrToList(promise);
            consumerMg.setPromise(promiseList);
        }

        if (StrUtil.isNotEmpty(phoneContent)) {
            List<String> phoneContentList = StrToList(phoneContent);
            consumerMg.setPhoneContent(phoneContentList);
        }

        if (StrUtil.isNotEmpty(ks)) {
            List<String> ksList = StrToList(ks);
            consumerMg.setKs(ksList);
        }

        if (StrUtil.isNotEmpty(ksContent)) {
            List<String> ksContentList = StrToList(ksContent);
            consumerMg.setKsContent(ksContentList);
        }

        if (StrUtil.isNotEmpty(dy)) {
            List<String> dyList = StrToList(dy);
            consumerMg.setDy(dyList);
        }

        if (StrUtil.isNotEmpty(dyContent)) {
            List<String> dyContentList = StrToList(dyContent);
            consumerMg.setDyContent(dyContentList);
        }

        if (StrUtil.isNotEmpty(pdd)) {
            List<String> pddList = StrToList(pdd);
            consumerMg.setPdd(pddList);
        }

        if (StrUtil.isNotEmpty(pddContent)) {
            List<String> pddContentList = StrToList(pddContent);
            consumerMg.setPddContent(pddContentList);
        }

        if (StrUtil.isNotEmpty(qq)) {
            List<String> qqList = StrToList(qq);
            consumerMg.setQq(qqList);
        }

        if (StrUtil.isNotEmpty(qqContent)) {
            List<String> qqContentList = StrToList(qqContent);
            consumerMg.setQqContent(qqContentList);
        }

        if (StrUtil.isNotEmpty(tbww)) {
            List<String> tbwwList = StrToList(tbww);
            consumerMg.setTbww(tbwwList);
        }

        if (StrUtil.isNotEmpty(tbwwContent)) {
            List<String> tbwwContentList = StrToList(tbwwContent);
            consumerMg.setTbwwContent(tbwwContentList);
        }

        if (StrUtil.isNotEmpty(email)) {
            List<String> emailList =StrToList(email);
            consumerMg.setEmail(emailList);
        }

        if (StrUtil.isNotEmpty(baidu)) {
            List<String> baiduList = StrToList(baidu);
            consumerMg.setBaidu(baiduList);
        }

        if (StrUtil.isNotEmpty(baiduContent)) {
            List<String> baiduContentList =StrToList(baiduContent);
            consumerMg.setBaiduContent(baiduContentList);
        }

        if (StrUtil.isNotEmpty(indexPage)) {
            List<String> indexPageList = StrToList(indexPage);
            consumerMg.setIndexPage(indexPageList);
        }

        if (StrUtil.isNotEmpty(remark)) {
            List<String> remarkList =StrToList(remark);
            consumerMg.setRemark(remarkList);
        }

        if (StrUtil.isNotEmpty(connectList)) {
            List<String> connectsList = StrToList(connectList);
            consumerMg.setConnectList(connectsList);
        }

        if (StrUtil.isNotEmpty(address)) {
            List<String> addressList = StrToList(address);
            consumerMg.setAddress(addressList);
        }

        if (StrUtil.isNotEmpty(logistialAddress)) {
            List<String> logistialAddressList = StrToList(logistialAddress);
            consumerMg.setLogistialAddress(logistialAddressList);
        }

        if (StrUtil.isNotEmpty(text1)) {
            List<String> textList1 = StrToList(text1);
            consumerMg.setText1(textList1);
        }

        if (StrUtil.isNotEmpty(text2)) {
            List<String> textList2 = StrToList(text2);
            consumerMg.setText2(textList2);
        }

        if (StrUtil.isNotEmpty(text3)) {
            List<String> textList3 = StrToList(text3);
            consumerMg.setText3(textList3);
        }

        if (StrUtil.isNotEmpty(text4)) {
            List<String> textList4 = StrToList(text4);
            consumerMg.setText4(textList4);
        }

        if (StrUtil.isNotEmpty(text5)) {
            List<String> textList5 = StrToList(text5);
            consumerMg.setText5(textList5);
        }

        if (StrUtil.isNotEmpty(text6)) {
            List<String> textList6 = StrToList(text6);
            consumerMg.setText6(textList6);
        }
        return Result.of(consumerMgService.updateConsumerMg(consumerMg));
    }

    @GetMapping("update")
    public Result update(
            @RequestParam(required = true,name = "cid")Long cid,
            @RequestParam(required = false,name = "level")String level,
            @RequestParam(required = false,name = "isNew")String isNew,
            @RequestParam(required = false,name = "cType")String cType,
            @RequestParam(required = false,name = "menu")String menu,
            @RequestParam(required = false,name = "company")String company,
            @RequestParam(required = false,name = "cName")String cName,
            @RequestParam(required = false,name = "phone")String phone,
            @RequestParam(required = false,name = "product")String product,
            @RequestParam(required = false,name = "salesman")String salesman,
            @RequestParam(required = false,name = "wxNick")String wxNick,
            @RequestParam(required = false,name = "wxPhone")String wxPhone,
            @RequestParam(required = false,name = "province")String province,
            @RequestParam(required = false,name = "city")String city,
            @RequestParam(required = false,name = "area")String area,
            @RequestParam(required = false,name = "country")String country,
            @RequestParam(required = false,name = "village")String village,
            @RequestParam(required = false,name = "agency")String agency,
            @RequestParam(required = false,name = "salesList")String salesList,
            @RequestParam(required = false,name = "promise")String promise,
            @RequestParam(required = false,name = "phoneContent")String phoneContent,
            @RequestParam(required = false,name = "ks")String ks,
            @RequestParam(required = false,name = "ksContent")String ksContent,
            @RequestParam(required = false,name = "dy")String dy,
            @RequestParam(required = false,name = "dyContent")String dyContent,
            @RequestParam(required = false,name = "pdd")String pdd,
            @RequestParam(required = false,name = "pddContent")String pddContent,
            @RequestParam(required = false,name = "qq")String qq,
            @RequestParam(required = false,name = "qqContent")String qqContent,
            @RequestParam(required = false,name = "tbww")String tbww,
            @RequestParam(required = false,name = "tbwwContent")String tbwwContent,
            @RequestParam(required = false,name = "email")String email,
            @RequestParam(required = false,name = "baidu")String baidu,
            @RequestParam(required = false,name = "baiduContent")String baiduContent,
            @RequestParam(required = false,name = "indexPage")String indexPage,
            @RequestParam(required = false,name = "remark")String remark,
            @RequestParam(required = false,name = "connectList")String connectList,
            @RequestParam(required = false,name = "address")String address,
            @RequestParam(required = false,name = "logistialAddress")String logistialAddress,
            @RequestParam(required = false,name = "group")String group,
            @RequestParam(required = false,name = "text1")String text1,
            @RequestParam(required = false,name = "text2")String text2,
            @RequestParam(required = false,name = "text3")String text3,
            @RequestParam(required = false,name = "text4")String text4,
            @RequestParam(required = false,name = "text5")String text5,
            @RequestParam(required = false,name = "text6")String text6
    ){

        SysConsumer sysConsumer = new SysConsumer();
        sysConsumer.setCid(cid);
        sysConsumer.setLevel(level);
        sysConsumer.setIsNew(isNew);
        sysConsumer.setCType(cType);
        sysConsumer.setMenu(menu);
        sysConsumer.setCompany(company);
        sysConsumer.setCName(cName);
        sysConsumer.setPhone(phone);
        sysConsumer.setProduct(product);
        sysConsumer.setSalesman(salesman);
        sysConsumer.setWxNick(wxNick);
        sysConsumer.setWxPhone(wxPhone);
        sysConsumer.setProvince(province);
        sysConsumer.setCity(city);
        sysConsumer.setArea(area);
        sysConsumer.setCountry(country);
        sysConsumer.setVillage(village);
        sysConsumer.setAgency(agency);
        sysConsumer.setSalesList(salesList);
        sysConsumer.setPromise(promise);
        sysConsumer.setPhoneContent(phoneContent);
        sysConsumer.setKs(ks);
        sysConsumer.setKsContent(ksContent);
        sysConsumer.setDy(dy);
        sysConsumer.setDyContent(dyContent);
        sysConsumer.setPdd(pdd);
        sysConsumer.setPddContent(pddContent);
        sysConsumer.setQq(qq);
        sysConsumer.setQqContent(qqContent);
        sysConsumer.setTbww(tbww);
        sysConsumer.setTbwwContent(tbwwContent);
        sysConsumer.setEmail(email);
        sysConsumer.setBaidu(baidu);
        sysConsumer.setBaiduContent(baiduContent);
        sysConsumer.setIndexPage(indexPage);
        sysConsumer.setRemark(remark);
        sysConsumer.setConnectList(connectList);
        sysConsumer.setAddress(address);
        sysConsumer.setLogistialAddress(logistialAddress);
        sysConsumer.setGroup(group);
        sysConsumer.setText1(text1);
        sysConsumer.setText2(text2);
        sysConsumer.setText3(text3);
        sysConsumer.setText4(text4);
        sysConsumer.setText5(text5);
        sysConsumer.setText6(text6);
        sysConsumerRepository.saveAndFlush(sysConsumer);
        return Result.of(sysConsumer);
    }
    @GetMapping("findAll")
    public Result findAll(
            @RequestParam(value = "pageIndex",defaultValue = "1")Integer pageIndex,
            @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize,
            @RequestParam(required = false,name = "level")String level,
            @RequestParam(required = false,name = "isNew")String isNew,
            @RequestParam(required = false,name = "cType")String cType,
            @RequestParam(required = false,name = "menu")String menu,
            @RequestParam(required = false,name = "company")String company,
            @RequestParam(required = false,name = "cName")String cName,
            @RequestParam(required = false,name = "phone")String phone,
            @RequestParam(required = false,name = "product")String product,
            @RequestParam(required = false,name = "salesman")String salesman,
            @RequestParam(required = false,name = "wxNick")String wxNick,
            @RequestParam(required = false,name = "wxPhone")String wxPhone,
            @RequestParam(required = false,name = "province")String province,
            @RequestParam(required = false,name = "city")String city,
            @RequestParam(required = false,name = "area")String area,
            @RequestParam(required = false,name = "country")String country,
            @RequestParam(required = false,name = "village")String village,
            @RequestParam(required = false,name = "agency")String agency,
            @RequestParam(required = false,name = "salesList")String salesList,
            @RequestParam(required = false,name = "promise")String promise,
            @RequestParam(required = false,name = "phoneContent")String phoneContent,
            @RequestParam(required = false,name = "ks")String ks,
            @RequestParam(required = false,name = "ksContent")String ksContent,
            @RequestParam(required = false,name = "dy")String dy,
            @RequestParam(required = false,name = "dyContent")String dyContent,
            @RequestParam(required = false,name = "pdd")String pdd,
            @RequestParam(required = false,name = "pddContent")String pddContent,
            @RequestParam(required = false,name = "qq")String qq,
            @RequestParam(required = false,name = "qqContent")String qqContent,
            @RequestParam(required = false,name = "tbww")String tbww,
            @RequestParam(required = false,name = "tbwwContent")String tbwwContent,
            @RequestParam(required = false,name = "email")String email,
            @RequestParam(required = false,name = "baidu")String baidu,
            @RequestParam(required = false,name = "baiduContent")String baiduContent,
            @RequestParam(required = false,name = "indexPage")String indexPage,
            @RequestParam(required = false,name = "remark")String remark,
            @RequestParam(required = false,name = "connectList")String connectList,
            @RequestParam(required = false,name = "address")String address,
            @RequestParam(required = false,name = "logistialAddress")String logistialAddress,
            @RequestParam(required = false,name = "group")String group,
            @RequestParam(required = false,name = "text1")String text1,
            @RequestParam(required = false,name = "text2")String text2,
            @RequestParam(required = false,name = "text3")String text3,
            @RequestParam(required = false,name = "text4")String text4,
            @RequestParam(required = false,name = "text5")String text5,
            @RequestParam(required = false,name = "text6")String text6

    ){


//        Specification<SysConsumer> queryCondition = new Specification<SysConsumer>() {
//            @Override
//            public Predicate toPredicate(Root<SysConsumer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
//                List<Predicate> predicateList = new ArrayList<>();
//                if (StrUtil.isNotEmpty(level)){
//                    predicateList.add(criteriaBuilder.like(root.get("level"), "%"+level+"%"));
//                }
//                if (StrUtil.isNotEmpty(isNew)){
//                    predicateList.add(criteriaBuilder.like(root.get("isNew"), "%"+isNew+"%"));
//                }
//                if (StrUtil.isNotEmpty(cType)){
//                    predicateList.add(criteriaBuilder.like(root.get("cType"), "%"+cType+"%"));
//                }
//                if (StrUtil.isNotEmpty(menu)){
//                    predicateList.add(criteriaBuilder.like(root.get("menu"), "%"+menu+"%"));
//                }
//                if (StrUtil.isNotEmpty(company)){
//                    predicateList.add(criteriaBuilder.like(root.get("company"), "%"+company+"%"));
//                }
//                if (StrUtil.isNotEmpty(cName)){
//                    predicateList.add(criteriaBuilder.like(root.get("cName"),"%"+ cName+"%"));
//                }
//                if (StrUtil.isNotEmpty(phone)) {
//                    predicateList.add(criteriaBuilder.like(root.get("phone"), "%"+phone+"%"));
//                }
//                if(StrUtil.isNotEmpty(product)){
//                    predicateList.add(criteriaBuilder.like(root.get("product"),"%"+ product+"%"));
//                }
//                if(StrUtil.isNotEmpty(salesman)){
//                    predicateList.add(criteriaBuilder.like(root.get("salesman"),"%"+ salesman+"%"));
//                }
//                if(StrUtil.isNotEmpty(wxNick)){
//                    predicateList.add(criteriaBuilder.like(root.get("wxNick"),"%"+ wxNick+"%"));
//                }
//                if(StrUtil.isNotEmpty(wxPhone)){
//                    predicateList.add(criteriaBuilder.like(root.get("wxPhone"),"%"+ wxPhone+"%"));
//                }
//                if(StrUtil.isNotEmpty(province)){
//                    predicateList.add(criteriaBuilder.like(root.get("province"),"%"+ province+"%"));
//                }
//                if(StrUtil.isNotEmpty(city)){
//                    predicateList.add(criteriaBuilder.like(root.get("city"),"%"+ city+"%"));
//                }
//                if(StrUtil.isNotEmpty(area)){
//                    predicateList.add(criteriaBuilder.like(root.get("area"),"%"+ area+"%"));
//                }
//                if(StrUtil.isNotEmpty(country)){
//                    predicateList.add(criteriaBuilder.like(root.get("country"),"%"+ country+"%"));
//                }
//                if(StrUtil.isNotEmpty(village)){
//                    predicateList.add(criteriaBuilder.like(root.get("village"),"%"+ village+"%"));
//                }
//                if(StrUtil.isNotEmpty(agency)){
//                    predicateList.add(criteriaBuilder.like(root.get("agency"),"%"+ agency+"%"));
//                }
//                if(StrUtil.isNotEmpty(salesList)){
//                    predicateList.add(criteriaBuilder.like(root.get("salesList"),"%"+ salesList+"%"));
//                }
//                if(StrUtil.isNotEmpty(promise)){
//                    predicateList.add(criteriaBuilder.like(root.get("promise"),"%"+ promise+"%"));
//                }
//                if(StrUtil.isNotEmpty(phoneContent)){
//                    predicateList.add(criteriaBuilder.like(root.get("phoneContent"),"%"+ phoneContent+"%"));
//                }
//                if(StrUtil.isNotEmpty(ks)){
//                    predicateList.add(criteriaBuilder.like(root.get("ks"),"%"+ ks+"%"));
//                }
//                if(StrUtil.isNotEmpty(ksContent)){
//                    predicateList.add(criteriaBuilder.like(root.get("ksContent"),"%"+ ksContent+"%"));
//                }
//                if(StrUtil.isNotEmpty(dy)){
//                    predicateList.add(criteriaBuilder.like(root.get("dy"),"%"+ dy+"%"));
//                }
//                if(StrUtil.isNotEmpty(dyContent)){
//                    predicateList.add(criteriaBuilder.like(root.get("dyContent"),"%"+ dyContent+"%"));
//                }
//                if(StrUtil.isNotEmpty(pdd)){
//                    predicateList.add(criteriaBuilder.like(root.get("pdd"),"%"+ pdd+"%"));
//                }
//                if(StrUtil.isNotEmpty(pddContent)){
//                    predicateList.add(criteriaBuilder.like(root.get("pddContent"),"%"+ pddContent+"%"));
//                }
//                if(StrUtil.isNotEmpty(qq)){
//                    predicateList.add(criteriaBuilder.like(root.get("qq"),"%"+ qq+"%"));
//                }
//                if(StrUtil.isNotEmpty(qqContent)){
//                    predicateList.add(criteriaBuilder.like(root.get("qqContent"),"%"+ qqContent+"%"));
//                }
//                if(StrUtil.isNotEmpty(tbww)){
//                    predicateList.add(criteriaBuilder.like(root.get("tbww"),"%"+ tbww+"%"));
//                }
//                if(StrUtil.isNotEmpty(tbwwContent)){
//                    predicateList.add(criteriaBuilder.like(root.get("tbwwContent"),"%"+ tbwwContent+"%"));
//                }
//                if(StrUtil.isNotEmpty(email)){
//                    predicateList.add(criteriaBuilder.like(root.get("email"),"%"+ email+"%"));
//                }
//                if(StrUtil.isNotEmpty(baidu)){
//                    predicateList.add(criteriaBuilder.like(root.get("baidu"),"%"+ baidu+"%"));
//                }
//                if(StrUtil.isNotEmpty(baiduContent)){
//                    predicateList.add(criteriaBuilder.like(root.get("baiduContent"),"%"+ baiduContent+"%"));
//                }
//                if(StrUtil.isNotEmpty(indexPage)){
//                    predicateList.add(criteriaBuilder.like(root.get("indexPage"),"%"+ indexPage+"%"));
//                }
//                if(StrUtil.isNotEmpty(remark)){
//                    predicateList.add(criteriaBuilder.like(root.get("remark"),"%"+ remark+"%"));
//                }
//                if(StrUtil.isNotEmpty(connectList)){
//                    predicateList.add(criteriaBuilder.like(root.get("connectList"),"%"+ connectList+"%"));
//                }
//                if(StrUtil.isNotEmpty(address)){
//                    predicateList.add(criteriaBuilder.like(root.get("address"),"%"+ address+"%"));
//                }
//                if(StrUtil.isNotEmpty(logistialAddress)){
//                    predicateList.add(criteriaBuilder.like(root.get("logistialAddress"),"%"+ logistialAddress+"%"));
//                }
//                if(StrUtil.isNotEmpty(group)){
//                    predicateList.add(criteriaBuilder.like(root.get("cGroup"),"%"+ group+"%"));
//                }else {
//                    String userName = SecurityUtil.getLoginUser().getUsername();
//                    Predicate pred1 = criteriaBuilder.equal(root.get("group"), userName);
//                    Predicate pred2 = criteriaBuilder.isNull(root.get("group"));
//
//                    predicateList.add(criteriaBuilder.or(pred1, pred2));
//
//                }
//                if(StrUtil.isNotEmpty(text1)){
//                    predicateList.add(criteriaBuilder.like(root.get("text1"),"%"+ text1+"%"));
//                }
//                if(StrUtil.isNotEmpty(text2)){
//                    predicateList.add(criteriaBuilder.like(root.get("text2"),"%"+ text2+"%"));
//                }
//                if(StrUtil.isNotEmpty(text3)){
//                    predicateList.add(criteriaBuilder.like(root.get("text3"),"%"+ text3+"%"));
//                }
//                if(StrUtil.isNotEmpty(text4)){
//                    predicateList.add(criteriaBuilder.like(root.get("text4"),"%"+ text4+"%"));
//                }
//                if(StrUtil.isNotEmpty(text5)){
//                    predicateList.add(criteriaBuilder.like(root.get("text5"),"%"+ text5+"%"));
//                }
//                if(StrUtil.isNotEmpty(text6)){
//                    predicateList.add(criteriaBuilder.like(root.get("text6"),"%"+ text6+"%"));
//                }
//                predicateList.add(criteriaBuilder.equal(root.get("stats"),"1"));
//
//                return    criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
//            }
//        };

//        Page page  = sysConsumerRepository.findAll(queryCondition, PageRequest.of(pageIndex - 1, pageSize, Sort.by(Sort.Direction.DESC, "cid")));
//        result = page.getContent();
//        count = page.getTotalElements();
        ConsumerMg consumerMg = new ConsumerMg();
        consumerMg.setLevel(level);
        consumerMg.setIsNew(isNew);
        consumerMg.setCType(cType);
        consumerMg.setPhone(phone);
        if (StrUtil.isNotEmpty(menu)) {
            List<String> menuList = new ArrayList<>();
            menuList.add(menu);
            consumerMg.setMenu(menuList);
        }

        if (StrUtil.isNotEmpty(company) ) {
            List<String> companyList = new ArrayList<>();
            companyList.add(company);
            consumerMg.setCompany(companyList);
        }

        if (StrUtil.isNotEmpty(cName)) {
            List<String> cNameList =new ArrayList<>();
            cNameList.add(cName);
            consumerMg.setCName(cNameList);
        }

        if (StrUtil.isNotEmpty(product)) {
            List<String> productList = new ArrayList<>();
            productList.add(product);
            consumerMg.setProduct(productList);
        }

        if (StrUtil.isNotEmpty(salesman)) {
            List<String> salesmanList =new ArrayList<>();
            salesmanList.add(salesman);
            consumerMg.setSalesman(salesmanList);
        }

        if (StrUtil.isNotEmpty(wxNick)) {
            List<String> wxNickList = new ArrayList<>();
            wxNickList.add(wxNick);
            consumerMg.setWxNick(wxNickList);
        }

        if (StrUtil.isNotEmpty(wxPhone)) {
            List<String> wxPhoneList = new ArrayList<>();
            wxPhoneList.add(wxPhone);
            consumerMg.setWxPhone(wxPhoneList);
        }

        consumerMg.setProvince(province);
        consumerMg.setCity(city);

        if (StrUtil.isNotEmpty(salesList)) {
            List<String> saleList = new ArrayList<>();
            saleList.add(salesList);
            consumerMg.setSalesList(saleList);
        }

        if (StrUtil.isNotEmpty(promise)) {
            List<String> promiseList = new ArrayList<>();
            promiseList.add(promise);
            consumerMg.setPromise(promiseList);
        }

        if (StrUtil.isNotEmpty(phoneContent)) {
            List<String> phoneContentList = new ArrayList<>();
            phoneContentList.add(phoneContent);
            consumerMg.setPhoneContent(phoneContentList);
        }

        if (StrUtil.isNotEmpty(ks)) {
            List<String> ksList = new ArrayList<>();
            ksList.add(ks);
            consumerMg.setKs(ksList);
        }

        if (StrUtil.isNotEmpty(ksContent)) {
            List<String> ksContentList = new ArrayList<>();
            ksContentList.add(ksContent);
            consumerMg.setKsContent(ksContentList);
        }

        if (StrUtil.isNotEmpty(dy)) {
            List<String> dyList = new ArrayList<>();
            dyList.add(dy);
            consumerMg.setDy(dyList);
        }

        if (StrUtil.isNotEmpty(dyContent)) {
            List<String> dyContentList = new ArrayList<>();
            dyContentList.add(dyContent);
            consumerMg.setDyContent(dyContentList);
        }

        if (StrUtil.isNotEmpty(pdd)) {
            List<String> pddList = new ArrayList<>();
            pddList.add(pdd);
            consumerMg.setPdd(pddList);
        }

        if (StrUtil.isNotEmpty(pddContent)) {
            List<String> pddContentList = new ArrayList<>();
            pddContentList.add(pddContent);
            consumerMg.setPddContent(pddContentList);
        }

        if (StrUtil.isNotEmpty(qq)) {
            List<String> qqList = new ArrayList<>();
            qqList.add(qq);
            consumerMg.setQq(qqList);
        }

        if (StrUtil.isNotEmpty(qqContent)) {
            List<String> qqContentList = new ArrayList<>();
            qqContentList.add(qqContent);
            consumerMg.setQqContent(qqContentList);
        }

        if (StrUtil.isNotEmpty(tbww)) {
            List<String> tbwwList = new ArrayList<>();
            tbwwList.add(tbww);
            consumerMg.setTbww(tbwwList);
        }

        if (StrUtil.isNotEmpty(tbwwContent)) {
            List<String> tbwwContentList = new ArrayList<>();
            tbwwContentList.add(tbwwContent);
            consumerMg.setTbwwContent(tbwwContentList);
        }

        if (StrUtil.isNotEmpty(email)) {
            List<String> emailList = new ArrayList<>();
            emailList.add(email);
            consumerMg.setEmail(emailList);
        }

        if (StrUtil.isNotEmpty(baidu)) {
            List<String> baiduList = new ArrayList<>();
            baiduList.add(baidu);
            consumerMg.setBaidu(baiduList);
        }

        if (StrUtil.isNotEmpty(baiduContent)) {
            List<String> baiduContentList = new ArrayList<>();
            baiduContentList.add(baiduContent);
            consumerMg.setBaiduContent(baiduContentList);
        }

        if (StrUtil.isNotEmpty(indexPage)) {
            List<String> indexPageList = new ArrayList<>();
            indexPageList.add(indexPage);
            consumerMg.setIndexPage(indexPageList);
        }

        if (StrUtil.isNotEmpty(remark)) {
            List<String> remarkList =new ArrayList<>();
            remarkList.add(remark);
            consumerMg.setRemark(remarkList);
        }

        if (StrUtil.isNotEmpty(connectList)) {
            List<String> connectsList = new ArrayList<>();
            connectsList.add(connectList);
            consumerMg.setConnectList(connectsList);
        }

        if (StrUtil.isNotEmpty(address)) {
            List<String> addressList = new ArrayList<>();
            addressList.add(address);
            consumerMg.setAddress(addressList);
        }

        if (StrUtil.isNotEmpty(logistialAddress)) {
            List<String> logistialAddressList = new ArrayList<>();
            logistialAddressList.add(logistialAddress);
            consumerMg.setLogistialAddress(logistialAddressList);
        }

        if (StrUtil.isNotEmpty(text1)) {
            List<String> textList1 = new ArrayList<>();
            textList1.add(text1);
            consumerMg.setText1(textList1);
        }

        if (StrUtil.isNotEmpty(text2)) {
            List<String> textList2 = new ArrayList<>();
            textList2.add(text2);
            consumerMg.setText2(textList2);
        }

        if (StrUtil.isNotEmpty(text3)) {
            List<String> textList3 = new ArrayList<>();
            textList3.add(text3);
            consumerMg.setText3(textList3);
        }

        if (StrUtil.isNotEmpty(text4)) {
            List<String> textList4 = new ArrayList<>();
            textList4.add(text4);
            consumerMg.setText4(textList4);
        }

        if (StrUtil.isNotEmpty(text5)) {
            List<String> textList5 = new ArrayList<>();
            textList5.add(text5);
            consumerMg.setText5(textList5);
        }

        if (StrUtil.isNotEmpty(text6)) {
            List<String> textList6 = new ArrayList<>();
            textList6.add(text6);
            consumerMg.setText6(textList6);
        }

        String userName = SecurityUtil.getLoginUser().getUsername();
        consumerMg.setGroup(userName);

        JSONObject jsonObject = consumerMgService.findConsumerMgByEntity(consumerMg,pageIndex-1,pageSize);
        return Result.of(jsonObject);
    }
    @GetMapping("findList")
    public Result findList(@RequestParam(value = "pageIndex",defaultValue = "1")Integer pageIndex,
                           @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize
    ){

        int start = (pageIndex-1)*pageSize;
        List<SysConsumer> page = sysConsumerRepository.findAllByParam(start,pageSize);
        return Result.of(page);
    }

    @RequestMapping("upLoadData")
    public Result upLoadData(
            MultipartFile multipartFile,
            @RequestParam(value = "tag", defaultValue = "false") String tag,
            @RequestParam(value = "share",defaultValue = "false")String share){
        System.out.println( multipartFile.getOriginalFilename());
        String fileName = multipartFile.getOriginalFilename();
        if (!(fileName.endsWith(".xls")||fileName.endsWith(".xlsx"))){
            return Result.of(400,false,"只能上传EXCEL文件");
        }
        boolean b = Boolean.parseBoolean(tag);
        boolean s = Boolean.parseBoolean(share);
        List<List<Object>> list = new ArrayList<>();
        try {
            list = ExcelUtil.getReader(multipartFile.getInputStream()).read();
        }catch (IOException e){
            e.printStackTrace();
        }

        int size = list.size();
        String userName = SecurityUtil.getLoginUser().getUsername();
        if (userName==null){
            return Result.of(400,false,"用户不存在");
        }
        Boolean upFile = false;
        Collection<GrantedAuthority> collection = SecurityUtil.getLoginUser().getAuthorities();
        for (GrantedAuthority grantedAuthority : collection) {
            String c =   grantedAuthority.getAuthority();
            SysAuthority sysAuthority =  sysAuthorityRepository.findAllByAuthorityName(c);
            if (sysAuthority!=null&&sysAuthority.getUpFile()!=null&&sysAuthority.getUpFile().equals("on")){
                upFile = true;
                break;
            }
        }
        if (!upFile){
            return Result.of(400,false,"权限不足！");
        }
        for (int i = 1; i < size; i++) {
            List<Object> rowList = list.get(i);
            String phone = valueOf(rowList.get(6));
            if ( rowList.get(6)==null){
                continue;
            }
            ConsumerMg consumerMg = consumerMgService.findConsumerMgById(phone);
            int len = rowList.size();
            if (consumerMg==null){
                consumerMg = new ConsumerMg();
                consumerMg.setId(phone);
                try {
                    if (0<len&&null!=rowList.get(0)){
                        consumerMg.setLevel(valueOf(rowList.get(0)));
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

                try {
                    if (1<len&&null!=rowList.get(1)){
                        consumerMg.setIsNew(valueOf(rowList.get(1)));
                    }
                }catch (Exception e){  e.printStackTrace();}

                try {
                    if (null!=rowList.get(2)&&2<len){
                        consumerMg.setCType(valueOf(rowList.get(2)));
                    }
                }catch (Exception e){}

                try {
                    if (null!=rowList.get(3)&&3<len){
                        List<String> menuList = new ArrayList<>();
                        menuList.add(valueOf(rowList.get(3)));
                        consumerMg.setMenu(menuList);
                    }
                }catch (Exception e){   }

                try {
                    if (null!=rowList.get(4)&&4<len){
                        List<String> companyList = new ArrayList<>();
                        companyList.add(valueOf(rowList.get(4)));
                        consumerMg.setCompany(companyList);
                    }
                }catch (Exception e){}

                try {
                    if (null!=rowList.get(5)&&5<len){
                        List<String> cNameList = new ArrayList<>();
                        cNameList.add(valueOf(rowList.get(5)));
                        consumerMg.setCName(cNameList);
                    }
                }catch (Exception e){}

                try {
                    if (null!=rowList.get(6)&&6<len){
                        consumerMg.setPhone(valueOf(rowList.get(6)));
                    }
                }catch (Exception e){}

                try {
                    if (null!=rowList.get(7)&&7<len){
                        List<String> productList = new ArrayList<>();
                        productList.add(valueOf(rowList.get(7)));
                        consumerMg.setProduct(productList);
                    }
                }catch (Exception e){

                }

                try {

                    if (null!=rowList.get(8)&&8<len){
                        List<String> imgUrlList = new ArrayList<>();
                        imgUrlList.add(valueOf(rowList.get(8)));
                        consumerMg.setImgUrl(imgUrlList);
                    }
                }catch (Exception e){

                }

                try {
                    if (null!=rowList.get(9)&&9<len){

                        List<String> videoUrlList = new ArrayList<>();
                        videoUrlList.add(valueOf(rowList.get(9)));
                        consumerMg.setVideoUrl(videoUrlList);
                    }
                }catch (Exception e){

                }

                try {
                    if (null!=rowList.get(10)&&10<len){
                        List<String> salesmanList = new ArrayList<>();
                        salesmanList.add(valueOf(rowList.get(10)));
                        consumerMg.setSalesman(salesmanList);
                    }
                }catch (Exception e){

                }

                try {
                    if (null!=rowList.get(11)&&11<len){
                        List<String> wxNickList = new ArrayList<>();
                        wxNickList.add(valueOf(rowList.get(11)));
                        consumerMg.setWxNick(wxNickList);
                    }
                }catch (Exception e){

                }

                try {
                    if (null!=rowList.get(12)&&12<len){

                        List<String> wxPhoneList = new ArrayList<>();
                        wxPhoneList.add(valueOf(rowList.get(12)));
                        consumerMg.setWxPhone(wxPhoneList);
                    }
                }catch (Exception e){

                }

                try {
                    if (null!=rowList.get(13)&&13<len){
                        List<String> addressList = new ArrayList<>();
                        addressList.add(valueOf(rowList.get(13)));
                        consumerMg.setAddress(addressList);
                    }
                }catch (Exception e){

                }

                try {
                    if (null!=rowList.get(14)&&14<len){
                        List<String> phone2List = new ArrayList<>();
                        phone2List.add(valueOf(rowList.get(14)));
                        consumerMg.setPhone2(phone2List);

                    }
                }catch (Exception e){

                }

                try {
                    if (null!=rowList.get(15)&&15<len){
                        List<String> salesList = new ArrayList<>();
                        salesList.add(valueOf(rowList.get(15)));
                        consumerMg.setSalesList(salesList);
                    }
                }catch (Exception e){

                }

                try {
                    if (null!=rowList.get(16)&&16<len){
                        List<String> promiseList = new ArrayList<>();
                        promiseList.add(valueOf(rowList.get(16)));
                        consumerMg.setPromise(promiseList);
                    }
                }catch (Exception e){

                }
                try {
                    if (null!=rowList.get(17)&&17<len){
                        List<String> phoneContentList = new ArrayList<>();
                        phoneContentList.add(valueOf(rowList.get(17)));
                        consumerMg.setPhoneContent(phoneContentList);
                    }
                } catch (Exception e) {

                }
                try {
                    if (null!=rowList.get(18)&&18<len){
                        List<String> ksList = new ArrayList<>();
                        ksList.add(valueOf(rowList.get(18)));
                        consumerMg.setKs(ksList);
                    }
                } catch (Exception e) {

                }

                try {
                    if (null!=rowList.get(19)&&19<len){
                        List<String> ksContentList = new ArrayList<>();
                        ksContentList.add(valueOf(rowList.get(19)));
                        consumerMg.setKsContent(ksContentList);
                    }
                } catch (Exception e) {

                }

                try {
                    if (null!=rowList.get(20)&&20<len){
                        List<String> dyList = new ArrayList<>();
                        dyList.add(valueOf(rowList.get(20)));
                        consumerMg.setDy(dyList);
                    }
                } catch (Exception e) {

                }

                try {
                    if (null!=rowList.get(21)&&21<len){
                        List<String> yContentList = new ArrayList<>();
                        yContentList.add(valueOf(rowList.get(21)));
                        consumerMg.setDyContent(yContentList);
                    }
                } catch (Exception e) {

                }


                try {
                    if (null!=rowList.get(22)&&22<len){
                        List<String> pddList = new ArrayList<>();
                        pddList.add(valueOf(rowList.get(22)));
                        consumerMg.setPdd(pddList);
                    }
                } catch (Exception e) {

                }

                try {
                    if (null!=rowList.get(23)&&23<len){
                        List<String> pddContentList = new ArrayList<>();
                        pddContentList.add(valueOf(rowList.get(23)));
                        consumerMg.setPddContent(pddContentList);
                    }
                } catch (Exception e) {

                }


                try {
                    if (null!=rowList.get(24)&&24<len){
                        List<String> qqList = new ArrayList<>();
                        qqList.add(valueOf(rowList.get(24)));
                        consumerMg.setQq(qqList);
                    }
                } catch (Exception e) {

                }

                try {
                    if (null!=rowList.get(25)&&25<len){
                        List<String> qqContentList = new ArrayList<>();
                        qqContentList.add(valueOf(rowList.get(25)));
                        consumerMg.setQqContent(qqContentList);
                    }
                } catch (Exception e) {

                }

                try {
                    if (null!=rowList.get(26)&&26<len){
                        List<String> tbwwList = new ArrayList<>();
                        tbwwList.add(valueOf(rowList.get(26)));
                        consumerMg.setTbww(tbwwList);
                    }
                } catch (Exception e) {

                }

                try {
                    if (null!=rowList.get(27)&&27<len){
                        List<String> tbwwContentList = new ArrayList<>();
                        tbwwContentList.add(valueOf(rowList.get(27)));
                        consumerMg.setTbwwContent(tbwwContentList);
                    }
                } catch (Exception e) {

                }

                try {
                    if (null!=rowList.get(28)&&28<len){
                        List<String> emailList = new ArrayList<>();
                        emailList.add(valueOf(rowList.get(28)));
                        consumerMg.setEmail(emailList);
                    }
                } catch (Exception e) {

                }


                try {
                    if (null!=rowList.get(29)&&29<len){
                        List<String> baiduList = new ArrayList<>();
                        baiduList.add(valueOf(rowList.get(29)));
                        consumerMg.setBaidu(baiduList);
                    }
                } catch (Exception e) {

                }

                try {
                    if (null!=rowList.get(30)&&30<len){

                        List<String> baiduContentList = new ArrayList<>();
                        baiduContentList.add(valueOf(rowList.get(30)));
                        consumerMg.setBaiduContent(baiduContentList);
                    }
                } catch (Exception e) {

                }


                try {
                    if (null!=rowList.get(31)&&31<len){
                        List<String> indexPageList = new ArrayList<>();
                        indexPageList.add(valueOf(rowList.get(31)));
                        consumerMg.setIndexPage(indexPageList);
                    }
                } catch (Exception e) {

                }
                try {
                    if (null!=rowList.get(32)&&32<len){
                        List<String> remarkList = new ArrayList<>();
                        remarkList.add(valueOf(rowList.get(32)));
                        consumerMg.setRemark(remarkList);
                    }
                } catch (Exception e) {

                }

                try {
                    if (null!=rowList.get(33)&&33<len){
                        List<String> connectList = new ArrayList<>();
                        connectList.add(valueOf(rowList.get(33)));
                        consumerMg.setConnectList(connectList);
                    }
                } catch (Exception e) {

                }

                try {
                    if (null!=rowList.get(34)&&34<len){
                        consumerMg.setProvince(valueOf(rowList.get(34)));
                    }
                } catch (Exception e) {

                }

                try {
                    if (null!=rowList.get(35)&&35<len){
                        consumerMg.setCity(valueOf(rowList.get(35)));
                    }
                } catch (Exception e) {

                }

                try {
                    if (null!=rowList.get(36)&&36<len){
                        consumerMg.setArea(valueOf(rowList.get(36)));
                    }
                } catch (Exception e) {

                }

                try {
                    if (null!=rowList.get(37)&&37<len){
                        consumerMg.setVillage(valueOf(rowList.get(37)));
                    }
                } catch (Exception e) {

                }

                try {
                    if (null!=rowList.get(38)&&38<len){
                        List<String> lgistialAddress = new ArrayList<>();
                        lgistialAddress.add(valueOf(rowList.get(38)));
                        consumerMg.setLogistialAddress(lgistialAddress);
                    }
                } catch (Exception e) {

                }
                if (s){
                    consumerMg.setGroup(valueOf(rowList.get(39)));
                }else {
                    consumerMg.setGroup("");
                }

                try {
                    if (null!=rowList.get(40)&&40<len){
                        List<String>  textList1 = new ArrayList<>();
                        textList1.add(valueOf(rowList.get(40)));
                        consumerMg.setText1(textList1);
                    }
                } catch (Exception e) {

                }

                try {
                    if (null!=rowList.get(41)&&41<len){
                        List<String>  textList2 = new ArrayList<>();
                        textList2.add(valueOf(rowList.get(41)));
                        consumerMg.setText2(textList2);
                    }
                } catch (Exception e) {

                }

                try {
                    if (null!=rowList.get(42)&&42<len){
                        List<String>  textList3 = new ArrayList<>();
                        textList3.add(valueOf(rowList.get(42)));
                        consumerMg.setText3(textList3);
                    }
                } catch (Exception e) {

                }

                try {
                    if (null!=rowList.get(43)&&43<len){
                        List<String>  textList4 = new ArrayList<>();
                        textList4.add(valueOf(rowList.get(43)));
                        consumerMg.setText4(textList4);
                    }
                } catch (Exception e) {

                }

                try {
                    if (null!=rowList.get(44)&&44<len){
                        List<String>  textList5 = new ArrayList<>();
                        textList5.add(valueOf(rowList.get(44)));
                        consumerMg.setText5(textList5);
                    }
                } catch (Exception e) {

                }
                try {
                    if (null!=rowList.get(45)&&45<len){
                        List<String>  textList6 = new ArrayList<>();
                        textList6.add(valueOf(rowList.get(45)));
                        consumerMg.setText6(textList6);
                    }
                } catch (Exception e) {

                }
                consumerMgService.saveConsumerMg(consumerMg);
            }else {
                //追加
                if (!b){
                    try {
                        if (null!=rowList.get(0)&&0<len){
                            consumerMg.setLevel(valueOf(rowList.get(0)));
                        }
                    } catch (Exception e) {

                    }
                    try {
                        if (null!=rowList.get(1)&&1<len){
                            consumerMg.setIsNew(valueOf(rowList.get(1)));
                        }
                    } catch (Exception e) {

                    }
                    try {
                        if (null!=rowList.get(2)&&2<len){
                            consumerMg.setCType(valueOf(rowList.get(2)));
                        }
                    } catch (Exception e) {

                    }
                    try {
                        if (null!=rowList.get(3)&&3<len){
                            List<String> menuList = new ArrayList<>();
                            menuList.addAll(consumerMg.getMenu());
                            menuList.add(valueOf(rowList.get(3)));
                            consumerMg.setMenu(menuList);
                        }
                    } catch (Exception e) {

                    }
                    try {
                        if (null!=rowList.get(4)&&4<len){
                            List<String> companyList = new ArrayList<>();
                            companyList.addAll(consumerMg.getCompany());
                            companyList.add(valueOf(rowList.get(4)));
                            consumerMg.setCompany(companyList);
                        }
                    } catch (Exception e) {

                    }
                    try {
                        if (null!=rowList.get(5)&&5<len){
                            List<String> cNameList = new ArrayList<>();
                            cNameList.addAll(consumerMg.getCName());
                            cNameList.add(valueOf(rowList.get(5)));
                            consumerMg.setCName(cNameList);
                        }
                    } catch (Exception e) {

                    }
                    try {
                        if (null!=rowList.get(6)&&6<len){
                            consumerMg.setPhone(valueOf(rowList.get(6)));
                        }
                    } catch (Exception e) {

                    }
                    try {
                        if (null!=rowList.get(7)&&7<len){
                            List<String> productList = new ArrayList<>();
                            productList.addAll(consumerMg.getProduct());
                            productList.add(valueOf(rowList.get(7)));
                            consumerMg.setProduct(productList);
                        }
                    } catch (Exception e) {

                    }
                    try {
                        if (null!=rowList.get(8)&&8<len){
                            List<String> imgUrlList = new ArrayList<>();
                            imgUrlList.addAll(consumerMg.getImgUrl());
                            imgUrlList.add(valueOf(rowList.get(8)));
                            consumerMg.setImgUrl(imgUrlList);
                        }
                    } catch (Exception e) {

                    }
                    try {
                        if (null!=rowList.get(9)&&9<len){
                            List<String> videoUrlList = new ArrayList<>();
                            videoUrlList.addAll(consumerMg.getVideoUrl());
                            videoUrlList.add(valueOf(rowList.get(9)));
                            consumerMg.setVideoUrl(videoUrlList);
                        }
                    } catch (Exception e) {

                    }
                    try {
                        if (null!=rowList.get(10)&&10<len){
                            List<String> salesmanList = new ArrayList<>();
                            salesmanList.addAll(consumerMg.getSalesman());
                            salesmanList.add(valueOf(rowList.get(10)));
                            consumerMg.setSalesman(salesmanList);
                        }
                    } catch (Exception e) {

                    }
                    try {
                        if (null!=rowList.get(11)&&11<len){
                            List<String> wxNickList = new ArrayList<>();
                            wxNickList.addAll(consumerMg.getWxNick());
                            wxNickList.add(valueOf(rowList.get(11)));
                            consumerMg.setWxNick(wxNickList);
                        }
                    } catch (Exception e) {

                    }
                    try {
                        if (null!=rowList.get(12)&&12<len){
                            List<String> wxPhoneList = new ArrayList<>();
                            wxPhoneList.addAll(consumerMg.getWxPhone());
                            wxPhoneList.add(valueOf(rowList.get(12)));
                            consumerMg.setWxPhone(wxPhoneList);
                        }
                    } catch (Exception e) {

                    }
                    try {
                        if (null!=rowList.get(13)&&13<len){
                            List<String> addressList = new ArrayList<>();
                            addressList.addAll(consumerMg.getAddress());
                            addressList.add(valueOf(rowList.get(13)));
                            consumerMg.setAddress(addressList);
                        }
                    } catch (Exception e) {

                    }
                    try {
                        if (null!=rowList.get(14)&&14<len){
                            List<String> phone2List = new ArrayList<>();
                            phone2List.addAll(consumerMg.getPhone2());
                            phone2List.add(valueOf(rowList.get(14)));
                            consumerMg.setPhone2(phone2List);

                        }
                    } catch (Exception e) {

                    }
                    try {
                        if (null!=rowList.get(15)&&15<len){
                            List<String> salesList = new ArrayList<>();
                            salesList.addAll(consumerMg.getSalesList());
                            salesList.add(valueOf(rowList.get(15)));
                            consumerMg.setSalesList(salesList);
                        }
                    } catch (Exception e) {

                    }
                    try {
                        if (null!=rowList.get(16)&&16<len){
                            List<String> promiseList = new ArrayList<>();
                            promiseList.addAll(consumerMg.getPromise());
                            promiseList.add(valueOf(rowList.get(16)));
                            consumerMg.setPromise(promiseList);
                        }
                    } catch (Exception e) {

                    }
                    try {
                        if (null!=rowList.get(17)&&17<len){
                            List<String> phoneContentList = new ArrayList<>();
                            phoneContentList.addAll(consumerMg.getPhoneContent());
                            phoneContentList.add(valueOf(rowList.get(17)));
                            consumerMg.setPhoneContent(phoneContentList);
                        }
                    } catch (Exception e) {

                    }
                    try {
                        if (null!=rowList.get(18)&&18<len){
                            List<String> ksList = new ArrayList<>();
                            ksList.addAll(consumerMg.getKs());
                            ksList.add(valueOf(rowList.get(18)));
                            consumerMg.setKs(ksList);
                        }
                    } catch (Exception e) {

                    }
                    try {
                        if (null!=rowList.get(19)&&19<len){
                            List<String> ksContentList = new ArrayList<>();
                            ksContentList.addAll(consumerMg.getKsContent());
                            ksContentList.add(valueOf(rowList.get(19)));
                            consumerMg.setKsContent(ksContentList);
                        }
                    } catch (Exception e) {

                    }
                    try {
                        if (null!=rowList.get(20)&&20<len){
                            List<String> dyList = new ArrayList<>();
                            dyList.addAll(consumerMg.getDy());
                            dyList.add(valueOf(rowList.get(20)));
                            consumerMg.setDy(dyList);
                        }
                    } catch (Exception e) {

                    }
                    try {
                        if (null!=rowList.get(21)&&21<len){
                            List<String> yContentList = new ArrayList<>();
                            yContentList.addAll(consumerMg.getDyContent());
                            yContentList.add(valueOf(rowList.get(21)));
                            consumerMg.setDyContent(yContentList);
                        }
                    } catch (Exception e) {

                    }
                    try {
                        if (null!=rowList.get(22)&&22<len){
                            List<String> pddList = new ArrayList<>();
                            pddList.addAll(consumerMg.getPdd());
                            pddList.add(valueOf(rowList.get(22)));
                            consumerMg.setPdd(pddList);
                        }
                    } catch (Exception e) {

                    }
                    try {
                        if (null!=rowList.get(23)&&23<len){
                            List<String> pddContentList = new ArrayList<>();
                            pddContentList.addAll(consumerMg.getPdd());
                            pddContentList.add(valueOf(rowList.get(23)));
                            consumerMg.setPddContent(pddContentList);
                        }
                    } catch (Exception e) {

                    }
                    try {
                        if (null!=rowList.get(24)&&24<len){
                            List<String> qqList = new ArrayList<>();
                            qqList.addAll(consumerMg.getQq());
                            qqList.add(valueOf(rowList.get(24)));
                            consumerMg.setQq(qqList);
                        }
                    } catch (Exception e) {

                    }
                    try {
                        if (null!=rowList.get(25)&&25<len){
                            List<String> qqContentList = new ArrayList<>();
                            qqContentList.addAll(consumerMg.getQqContent());
                            qqContentList.add(valueOf(rowList.get(25)));
                            consumerMg.setQqContent(qqContentList);
                        }
                    } catch (Exception e) {

                    }
                    try {
                        if (null!=rowList.get(26)&&26<len){
                            List<String> tbwwList = new ArrayList<>();
                            tbwwList.addAll(consumerMg.getTbww());
                            tbwwList.add(valueOf(rowList.get(26)));
                            consumerMg.setTbww(tbwwList);
                        }
                    } catch (Exception e) {

                    }
                    try {
                        if (null!=rowList.get(27)&&27<len){
                            List<String> tbwwContentList = new ArrayList<>();
                            tbwwContentList.addAll(consumerMg.getTbwwContent());
                            tbwwContentList.add(valueOf(rowList.get(27)));
                            consumerMg.setTbwwContent(tbwwContentList);
                        }
                    } catch (Exception e) {

                    }
                    try {
                        if (null!=rowList.get(28)&&28<len){
                            List<String> emailList = new ArrayList<>();
                            emailList.addAll(consumerMg.getEmail());
                            emailList.add(valueOf(rowList.get(28)));
                            consumerMg.setEmail(emailList);
                        }
                    } catch (Exception e) {

                    }
                    try {
                        if (null!=rowList.get(29)&&29<len){
                            List<String> baiduList = new ArrayList<>();
                            baiduList.addAll(consumerMg.getBaidu());
                            baiduList.add(valueOf(rowList.get(29)));
                            consumerMg.setBaidu(baiduList);
                        }
                    } catch (Exception e) {

                    }
                    try {
                        if (null!=rowList.get(30)&&30<len){
                            List<String> baiduContentList = new ArrayList<>();
                            baiduContentList.addAll(consumerMg.getBaiduContent());
                            baiduContentList.add(valueOf(rowList.get(30)));
                            consumerMg.setBaiduContent(baiduContentList);
                        }
                    } catch (Exception e) {

                    }
                    try {
                        if (null!=rowList.get(31)&&31<len){
                            List<String> indexPageList = new ArrayList<>();
                            indexPageList.addAll(consumerMg.getIndexPage());
                            indexPageList.add(valueOf(rowList.get(31)));
                            consumerMg.setIndexPage(indexPageList);
                        }
                    } catch (Exception e) {

                    }
                    try {
                        if (null!=rowList.get(32)&&32<len){
                            List<String> remarkList = new ArrayList<>();
                            remarkList.addAll(consumerMg.getRemark());
                            remarkList.add(valueOf(rowList.get(32)));
                            consumerMg.setRemark(remarkList);
                        }
                    } catch (Exception e) {

                    }
                    try {
                        if (null!=rowList.get(33)&&33<len){
                            List<String> connectList = new ArrayList<>();
                            connectList.addAll(consumerMg.getConnectList());
                            connectList.add(valueOf(rowList.get(33)));
                            consumerMg.setConnectList(connectList);
                        }
                    } catch (Exception e) {

                    }
                    try {
                        if (null!=rowList.get(34)&&34<len){
                            consumerMg.setProvince(valueOf(rowList.get(34)));
                        }
                    } catch (Exception e) {

                    }
                    try {
                        if (null!=rowList.get(35)&&35<len){
                            consumerMg.setCity(valueOf(rowList.get(35)));
                        }
                    } catch (Exception e) {

                    }
                    try {
                        if (null!=rowList.get(36)&&36<len){
                            consumerMg.setArea(valueOf(rowList.get(36)));
                        }
                    } catch (Exception e) {

                    }
                    try {
                        if (null!=rowList.get(37)&&37<len){
                            consumerMg.setVillage(valueOf(rowList.get(37)));
                        }
                    } catch (Exception e) {

                    }
                    try {
                        if (null!=rowList.get(38)&&38<len){
                            List<String> lgistialAddress = new ArrayList<>();
                            lgistialAddress.addAll(consumerMg.getLogistialAddress());
                            lgistialAddress.add(valueOf(rowList.get(38)));
                            consumerMg.setLogistialAddress(lgistialAddress);
                        }
                    } catch (Exception e) {

                    }
                    if (s){
                        consumerMg.setGroup(valueOf(rowList.get(39)));
                    }else {
                        consumerMg.setGroup("");
                    }

                    try {
                        if (null!=rowList.get(40)&&40<len){
                            List<String>  textList1 = new ArrayList<>();
                            textList1.addAll(consumerMg.getText1());
                            textList1.add(valueOf(rowList.get(40)));
                            consumerMg.setText1(textList1);
                        }
                    } catch (Exception e) {

                    }
                    try {
                        if (null!=rowList.get(41)&&41<len){
                            List<String>  textList2 = new ArrayList<>();
                            textList2.addAll(consumerMg.getText2());
                            textList2.add(valueOf(rowList.get(41)));
                            consumerMg.setText2(textList2);
                        }
                    } catch (Exception e) {

                    }
                    try {
                        if (null!=rowList.get(42)&&42<len){
                            List<String>  textList3 = new ArrayList<>();
                            textList3.addAll(consumerMg.getText3());
                            textList3.add(valueOf(rowList.get(42)));
                            consumerMg.setText3(textList3);
                        }
                    } catch (Exception e) {

                    }
                    try {
                        if (null!=rowList.get(43)&&43<len){
                            List<String>  textList4 = new ArrayList<>();
                            textList4.addAll(consumerMg.getText4());
                            textList4.add(valueOf(rowList.get(43)));
                            consumerMg.setText4(textList4);
                        }
                    } catch (Exception e) {

                    }
                    try {
                        if (null!=rowList.get(44)&&44<len){
                            List<String>  textList5 = new ArrayList<>();
                            textList5.addAll(consumerMg.getText5());
                            textList5.add(valueOf(rowList.get(44)));
                            consumerMg.setText5(textList5);
                        }
                    } catch (Exception e) {

                    }
                    try {
                        if (null!=rowList.get(45)&&45<len){
                            List<String>  textList6 = new ArrayList<>();
                            textList6.addAll(consumerMg.getText6());
                            textList6.add(valueOf(rowList.get(45)));
                            consumerMg.setText6(textList6);
                        }
                    } catch (Exception e) {

                    }
                }else {
                    consumerMg.setId(phone);
                    try {
                        if (0<len&&null!=rowList.get(0)){
                            consumerMg.setLevel(valueOf(rowList.get(0)));
                        }
                    }catch (Exception e){

                    }

                    try {
                        if (1<len&&null!=rowList.get(1)){
                            consumerMg.setIsNew(valueOf(rowList.get(1)));
                        }
                    }catch (Exception e){   }

                    try {
                        if (null!=rowList.get(2)&&2<len){
                            consumerMg.setCType(valueOf(rowList.get(2)));
                        }
                    }catch (Exception e){}

                    try {
                        if (null!=rowList.get(3)&&3<len){
                            List<String> menuList = new ArrayList<>();
                            menuList.add(valueOf(rowList.get(3)));
                            consumerMg.setMenu(menuList);
                        }
                    }catch (Exception e){   }

                    try {
                        if (null!=rowList.get(4)&&4<len){
                            List<String> companyList = new ArrayList<>();
                            companyList.add(valueOf(rowList.get(4)));
                            consumerMg.setCompany(companyList);
                        }
                    }catch (Exception e){}

                    try {
                        if (null!=rowList.get(5)&&5<len){
                            List<String> cNameList = new ArrayList<>();
                            cNameList.add(valueOf(rowList.get(5)));
                            consumerMg.setCName(cNameList);
                        }
                    }catch (Exception e){}

                    try {
                        if (null!=rowList.get(6)&&6<len){
                            consumerMg.setPhone(valueOf(rowList.get(6)));
                        }
                    }catch (Exception e){}

                    try {
                        if (null!=rowList.get(7)&&7<len){
                            List<String> productList = new ArrayList<>();
                            productList.add(valueOf(rowList.get(7)));
                            consumerMg.setProduct(productList);
                        }
                    }catch (Exception e){

                    }

                    try {

                        if (null!=rowList.get(8)&&8<len){
                            List<String> imgUrlList = new ArrayList<>();
                            imgUrlList.add(valueOf(rowList.get(8)));
                            consumerMg.setImgUrl(imgUrlList);
                        }
                    }catch (Exception e){

                    }

                    try {
                        if (null!=rowList.get(9)&&9<len){

                            List<String> videoUrlList = new ArrayList<>();
                            videoUrlList.add(valueOf(rowList.get(9)));
                            consumerMg.setVideoUrl(videoUrlList);
                        }
                    }catch (Exception e){

                    }

                    try {
                        if (null!=rowList.get(10)&&10<len){
                            List<String> salesmanList = new ArrayList<>();
                            salesmanList.add(valueOf(rowList.get(10)));
                            consumerMg.setSalesman(salesmanList);
                        }
                    }catch (Exception e){

                    }

                    try {
                        if (null!=rowList.get(11)&&11<len){
                            List<String> wxNickList = new ArrayList<>();
                            wxNickList.add(valueOf(rowList.get(11)));
                            consumerMg.setWxNick(wxNickList);
                        }
                    }catch (Exception e){

                    }

                    try {
                        if (null!=rowList.get(12)&&12<len){

                            List<String> wxPhoneList = new ArrayList<>();
                            wxPhoneList.add(valueOf(rowList.get(12)));
                            consumerMg.setWxPhone(wxPhoneList);
                        }
                    }catch (Exception e){

                    }

                    try {
                        if (null!=rowList.get(13)&&13<len){
                            List<String> addressList = new ArrayList<>();
                            addressList.add(valueOf(rowList.get(13)));
                            consumerMg.setAddress(addressList);
                        }
                    }catch (Exception e){

                    }

                    try {
                        if (null!=rowList.get(14)&&14<len){
                            List<String> phone2List = new ArrayList<>();
                            phone2List.add(valueOf(rowList.get(14)));
                            consumerMg.setPhone2(phone2List);

                        }
                    }catch (Exception e){

                    }

                    try {
                        if (null!=rowList.get(15)&&15<len){
                            List<String> salesList = new ArrayList<>();
                            salesList.add(valueOf(rowList.get(15)));
                            consumerMg.setSalesList(salesList);
                        }
                    }catch (Exception e){

                    }

                    try {
                        if (null!=rowList.get(16)&&16<len){
                            List<String> promiseList = new ArrayList<>();
                            promiseList.add(valueOf(rowList.get(16)));
                            consumerMg.setPromise(promiseList);
                        }
                    }catch (Exception e){

                    }
                    try {
                        if (null!=rowList.get(17)&&17<len){
                            List<String> phoneContentList = new ArrayList<>();
                            phoneContentList.add(valueOf(rowList.get(17)));
                            consumerMg.setPhoneContent(phoneContentList);
                        }
                    } catch (Exception e) {

                    }
                    try {
                        if (null!=rowList.get(18)&&18<len){
                            List<String> ksList = new ArrayList<>();
                            ksList.add(valueOf(rowList.get(18)));
                            consumerMg.setKs(ksList);
                        }
                    } catch (Exception e) {

                    }

                    try {
                        if (null!=rowList.get(19)&&19<len){
                            List<String> ksContentList = new ArrayList<>();
                            ksContentList.add(valueOf(rowList.get(19)));
                            consumerMg.setKsContent(ksContentList);
                        }
                    } catch (Exception e) {

                    }

                    try {
                        if (null!=rowList.get(20)&&20<len){
                            List<String> dyList = new ArrayList<>();
                            dyList.add(valueOf(rowList.get(20)));
                            consumerMg.setDy(dyList);
                        }
                    } catch (Exception e) {

                    }

                    try {
                        if (null!=rowList.get(21)&&21<len){
                            List<String> yContentList = new ArrayList<>();
                            yContentList.add(valueOf(rowList.get(21)));
                            consumerMg.setDyContent(yContentList);
                        }
                    } catch (Exception e) {

                    }


                    try {
                        if (null!=rowList.get(22)&&22<len){
                            List<String> pddList = new ArrayList<>();
                            pddList.add(valueOf(rowList.get(22)));
                            consumerMg.setPdd(pddList);
                        }
                    } catch (Exception e) {

                    }

                    try {
                        if (null!=rowList.get(23)&&23<len){
                            List<String> pddContentList = new ArrayList<>();
                            pddContentList.add(valueOf(rowList.get(23)));
                            consumerMg.setPddContent(pddContentList);
                        }
                    } catch (Exception e) {

                    }


                    try {
                        if (null!=rowList.get(24)&&24<len){
                            List<String> qqList = new ArrayList<>();
                            qqList.add(valueOf(rowList.get(24)));
                            consumerMg.setQq(qqList);
                        }
                    } catch (Exception e) {

                    }

                    try {
                        if (null!=rowList.get(25)&&25<len){
                            List<String> qqContentList = new ArrayList<>();
                            qqContentList.add(valueOf(rowList.get(25)));
                            consumerMg.setQqContent(qqContentList);
                        }
                    } catch (Exception e) {

                    }

                    try {
                        if (null!=rowList.get(26)&&26<len){
                            List<String> tbwwList = new ArrayList<>();
                            tbwwList.add(valueOf(rowList.get(26)));
                            consumerMg.setTbww(tbwwList);
                        }
                    } catch (Exception e) {

                    }

                    try {
                        if (null!=rowList.get(27)&&27<len){
                            List<String> tbwwContentList = new ArrayList<>();
                            tbwwContentList.add(valueOf(rowList.get(27)));
                            consumerMg.setTbwwContent(tbwwContentList);
                        }
                    } catch (Exception e) {

                    }

                    try {
                        if (null!=rowList.get(28)&&28<len){
                            List<String> emailList = new ArrayList<>();
                            emailList.add(valueOf(rowList.get(28)));
                            consumerMg.setEmail(emailList);
                        }
                    } catch (Exception e) {

                    }


                    try {
                        if (null!=rowList.get(29)&&29<len){
                            List<String> baiduList = new ArrayList<>();
                            baiduList.add(valueOf(rowList.get(29)));
                            consumerMg.setBaidu(baiduList);
                        }
                    } catch (Exception e) {

                    }

                    try {
                        if (null!=rowList.get(30)&&30<len){

                            List<String> baiduContentList = new ArrayList<>();
                            baiduContentList.add(valueOf(rowList.get(30)));
                            consumerMg.setBaiduContent(baiduContentList);
                        }
                    } catch (Exception e) {

                    }


                    try {
                        if (null!=rowList.get(31)&&31<len){
                            List<String> indexPageList = new ArrayList<>();
                            indexPageList.add(valueOf(rowList.get(31)));
                            consumerMg.setIndexPage(indexPageList);
                        }
                    } catch (Exception e) {

                    }
                    try {
                        if (null!=rowList.get(32)&&32<len){
                            List<String> remarkList = new ArrayList<>();
                            remarkList.add(valueOf(rowList.get(32)));
                            consumerMg.setRemark(remarkList);
                        }
                    } catch (Exception e) {

                    }

                    try {
                        if (null!=rowList.get(33)&&33<len){
                            List<String> connectList = new ArrayList<>();
                            connectList.add(valueOf(rowList.get(33)));
                            consumerMg.setConnectList(connectList);
                        }
                    } catch (Exception e) {

                    }

                    try {
                        if (null!=rowList.get(34)&&34<len){
                            consumerMg.setProvince(valueOf(rowList.get(34)));
                        }
                    } catch (Exception e) {

                    }

                    try {
                        if (null!=rowList.get(35)&&35<len){
                            consumerMg.setCity(valueOf(rowList.get(35)));
                        }
                    } catch (Exception e) {

                    }

                    try {
                        if (null!=rowList.get(36)&&36<len){
                            consumerMg.setArea(valueOf(rowList.get(36)));
                        }
                    } catch (Exception e) {

                    }

                    try {
                        if (null!=rowList.get(37)&&37<len){
                            consumerMg.setVillage(valueOf(rowList.get(37)));
                        }
                    } catch (Exception e) {

                    }

                    try {
                        if (null!=rowList.get(38)&&38<len){
                            List<String> lgistialAddress = new ArrayList<>();
                            lgistialAddress.add(valueOf(rowList.get(38)));
                            consumerMg.setLogistialAddress(lgistialAddress);
                        }
                    } catch (Exception e) {

                    }
                    if (s){
                        consumerMg.setGroup(valueOf(rowList.get(39)));
                    }else {
                        consumerMg.setGroup("");
                    }

                    try {
                        if (null!=rowList.get(40)&&40<len){
                            List<String>  textList1 = new ArrayList<>();
                            textList1.add(valueOf(rowList.get(40)));
                            consumerMg.setText1(textList1);
                        }
                    } catch (Exception e) {

                    }

                    try {
                        if (null!=rowList.get(41)&&41<len){
                            List<String>  textList2 = new ArrayList<>();
                            textList2.add(valueOf(rowList.get(41)));
                            consumerMg.setText2(textList2);
                        }
                    } catch (Exception e) {

                    }

                    try {
                        if (null!=rowList.get(42)&&42<len){
                            List<String>  textList3 = new ArrayList<>();
                            textList3.add(valueOf(rowList.get(42)));
                            consumerMg.setText3(textList3);
                        }
                    } catch (Exception e) {

                    }

                    try {
                        if (null!=rowList.get(43)&&43<len){
                            List<String>  textList4 = new ArrayList<>();
                            textList4.add(valueOf(rowList.get(43)));
                            consumerMg.setText4(textList4);
                        }
                    } catch (Exception e) {

                    }

                    try {
                        if (null!=rowList.get(44)&&44<len){
                            List<String>  textList5 = new ArrayList<>();
                            textList5.add(valueOf(rowList.get(44)));
                            consumerMg.setText5(textList5);
                        }
                    } catch (Exception e) {

                    }
                    try {
                        if (null!=rowList.get(45)&&45<len){
                            List<String>  textList6 = new ArrayList<>();
                            textList6.add(valueOf(rowList.get(45)));
                            consumerMg.setText6(textList6);
                        }
                    } catch (Exception e) {

                    }
                }
                consumerMgService.saveConsumerMg(consumerMg);
            }
        }
        return Result.of(200);
    }
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping("/upload")
    public Result upload(
            MultipartFile multipartFile,
            @RequestParam(value = "tag", defaultValue = "false") String tag,
            @RequestParam(value = "share",defaultValue = "false")String share
    ) throws IOException {

        String userName = SecurityUtil.getLoginUser().getUsername();
        boolean b = Boolean.parseBoolean(tag);
        boolean s = Boolean.parseBoolean(share);
        List<List<Object>> list = ExcelUtil.getReader(multipartFile.getInputStream()).read();
        int size = list.size();

        for (int i = 1; i < size; i++) {
            List<Object> rowList = list.get(i);
            SysConsumer sysConsumer = new SysConsumer();
            try {
                sysConsumer.setLevel(valueOf(rowList.get(0)));
                sysConsumer.setIsNew(valueOf(rowList.get(1)));
                sysConsumer.setCType(valueOf(rowList.get(2)));
                sysConsumer.setMenu(valueOf(rowList.get(3)));
                sysConsumer.setCompany(valueOf(rowList.get(4)));
                sysConsumer.setCName(valueOf(rowList.get(5)));
                sysConsumer.setPhone(valueOf(rowList.get(6)));
                sysConsumer.setProduct(valueOf(rowList.get(7)));
                sysConsumer.setImgUrl(valueOf(rowList.get(8)));
                sysConsumer.setVideoUrl(valueOf(rowList.get(9)));
                sysConsumer.setSalesman(valueOf(rowList.get(10)));
                sysConsumer.setWxNick(valueOf(rowList.get(11)));
                sysConsumer.setWxPhone(valueOf(rowList.get(12)));
                sysConsumer.setAddress(valueOf(rowList.get(13)));
                sysConsumer.setPhone2(valueOf(rowList.get(14)));
                sysConsumer.setSalesList(valueOf(rowList.get(15)));
                sysConsumer.setPromise(valueOf(rowList.get(16)));
                sysConsumer.setPhoneContent(valueOf(rowList.get(17)));
                sysConsumer.setKs(valueOf(rowList.get(18)));
                sysConsumer.setKsContent(valueOf(rowList.get(19)));
                sysConsumer.setDy(valueOf(rowList.get(20)));
                sysConsumer.setDyContent(valueOf(rowList.get(21)));
                sysConsumer.setPdd(valueOf(rowList.get(22)));
                sysConsumer.setPddContent(valueOf(rowList.get(23)));
                sysConsumer.setQq(valueOf(rowList.get(24)));
                sysConsumer.setQqContent(valueOf(rowList.get(25)));
                sysConsumer.setTbww(valueOf(rowList.get(26)));
                sysConsumer.setTbwwContent(valueOf(rowList.get(27)));
                sysConsumer.setEmail(valueOf(rowList.get(28)));
                sysConsumer.setBaidu(valueOf(rowList.get(29)));
                sysConsumer.setBaiduContent(valueOf(rowList.get(30)));
                sysConsumer.setIndexPage(valueOf(rowList.get(31)));
                sysConsumer.setRemark(valueOf(rowList.get(32)));
                sysConsumer.setConnectList(valueOf(rowList.get(33)));
                sysConsumer.setProvince(valueOf(rowList.get(34)));
                sysConsumer.setCity(valueOf(rowList.get(35)));
                sysConsumer.setArea(valueOf(rowList.get(36)));
                sysConsumer.setVillage(valueOf(rowList.get(37)));
                sysConsumer.setLogistialAddress(valueOf(rowList.get(38)));
//                sysConsumer.setGroup(valueOf(rowList.get(39)));
                if (s){
                    sysConsumer.setGroup(userName);
                }else {
                    sysConsumer.setGroup("");
                }
                sysConsumer.setText1(valueOf(rowList.get(40)));
                sysConsumer.setText2(valueOf(rowList.get(41)));
                sysConsumer.setText3(valueOf(rowList.get(42)));
                sysConsumer.setText4(valueOf(rowList.get(43)));
                sysConsumer.setText5(valueOf(rowList.get(44)));
                sysConsumer.setText6(valueOf(rowList.get(45)));
            } catch (IndexOutOfBoundsException e) {
            }
            if (!b) {
                sysConsumerRepository.updateSysConsumerStatus(sysConsumer.getPhone(),userName);
            } else {
                sysConsumerRepository.deleteAllByPhone(sysConsumer.getPhone(),userName);
            }
            sysConsumer.setStats("1");

            sysConsumerRepository.save(sysConsumer);
        }
        return Result.of("success");
    }

    @GetMapping("file/getImg/{fileName}")
    public void getImg(@PathVariable String fileName, HttpServletResponse response) throws UnsupportedEncodingException {
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        byte[] buffer = new byte[1024];
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        OutputStream os = null;
//        String filepath = "/Volumes/SD/myfiles/";
//        String filepath = "D:/picture/";
        File file = new File(filepath+fileName);
        try {
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            os = response.getOutputStream();
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
        } catch (Exception e) {
            System.out.println("Download  failed!");
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (os!=null){
                try {
                    os.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    @GetMapping("findById")
    public Result findById(@RequestParam("id")String id){
        ConsumerMg consumerMg = consumerMgService.findConsumerMgById(id);
        return Result.of(consumerMg);
    }
    @GetMapping("findImgUrl")
    public Result findImgUrl(@RequestParam("phone")String phone) throws IOException {
        String url = "http://"+"222.174.180.94" +":"+"8888/file/getImg/";
//       String url = "http://"+"127.0.0.1" +":"+"8888/file/getImg/";
        List<String> reList = new ArrayList<>();
        List<String> vList = new ArrayList<>();
        JSONObject re = new JSONObject();
//        String path = "/Volumes/SD/myfiles/";
        File file = new File(filepath);
        File[] files = file.listFiles();
        if (files==null){
            return Result.of(null);
        }
        for (File f : files) {
            if (!f.isDirectory()){
                String fileName = f.getName();
                if (fileName.startsWith(phone)){
                    String type = Files.probeContentType(Paths.get(f.getAbsolutePath()));
                    if (type.startsWith("image")){
                        reList.add(url+fileName);
                    }
                    if (type.startsWith("video")){
                        vList.add(url+fileName);
                    }
                }
            }
        }
        re.put("imgList",reList);
        re.put("videoList",vList);
        return Result.of(re);
    }

    @GetMapping("allowDownFile")
    public Result allowDownFile(){
        String userName = SecurityUtil.getLoginUser().getUsername();
        if (userName==null){
            return Result.of(400,false,"用户不存在");
        }
        Boolean upFile = false;
        Collection<GrantedAuthority> collection = SecurityUtil.getLoginUser().getAuthorities();
        for (GrantedAuthority grantedAuthority : collection) {
            String c =   grantedAuthority.getAuthority();
            SysAuthority sysAuthority =  sysAuthorityRepository.findAllByAuthorityName(c);
            if (sysAuthority!=null&&sysAuthority.getUpFile()!=null&&sysAuthority.getUpFile().equals("on")){
                upFile = true;
                break;
            }
        }
        if (!upFile){
            return Result.of(400,false,"权限不足！");
        }
        return Result.of(200);
    }

    @GetMapping("deleteConsumer")
    public Result deleteConsumer(@RequestParam("id")String id){
        consumerMgService.delete(id);
        return Result.of(200);
    }
    @GetMapping("exprotData")
    public void exprotData(  @RequestParam(required = false,name = "level")String level,
                             @RequestParam(required = false,name = "isNew")String isNew,
                             @RequestParam(required = false,name = "cType")String cType,
                             @RequestParam(required = false,name = "menu")String menu,
                             @RequestParam(required = false,name = "company")String company,
                             @RequestParam(required = false,name = "cName")String cName,
                             @RequestParam(required = false,name = "phone")String phone,
                             @RequestParam(required = false,name = "product")String product,
                             @RequestParam(required = false,name = "salesman")String salesman,
                             @RequestParam(required = false,name = "wxNick")String wxNick,
                             @RequestParam(required = false,name = "wxPhone")String wxPhone,
                             @RequestParam(required = false,name = "province")String province,
                             @RequestParam(required = false,name = "city")String city,
                             @RequestParam(required = false,name = "area")String area,
                             @RequestParam(required = false,name = "country")String country,
                             @RequestParam(required = false,name = "village")String village,
                             @RequestParam(required = false,name = "agency")String agency,
                             @RequestParam(required = false,name = "salesList")String salesList,
                             @RequestParam(required = false,name = "promise")String promise,
                             @RequestParam(required = false,name = "phoneContent")String phoneContent,
                             @RequestParam(required = false,name = "ks")String ks,
                             @RequestParam(required = false,name = "ksContent")String ksContent,
                             @RequestParam(required = false,name = "dy")String dy,
                             @RequestParam(required = false,name = "dyContent")String dyContent,
                             @RequestParam(required = false,name = "pdd")String pdd,
                             @RequestParam(required = false,name = "pddContent")String pddContent,
                             @RequestParam(required = false,name = "qq")String qq,
                             @RequestParam(required = false,name = "qqContent")String qqContent,
                             @RequestParam(required = false,name = "tbww")String tbww,
                             @RequestParam(required = false,name = "tbwwContent")String tbwwContent,
                             @RequestParam(required = false,name = "email")String email,
                             @RequestParam(required = false,name = "baidu")String baidu,
                             @RequestParam(required = false,name = "baiduContent")String baiduContent,
                             @RequestParam(required = false,name = "indexPage")String indexPage,
                             @RequestParam(required = false,name = "remark")String remark,
                             @RequestParam(required = false,name = "connectList")String connectList,
                             @RequestParam(required = false,name = "address")String address,
                             @RequestParam(required = false,name = "logistialAddress")String logistialAddress,
                             @RequestParam(required = false,name = "group")String group,
                             @RequestParam(required = false,name = "text1")String text1,
                             @RequestParam(required = false,name = "text2")String text2,
                             @RequestParam(required = false,name = "text3")String text3,
                             @RequestParam(required = false,name = "text4")String text4,
                             @RequestParam(required = false,name = "text5")String text5,
                             @RequestParam(required = false,name = "text6")String text6,
                             @RequestParam(required = false,name = "t")Long t,
                             HttpServletResponse response
    ) throws IOException {
        Date date = new Date(t*1000);
        String time = DateUtil.format(date, "yyyyMMddHHmmss");
        ConsumerMg consumerMg = new ConsumerMg();
        consumerMg.setLevel(level);
        consumerMg.setIsNew(isNew);
        consumerMg.setCType(cType);
        consumerMg.setPhone(phone);
        if (StrUtil.isNotEmpty(menu)) {
            List<String> menuList = new ArrayList<>();
            menuList.add(menu);
            consumerMg.setMenu(menuList);
        }

        if (StrUtil.isNotEmpty(company) ) {
            List<String> companyList = new ArrayList<>();
            companyList.add(company);
            consumerMg.setCompany(companyList);
        }

        if (StrUtil.isNotEmpty(cName)) {
            List<String> cNameList =new ArrayList<>();
            cNameList.add(cName);
            consumerMg.setCName(cNameList);
        }

        if (StrUtil.isNotEmpty(product)) {
            List<String> productList = new ArrayList<>();
            productList.add(product);
            consumerMg.setProduct(productList);
        }

        if (StrUtil.isNotEmpty(salesman)) {
            List<String> salesmanList =new ArrayList<>();
            salesmanList.add(salesman);
            consumerMg.setSalesman(salesmanList);
        }

        if (StrUtil.isNotEmpty(wxNick)) {
            List<String> wxNickList = new ArrayList<>();
            wxNickList.add(wxNick);
            consumerMg.setWxNick(wxNickList);
        }

        if (StrUtil.isNotEmpty(wxPhone)) {
            List<String> wxPhoneList = new ArrayList<>();
            wxPhoneList.add(wxPhone);
            consumerMg.setWxPhone(wxPhoneList);
        }

        consumerMg.setProvince(province);
        consumerMg.setCity(city);

        if (StrUtil.isNotEmpty(salesList)) {
            List<String> saleList = new ArrayList<>();
            saleList.add(salesList);
            consumerMg.setSalesList(saleList);
        }

        if (StrUtil.isNotEmpty(StrUtil.trim(promise))) {
            List<String> promiseList = new ArrayList<>();
            promiseList.add(promise);
            consumerMg.setPromise(promiseList);
        }

        if (StrUtil.isNotEmpty(phoneContent)) {
            List<String> phoneContentList = new ArrayList<>();
            phoneContentList.add(phoneContent);
            consumerMg.setPhoneContent(phoneContentList);
        }

        if (StrUtil.isNotEmpty(ks)) {
            List<String> ksList = new ArrayList<>();
            ksList.add(ks);
            consumerMg.setKs(ksList);
        }

        if (StrUtil.isNotEmpty(ksContent)) {
            List<String> ksContentList = new ArrayList<>();
            ksContentList.add(ksContent);
            consumerMg.setKsContent(ksContentList);
        }

        if (StrUtil.isNotEmpty(dy)) {
            List<String> dyList = new ArrayList<>();
            dyList.add(dy);
            consumerMg.setDy(dyList);
        }

        if (StrUtil.isNotEmpty(dyContent)) {
            List<String> dyContentList = new ArrayList<>();
            dyContentList.add(dyContent);
            consumerMg.setDyContent(dyContentList);
        }

        if (StrUtil.isNotEmpty(pdd)) {
            List<String> pddList = new ArrayList<>();
            pddList.add(pdd);
            consumerMg.setPdd(pddList);
        }

        if (StrUtil.isNotEmpty(pddContent)) {
            List<String> pddContentList = new ArrayList<>();
            pddContentList.add(pddContent);
            consumerMg.setPddContent(pddContentList);
        }

        if (StrUtil.isNotEmpty(qq)) {
            List<String> qqList = new ArrayList<>();
            qqList.add(qq);
            consumerMg.setQq(qqList);
        }

        if (StrUtil.isNotEmpty(qqContent)) {
            List<String> qqContentList = new ArrayList<>();
            qqContentList.add(qqContent);
            consumerMg.setQqContent(qqContentList);
        }

        if (StrUtil.isNotEmpty(tbww)) {
            List<String> tbwwList = new ArrayList<>();
            tbwwList.add(tbww);
            consumerMg.setTbww(tbwwList);
        }

        if (StrUtil.isNotEmpty(tbwwContent)) {
            List<String> tbwwContentList = new ArrayList<>();
            tbwwContentList.add(tbwwContent);
            consumerMg.setTbwwContent(tbwwContentList);
        }

        if (StrUtil.isNotEmpty(email)) {
            List<String> emailList = new ArrayList<>();
            emailList.add(email);
            consumerMg.setEmail(emailList);
        }

        if (StrUtil.isNotEmpty(baidu)) {
            List<String> baiduList = new ArrayList<>();
            baiduList.add(baidu);
            consumerMg.setBaidu(baiduList);
        }

        if (StrUtil.isNotEmpty(baiduContent)) {
            List<String> baiduContentList = new ArrayList<>();
            baiduContentList.add(baiduContent);
            consumerMg.setBaiduContent(baiduContentList);
        }

        if (StrUtil.isNotEmpty(indexPage)) {
            List<String> indexPageList = new ArrayList<>();
            indexPageList.add(indexPage);
            consumerMg.setIndexPage(indexPageList);
        }

        if (StrUtil.isNotEmpty(remark)) {
            List<String> remarkList =new ArrayList<>();
            remarkList.add(remark);
            consumerMg.setRemark(remarkList);
        }

        if (StrUtil.isNotEmpty(connectList)) {
            List<String> connectsList = new ArrayList<>();
            connectsList.add(connectList);
            consumerMg.setConnectList(connectsList);
        }

        if (StrUtil.isNotEmpty(address)) {
            List<String> addressList = new ArrayList<>();
            addressList.add(address);
            consumerMg.setAddress(addressList);
        }

        if (StrUtil.isNotEmpty(logistialAddress)) {
            List<String> logistialAddressList = new ArrayList<>();
            logistialAddressList.add(logistialAddress);
            consumerMg.setLogistialAddress(logistialAddressList);
        }

        if (StrUtil.isNotEmpty(text1)) {
            List<String> textList1 = new ArrayList<>();
            textList1.add(text1);
            consumerMg.setText1(textList1);
        }

        if (StrUtil.isNotEmpty(text2)) {
            List<String> textList2 = new ArrayList<>();
            textList2.add(text2);
            consumerMg.setText2(textList2);
        }

        if (StrUtil.isNotEmpty(text3)) {
            List<String> textList3 = new ArrayList<>();
            textList3.add(text3);
            consumerMg.setText3(textList3);
        }

        if (StrUtil.isNotEmpty(text4)) {
            List<String> textList4 = new ArrayList<>();
            textList4.add(text4);
            consumerMg.setText4(textList4);
        }

        if (StrUtil.isNotEmpty(text5)) {
            List<String> textList5 = new ArrayList<>();
            textList5.add(text5);
            consumerMg.setText5(textList5);
        }

        if (StrUtil.isNotEmpty(text6)) {
            List<String> textList6 = new ArrayList<>();
            textList6.add(text6);
            consumerMg.setText6(textList6);
        }


        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");



        Long pageSize = 500000L;
        Long pageIndex = 0L;
        List<ConsumerMg> consumerMgList ;
        List<List<Object>> excelList = new ArrayList<>() ;
        //循环分页查询
        String savePath = cachePath+time+"/";
        while (true){
            consumerMgList = consumerMgService.findConsumerMgByEntity(consumerMg,  pageIndex,  pageSize);
            //没有数据  立即结束循环
            if (consumerMgList.size()==0){
                break;
            }
            List<Object> tmp;
            List<Object> title = new ArrayList<Object>(){
                {
                    add(0,"级别");
                    add(1,"级别");
                    add(2,"类别");
                    add(3,"目录");
                    add(4,"公司");
                    add(5,"姓名");
                    add(6,"手机");
                    add(7,"产品");
                    add(8,"图片");
                    add(9,"视频");
                    add(10,"业务员");
                    add(11,"微信昵称");
                    add(12,"存在手机");
                    add(13,"地址");
                    add(14,"更多电话");
                    add(15,"销售记录");
                    add(16,"信用评估");
                    add(17,"电话内容");
                    add(18,"快手");
                    add(19,"快手内容");
                    add(20,"抖音");
                    add(21,"抖音内容");
                    add(22,"拼多多");
                    add(23,"拼多多内容");
                    add(24,"qq");
                    add(25,"qq内容");
                    add(26,"淘宝旺旺");
                    add(27,"旺旺内容");
                    add(28,"邮箱");
                    add(29,"百度");
                    add(30,"百度内容");
                    add(31,"主页");
                    add(32,"备注");
                    add(33,"沟通记录");
                    add(34,"省份");
                    add(35,"地市");
                    add(36,"县市");
                    add(37,"街道");
                    add(38,"曾用物流");
                    add(39,"分组");
                    add(40,"备用一");
                    add(41,"备用二");
                    add(42,"备用三");
                    add(43,"备用四");
                    add(44,"备用五");
                    add(45,"备用六");
                }
            };

            excelList.add(title);
            for (ConsumerMg mg : consumerMgList) {
                tmp  = new ArrayList<>();
                tmp.add(0,mg.getLevel());
                tmp.add(1,mg.getIsNew());
                tmp.add(2,mg.getCType());
                tmp.add(3,formart(mg.getMenu()));
                tmp.add(4,formart(mg.getCompany()));
                tmp.add(5,formart(mg.getCName()));
                tmp.add(6,mg.getPhone());
                tmp.add(7,formart(mg.getProduct()));
                tmp.add(8,formart(mg.getImgUrl()));
                tmp.add(9,formart(mg.getVideoUrl()));
                tmp.add(10,formart(mg.getSalesman()));
                tmp.add(11,formart(mg.getWxNick()));
                tmp.add(12,formart(mg.getWxPhone()));
                tmp.add(13,formart(mg.getAddress()));
                tmp.add(14,formart(mg.getPhone2()));
                tmp.add(15,formart(mg.getSalesList()));
                tmp.add(16,formart(mg.getPromise()));
                tmp.add(17,formart(mg.getPhoneContent()));
                tmp.add(18,formart(mg.getKs()));
                tmp.add(19,formart(mg.getKsContent()));
                tmp.add(20,formart(mg.getDy()));
                tmp.add(21,formart(mg.getDyContent()));
                tmp.add(22,formart(mg.getPdd()));
                tmp.add(23,formart(mg.getPddContent()));
                tmp.add(24,formart(mg.getQq()));
                tmp.add(25,formart(mg.getQqContent()));
                tmp.add(26,formart(mg.getTbww()));
                tmp.add(27,formart(mg.getTbwwContent()));
                tmp.add(28,formart(mg.getEmail()));
                tmp.add(29,formart(mg.getBaidu()));
                tmp.add(30,formart(mg.getBaiduContent()));
                tmp.add(31,formart(mg.getIndexPage()));
                tmp.add(32,formart(mg.getRemark()));
                tmp.add(33,formart(mg.getConnectList()));
                tmp.add(34,(mg.getProvince()));
                tmp.add(35,mg.getCity());
                tmp.add(36,mg.getArea());
                tmp.add(37,mg.getCountry());
                tmp.add(38,formart(mg.getLogistialAddress()));
                tmp.add(39,mg.getGroup());
                tmp.add(40,formart(mg.getText1()));
                tmp.add(41,formart(mg.getText2()));
                tmp.add(42,formart(mg.getText3()));
                tmp.add(43,formart(mg.getText4()));
                tmp.add(44,formart(mg.getText5()));
                tmp.add(45,formart(mg.getText6()));
                excelList.add(tmp);
            }
            //创建带数据的excel

            createExcel(excelList,savePath,time+"-"+pageIndex);
            pageIndex = pageIndex+1;
            consumerMgList.clear();
            excelList.clear();
        }


        File zip =  ZipUtil.zip(savePath);

        InputStream inputStream = null;
        InputStream fis = null;
        OutputStream toClient = null;
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(zip.getName(), "UTF-8"));
        try {
            inputStream = new FileInputStream(zip);
            fis = new BufferedInputStream(inputStream);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            toClient = new BufferedOutputStream(response.getOutputStream());
            toClient.write(buffer);
        }catch (IOException e){
            if (fis!=null){
                fis.close();
            }
            if (toClient!=null){
                toClient.flush();
                toClient.close();
            }
        }finally {
            if (fis!=null){
                fis.close();
            }
            if (toClient!=null){
                toClient.flush();
                toClient.close();
            }
        }

    }
    private static String valueOf(Object str){
        if (str==null){
            return "";
        }
        return String.valueOf(str);
    }


    private String formart(List list){
        if (list==null||list.size()==0){
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (Object o : list) {
            if (o instanceof  String){
                String str = String.valueOf(o);
                 if (!StrUtil.isBlankIfStr(str)){
                     sb.append(o).append("\n");
                 }

            }

        }
        return sb.toString();
    }

    private void createExcel(List<List<Object>> list,String path,String index){
        File file = new File(path);
        if (!file.exists()){
            file.mkdirs();
        }
        BigExcelWriter writer=null;
        try {
            writer = ExcelUtil.getBigWriter(path+index+".xlsx");
            writer.write(list);
        }catch (Exception e){
            if (writer!=null){
                writer.close();
            }
        }finally {
            if (writer!=null){
                writer.close();
            }
        }



    }
}
