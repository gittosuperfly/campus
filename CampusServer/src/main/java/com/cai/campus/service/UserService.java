package com.cai.campus.service;

import com.cai.campus.entity.User;
import java.util.List;

/**
 * (User)表服务接口
 *
 * @author 蔡宇飞
 * @since 2020-02-19 22:14:11
 */
public interface UserService {

    /**
     * 通过ID查询单条数据
     *
     * @param uid 主键
     * @return 实例对象
     */
    User queryById(Integer uid);


    /**
     * 通过PhoneNumber查询单条数据
     *
     * @param phoneNumber 电话号码
     * @return 实例对象
     */
    User queryByPhoneNumber(String phoneNumber);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<User> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User insert(User user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User update(User user);

    /**
     * 通过主键删除数据
     *
     * @param uid 主键
     * @return 是否成功
     */
    boolean deleteById(Integer uid);

}