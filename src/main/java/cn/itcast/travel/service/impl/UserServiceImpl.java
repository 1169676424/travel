package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.util.MailUtils;
import cn.itcast.travel.util.UuidUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author ply
 * @date 2021/4/8 - 8:29
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public boolean isExistUser(String username) {
        User user = userDao.findUserByUserName(username);
        if (user != null) {
            //用户名已存在
            return  true;
        }
        return false;
    }

    @Override
    public int insertUser(User user) {
        user.setStatus("N");
        user.setCode(UuidUtil.getUuid());
        int i = userDao.insertUser(user);
        if (i == 1) {
            String text = "<a href='http://localhost:8080/travel/user/active?code=" + user.getCode() + "'>点击激活【黑马旅游网】</a>";
            String email = user.getEmail();
            String title = "激活邮件";
            MailUtils.sendMail(email, text, title);
        }
        return i;
    }

    @Override
    public boolean active(String code) {
        User user = userDao.findUserByCode(code);
        if (user == null) {
            //用户不存在，返回激活失败
            return  false;
        }
        int code1 = userDao.updateByCode(code);
        if (code1 == 1) {
            //更新成功，激活成功
            return  true;
        }
        return false;
    }

    @Override
    public User login(User user) {
        User user1 = userDao.login(user);
        return user1;
    }


}
