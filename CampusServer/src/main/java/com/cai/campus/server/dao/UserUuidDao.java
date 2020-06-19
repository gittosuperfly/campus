package com.cai.campus.server.dao;

import com.cai.campus.server.entity.UserUuid;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (UserUuid)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-11 18:54:50
 */
public interface UserUuidDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UserUuid queryById(Integer id);

    UserUuid queryByUid(Integer uid);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<UserUuid> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param userUuid 实例对象
     * @return 对象列表
     */
    List<UserUuid> queryAll(UserUuid userUuid);

    /**
     * 新增数据
     *
     * @param userUuid 实例对象
     * @return 影响行数
     */
    int insert(UserUuid userUuid);

    /**
     * 修改数据
     *
     * @param userUuid 实例对象
     * @return 影响行数
     */
    int update(UserUuid userUuid);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}