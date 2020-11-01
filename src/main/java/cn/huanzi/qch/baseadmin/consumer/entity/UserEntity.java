package cn.huanzi.qch.baseadmin.consumer.entity;

import lombok.ToString;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;

@ToString
public class UserEntity  implements Serializable {
    private static final long serialVersionUID = -3258839839160856613L;
    @Id
    private String id;
    private String userName;
    private String passWord;
    private List<String> tags;
    private List<String> hobbyList;


    public List<String> getHobbyList() {
        return hobbyList;
    }

    public void setHobbyList(List<String> hobbyList) {
        this.hobbyList = hobbyList;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }



}
