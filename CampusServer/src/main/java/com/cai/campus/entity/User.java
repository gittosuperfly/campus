package com.cai.campus.entity;

import lombok.*;
import org.springframework.context.annotation.Bean;

import java.io.Serializable;

/**
 * (User)实体类
 *
 * @author 蔡宇飞
 * @since 2020-02-21 17:04:59
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements Serializable {

    public User(String phone, String password) {
        this.phone = phone;
        this.password = password;
    }

    private static final long serialVersionUID = -90182645782362796L;

    private Integer uid;

    private String phone;

    private String password;

    private String name;
}