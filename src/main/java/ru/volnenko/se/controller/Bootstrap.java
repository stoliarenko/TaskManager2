package ru.volnenko.se.controller;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import ru.volnenko.se.api.repository.IProjectRepository;
import ru.volnenko.se.api.repository.ITaskRepository;
import ru.volnenko.se.api.service.IDomainService;
import ru.volnenko.se.api.service.IProjectService;
import ru.volnenko.se.api.service.ITaskService;
import ru.volnenko.se.api.service.ServiceLocator;
import ru.volnenko.se.command.AbstractCommand;
import ru.volnenko.se.entity.CommandEnteredEvent;

import java.util.*;

@Component
@Setter(onMethod_ = {@Autowired})
public final class Bootstrap implements ServiceLocator {

    private ApplicationEventPublisher publisher;

    private ITaskRepository taskRepository;

    private IProjectRepository projectRepository;

    private IProjectService projectService;

    private ITaskService taskService;

    private IDomainService domainService;

    private Map<String, AbstractCommand> commands = new LinkedHashMap<>();

    private final Scanner scanner = new Scanner(System.in);

    public ITaskRepository getTaskRepository() {
        return taskRepository;
    }

    public IProjectRepository getProjectRepository() {
        return projectRepository;
    }

    public IProjectService getProjectService() {
        return projectService;
    }

    public ITaskService getTaskService() {
        return taskService;
    }

    public IDomainService getDomainService() {
        return domainService;
    }

    @Autowired
    final void setCommands(final List<AbstractCommand> commands) {
        for (final AbstractCommand command : commands) {
            this.commands.put(command.command(), command);
        }
    }

    public void start() throws Exception {
        System.out.println("*** WELCOME TO TASK MANAGER ***");
        String command = "";
        while (!"exit".equals(command)) {
            command = scanner.nextLine();
            publisher.publishEvent(new CommandEnteredEvent(this, command));
        }
    }

    public List<AbstractCommand> getListCommand() {
        return new ArrayList<>(commands.values());
    }

    public String nextLine() {
        return scanner.nextLine();
    }

    public Integer nextInteger() {
        final String value = nextLine();
        if (value == null || value.isEmpty()) return null;
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            return null;
        }
    }

}
