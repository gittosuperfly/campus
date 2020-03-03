package com.cai.campus.server.service.impl;

import com.cai.campus.server.entity.UserDetail;
import com.cai.campus.server.dao.UserDetailDao;
import com.cai.campus.server.service.UserDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (UserDetail)表服务实现类
 *
 * @author 蔡宇飞
 * @since 2020-03-03 17:46:37
 */
@Service("userDetailService")
public class UserDetailServiceImpl implements UserDetailService {
    @Resource
    private UserDetailDao userDetailDao;

    /**
     * 通过ID查询单条数据
     *
     * @param uid 主键
     * @return 实例对象
     */
    @Override
    public UserDetail queryById(Integer uid) {
        return this.userDetailDao.queryById(uid);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<UserDetail> queryAllByLimit(int offset, int limit) {
        return this.userDetailDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param userDetail 实例对象
     * @return 实例对象
     */
    @Override
    public UserDetail insert(UserDetail userDetail) {
        this.userDetailDao.insert(userDetail);
        return userDetail;
    }

    /**
     * 修改数据
     *
     * @param userDetail 实例对象
     * @return 实例对象
     */
    @Override
    public UserDetail update(UserDetail userDetail) {
        this.userDetailDao.update(userDetail);
        return this.queryById(userDetail.getUid());
    }

    /**
     * 通过主键删除数据
     *
     * @param uid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer uid) {
        return this.userDetailDao.deleteById(uid) > 0;
    }
}