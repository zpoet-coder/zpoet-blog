package cn.zpoet.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan; // 确保导入这个注解

@SpringBootApplication
@MapperScan("cn.zpoet.blog.mapper") // 扫描mapper接口
public class BackstageDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackstageDemoApplication.class, args);
    }

}
