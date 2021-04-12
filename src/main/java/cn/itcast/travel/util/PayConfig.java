package cn.itcast.travel.util;

import com.github.wxpay.sdk.WXPayConfig;

import java.io.*;

public class PayConfig implements WXPayConfig {

    //企业方公众号Id
    public static String appId = "wx8397f8696b538317";
    //财付通平台的商户账号
    public static String partner = "1473426802";
    //财付通平台的商户密钥
    public static String partnerKey = "T6m9iK73b0kn9g5v426MKfHQH7X8rKwb";


    private byte[] certData;

    public PayConfig() {
    }

    public String getAppID() {
        return appId;
    }

    public String getMchID() {
        return partner;
    }

    public String getKey() {
        return partnerKey;
    }

    public InputStream getCertStream() {
        ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }

    public int getHttpConnectTimeoutMs() {
        return 8000;
    }

    public int getHttpReadTimeoutMs() {
        return 10000;
    }


}
