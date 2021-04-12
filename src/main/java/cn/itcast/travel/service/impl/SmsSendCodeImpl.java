package cn.itcast.travel.service.impl;

import cn.itcast.travel.domain.SmsResult;
import cn.itcast.travel.service.SmsSendCode;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Service;

import static cn.itcast.travel.util.HttpURLUtil.doPost;

/**
 * @author ply
 * @date 2021/4/7 - 14:47
 */
@Service
public class SmsSendCodeImpl implements SmsSendCode {

    @Override
    public SmsResult sendSmsCheckCode(String telephone) {
        //调用service的发送短信方法
        String url = "http://localhost:56085/sailing/generate?effectiveTime=300&name=sms";
        String json = "{\"mobile\":\"" + telephone + "\"}";
        String s =doPost(url, json);
        SmsResult smsResult = JSON.parseObject(s,SmsResult.class);
        return smsResult;
    }

    @Override//校验验证码方法
    public SmsResult jySmsCheckCode(String key, String code) {
        String url = "http://localhost:56085/sailing/verify?name=sms&verificationKey=" + key + "&verificationCode=" + code;
        String s = doPost(url, null);
        SmsResult smsResult = JSON.parseObject(s, SmsResult.class);
        return smsResult;
    }
}
