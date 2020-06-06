package ru.volnenko.se;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.volnenko.se.controller.Bootstrap;

public class App {

    public static void main(String[] args) throws Exception {
        final ApplicationContext context = new AnnotationConfigApplicationContext("ru.volnenko.se");
        final Bootstrap serviceLocator = context.getBean(Bootstrap.class);
        serviceLocator.start();
    }

}
