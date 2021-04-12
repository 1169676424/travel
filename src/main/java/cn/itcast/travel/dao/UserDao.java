package cn.itcast.travel.dao;

import cn.itcast.travel.domain.User;

/**
 * @author ply
 * @date 2021/4/8 - 8:26
 */
public interface UserDao {

    User findUserByUserName(String username);

    int insertUser(User user);

    User findUserByCode(String code);

    int updateByCode(String code);

    User login(User user);
}
