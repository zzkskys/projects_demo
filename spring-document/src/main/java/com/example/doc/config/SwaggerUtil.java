package com.example.doc.config;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import springfox.documentation.RequestHandler;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.service.contexts.SecurityContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SwaggerUtil {

    private static final String X_AUTH_TOKEN = "x-auth-token";

    public static List<SecurityContext> securityContext() {
        AuthorizationScope[] scopes = new AuthorizationScope[]{
                new AuthorizationScope("global", "")
        };


        List<SecurityReference> references = Arrays.asList(new SecurityReference(X_AUTH_TOKEN, scopes));

        SecurityContext build = SecurityContext
                .builder()
                //查找生效的作用域
//                .operationSelector()
                .securityReferences(references)
                .build();

        List<SecurityContext> contexts = new ArrayList<>();
        contexts.add(build);
        return contexts;
    }

    public static List<SecurityScheme> securitySchemes() {
        return Arrays.asList(new ApiKey(X_AUTH_TOKEN, X_AUTH_TOKEN, "header"));
//        return Arrays.asList(new BosApiKey(X_AUTH_TOKEN, "要登录的 API Key"));
    }


    /**
     * 获得被忽略的类
     */
    public static Class<?>[] ignoreParameters(List<String> classNameList) {
        List<Class<?>> classes = new ArrayList<>();

        try {
            Class<?> authClass = Class.forName("org.springframework.security.core.annotation.AuthenticationPrincipal");
            classes.add(authClass);
        } catch (ClassNotFoundException e) {
            //nothing to do
        }

        // 因为每个类都需要判断,因此循环内抓异常
        for (String className : classNameList) {
            Class<?> aClass = null;
            try {
                aClass = Class.forName(className);
                classes.add(aClass);
            } catch (ClassNotFoundException e) {
                // nothing to do
            }
        }
        Class<?>[] ignores = new Class[classes.size()];
        for (int i = 0; i < classes.size(); i++) {
            ignores[i] = classes.get(i);
        }
        classes.add(ModelAndView.class);
        classes.add(View.class);
        return ignores;
    }

    /**
     * 判断该 API 是否是超级管理员才可访问的API
     */
    public static boolean isSuperAdminApi(RequestHandler requestHandler) {
        return requestHandler.findControllerAnnotation(RestController.class).isPresent()
                && (requestHandler.findControllerAnnotation(SuperAdminAPI.class).isPresent() || requestHandler.findAnnotation(SuperAdminAPI.class).isPresent());
    }
}
