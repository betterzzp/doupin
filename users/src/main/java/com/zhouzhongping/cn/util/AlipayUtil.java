package com.zhouzhongping.cn.util;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.zhouzhongping.cn.entity.Order;

public class AlipayUtil {
    private static String URL = "https://openapi.alipaydev.com/gateway.do";
    private static String APP_ID = "2016101600699671";
    private static String APP_PRIVATE_KEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDvgm0OjBSjQFzO3RG7aAGIr/LKIw6V/OJQHK/Vr8irfNAM7vsFFIPx1zp/qckEvS2lveZ1bKWKonPfi//SGofVfsNTtwenR0HkCPiM1F8as7My6cmYJtpjAYFUuX4yNQP7l5LEtfQIEHoQTJuUfIkD19CksIdV2+4zecOPUbxWJtS8NKqEhyxAdVi8ESegv2i0dcaBN9XK3pQu7TwjTIilsGU3HKCkD7JyPU/iltyz7ixe/B0egS++a3+87NB63oyy6dmpCFPq8e7+NHeYj/UDNBXj8FYc8P18wsdYg/6eCd1kq+PFZoSG9DXQ9Py+RWNi3UrAYHWZTbtxr2lKOeL/AgMBAAECggEAG02j/weLBr08lzxuXA3UNu/P3qk2bXTBm95ZUSPT3XDCCudnaEHZdMVTXuBNESQ7wuwuDG7RREeWrAI6rInnuDfWLoHr9m7SHRvEsdE38kRQBlrOZ3DubmDqjG0E7NZMWO8DhTc01YKDV75T8g5sVCrTFyMkmm4S7n1QBNONg8h69d49Fx4CzdBW2xSTxkNbWRUzPvve3rZb6K6Yv8VE5j5UVvvs1VvbIYdfxonZVeFXE8/J4ZSwwon1udUsdKPE5L0lTh2RPowTHkd/pgCsCSvafNi0DFPtkOLNJyvaqAPmOfbaZE96zBPybeNIPpH9cWl5Fv/8tyaPxCn8J+Se4QKBgQD/m+jeoqNV9NiM65jv8Ke23iW3UVztX145bWsLvPcv4X3dp0o2ujJklUXWo1fAdOUClSRYN7Ee9YX9T4prwALncYW3cLMLtlYzGju/Ad3x8DbqRIHXRvKJkXYVvqnoHV9vG2o5MUVcBIYey9D5K8GjfVgkBKp41h7+IOYA+dB08QKBgQDv4DZQK6ktO9hlT5S7TvHGllHUcvGQB0QYCfak0otTpKo7/rJzMJmLOFYa/BkusMZy+Z0vYxwONPsE8/MfWRcCizuPuYUJSh3Iwyh0sN0MbyIwbiab/ryhvSpKHn6jaH1CmhY9Q9leHczRS3bLsJeOBqh2UQnqA6lPrKCwT5AW7wKBgDYHocC1xircN44KGujVCM6JxJUsWKVAXG1tKIxxpBp14qIgw3ov/4jtWLbXDix7SREzr1wKYaE2qtzxnEPMcZC0mVw+MUqPf0cMC4cDgQOsEB+S3ZNAxvG3UqukAswysUKOG/T86jlaJ3OJB6Iz0pmZXDE430Ptlt2G3LdmFt8xAoGAZIiY2tLhrZaWZCeNU0+L30oi86JVAaM588VBPOUNBbpOqqRdkA9/Wqz60YZ022+1THy3VyP3nzCvP+0qKSpCjYtjJCKB0lngz8KTUwzJKCvvrVKNFfYWgsbwUW4kb0IctWw1A+pK0Lo2nEU/fbD+Y8UAl83xFMYCEOVt11cRMbsCgYBuZanj+U9zzL67QmLxIugD3Hx7ZTKnBTPrLNPErA51WFHOsvajiXxL5Zy+E3/uFvCD/B1zFJZNzhkP19JLY8ZxUe9YHxn4MnugoHYrsWTuSG2OWZv5BHf7yuTX4G4idytWgFQiUDgCMpUx3gQF89mKRzckrInvHdaM5/ygTYwv2w==";
    private static String FORMAT = "json";
    private static String CHARSET = "UTF-8";
    private static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmltFVNTb3LjZn4wA/4AiVl+jmMGXi3pUAANx1S/ICbbdw4y11bBmRihZPz/ys4mhEbCBvwlDGVGEAAzFmvZZZQ+XEBr/QfS2GQT5tjHISEBp+CGoEU9YVeiVXVlr6XIGn0EZaebsQi4nHgmzzAd7JvJtAM9UsfCuL/Cvz1lO71wnoGdfTo47tm8AZtR1wdpsMqPrDBno0CXncaDmE6vVqjukhPOrRXMZR8vKPESsgznuaTtUbbFQ7eAHIYWMU5KJe2z0rPCJlIVah8ehdAWzrc+r5Bg7h35JapO62XgLDx3jTaDMz+eJQVKsOIh0oN7ory2iyR/LIjcUIW2IqsjZPwIDAQAB";
    private static String SIGN_TYPE = "RSA2";
    public static void doPay(HttpServletRequest httpRequest, HttpServletResponse httpResponse,Order order) throws ServletException, IOException {
        AlipayClient alipayClient = new DefaultAlipayClient(URL, APP_ID,APP_PRIVATE_KEY, FORMAT, CHARSET, ALIPAY_PUBLIC_KEY, SIGN_TYPE); // 获得初始化的AlipayClient
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();// 创建API对应的request
        String returnUrl = "http://mall.zhouzhongping.cn/";
        alipayRequest.setReturnUrl(returnUrl);
        String notifyUrl = "http://mall.zhouzhongping.cn/pay/payStatusNotify";
        alipayRequest.setNotifyUrl(notifyUrl);// 在公共参数中设置回跳和通知地址
        String  out_trade_no = order.getNumber();
        BigDecimal total_amountA = order.getPayAmount();
        BigDecimal total_amount = new BigDecimal(total_amountA.toString()+".00");
        String product_code = "FAST_INSTANT_TRADE_PAY";
        String subject = order.getNumber();
        String body = order.getId();
        alipayRequest.setBizContent("{" + "\"out_trade_no\":\""+out_trade_no+"\","
                + "    \"product_code\":\""+product_code+"\"," + "\"total_amount\":"+total_amount+","
                + "    \"subject\":\""+subject+"\"}");// 填充业务参数
        System.out.println(alipayRequest.getBizContent());
        String form = "";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); // 调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        httpResponse.setContentType("text/html;charset=" + CHARSET);
        httpResponse.getWriter().write(form);// 直接将完整的表单html输出到页面
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();
    }
}
