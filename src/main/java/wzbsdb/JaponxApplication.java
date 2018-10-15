package wzbsdb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 〈一句话功能简述〉<br>
 * 启动类
 *
 * @author Surging
 * @create 2018/9/19
 * @since 1.0.0
 */
@SpringBootApplication
@EnableScheduling   // 开启定时任务
public class JaponxApplication {
    public static void main(String[] args) {
        SpringApplication.run(JaponxApplication.class, args);
    }
}
