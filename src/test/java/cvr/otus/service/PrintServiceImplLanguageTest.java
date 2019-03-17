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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("PrintServiceImplLanguageTest LANGUAGES")
@ExtendWith(SpringExtension.class)
@SpringBootTest
class PrintServiceImplLanguageTest {
    @Autowired
    private MessageSource messageSource;

    private LocaleProps localeProps = mock(LocaleProps.class);

    private PrintServiceImpl printService;// = mock(PrintServiceImpl.class);
    private String printStr = "";

    private void say(String language, String nation) {
        printStr = "";
        when(localeProps.getLanguage()).thenReturn(language);
        printService = new PrintServiceImpl(messageSource, localeProps);
        printService.setPrinter(s -> printStr += s);
        printService.say("test");
        assertEquals(nation, printStr);

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