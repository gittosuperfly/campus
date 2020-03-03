package com.cai.campus.server.service;

import com.cai.campus.server.entity.GroupAccount;
import java.util.List;

/**
 * (GroupAccount)表服务接口
 *
 * @author 蔡宇飞
 * @since 2020-03-03 17:46:32
 */
public interface GroupAccountService {

    /**
     * 通过ID查询单条数据
     *
     * @param groupId 主键
     * @return 实例对象
     */
    GroupAccount queryById(Integer groupId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<GroupAccount> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param groupAccount 实例对象
     * @return 实例对象
     */
    GroupAccount insert(GroupAccount groupAccount);

    /**
     * 修改数据
     *
     * @param groupAccount 实例对象
     * @return 实例对象
     */
    GroupAccount update(GroupAccount groupAccount);

    /**
     * 通过主键删除数据
     *
     * @param groupId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer groupId);

}