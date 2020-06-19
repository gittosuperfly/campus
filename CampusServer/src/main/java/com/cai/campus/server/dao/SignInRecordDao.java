package com.cai.campus.server.dao;

import com.cai.campus.server.entity.SignInRecord;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (SignInRecord)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-07 18:35:36
 */
public interface SignInRecordDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SignInRecord queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<SignInRecord> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param signInRecord 实例对象
     * @return 对象列表
     */
    List<SignInRecord> queryAll(SignInRecord signInRecord);

    /**
     * 新增数据
     *
     * @param signInRecord 实例对象
     * @return 影响行数
     */
    int insert(SignInRecord signInRecord);

    /**
     * 修改数据
     *
     * @param signInRecord 实例对象
     * @return 影响行数
     */
    int update(SignInRecord signInRecord);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    int deleteBySignInId(Integer signId);

}