package cn.itcast.travel.service;

import cn.itcast.travel.domain.SmsResult;

/**
 * @author ply
 * @date 2021/4/7 - 14:46
 */
public interface SmsSendCode {

    SmsResult sendSmsCheckCode(String telephone);//发送手机验证码

    SmsResult jySmsCheckCode(String key,String code);//校验验证码
}
