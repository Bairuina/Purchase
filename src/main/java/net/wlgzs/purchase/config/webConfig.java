package net.wlgzs.purchase.config;

import org.junit.Test;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class webConfig implements WebMvcConfigurer  {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        //配置图片显示路径
        String photoPath=System.getProperty("user.dir");
//        photoPath=photoPath.replace('\\','/');
        registry.addResourceHandler("/photoData/**") .addResourceLocations("file:"+photoPath+"/pictures/");

    }

    @Test
    public void kl(){
        String photoPath=System.getProperty("user.dir");
        photoPath=photoPath.replace('\\','/');
        System.out.println(photoPath);
    }

}


