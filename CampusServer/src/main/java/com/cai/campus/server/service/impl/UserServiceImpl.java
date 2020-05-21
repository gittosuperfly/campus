package com.cai.campus.server.service.impl;

import com.cai.campus.server.entity.UserAccount;
import com.cai.campus.server.dao.UserAccountDao;
import com.cai.campus.server.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (UserAccount)表服务实现类
 *
 * @author makejava
 * @since 2020-03-14 17:42:07
 */
@Service("UserService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserAccountDao userAccountDao;

    /**
     * 通过ID查询单条数据
     *
     * @param uid 主键
     * @return 实例对象
     */
    @Override
    public UserAccount queryById(Integer uid) {
        return this.userAccountDao.queryById(uid);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param phone 电话
     * @return 实例对象
     */
    @Override
    public UserAccount queryByPhone(String phone) {
        return this.userAccountDao.queryByPhone(phone);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<UserAccount> queryAllByLimit(int offset, int limit) {
        return this.userAccountDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param userAccount 实例对象
     * @return 实例对象
     */
    @Override
    public UserAccount insert(UserAccount userAccount) {
        this.userAccountDao.insert(userAccount);
        return userAccount;
    }

    /**
     * 修改数据
     *
     * @param userAccount 实例对象
     * @return 实例对象
     */
    @Override
    public UserAccount update(UserAccount userAccount) {
        this.userAccountDao.update(userAccount);
        return this.queryById(userAccount.getUid());
    }

    /**
     * 通过主键删除数据
     *
     * @param uid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer uid) {
        return this.userAccountDao.deleteById(uid) > 0;
    }
}