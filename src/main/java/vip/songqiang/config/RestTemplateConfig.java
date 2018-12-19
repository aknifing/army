package vip.songqiang.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import vip.songqiang.bean.GlobalParams;

import java.util.Date;

/**
 * restTemplate配置
 * @author tyrion.song
 * @date 2018年1月4日 下午2:50:16
 */
@Configuration
public class RestTemplateConfig {

    @Bean
    public GlobalParams generateGlobalParams(){
        GlobalParams g = new GlobalParams();
        g.setStartDate(new Date());
        return  g;
    }
}


