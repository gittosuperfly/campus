package com.cai.campus.server.service.impl;

import com.cai.campus.server.entity.UserUuid;
import com.cai.campus.server.dao.UserUuidDao;
import com.cai.campus.server.service.UserUuidService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (UserUuid)表服务实现类
 *
 * @author makejava
 * @since 2020-06-11 18:54:50
 */
@Service("userUuidService")
public class UserUuidServiceImpl implements UserUuidService {
    @Resource
    private UserUuidDao userUuidDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public UserUuid queryById(Integer id) {
        return this.userUuidDao.queryById(id);
    }

    @Override
    public UserUuid queryByUid(Integer uid) {
        return this.userUuidDao.queryByUid(uid);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<UserUuid> queryAllByLimit(int offset, int limit) {
        return this.userUuidDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param userUuid 实例对象
     * @return 实例对象
     */
    @Override
    public UserUuid insert(UserUuid userUuid) {
        this.userUuidDao.insert(userUuid);
        return userUuid;
    }

    /**
     * 修改数据
     *
     * @param userUuid 实例对象
     * @return 实例对象
     */
    @Override
    public UserUuid update(UserUuid userUuid) {
        this.userUuidDao.update(userUuid);
        return this.queryById(userUuid.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.userUuidDao.deleteById(id) > 0;
    }
}