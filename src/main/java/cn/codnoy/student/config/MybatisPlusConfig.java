package cn.codnoy.student.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis Plus配置类
 * 配置MyBatis Plus的相关插件和功能
 */
@Configuration
public class MybatisPlusConfig {

    /**
     * 配置MyBatis Plus拦截器
     * 添加所需的插件拦截器，如乐观锁等
     *
     * @return MybatisPlusInterceptor拦截器实例
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();

        // 分页插件（已注释，可根据需要启用）
        // interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));

        // 乐观锁插件
        // 用于处理并发更新时的数据一致性问题
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());

        return interceptor;
    }

    // 自定义SQL注入器（可选，已注释）
    // @Bean
    // public MySqlInjector sqlInjector() {
    //     return new MySqlInjector();
    // }
}
