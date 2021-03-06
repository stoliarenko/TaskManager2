package ru.volnenko.se.command.task;

import org.springframework.stereotype.Component;
import ru.volnenko.se.command.AbstractCommand;

/**
 * @author Denis Volnenko
 */
@Component
public final class TaskClearCommand extends AbstractCommand {

    @Override
    public String description() {
        return "Remove all tasks.";
    }

    @Override
    public String command() {
        return "task-clear";
    }

    @Override
    public void execute() {
        bootstrap.getTaskRepository().clear();
        System.out.println("[ALL TASK REMOVED]");
    }

}
