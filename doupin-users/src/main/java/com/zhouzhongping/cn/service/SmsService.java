package com.zhouzhongping.cn.service;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import org.json.JSONException;
import java.io.IOException;

@Service
@Repository
public class SmsService {
    private int appid = 1400254434; // SDK AppID 以1400开头
    private String appkey = "110a839bddd36fc074fc0e8c102cc97c";
    private int templateId = 418005;
    private String smsSign = "记忆森林";
    public boolean sendMsg(String phoneNumber,String validateCode){
        try {
            String[] params = {validateCode,"5"};
            String[] phoneNumbers ={phoneNumber} ;
            SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
            SmsSingleSenderResult result = ssender.sendWithParam("86", phoneNumbers[0],
                    templateId, params, smsSign, "", "");
            return true;
        } catch (HTTPException e) {
            // HTTP 响应码错误
            e.printStackTrace();
            return false;
        } catch (JSONException e) {
            // JSON 解析错误
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            // 网络 IO 错误
            e.printStackTrace();
            return false;
        }
    }
}
