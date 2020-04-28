package com.cai.campus.server.dao;

import com.cai.campus.server.entity.GroupAccount;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (GroupAccount)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-28 23:57:08
 */
public interface GroupAccountDao {

    /**
     * 通过ID查询单条数据
     *
     * @param groupId 主键
     * @return 实例对象
     */
    GroupAccount queryById(Integer groupId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<GroupAccount> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param groupAccount 实例对象
     * @return 对象列表
     */
    List<GroupAccount> queryAll(GroupAccount groupAccount);

    /**
     * 新增数据
     *
     * @param groupAccount 实例对象
     * @return 影响行数
     */
    int insert(GroupAccount groupAccount);

    /**
     * 修改数据
     *
     * @param groupAccount 实例对象
     * @return 影响行数
     */
    int update(GroupAccount groupAccount);

    /**
     * 通过主键删除数据
     *
     * @param groupId 主键
     * @return 影响行数
     */
    int deleteById(Integer groupId);

}