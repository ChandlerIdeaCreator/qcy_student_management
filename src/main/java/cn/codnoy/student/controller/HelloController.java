package cn.codnoy.student.controller;

import cn.codnoy.student.service.HelloService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST控制器示例
 *
 * @RestController 是@Controller和@ResponseBody的组合注解
 * 表示这个类中的方法返回值直接作为HTTP响应体
 */
@RestController
public class HelloController {

    private final HelloService helloService;

    // 构造器注入依赖
    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    /**
     * 基础Hello World接口
     *
     * @return 固定欢迎消息
     */
    @GetMapping("/hello")
    public String sayHello() {
        return helloService.generateGreeting(null);
    }

    /**
     * 带参数的Hello接口
     *
     * @param name 用户名
     * @return 个性化欢迎消息
     */
    @GetMapping("/greet")
    public String greet(@RequestParam(required = false) String name) {
        return helloService.generateGreeting(name);
    }
}