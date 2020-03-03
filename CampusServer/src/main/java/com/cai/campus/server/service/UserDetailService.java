package com.cai.campus.server.service;

import com.cai.campus.server.entity.UserDetail;
import java.util.List;

/**
 * (UserDetail)表服务接口
 *
 * @author 蔡宇飞
 * @since 2020-03-03 17:46:37
 */
public interface UserDetailService {

    /**
     * 通过ID查询单条数据
     *
     * @param uid 主键
     * @return 实例对象
     */
    UserDetail queryById(Integer uid);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<UserDetail> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param userDetail 实例对象
     * @return 实例对象
     */
    UserDetail insert(UserDetail userDetail);

    /**
     * 修改数据
     *
     * @param userDetail 实例对象
     * @return 实例对象
     */
    UserDetail update(UserDetail userDetail);

    /**
     * 通过主键删除数据
     *
     * @param uid 主键
     * @return 是否成功
     */
    boolean deleteById(Integer uid);

}