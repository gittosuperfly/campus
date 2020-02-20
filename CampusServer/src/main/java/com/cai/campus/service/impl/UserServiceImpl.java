package com.cai.campus.service.impl;

import com.cai.campus.entity.User;
import com.cai.campus.dao.UserDao;
import com.cai.campus.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (User)表服务实现类
 *
 * @author 蔡宇飞
 * @since 2020-02-19 22:14:13
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    /**
     * 通过ID查询单条数据
     *
     * @param uid 主键
     * @return 实例对象
     */
    @Override
    public User queryById(Integer uid) {
        return this.userDao.queryById(uid);
    }

    /**
     * 通过PhoneNumber查询单条数据
     *
     * @param phoneNumber 电话号码
     * @return 实例对象
     */
    @Override
    public User queryByPhoneNumber(String phoneNumber) {
        return this.userDao.queryByPhoneNumber(phoneNumber);
    }


    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<User> queryAllByLimit(int offset, int limit) {
        return this.userDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User insert(User user) {
        this.userDao.insert(user);
        return user;
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User update(User user) {
        this.userDao.update(user);
        return this.queryById(user.getUid());
    }

    /**
     * 通过主键删除数据
     *
     * @param uid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer uid) {
        return this.userDao.deleteById(uid) > 0;
    }
}