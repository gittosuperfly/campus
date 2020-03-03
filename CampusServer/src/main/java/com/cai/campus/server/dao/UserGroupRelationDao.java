package com.cai.campus.server.dao;

import com.cai.campus.server.entity.UserGroupRelation;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (UserGroupRelation)表数据库访问层
 *
 * @author 蔡宇飞
 * @since 2020-03-03 17:46:37
 */
public interface UserGroupRelationDao {

    /**
     * 通过ID查询单条数据
     *
     * @param uid 主键
     * @return 实例对象
     */
    UserGroupRelation queryById(Integer uid);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
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
     * @param uid 主键
     * @return 影响行数
     */
    int deleteById(Integer uid);

}