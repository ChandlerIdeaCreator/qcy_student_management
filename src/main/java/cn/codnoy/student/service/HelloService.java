package cn.codnoy.student.service;

import org.springframework.stereotype.Service;

/**
 * 业务服务层示例
 *
 * @Service 注解表示这是一个Spring管理的服务组件
 */
@Service
public class HelloService {

    /**
     * 生成欢迎消息
     *
     * @param name 用户名
     * @return 个性化欢迎消息
     */
    public String generateGreeting(String name) {
        if (name == null || name.trim().isEmpty()) {
            name = "World";
        }
        return String.format("Hello, %s! Welcome to SpringBoot 3!", name);
    }
}