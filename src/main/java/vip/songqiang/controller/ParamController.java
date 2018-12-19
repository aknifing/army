package vip.songqiang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import vip.songqiang.bean.GlobalParams;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class ParamController {
    @Autowired
    GlobalParams globalParams;

    @RequestMapping("/set")
    public @ResponseBody
    String set(String data) {
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyyMMddHHmm").parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        globalParams.setStartDate(date);
        return "OK";
    }
}
