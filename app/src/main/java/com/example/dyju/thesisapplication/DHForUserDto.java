package com.example.dyju.thesisapplication;

import java.util.zip.Inflater;

/**
 * Created by dyju on 2017-04-09.
 */
public class DHForUserDto {

    private Integer userid;

    DhDatas dhDatas;

    public DHForUserDto(Integer userid, DhDatas dhDatas) {
        this.userid = userid;
        this.dhDatas = dhDatas;
    }

    public DHForUserDto() {}

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public DhDatas getDhDatas() {
        return dhDatas;
    }

    public void setDhDatas(DhDatas dhDatas) {
        this.dhDatas = dhDatas;
    }
}
