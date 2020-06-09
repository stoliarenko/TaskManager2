package ru.volnenko.se.entity;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class CommandEnteredEvent extends ApplicationEvent {

    private final String name;

    public CommandEnteredEvent(Object source, String name) {
        super(source);
        this.name = name;
    }

}
