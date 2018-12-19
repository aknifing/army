package vip.songqiang.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class News {
    Date date;
    String url;
    String title;
    String rawString;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDate(String string) {
        try {
            this.date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(string);
        } catch (ParseException e) {
            this.date = null;
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRawString() {
        return rawString;
    }

    public void setRawString(String rawString) {
        this.rawString = rawString;
    }
}