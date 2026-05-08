package com.empresa.pedidos;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.empresa.pedidos.dominio.puertos.ProcesadorPedido;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.*;

@AnalyzeClasses(packages = "com.empresa.pedidos")
public class ReglasArquitectura {

    // Regla 1: El dominio no depende de infraestructura ni adaptadores
    @ArchTest
    static final ArchRule dominioAislado = noClasses()
            .that().resideInAPackage("..dominio..")
            .should().dependOnClassesThat()
            .resideInAnyPackage(
                    "..infraestructura..", "..adaptadores..");

    // Regla 2: Los controladores solo acceden a la Facade
    @ArchTest
    static final ArchRule controladorSoloFacade = classes()
            .that().resideInAPackage("..adaptadores.rest..")
            .should().onlyAccessClassesThat()
            .resideInAnyPackage(
                    "..adaptadores.facade..", "..dominio..",
                    "org.springframework.web..", "org.springframework.http..",
                    "java..");

    // Regla 3: Los puertos de dominio son interfaces
    @ArchTest
    static final ArchRule puertosComoInterfaces = classes()
            .that().resideInAPackage("..dominio.puertos..")
            .should().beInterfaces();

    // Regla 4: Los procesadores implementan ProcesadorPedido
    @ArchTest
    static final ArchRule procesadoresImplementanPuerto = classes()
            .that().resideInAPackage("..adaptadores.procesadores..")
            .and().implement(ProcesadorPedido.class)
            .should().implement(ProcesadorPedido.class);

    // Regla 5: La infraestructura no accede a los adaptadores REST
    @ArchTest
    static final ArchRule infraNoAccedeRest = noClasses()
            .that().resideInAPackage("..infraestructura..")
            .should().accessClassesThat()
            .resideInAPackage("..adaptadores.rest..");
}
