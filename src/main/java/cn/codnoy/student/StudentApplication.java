package cn.codnoy.student;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 学籍管理系统启动类
 */
@SpringBootApplication
@MapperScan("cn.codnoy.student.mapper") // 扫描MyBatis Mapper接口
public class StudentApplication {
    public static void main(String[] args) {
        try {
            SpringApplication.run(StudentApplication.class, args);
            System.out.println("学籍管理系统启动成功！");
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}