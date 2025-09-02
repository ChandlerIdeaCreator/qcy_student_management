package cn.codnoy.student.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * SpringDoc配置类（OpenAPI 3）
 * 配置API文档的相关信息，使用SpringDoc替代Swagger2
 */
@Configuration
public class SpringDocConfig {

    /**
     * 自定义OpenAPI配置
     * 设置API文档的标题、版本、描述等信息
     *
     * @return OpenAPI配置对象
     */
    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                // 设置API基本信息
                .info(new Info()
                        .title("学籍管理系统API文档")  // API文档标题
                        .version("1.0")             // API版本
                        .description("学生管理模块接口文档") // API描述
                        .license(new License()
                                .name("Apache2.0")  // 许可证名称
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")) // 许可证URL
                );
    }
}
