package com.yiyoupin.stock.model;

import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author wangcc
 * @date 2018/3/24
 * @describe 用户信息
 */

public class UserModel implements Serializable {

    /**
     * id : 89
     * name : 王大锤
     * equipment_id : SWB4R33432
     * nick_name : 小甜
     * phone : 13833336666
     * open_id : 332
     * user_picture : http://www.abc.afdfa.jpg
     * sex : 1
     * birthday : 1999-01-01
     * email : ccytccyt@163.com
     * is_set_login_password : 0
     * is_bind_phone : 1
     */
    private String id;
    private String name;
    private String equipment_id;
    private String nick_name;
    private String phone;
    private String open_id;
    private String user_picture;
    private String sex;
    private String birthday;
    private String email;
    private String consume_total;
    private int is_set_login_password;
    private int is_bind_phone;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEquipment_id() {
        return equipment_id;
    }

    public void setEquipment_id(String equipment_id) {
        this.equipment_id = equipment_id;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOpen_id() {
        return open_id;
    }

    public void setOpen_id(String open_id) {
        this.open_id = open_id;
    }

    public String getUser_picture() {
        return user_picture;
    }

    public void setUser_picture(String user_picture) {
        this.user_picture = user_picture;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIs_set_login_password() {
        return is_set_login_password;
    }

    public void setIs_set_login_password(int is_set_login_password) {
        this.is_set_login_password = is_set_login_password;
    }

    public int getIs_bind_phone() {
        return is_bind_phone;
    }

    public void setIs_bind_phone(int is_bind_phone) {
        this.is_bind_phone = is_bind_phone;
    }

    public String getConsume_total() {
        return TextUtils.isEmpty(consume_total)?"":consume_total;
    }

    public void setConsume_total(String consume_total) {
        this.consume_total = consume_total;
    }
}
