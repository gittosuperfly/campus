package com.cai.campus.server.service;

import com.cai.campus.server.entity.GroupNotice;
import java.util.List;

/**
 * (GroupNotice)表服务接口
 *
 * @author 蔡宇飞
 * @since 2020-03-03 22:38:10
 */
public interface GroupNoticeService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    GroupNotice queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<GroupNotice> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param groupNotice 实例对象
     * @return 实例对象
     */
    GroupNotice insert(GroupNotice groupNotice);

    /**
     * 修改数据
     *
     * @param groupNotice 实例对象
     * @return 实例对象
     */
    GroupNotice update(GroupNotice groupNotice);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}