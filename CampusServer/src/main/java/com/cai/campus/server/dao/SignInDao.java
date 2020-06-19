package com.cai.campus.server.dao;

import com.cai.campus.server.entity.SignIn;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (SignIn)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-01 16:09:24
 */
public interface SignInDao {

    /**
     * 通过ID查询单条数据
     *
     * @param signId 主键
     * @return 实例对象
     */
    SignIn queryById(Integer signId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<SignIn> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param signIn 实例对象
     * @return 对象列表
     */
    List<SignIn> queryAll(SignIn signIn);

    /**
     * 新增数据
     *
     * @param signIn 实例对象
     * @return 影响行数
     */
    int insert(SignIn signIn);

    /**
     * 修改数据
     *
     * @param signIn 实例对象
     * @return 影响行数
     */
    int update(SignIn signIn);

    /**
     * 通过主键删除数据
     *
     * @param signId 主键
     * @return 影响行数
     */
    int deleteById(Integer signId);

}