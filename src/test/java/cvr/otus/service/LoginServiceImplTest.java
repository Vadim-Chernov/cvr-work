package cvr.otus.service;

import cvr.otus.Main;
import cvr.otus.domain.Student;
import cvr.otus.utils.PrintService;
import cvr.otus.utils.PrintServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
@DisplayName("LoginServiceImplTest + ApplicationContext")
class LoginServiceImplTest {
    private AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);

    private LoginServiceImpl test = context.getBean(LoginServiceImpl.class);

    private ArrayList<String> words = new ArrayList<>();
    private String printStr = "";


    @BeforeEach
    void setUp() {
        PrintServiceImpl ps = (PrintServiceImpl) test.getPs();
        ps.setPrint(s -> printStr += s);
    }

    @Test
    void login() {
        words.add("Ivanov");
        words.add("1");
        test.setScanner(words.iterator());
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
        test.setScanner(words.iterator());
        Student student = test.login();
        assertNull(student);
        assertTrue(printStr.contains("выход"));
    }
}