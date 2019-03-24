package cvr.otus.shell;

import cvr.otus.Runner;
import cvr.otus.domain.Student;
import cvr.otus.service.PrintServiceImpl;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

import java.util.Locale;

@ShellComponent
public class ShellCommands {
    private Boolean authorized = false;
    private Student student= null;

    private final SelectLanguage service;
    private final PrintServiceImpl printService;
    private final Runner runner;

    public ShellCommands(SelectLanguage service, PrintServiceImpl printService, Runner runner) {
        this.service = service;
        this.printService = printService;
        this.runner = runner;
    }

    @ShellMethod("выбор локали")
    public void sl() {
        System.out.println("ru - русский");
        System.out.println("al - албанский");
        System.out.println("ar - армянский");
        String language = service.selectLanguage();
        printService.setLocale(new Locale(language));
    }


    @ShellMethod("Login")
    public Student log() {
        student = runner.login();
        authorized = (student != null);
        return student;
    }

    @ShellMethod("Пройти тест")
    @ShellMethodAvailability("isAvailable")
    public void exam() {
        runner.examine(student);
    }

    private Availability isAvailable() {
        return authorized ? Availability.available() : Availability.unavailable("Сначала необходимо авторизоваться!");
    }
}
