package com.cai.campus.server.service.impl;

import com.cai.campus.server.entity.GroupNotice;
import com.cai.campus.server.dao.GroupNoticeDao;
import com.cai.campus.server.service.GroupNoticeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (GroupNotice)表服务实现类
 *
 * @author 蔡宇飞
 * @since 2020-03-03 22:38:10
 */
@Service("groupNoticeService")
public class GroupNoticeServiceImpl implements GroupNoticeService {
    @Resource
    private GroupNoticeDao groupNoticeDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public GroupNotice queryById(Integer id) {
        return this.groupNoticeDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<GroupNotice> queryAllByLimit(int offset, int limit) {
        return this.groupNoticeDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param groupNotice 实例对象
     * @return 实例对象
     */
    @Override
    public GroupNotice insert(GroupNotice groupNotice) {
        this.groupNoticeDao.insert(groupNotice);
        return groupNotice;
    }

    /**
     * 修改数据
     *
     * @param groupNotice 实例对象
     * @return 实例对象
     */
    @Override
    public GroupNotice update(GroupNotice groupNotice) {
        this.groupNoticeDao.update(groupNotice);
        return this.queryById(groupNotice.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.groupNoticeDao.deleteById(id) > 0;
    }
}