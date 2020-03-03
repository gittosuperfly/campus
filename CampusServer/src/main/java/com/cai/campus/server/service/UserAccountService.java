package com.cai.campus.server.service;

import com.cai.campus.server.entity.UserAccount;
import java.util.List;

/**
 * (UserAccount)表服务接口
 *
 * @author 蔡宇飞
 * @since 2020-03-03 17:46:37
 */
public interface UserAccountService {

    /**
     * 通过ID查询单条数据
     *
     * @param uid 主键
     * @return 实例对象
     */
    UserAccount queryById(Integer uid);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<UserAccount> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param userAccount 实例对象
     * @return 实例对象
     */
    UserAccount insert(UserAccount userAccount);

    /**
     * 修改数据
     *
     * @param userAccount 实例对象
     * @return 实例对象
     */
    UserAccount update(UserAccount userAccount);

    /**
     * 通过主键删除数据
     *
     * @param uid 主键
     * @return 是否成功
     */
    boolean deleteById(Integer uid);

}