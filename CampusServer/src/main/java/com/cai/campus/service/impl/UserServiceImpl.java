package com.cai.campus.service.impl;

import com.cai.campus.entity.User;
import com.cai.campus.dao.UserDao;
import com.cai.campus.model.Response;
import com.cai.campus.model.ResultCode;
import com.cai.campus.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (User)表服务实现类
 *
 * @author 蔡宇飞
 * @since 2020-02-21 17:05:04
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    public Response<User> register(String phone, String password) {
        if (this.userDao.queryByPhone(phone) == null) {
            User user = new User(phone, password);
            this.userDao.insert(user);
            return Response.ok(user);
        } else {
            return Response.error(ResultCode.BAD_REQUEST, "该手机号已被注册");
        }
    }

    @Override
    public Response<Object> login(String phone, String password) {
        User user = this.userDao.queryByPhone(phone);
        if (user == null) {
            return Response.error(ResultCode.BAD_REQUEST, "该手机号暂未注册");
        } else {
            if (password.equals(user.getPassword())) {
                return Response.ok();
            } else {
                return Response.error(ResultCode.BAD_REQUEST, "密码错误");
            }
        }
    }

    @Override
    public Response<User> query(String type, String value) {
        if (type.equals("id")) {
            return Response.ok(this.userDao.queryById(Integer.parseInt(value)));
        } else if (type.equals("phone")) {
            return Response.ok(this.userDao.queryByPhone(value));
        } else {
            return Response.error(ResultCode.NOT_FOUND, "无效的查询类型");
        }
    }

    @Override
    public Response<User> update(int uid, String password, String name) {
        User user = User.builder().uid(uid).name(name).password(password).build();
        this.userDao.update(user);
        return Response.ok(user);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<User> queryAllByLimit(int offset, int limit) {
        return this.userDao.queryAllByLimit(offset, limit);
    }

    /**
     * 通过主键删除数据
     *
     * @param uid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer uid) {
        return this.userDao.deleteById(uid) > 0;
    }
}