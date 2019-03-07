package cvr.otus.service;

import cvr.otus.Main;
import cvr.otus.domain.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("LoginServiceImplTest + SpringExtension")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Main.class)
class LoginServiceImplTest {
    @Autowired
    private LoginServiceImpl test;
    private ArrayList<String> words = new ArrayList<>();
    private String printStr = "";
    private PrintServiceImpl printService;


    @BeforeEach
    void setUp() {
        printService = (PrintServiceImpl) test.getPrintService();
        printService.setPrinter(s -> printStr += s);
        printService.setScanner(words.iterator());
    }

    @Test
    void login() {
        words.add("Ivanov");
        words.add("1");
        printService.setPrinter(s -> printStr += s);
        printService.setScanner(words.iterator());
        Student student = test.login();
        assertEquals("Ivanov", student.getName());
    }

    @Test
    void loginBadly() {
        words.add("Ivanov");
        words.add("2");
        words.add("Ivanov");
        words.add("2");
        words.add("Ivanov");
        words.add("2");
        printService.setPrinter(s -> printStr += s);
        printService.setScanner(words.iterator());
        Student student = test.login();
        assertNull(student);
        assertTrue(printStr.contains("выход"));
    }
}