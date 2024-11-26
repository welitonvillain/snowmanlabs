package com.snowmanlabs.challenge.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

public class ArchitectureTest {

    private final JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.snowmanlabs.challenge");

    @Test
    void domainClassesShouldNotAccessInfrastructure() {
        ArchRule rule = classes()
                .that().resideInAPackage("..domain..")
                .should().onlyDependOnClassesThat().resideInAnyPackage("..domain..", "..service..", "java..", "javax..", "org.springframework..", "lombok..", "..shared..");

        rule.check(importedClasses);
    }

    @Test
    void controllersShouldResideInWebPackage() {
        ArchRule rule = classes()
                .that().haveSimpleNameEndingWith("Controller")
                .should().resideInAPackage("..in.web..")
                .because("Controladores devem estar no pacote 'in.web'.");

        rule.check(importedClasses);
    }

    @Test
    void infrastructureClassesShouldOnlyDependOnSharedOrDomain() {
        ArchRule rule = classes()
                .that().resideInAPackage("..infrastructure..")
                .should().onlyAccessClassesThat().resideInAnyPackage("..domain..", "..service..", "java..", "javax..", "org.springframework..", "lombok..", "..shared..", "..application..", "..infrastructure..")
                .because("Infraestrutura deve depender apenas de 'shared', 'application' ou 'domain'.");

        rule.check(importedClasses);
    }

}
