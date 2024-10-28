import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;

@AnalyzeClasses(packages = "be.kdg.prog6.boundedcontextWarehouse", importOptions = ImportOption.DoNotIncludeTests.class)
class ArquitectureTestInWarehouseBoundedContext {

    private static final String DTOS_PACKAGE = "be.kdg.prog6.boundedcontextWarehouse.adapters.in.web.dto";
    private static final String IN_WEB_PACKAGE_AND_SUB_PACKAGES_OF_IT = "be.kdg.prog6.boundedcontextWarehouse.adapters.in.web..";
    private static final String IN_WEB_PACKAGE = "be.kdg.prog6.boundedcontextWarehouse.adapters.in.web";
    private static final String PORTS_IN_PACKAGE = "be.kdg.prog6.boundedcontextWarehouse.ports.in..";
    private static final String CORE_PACKAGE = "be.kdg.prog6.boundedcontextWarehouse.core";
    private static final String AMPQ_PACKAGE = "be.kdg.prog6.boundedcontextWarehouse.adapters.ampq";
    private static final String OUT_ADAPTER_PACKAGE = "be.kdg.prog6.boundedcontextWarehouse.adapters.out.db";
    private static final String PORTS_OUT_PACKAGE = "be.kdg.prog6.boundedcontextWarehouse.adapters.ports.out";
    private static final String DOMAIN_PACKAGE = "be.kdg.prog6.boundedcontextWarehouse.domain";


    @ArchTest
    static final ArchRule repositoriesShouldOnlyBeUsedInDBAdapters =
            ArchRuleDefinition.classes()
                    .that()
                    .haveSimpleNameContaining("DBAdapter")
                    .should()
                    .dependOnClassesThat()
                    .haveSimpleNameContaining("Repository")
                    .because("Classes named 'Repository' should only be used in classes named 'DBAdapter'");

    @ArchTest
    static final ArchRule nonDBAdaptersShouldNotDependOnRepositories =
            ArchRuleDefinition.noClasses()
                    .that()
                    .haveSimpleNameNotContaining("DBAdapter")
                    .and()
                    .haveSimpleNameNotContaining("Repository")
                    .should()
                    .dependOnClassesThat()
                    .haveSimpleNameContaining("Repository")
                    .because("Classes not named 'DBAdapter' should not depend on 'Repository'");


    // Just one path, dtos might not be used in a web package controller
    @ArchTest
    static final ArchRule dtosShouldOnlyBeUsedInWebPackage =
            ArchRuleDefinition.noClasses()
                    .that()
                    .resideOutsideOfPackage(IN_WEB_PACKAGE_AND_SUB_PACKAGES_OF_IT)
                    .should()
                    .dependOnClassesThat()
                    .resideInAnyPackage(DTOS_PACKAGE)
                    .as("DTOs should only be used within the in.web package and in classes named Controller")
                    .because("DTOs are meant to be used to show really specific data to users outside the business");

    @ArchTest
    static final ArchRule dtosShouldOnlyByControllers =
            ArchRuleDefinition.noClasses()
                    .that()
                    .resideInAPackage(IN_WEB_PACKAGE_AND_SUB_PACKAGES_OF_IT)
                    .and()
                    .haveSimpleNameNotContaining("Controller")
                    .and()
                    .haveSimpleNameNotContaining("Dto")
                    .should()
                    .dependOnClassesThat()
                    .resideInAnyPackage(DTOS_PACKAGE)
                    .as("DTOs should only be used within the in.web package and in classes named Controller")
                    .because("DTOs are meant to be used to show really specific data to users outside the business");

    @ArchTest
    static final ArchRule domainShouldNotDependOnAnyOtherLayerRule =
            ArchRuleDefinition.noClasses()
                    .that()
                    .resideInAPackage(DOMAIN_PACKAGE)
                    .should()
                    .dependOnClassesThat()
                    .resideInAnyPackage(IN_WEB_PACKAGE,PORTS_OUT_PACKAGE, PORTS_IN_PACKAGE, CORE_PACKAGE)
                    .because("Domain should not depend on other layers.");


    @ArchTest
    static final ArchRule controllersShouldOnlyDependOnUseCases =
            ArchRuleDefinition.classes()
                    .that()
                    .resideInAPackage(IN_WEB_PACKAGE)
                    .should()
                    .dependOnClassesThat()
                    .resideInAnyPackage(PORTS_IN_PACKAGE)
                    .because("Controller and listeners should only depend on use cases");

    @ArchTest
    static final ArchRule defaultUseCasesShouldNotImplementSomethingElseThanUseCasePorts =
            ArchRuleDefinition.noClasses()
                    .that()
                    .resideOutsideOfPackage(PORTS_IN_PACKAGE)
                    .should()
                    .dependOnClassesThat()
                    .resideInAnyPackage(CORE_PACKAGE, IN_WEB_PACKAGE)
                    .because("Classes in the web adapter should only depend on UseCase classes in the ports.in package");


    @ArchTest
    static final ArchRule defaultUseCasesShouldAlwaysImplementAUseCasePort =
            ArchRuleDefinition.classes()
                    .that()
                    .resideInAPackage(CORE_PACKAGE)
                    .should()
                    .dependOnClassesThat()
                    .resideInAnyPackage(PORTS_IN_PACKAGE, PORTS_OUT_PACKAGE, DOMAIN_PACKAGE)
                    .because("Classes in the web adapter should only depend on UseCase classes in the ports.in package");


    @ArchTest
    static final ArchRule nonAdaptersShouldNotDependOnPortsOut =
            ArchRuleDefinition.noClasses()
                    .that()
                    .resideOutsideOfPackages(AMPQ_PACKAGE, OUT_ADAPTER_PACKAGE)
                    .should()
                    .dependOnClassesThat()
                    .resideInAnyPackage(PORTS_OUT_PACKAGE) // Classes in ports.out package
                    .because("Classes outside 'adapters.ampq' and 'adapters.out.db' should not depend on 'ports.out'");

}
