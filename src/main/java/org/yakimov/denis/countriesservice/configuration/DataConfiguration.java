//package org.yakimov.denis.countriesservice.configuration;
//
//import org.springframework.boot.web.servlet.MultipartConfigFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.util.unit.DataSize;
//import org.springframework.util.unit.DataUnit;
//import org.springframework.web.multipart.MultipartResolver;
//import org.springframework.web.multipart.support.StandardServletMultipartResolver;
//import org.yakimov.denis.countriesservice.support.Constants;
//
//import javax.servlet.MultipartConfigElement;
//
//@Configuration
//public class DataConfiguration {
//    @Bean(name = "commonsMultipartResolver")
//    public MultipartResolver multipartResolver() {
//        return new StandardServletMultipartResolver();
//    }
//
//
//    @Bean
//    public MultipartConfigElement multipartConfigElement() {
//        MultipartConfigFactory factory = new MultipartConfigFactory();
//        factory.setMaxFileSize(DataSize.of(Constants.SIZE, DataUnit.MEGABYTES));
//        factory.setMaxRequestSize(DataSize.of(Constants.SIZE, DataUnit.MEGABYTES));
//        return factory.createMultipartConfig();
//    }
//}
