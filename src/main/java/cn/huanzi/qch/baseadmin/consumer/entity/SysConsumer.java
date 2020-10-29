package cn.huanzi.qch.baseadmin.consumer.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "sys_consumer")
@Data
public class SysConsumer {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private long cid;
  private String level;
  private String isNew;
  @Column(nullable = false,name = "c_type")
  private String cType;
  private String menu;
  private String company;
  private String cName;
  private String phone;
  private String product;
  private String imgUrl;
  private String videoUrl;
  private String salesman;
  private String wxNick;
  private String wxPhone;
  private String province;
  private String city;
  private String area;
  private String country;
  private String village;
  private String agency;
  private String phone2;
  private String salesList;
  private String promise;
  private String phoneContent;
  private String ks;
  private String ksContent;
  private String dy;
  private String dyContent;
  private String pdd;
  private String pddContent;
  private String qq;
  private String qqContent;
  private String tbww;
  private String tbwwContent;
  private String email;
  private String baidu;
  private String baiduContent;
  private String indexPage;
  private String remark;
  private String connectList;
  private String address;
  private String logistialAddress;
  @Column(nullable = false,name = "c_group")
  private String group;
  @Column(nullable = false,name = "text_1")
  private String text1;
  @Column(nullable = false,name = "text_2")
  private String text2;
  @Column(nullable = false,name = "text_3")
  private String text3;
  @Column(nullable = false,name = "text_4")
  private String text4;
  @Column(nullable = false,name = "text_5")
  private String text5;
  @Column(nullable = false,name = "text_6")
  private String text6;
  @Column(nullable = false,name = "version")
  private String version;
  @Column(nullable = false,name = "stats")
  private String stats;
}
