package cvr.otus.shell;

import cvr.otus.service.PrintService;
import cvr.otus.service.PrintServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.Locale;

import static java.lang.System.exit;

@ShellComponent
public class ShellCommands {
    private final SelectLanguage service;

    @Autowired
    private PrintServiceImpl printService;

    @Autowired
    public ShellCommands(SelectLanguage service) {
        this.service = service;
    }

    @ShellMethod("выбор локали")
    public void   sl() {
        System.out.println("ru - русский");
        System.out.println("al - албанский");
        System.out.println("ar - армянский");
        String language = service.selectLanguage();
        printService.setLocale(new Locale(language));
    }
}
