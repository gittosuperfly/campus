package com.cai.campus.server.dao;

import com.cai.campus.server.entity.GroupNoticeRelation;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (GroupNoticeRelation)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-14 16:08:43
 */
public interface GroupNoticeRelationDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    GroupNoticeRelation queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<GroupNoticeRelation> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param groupNoticeRelation 实例对象
     * @return 对象列表
     */
    List<GroupNoticeRelation> queryAll(GroupNoticeRelation groupNoticeRelation);

    /**
     * 新增数据
     *
     * @param groupNoticeRelation 实例对象
     * @return 影响行数
     */
    int insert(GroupNoticeRelation groupNoticeRelation);

    /**
     * 修改数据
     *
     * @param groupNoticeRelation 实例对象
     * @return 影响行数
     */
    int update(GroupNoticeRelation groupNoticeRelation);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}