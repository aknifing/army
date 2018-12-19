package vip.songqiang.util;


import com.github.qcloudsms.SmsMultiSender;
import com.github.qcloudsms.SmsMultiSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import org.json.JSONException;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SMSUtile {

    public void sendSMS(String[] receiver, String content) {

        try {
            String[] params = {"AK", content.substring(0, 9)};//数组具体的元素个数和模板中变量个数必须一致，例如事例中templateId:5678对应一个变量，参数数组中元素个数也必须是一个
            SmsMultiSender msender = new SmsMultiSender(1400170298, "03d659081aa7bed2c98d4a29ca98aa8e");
            SmsMultiSenderResult result
                    = msender.sendWithParam("86", receiver, 249309, params, "186023", "", "");

            System.out.println(result);
        } catch (HTTPException e) {
            // HTTP响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // json解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络IO错误
            e.printStackTrace();
        }

    }
}
