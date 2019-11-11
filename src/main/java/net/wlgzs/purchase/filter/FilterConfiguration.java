package net.wlgzs.purchase.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author:胡亚星
 * @createTime 2018-05-12 11:30
 * @description:
 **/
@Configuration
public class FilterConfiguration {

    @Bean
    public FilterRegistrationBean filterLoginRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        //注入过滤器
        registration.setFilter(new LoginFilter());
        //拦截规则
        registration.addUrlPatterns("/accessory-list/*");
        registration.addUrlPatterns("/contract/*");
        registration.addUrlPatterns("/order-data/*");
        registration.addUrlPatterns("/parts/*");
//        registration.addUrlPatterns("/parts-offer/*");
        registration.addUrlPatterns("/product/*");
        registration.addUrlPatterns("/product-list/*");
//        registration.addUrlPatterns("/productoffer/*");
        registration.addUrlPatterns("/service/*");
        registration.addUrlPatterns("/service-list/*");
//        registration.addUrlPatterns("/service-offer/*");
        registration.addUrlPatterns("/service-value/*");
        registration.addUrlPatterns("/user/*");

        //过滤器名称
        registration.setName("LoginFilter");
        //是否自动注册 false 取消Filter的自动注册
        registration.setEnabled(true);
        //过滤器顺序
        registration.setOrder(Integer.MAX_VALUE);
        return registration;
    }

}