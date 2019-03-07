package cvr.otus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Locale;
import java.util.Scanner;
import java.util.function.Consumer;

@Service

public class PrintServiceImpl implements PrintService {

    private static final String EOL = "\n";

    private Consumer<String> printer;

    private Iterator<String> scanner;

    private final MessageSource messageSource;
    private final Locale locale;

    void setPrinter(Consumer<String> printer) {
        this.printer = printer;
    }

    void setScanner(Iterator<String> scanner) {
        this.scanner = scanner;
    }

    @Autowired
    public PrintServiceImpl(MessageSource messageSource, Locale locale) {
        this.messageSource = messageSource;
        this.locale = locale;
        this.scanner = new Scanner(System.in);
        this.printer = System.out::print;
    }

    @Override
    public String next() {
        return scanner.next();
    }


    @Override
    public void say(String message) {
        printer.accept(messageSource.getMessage(message, null, locale));
    }

    @Override
    public void sayln(String message) {
        printer.accept(messageSource.getMessage(message, null, locale) + EOL);
    }

    @Override
    public void say(String key, String additional) {
        printer.accept(messageSource.getMessage(key, new String[]{additional}, locale));
    }

    @Override
    public void say(String key, String[] additional) {
        printer.accept(messageSource.getMessage(key, additional, locale));
    }

    @Override
    public void sayln(String message, String[] additional) {
        printer.accept(messageSource.getMessage(message, additional, locale) + EOL);
    }

    @Override
    public void sayln(String key, String additional) {
        printer.accept(messageSource.getMessage(key, new String[]{additional}, locale) + EOL);
    }
}