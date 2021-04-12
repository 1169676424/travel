package cn.itcast.travel.service;

import cn.itcast.travel.domain.User;

/**
 * @author ply
 * @date 2021/4/8 - 8:28
 */
public interface UserService {
    boolean isExistUser(String username);//判断用户名是否存在

    int insertUser(User user);//添加用户

    boolean active(String code);//激活用户

    User login(User user);//登录
}
