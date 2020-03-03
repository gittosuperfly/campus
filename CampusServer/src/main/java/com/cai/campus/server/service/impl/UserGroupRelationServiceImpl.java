package com.cai.campus.server.service.impl;

import com.cai.campus.server.entity.UserGroupRelation;
import com.cai.campus.server.dao.UserGroupRelationDao;
import com.cai.campus.server.service.UserGroupRelationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (UserGroupRelation)表服务实现类
 *
 * @author 蔡宇飞
 * @since 2020-03-03 17:46:37
 */
@Service("userGroupRelationService")
public class UserGroupRelationServiceImpl implements UserGroupRelationService {
    @Resource
    private UserGroupRelationDao userGroupRelationDao;

    /**
     * 通过ID查询单条数据
     *
     * @param uid 主键
     * @return 实例对象
     */
    @Override
    public UserGroupRelation queryById(Integer uid) {
        return this.userGroupRelationDao.queryById(uid);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<UserGroupRelation> queryAllByLimit(int offset, int limit) {
        return this.userGroupRelationDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param userGroupRelation 实例对象
     * @return 实例对象
     */
    @Override
    public UserGroupRelation insert(UserGroupRelation userGroupRelation) {
        this.userGroupRelationDao.insert(userGroupRelation);
        return userGroupRelation;
    }

    /**
     * 修改数据
     *
     * @param userGroupRelation 实例对象
     * @return 实例对象
     */
    @Override
    public UserGroupRelation update(UserGroupRelation userGroupRelation) {
        this.userGroupRelationDao.update(userGroupRelation);
        return this.queryById(userGroupRelation.getUid());
    }

    /**
     * 通过主键删除数据
     *
     * @param uid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer uid) {
        return this.userGroupRelationDao.deleteById(uid) > 0;
    }
}