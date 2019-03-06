package cvr.otus.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.function.Consumer;

@Service

public class PrintServiceImpl implements PrintService {

    private static final String EOL = "\n";

    @Autowired
    private Consumer<String> print;

    private MessageSource messageSource;
    private Locale locale;

    public void setPrint(Consumer<String> print) {
        this.print = print;
    }


    @Autowired
    public PrintServiceImpl(MessageSource messageSource, Locale locale) {
        this.messageSource = messageSource;
        this.locale = locale;
    }

    @Override
    public void say(String message) {
        print.accept(messageSource.getMessage(message, null, locale));
    }

    @Override
    public void sayln(String message) {
        print.accept(messageSource.getMessage(message, null, locale) + EOL);
    }

    @Override
    public void say(String key, String additional) {
        print.accept(messageSource.getMessage(key, new String[]{additional}, locale));
    }

    @Override
    public void say(String key, String[] additional) {
        print.accept(messageSource.getMessage(key, additional, locale));
    }

    @Override
    public void sayln(String message, String[] additional) {
        print.accept(messageSource.getMessage(message, additional, locale) + EOL);
    }

    @Override
    public void sayln(String key, String additional) {
        print.accept(messageSource.getMessage(key, new String[]{additional}, locale) + EOL);
    }
}