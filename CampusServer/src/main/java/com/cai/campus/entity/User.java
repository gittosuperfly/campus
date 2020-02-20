package com.cai.campus.entity;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;

/**
 * (User)实体类
 *
 * @author 蔡宇飞
 * @since 2020-02-19 22:14:09
 */

@Data
public class User implements Serializable {
    private static final long serialVersionUID = -41141018003352092L;

    private Integer uid;

    private String realname;

    private String nickname;

    private String phonenumber;

    private String password;

}