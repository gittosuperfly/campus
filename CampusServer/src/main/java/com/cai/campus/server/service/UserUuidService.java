package com.cai.campus.server.service;

import com.cai.campus.server.entity.UserUuid;
import java.util.List;

/**
 * (UserUuid)表服务接口
 *
 * @author makejava
 * @since 2020-06-11 18:54:50
 */
public interface UserUuidService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UserUuid queryById(Integer id);

    UserUuid queryByUid(Integer uid);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<UserUuid> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param userUuid 实例对象
     * @return 实例对象
     */
    UserUuid insert(UserUuid userUuid);

    /**
     * 修改数据
     *
     * @param userUuid 实例对象
     * @return 实例对象
     */
    UserUuid update(UserUuid userUuid);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}