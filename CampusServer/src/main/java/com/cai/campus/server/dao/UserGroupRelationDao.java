package com.cai.campus.server.dao;

import com.cai.campus.server.entity.UserGroupRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (UserGroupRelation)表数据库访问层
 *
 * @author makejava
 * @since 2020-05-12 15:41:24
 */
public interface UserGroupRelationDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UserGroupRelation queryById(Integer id);

    UserGroupRelation queryUserIsEmptyGroup(Integer uid,Integer groupId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<UserGroupRelation> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param userGroupRelation 实例对象
     * @return 对象列表
     */
    List<UserGroupRelation> queryAll(UserGroupRelation userGroupRelation);

    /**
     * 新增数据
     *
     * @param userGroupRelation 实例对象
     * @return 影响行数
     */
    int insert(UserGroupRelation userGroupRelation);

    /**
     * 修改数据
     *
     * @param userGroupRelation 实例对象
     * @return 影响行数
     */
    int update(UserGroupRelation userGroupRelation);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     */
    void deleteById(Integer id);

    /**
     * 通过groupId删除数据
     *
     * @param groupId 群id
     */
    void deleteByGroupId(Integer groupId);


}