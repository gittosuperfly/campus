package com.cai.campus.service;

import com.cai.campus.entity.User;
import com.cai.campus.model.Response;

import java.util.List;

/**
 * (User)表服务接口
 *
 * @author 蔡宇飞
 * @since 2020-02-21 17:05:03
 */
public interface UserService {

    Response<User> register(String phone, String password);

    Response<Object> login(String phone, String password);

    Response<User> query(String type, String value);

    Response<User> update(int uid, String password, String name);


    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<User> queryAllByLimit(int offset, int limit);


    /**
     * 通过主键删除数据
     *
     * @param uid 主键
     * @return 是否成功
     */
    boolean deleteById(Integer uid);

}