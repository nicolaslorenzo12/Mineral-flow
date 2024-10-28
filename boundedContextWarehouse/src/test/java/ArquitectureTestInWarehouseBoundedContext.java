import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.springframework.stereotype.Service;

@AnalyzeClasses(packages = "be.kdg.prog6.boundedcontextWarehouse", importOptions = ImportOption.DoNotIncludeTests.class)
class ArquitectureTestInWarehouseBoundedContext {

    private static final String DTOS_PACKAGE = "be.kdg.prog6.boundedcontextWarehouse.adapters.in.web.dto";
    private static final String IN_WEB_PACKAGE_AND_SUB_PACKAGES_OF_IT = "be.kdg.prog6.boundedcontextWarehouse.adapters.in.web..";
    private static final String IN_WEB_PACKAGE = "be.kdg.prog6.boundedcontextWarehouse.adapters.in.web";
    private static final String IN_ADAPTER_PACKAGE = "be.kdg.prog6.boundedcontextWarehouse.adapters.in";
    private static final String OUT_ADAPTER_PACKAGE = "be.kdg.prog6.boundedcontextWarehouse.adapters.out";
    private static final String PORTS_IN_PACKAGE = "be.kdg.prog6.boundedcontextWarehouse.ports.in..";
    private static final String CORE_PACKAGE = "be.kdg.prog6.boundedcontextWarehouse.core";
    private static final String IN_ADAPTER_AMPQ_PACKAGE = "be.kdg.prog6.boundedcontextWarehouse.adapters.in.ampq";
    private static final String OUT_ADAPTER_AMPQ_PACKAGE = "be.kdg.prog6.boundedcontextWarehouse.adapters.out.ampq";
    private static final String DB_OUT_ADAPTER_PACKAGE = "be.kdg.prog6.boundedcontextWarehouse.adapters.out.db";
    private static final String PORTS_OUT_PACKAGE = "be.kdg.prog6.boundedcontextWarehouse.ports.out";
    private static final String DOMAIN_PACKAGE = "be.kdg.prog6.boundedcontextWarehouse.domain";


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


    @ArchTest
    static final ArchRule dtosShouldOnlyBeUsedInWebPackage =
            ArchRuleDefinition.noClasses()
                    .that()
                    .resideOutsideOfPackage(IN_WEB_PACKAGE_AND_SUB_PACKAGES_OF_IT)
                    .should()
                    .dependOnClassesThat()
                    .resideInAnyPackage(DTOS_PACKAGE)
                    .as("DTOs should only be used within the in.web package")
                    .because("DTOs are meant to be used to show specific data to users outside the business");



    @ArchTest
    static final ArchRule domainClassesShouldNotDependOnAnythingBeyondDomainClasses =
            ArchRuleDefinition.noClasses()
                    .that()
                    .resideInAPackage(DOMAIN_PACKAGE)
                    .should()
                    .dependOnClassesThat()
                    .resideInAnyPackage(IN_ADAPTER_PACKAGE,PORTS_OUT_PACKAGE, PORTS_IN_PACKAGE, CORE_PACKAGE, OUT_ADAPTER_PACKAGE)
                    .because("Domain should not depend on other layers.");


    @ArchTest
    static final ArchRule portsInAndAdaptersInAndControllersAreTheOnlyOnesWhichShouldDependOnUseCases =
            ArchRuleDefinition.noClasses()
                    .that()
                    .resideOutsideOfPackages(PORTS_IN_PACKAGE, IN_ADAPTER_PACKAGE)
                    .should()
                    .dependOnClassesThat()
                    .resideInAPackage(CORE_PACKAGE)
                    .because("Use cases should only depend on default use cases, listener or controllers");


    @ArchTest
    static final ArchRule defaultUseCasesShouldOnlyDependOnPortsInAndPortsOutAndDomainClasses=
            ArchRuleDefinition.noClasses()
                    .that()
                    .resideInAPackage(CORE_PACKAGE)
                    .should()
                    .dependOnClassesThat()
                    .resideOutsideOfPackages(PORTS_IN_PACKAGE, PORTS_OUT_PACKAGE, DOMAIN_PACKAGE)
                    .andShould()
                    .notBeAnnotatedWith(Service.class)
                    .because("Core use cases should only depend on ports, domain classes and Spring services for functionality");



    @ArchTest
    static final ArchRule nonAdaptersShouldNotDependOnPortsOut =
            ArchRuleDefinition.noClasses()
                    .that()
                    .resideOutsideOfPackages(OUT_ADAPTER_AMPQ_PACKAGE, DB_OUT_ADAPTER_PACKAGE, CORE_PACKAGE)
                    .should()
                    .dependOnClassesThat()
                    .resideInAPackage(PORTS_OUT_PACKAGE) // Classes in ports.out package
                    .because("Classes outside ampq out, db adapters and core should not depend on 'ports.out'");

}
