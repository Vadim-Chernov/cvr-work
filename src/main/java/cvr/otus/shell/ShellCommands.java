package cvr.otus.shell;

import cvr.otus.service.PrintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import static java.lang.System.exit;

@ShellComponent
public class ShellCommands {
    private final SelectLanguage service;

    @Autowired
    private PrintService printService;

    @Autowired
    public ShellCommands(SelectLanguage service) {
        this.service = service;
    }

    @ShellMethod("HHHHHH")
    public String hello(@ShellOption String name) {
        // invoke service
        return service.hello(name);
    }
    @ShellMethod("Bye")
    public String bye(@ShellOption String name) {
        // invoke service
        exit(0);
        return service.bye(name);
    }
}
