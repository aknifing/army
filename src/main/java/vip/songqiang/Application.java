package vip.songqiang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 程序启动主类 
 * @SpringBootApplication= @Configuration @EnableAutoConfiguration @ComponentScan
 * @author tyrion.song
 * @date 2017年12月27日 下午5:09:28
 * patch1
 */
@SpringBootApplication
@EnableScheduling
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

	}
}
