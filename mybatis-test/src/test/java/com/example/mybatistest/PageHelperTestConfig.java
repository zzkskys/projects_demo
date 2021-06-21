package com.example.mybatistest;

import com.github.pagehelper.PageInterceptor;
import com.github.pagehelper.autoconfigure.PageHelperProperties;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Properties;

/**
 * 参照 {@link com.github.pagehelper.autoconfigure.PageHelperAutoConfiguration} 为单元测试而设置的拦截类。
 */
@TestConfiguration
@AutoConfigureAfter(MybatisAutoConfiguration.class)
public class PageHelperTestConfig {
    @Autowired
    private List<SqlSessionFactory> sqlSessionFactoryList;


    @Autowired
    private PageHelperProperties helperProperties;

    /**
     * 接受分页插件额外的属性
     */
    @Bean
    @ConfigurationProperties(prefix = PageHelperProperties.PAGEHELPER_PREFIX)
    public Properties pageHelperProperties() {
        return new Properties();
    }

    @Bean
    public PageHelperProperties properties() {
        PageHelperProperties properties = new PageHelperProperties();
        properties.setReasonable(true);
        properties.setSupportMethodsArguments(true);
        properties.setParams("count=countSql");
        return properties;
    }

    @PostConstruct
    public void addPageInterceptor() {
        PageInterceptor interceptor = new PageInterceptor();
        Properties properties = new Properties(helperProperties.getProperties());
        interceptor.setProperties(properties);
        for (SqlSessionFactory sqlSessionFactory : sqlSessionFactoryList) {
            org.apache.ibatis.session.Configuration configuration = sqlSessionFactory.getConfiguration();
            if (!containsInterceptor(configuration, interceptor)) {
                configuration.addInterceptor(interceptor);
            }
        }
    }

    /**
     * 是否已经存在相同的拦截器
     */
    private boolean containsInterceptor(org.apache.ibatis.session.Configuration configuration, Interceptor interceptor) {
        try {
            return configuration.getInterceptors().contains(interceptor);
        } catch (Exception e) {
            return false;
        }
    }
}
