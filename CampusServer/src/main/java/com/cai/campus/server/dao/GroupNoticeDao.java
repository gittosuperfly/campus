package com.cai.campus.server.dao;

import com.cai.campus.server.entity.GroupNotice;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (GroupNotice)表数据库访问层
 *
 * @author 蔡宇飞
 * @since 2020-03-03 22:38:10
 */
public interface GroupNoticeDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    GroupNotice queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<GroupNotice> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param groupNotice 实例对象
     * @return 对象列表
     */
    List<GroupNotice> queryAll(GroupNotice groupNotice);

    /**
     * 新增数据
     *
     * @param groupNotice 实例对象
     * @return 影响行数
     */
    int insert(GroupNotice groupNotice);

    /**
     * 修改数据
     *
     * @param groupNotice 实例对象
     * @return 影响行数
     */
    int update(GroupNotice groupNotice);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}