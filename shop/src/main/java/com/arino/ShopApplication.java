package com.arino;

import com.arino.utils.InfoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class ShopApplication {

    public static void main(String[] args) {

        // 运行App
        SpringApplication.run(ShopApplication.class, args);

        // 打印运行地址，方便测试
        InfoUtil.log();
    }

}
