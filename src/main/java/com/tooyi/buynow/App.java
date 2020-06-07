package com.tooyi.buynow;

import com.tooyi.dao.UserDOMapper;
import com.tooyi.dataobject.UserDO;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@EnableAutoConfiguration //可以帮助SpringBoot应用将所有符合条件的@Configuration配置都加载到当前SpringBoot创建并使用的IoC容器
@SpringBootApplication(scanBasePackages = {"com.tooyi"}) //与上注解异曲同工
@RestController
@MapperScan("com.tooyi.dao")
public class App {

    @Autowired
    private UserDOMapper userDOMapper;
    // 数据库连接测试
    @RequestMapping("/")
    public String home(){
        UserDO userDO = userDOMapper.selectByPrimaryKey(1);
        if(userDO == null) {
            return "用户对象不存在";
        }else {
            return userDO.getName();
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");
        SpringApplication.run(App.class, args);
    }
}