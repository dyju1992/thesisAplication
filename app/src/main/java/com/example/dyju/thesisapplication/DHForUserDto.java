package com.example.dyju.thesisapplication;

import java.io.Serializable;
import java.util.zip.Inflater;


public class DHForUserDto implements Serializable {

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

    @Override
    public boolean equals(Object o){
        DHForUserDto dhForUserDto = (DHForUserDto) o;
        DhDatas dhDatasToCheck = dhForUserDto.getDhDatas();
        DhDatas thisDhDatas = this.dhDatas;
        return thisDhDatas.getA().equals(dhDatasToCheck.getA());
//        return this.dhDatas.equals(dhForUserDto.getDhDatas());
//        return this.getDhDatas().getA().equals(dhForUserDto.getDhDatas().getA()) &&
//                this.getDhDatas().getAlpha().equals(dhForUserDto.getDhDatas().getAlpha())&&
//                this.getDhDatas().getD().equals(dhForUserDto.getDhDatas().getD()) &&
//                this.getDhDatas().getTheta().equals(dhForUserDto.getDhDatas().getTheta());
    }

//    private boolean checkIfDhDatasAreValid(DhDatas dhDatas, DhDatas dhDatas1){
//        return dhDatas.getA().equals(dhDatas1.getA()) && dhDatas.getD().equals(dhDatas1.getD() &&
//        dhDatas.getTheta().equals(dh))
//    }

    @Override
    public int hashCode(){
//        return (dhDatas.getAlpha() == null || dhDatas.getA() == null || dhDatas.getTheta() == null || dhDatas.getD() == null) ? 0 : 1;
        return dhDatas != null ? 1 : 0;
    }
}
