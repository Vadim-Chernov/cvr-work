package cvr.otus.service;

import cvr.otus.config.LocaleProps;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("PrintServiceImplLanguageTest LANGUAGES")
@ExtendWith(SpringExtension.class)
@SpringBootTest
class PrintServiceImplLanguageTest {
    @Autowired
    private PrintServiceImpl printService;

    private void say(String language, String nation) {
        StringBuilder out = new StringBuilder();
        printService.setLocale(new Locale(language));
        printService.setPrinter(out::append);
        printService.say("test");
        assertEquals(nation, out.toString());
    }

    @Test
    @DisplayName("  русский ")
    void sayRu() {
        say("ru", "russian");
    }

    @Test
    @DisplayName("  армянский ")
    void sayAr() {
        say("ar", "armenian");
    }

    @Test
    @DisplayName("  албанский ")
    void sayAl() {
        say("al", "albanian");
    }

}