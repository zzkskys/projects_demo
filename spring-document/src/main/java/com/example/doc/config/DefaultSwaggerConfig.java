package com.example.doc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * Created Date : 2020/08/20
 *
 * @author zzk
 */
@EnableSwagger2
@Configuration
public class DefaultSwaggerConfig {


    /**
     * 默认的 swagger 接口扫描
     */
    @Bean
    public Docket defaultDocket() {

        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("Spring 测试 API")
                .description("测试 API 生成")
                .version("1.0.0")
                .build();

        return new Docket(DocumentationType.SWAGGER_2)
                .ignoredParameterTypes(SwaggerUtil.ignoreParameters(Collections.emptyList()))
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo)
                .select()
                .apis(handler -> handler.findControllerAnnotation(RestController.class).isPresent()
                        && (!SwaggerUtil.isSuperAdminApi(handler)))
                .build()
                .securityContexts(SwaggerUtil.securityContext())
                .securitySchemes(SwaggerUtil.securitySchemes());
    }


}
