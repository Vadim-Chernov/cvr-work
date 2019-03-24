package cvr.otus.service;

import cvr.otus.domain.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("LoginServiceImplTest + SpringExtension")
@ExtendWith(SpringExtension.class)
@SpringBootTest

class LoginServiceImplTest {
    @Autowired
    private LoginServiceImpl loginService;

    private ArrayList<String> words = new ArrayList<>();
    private PrintServiceImpl printService;

    StringBuilder out;

    @BeforeEach
    void setUp() {
        out = new StringBuilder();
        printService = (PrintServiceImpl) loginService.getPrintService();
        printService.setPrinter(out::append);
    }

    @Test
    void login() {
        words.add("Ivanov");
        words.add("1");
        printService.setScanner(words.iterator());
        Student student = loginService.login();
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
        printService.setScanner(words.iterator());
        Student student = loginService.login();
        assertNull(student);
        assertTrue(out.toString().contains("выход"));
    }
}