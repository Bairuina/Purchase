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
        registry.addResourceHandler("/photoData/**") .addResourceLocations("file:"+photoPath+"/pictures/");

        //配置验收单PDF路径
        String PDFPath=System.getProperty("user.dir");
        registry.addResourceHandler("/PDFData/**") .addResourceLocations("file:"+PDFPath+"/PDFData/");

        //配置验收单word路径
        String WordDataPath=System.getProperty("user.dir");
        registry.addResourceHandler("/WordData/**") .addResourceLocations("file:"+WordDataPath+"/WordData/");

    }

}




