import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import com.tngtech.archunit.library.Architectures;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(packages = "be.kdg.prog6.boundedcontextWarehouse", importOptions = ImportOption.DoNotIncludeTests.class)
class ArquitectureTestInWarehouseBoundedContext {

    private static final String COMMON_COMMANDS = "be.kdg.prog6.common.commands..";
    private static final String COMMON_EVENTS = "be.kdg.prog6.common.events..";
    private static final String LISTENERS = "be.kdg.prog6.adapters.in.web..";
    private static final String DB_ADAPTERS_PACKAGE = "be.kdg.prog6.boundedcontextWarehouse.adapters.out.db..";
    private static final String DTOS_PACKAGE = "be.kdg.prog6.boundedcontextWarehouse.adapters.in.web.dto..";
    private static final String IN_WEB_PACKAGE = "be.kdg.prog6.boundedcontextWarehouse.adapters.in.web..";
    private static final String PORTS_IN_PACKAGE = "be.kdg.prog6.boundedcontextWarehouse.ports.in..";


    @ArchTest
    static final ArchRule repositoriesShouldOnlyBeUsedInDbAdaptersAndDBAdapters =
            noClasses()
                    .that()
                    .resideOutsideOfPackage(DB_ADAPTERS_PACKAGE)
                    .should()
                    .dependOnClassesThat()
                    .resideInAPackage(DB_ADAPTERS_PACKAGE)
                    .andShould()
                    .dependOnClassesThat()
                    .haveSimpleNameEndingWith("DBAdapter")
                    .because("Repositories should only be used inside DB adapters or classes ending with 'DBAdapter' to maintain separation of concerns.");


    @ArchTest
    static final ArchRule dtosShouldOnlyBeUsedInWebClasses =
            ArchRuleDefinition.noClasses()
                    .that()
                    .resideOutsideOfPackage(IN_WEB_PACKAGE) // Classes outside in.web
                    .should()
                    .dependOnClassesThat()
                    .resideInAnyPackage(DTOS_PACKAGE) // Depend on DTOs
                    .because("DTOs should only be used in classes within the in.web package to ensure proper separation of concerns.");


    @ArchTest
    static final ArchRule webAdaptersShouldOnlyDependOnUseCases =
            ArchRuleDefinition.noClasses()
                    .that()
                    .resideInAPackage(IN_WEB_PACKAGE) // Classes in adapters.in.web
                    .should()
                    .dependOnClassesThat()
                    .resideInAnyPackage(PORTS_IN_PACKAGE) // Classes in ports.in
                    .andShould()
                    .haveSimpleNameEndingWith("UseCase") // Must end with UseCase
                    .because("Classes in the web adapter should only depend on UseCase classes in the ports.in package to maintain separation of concerns.");

//    @ArchTest
//    static final ArchRule commandsShouldNotDependOnEventsOrListeners =
//            noClasses().that().resideInAPackage(COMMON_COMMANDS)
//                    .should().dependOnClassesThat().resideInAnyPackage(
//                            COMMON_EVENTS,
//                            LISTENERS
//                    )
//                    .because("Commands should be independent and not rely on events or listeners to maintain separation of concerns.");
//
//    @ArchTest
//    static final ArchRule eventsShouldNotDependOnCommandsOrListeners =
//            noClasses().that().resideInAPackage(COMMON_EVENTS)
//                    .should().dependOnClassesThat().resideInAnyPackage(
//                            COMMON_COMMANDS,
//                            LISTENERS
//                    )
//                    .because("Events should be pure data carriers, not dependent on commands or listeners.");



}
