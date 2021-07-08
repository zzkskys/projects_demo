package arch.example;

import com.example.arch.ArchApplication;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.transaction.annotation.Transactional;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@AnalyzeClasses(packagesOf = ArchApplication.class)
public class LayeredArchitectureTest {

    public static final String PACKAGE = "com.example.arch";

    public static final String 表示层 = "表示层";

    public static final String 应用服务层 = "应用服务层";

    public static final String 领域服务层 = "领域服务层";

    public static final String 基础设施层 = "基础设施层";

    public static final String UI查询层 = "UI查询层";

    public static final String 配置类层 = "配置类层";

    @ArchTest
    static ArchRule 分层依赖规则 = layeredArchitecture()
            .layer(表示层).definedBy(PACKAGE + ".controller..")
            .layer(应用服务层).definedBy(PACKAGE + ".application")
            .layer(领域服务层).definedBy(PACKAGE + ".domain..")
            .layer(基础设施层).definedBy(PACKAGE + ".infrastructure..")
            .layer(UI查询层).definedBy(PACKAGE + ".infrastructure.query..")
            .layer(配置类层).definedBy(PACKAGE + ".config..")
            .whereLayer(配置类层).mayNotBeAccessedByAnyLayer()
            .whereLayer(表示层).mayNotBeAccessedByAnyLayer()
            .whereLayer(UI查询层).mayOnlyBeAccessedByLayers(表示层);

    @ArchTest
    static ArchRule 应用服务层的类必须以_AppService结尾并且有_Transaction注解 = classes()
            .that()
            .resideInAPackage("..application")
            .should()
            .haveSimpleNameContaining("AppService")
            .andShould()
            .beAnnotatedWith(Transactional.class);

    @ArchTest
    static ArchRule 表示层的类必须以_Controller结尾 = classes()
            .that()
            .resideInAPackage("..controller")
            .should()
            .haveSimpleNameEndingWith("Controller");
}
