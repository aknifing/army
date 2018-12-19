package vip.songqiang;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import vip.songqiang.bean.GlobalParams;
import vip.songqiang.bean.News;
import vip.songqiang.util.MailUtil;
import vip.songqiang.util.SMSUtile;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class FoxModuleTask {

    @Autowired
    GlobalParams globalParams;

    @Autowired
    MailUtil mailUtil;

    @Autowired
    SMSUtile smsUtile;

    @Scheduled(fixedDelay = 60000)
    public void test81() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "*/*");
        headers.set("Accept-Language", "zh-CN,zh;q=0.9");

        String body = "";
        HttpEntity<String> httpEntity = new HttpEntity<>(body, headers);


        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://www.81rc.mil.cn/news/node_56319.htm", httpEntity, String.class);
        String responseBody = responseEntity.getBody();

        List<News> a = getMatcher("<li><span>(.+?)</span><a(.+?)target=\"_blank\">(.+?)</li>", responseBody);
        for (News news : a) {
            if (news.getDate().after(globalParams.getStartDate())) {
                try {
                 /*
                  mailUtil.sendSimpleEmail("eclipse1225@163.com",
                            new String[]{"18266367779@qq.com"}, null,
                            String.format("[八一新消息]%s", news.getTitle()),
                            news.getUrl());
                            */
                    smsUtile.sendSMS(new String[]{"18266367779"}, news.getTitle());
                    globalParams.setStartDate(news.getDate());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static List<News> getMatcher(String regex, String source) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(source);
        List<News> list = new ArrayList<>();
        while (matcher.find()) {
            News news = new News();
            news.setRawString(matcher.group(0));
            news.setDate(matcher.group(1));
            news.setUrl(matcher.group(2));
            news.setTitle(matcher.group(3));
            list.add(news);
        }
        return list;
    }

}
