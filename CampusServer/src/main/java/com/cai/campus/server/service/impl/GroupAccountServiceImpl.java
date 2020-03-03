package com.cai.campus.server.service.impl;

import com.cai.campus.server.entity.GroupAccount;
import com.cai.campus.server.dao.GroupAccountDao;
import com.cai.campus.server.service.GroupAccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (GroupAccount)表服务实现类
 *
 * @author 蔡宇飞
 * @since 2020-03-03 17:46:34
 */
@Service("groupAccountService")
public class GroupAccountServiceImpl implements GroupAccountService {
    @Resource
    private GroupAccountDao groupAccountDao;

    /**
     * 通过ID查询单条数据
     *
     * @param groupId 主键
     * @return 实例对象
     */
    @Override
    public GroupAccount queryById(Integer groupId) {
        return this.groupAccountDao.queryById(groupId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<GroupAccount> queryAllByLimit(int offset, int limit) {
        return this.groupAccountDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param groupAccount 实例对象
     * @return 实例对象
     */
    @Override
    public GroupAccount insert(GroupAccount groupAccount) {
        this.groupAccountDao.insert(groupAccount);
        return groupAccount;
    }

    /**
     * 修改数据
     *
     * @param groupAccount 实例对象
     * @return 实例对象
     */
    @Override
    public GroupAccount update(GroupAccount groupAccount) {
        this.groupAccountDao.update(groupAccount);
        return this.queryById(groupAccount.getGroupId());
    }

    /**
     * 通过主键删除数据
     *
     * @param groupId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer groupId) {
        return this.groupAccountDao.deleteById(groupId) > 0;
    }
}