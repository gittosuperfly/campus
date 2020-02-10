package com.cai.campus.repository;


import org.springframework.stereotype.Repository;

@Repository
public class LoginRepository {
    /**
     * 用户ID是否正确（存在）
     */
    boolean isIdCorrect(String uid){
        return true;
    }

    /**
     * 用户密码是否正确
     */
    boolean isPasswordCorrect(String uid , String password){
        return true;
    }




}


