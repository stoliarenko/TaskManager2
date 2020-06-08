package ru.volnenko.se.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CommandEnteredEvent {

    private final String name;

}
