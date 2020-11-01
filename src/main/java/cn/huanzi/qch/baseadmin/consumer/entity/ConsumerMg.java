package cn.huanzi.qch.baseadmin.consumer.entity;

import lombok.Data;


import javax.persistence.Id;
import java.util.List;

@Data
public class ConsumerMg {
    @Id
    private String id;
    private String phone;
    private String level;
    private String isNew;
    private String cType;
    private List<String> menu;
    private List<String> company;
    private List<String> cName;
    private List<String> product;
    private List<String> imgUrl;
    private List<String> videoUrl;
    private List<String> salesman;
    private List<String> wxNick;
    private List<String> wxPhone;
    private String province;
    private String city;
    private String area;
    private String country;
    private String village;
    private String agency;
    private List<String> phone2;
    private List<String> salesList;
    private List<String> promise;
    private List<String> phoneContent;
    private List<String> ks;
    private List<String> ksContent;
    private List<String> dy;
    private List<String> dyContent;
    private List<String> pdd;
    private List<String> pddContent;
    private List<String> qq;
    private List<String> qqContent;
    private List<String> tbww;
    private List<String> tbwwContent;
    private List<String> email;
    private List<String> baidu;
    private List<String> baiduContent;
    private List<String> indexPage;
    private List<String> remark;
    private List<String> connectList;
    private List<String> address;
    private List<String> logistialAddress;
    private String group;
    private List<String> text1;
    private List<String> text2;
    private List<String> text3;
    private List<String> text4;
    private List<String> text5;
    private List<String> text6;


}
