package ru.volnenko.se.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import ru.volnenko.se.controller.Bootstrap;
import ru.volnenko.se.entity.CommandEnteredEvent;

/**
 * @author Denis Volnenko
 */
public abstract class AbstractCommand implements ApplicationListener<CommandEnteredEvent> {

    protected Bootstrap bootstrap;

    public Bootstrap getBootstrap() {
        return bootstrap;
    }

    @Autowired
    public void setBootstrap(Bootstrap bootstrap) {
        this.bootstrap = bootstrap;
    }

    public abstract void execute() throws Exception;

    public abstract String command();

    public abstract String description();

    @Override
    public void onApplicationEvent(CommandEnteredEvent event) {
        if (this.command().equals(event.getName())) {
            try {
                execute();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
