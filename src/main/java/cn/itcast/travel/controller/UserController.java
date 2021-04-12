package cn.itcast.travel.controller;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.SmsResult;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.SmsSendCode;
import cn.itcast.travel.service.UserService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author ply
 * @date 2021/4/7 - 14:35
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private SmsSendCode smsResultCode;

    @Resource
    private UserService userService;

    /**
     * 发生手机短信验证验证码
     */
    @ResponseBody
    @RequestMapping(value = "/sendSmsCheckCode",method = RequestMethod.GET)
    public SmsResult sendSmsCheckCode(String telephone){
        SmsResult smsResult = smsResultCode.sendSmsCheckCode(telephone);
        return smsResult;
    }

    /**
     * 注册功能
     *
     * @param request
     * @param response
     */
    @ResponseBody
    @RequestMapping(value = "/registered",method = RequestMethod.POST)
    public ResultInfo registered(HttpServletRequest request, HttpServletResponse response) throws InvocationTargetException, IllegalAccessException {
        //验证校验
        String checkCodeKey = request.getParameter("checkCodeKey");
        String check = request.getParameter("check");
        SmsResult smsResult = smsResultCode.jySmsCheckCode(checkCodeKey, check);
        boolean result = (boolean) smsResult.getResult();
        if (!result) {
            return new ResultInfo(false, "验证码错误!");
        }

        //用户名不能重复
        String username = request.getParameter("username");
        boolean existUser = userService.isExistUser(username);
        if (existUser) {
            return new ResultInfo(false, "用户名已存在");
        }

        //添加用户信息到数据库
        // 1.获取数据,封装对象
        User user = new User();
        Map<String, String[]> parameterMap = request.getParameterMap();
        BeanUtils.populate(user, parameterMap);
        int i = userService.insertUser(user);
        if (i <= 0) {
            return new ResultInfo(false, "注册失败，请联系管理员");
        }
        return new ResultInfo(true, "注册成功!");
    }


    /**
     * 激活功能
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/active",method = RequestMethod.GET)
    public void active(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String code = request.getParameter("code");
        boolean active = userService.active(code);
        String msg;
        if (active) {
            System.out.println("c" + request.getContextPath());
            msg = "激活成功，请<a href='" + request.getContextPath() + "/login.html'>登录</a>";
            } else {
                msg = "激活失败，请联系管理员!";
            }
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(msg);

    }


    /**
     * 登录功能
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResultInfo login(HttpServletRequest request, HttpServletResponse response)
            throws InvocationTargetException, IllegalAccessException {
        //1、校验验证码
        String check = request.getParameter("check");
        String check_code = (String)request.getSession().getAttribute("CHECK_CODE");
        if (!(check != null && check.toLowerCase().equals(check_code.toLowerCase()))) {
            return new ResultInfo(false, "验证码错误!");
        }
        //2.获取用户名和密码数据 ,封装User对象
        User user = new User();
        Map<String, String[]> parameterMap = request.getParameterMap();
        BeanUtils.populate(user, parameterMap);
        //3.判断用户对象是否为null
        User login = userService.login(user);
        if (login == null) {
            return new ResultInfo(false, "用户名或密码错误");
        }
        //4.判断用户是否激活
        String status = login.getStatus();
        if (!"Y".equals(status)) {
            return new ResultInfo(false, "账号未激活，请激活");
        }
        //5.登录成功: 验证通过，保存登录用户信息到Session
        request.getSession().setAttribute("user", login);
        return new ResultInfo(true);
    }


    /**
     * 获取当前登录用户信息
     *
     * @param request
     */
    @ResponseBody
    @RequestMapping(value = "/findOne",method = RequestMethod.GET)
    public User findOne(HttpServletRequest request) {
        //从session中获取登录用户
        User user = (User) request.getSession().getAttribute("user");
        return user;
    }


    /**
     * 退出功能
     *
     * @param request
     */
    @RequestMapping(value = "/exit",method = RequestMethod.GET)
    public String exit(HttpServletRequest request) {
        //1.销毁session
        request.getSession().invalidate();
        //2.跳转登录页面
        return "redirect:/login.html";
    }

}
