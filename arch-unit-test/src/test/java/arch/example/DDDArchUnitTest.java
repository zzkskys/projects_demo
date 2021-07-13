package arch.example;

import com.example.arch.ArchApplication;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.GeneralCodingRules.NO_CLASSES_SHOULD_USE_JAVA_UTIL_LOGGING;

@AnalyzeClasses(packagesOf = ArchApplication.class)
public class DDDArchUnitTest {


    @ArchTest
    ArchRule 配置类规则 = classes()
            .that()
            .areAnnotatedWith(Configuration.class)
            .should()
            .resideInAnyPackage("..config..")
            .andShould()
            .haveSimpleNameEndingWith("Config")
            .as("@Configuration 注解的类 1. 在 config 包下 2. 以 Config 结尾");


    @ArchTest
    ArchRule 外部配置类规则 = classes()
            .that()
            .areAnnotatedWith(ConfigurationProperties.class)
            .should()
            .resideInAnyPackage("..config..")
            .andShould()
            .haveSimpleNameEndingWith("Properties")
            .as("@ConfigurationProperties 注解的类 1. 在 config 包下 2. 以 Properties 结尾");
//
//    @ArchTest
//    ArchRule Feign规则 = classes()
//            .that()
//            .areAnnotatedWith(FeignClient.class)
//            .should()
//            .resideInAPackage("..infrastructure.client..")
//            .as("@FeignClient 注解的类必须在 infrastructure.client 包下");
//
//    @ArchTest
//    ArchRule MapStruct规则 = classes()
//            .that()
//            .areAnnotatedWith(Mapper.class)
//            .should()
//            .resideInAPackage("..infrastructure.converter..")
//            .as("MapStruct @Mapper注解下的类必须在 infrastructure.converter 包下");
//
//    @ArchTest
//    ArchRule Mybatis规则 = classes()
//            .that()
//            .areAnnotatedWith(org.apache.ibatis.annotations.Mapper.class)
//            .should()
//            .resideInAnyPackage("..infrastructure.query..")
//            .as("Mybatis 的 @Mapper 注解的类必须在 infrastructure.query 包下");
//
//    @ArchTest
//    ArchRule Entity规则 = classes()
//            .that()
//            .areAnnotatedWith(Entity.class)
//            .should()
//            .resideInAPackage("..domain..")
//            .as("@Entity 注解的类必须在 domain 包下");
//
//
//    @ArchTest
//    ArchRule Repository规则 = classes()
//            .that()
//            .areAnnotatedWith(Repository.class)
//            .should()
//            .resideInAnyPackage("..domain..", "..infrastructure..")
//            .as("@Repository 注解的类必须在 domain 包下或者 infrastructure 包下");
//
//    @ArchTest
//    ArchRule 控制器规则 = classes()
//            .that()
//            .areAnnotatedWith(RestController.class)
//            .should()
//            .resideInAnyPackage("..web..", "..controller..")
//            .andShould()
//            .beAnnotatedWith(Api.class)
//            .as("@RestController 注解的类必须在 web 或 controller 包下且必须有 @Api 注解");


    @ArchTest
    ArchRule 应用服务规则 = classes()
            .that()
            .resideInAPackage("..application..")
            .should()
            .beAnnotatedWith(Service.class)
            .andShould()
            .beAnnotatedWith(Transactional.class)
            .andShould()
            .haveSimpleNameEndingWith("AppService")
            .as("application 包下的类 : 1. 必须有 @Service 注解 2.必须有 @Transactional 注解 3. 必须以 AppService 结尾");

    /**
     * 不应该使用java.util.logging来记录日志
     */
    @ArchTest
    ArchRule NO_JAVA_UTIL_LOGGING = NO_CLASSES_SHOULD_USE_JAVA_UTIL_LOGGING;
}