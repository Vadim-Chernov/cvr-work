package cvr.otus.utils;

import cvr.otus.Main;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("PrintServiceImplTest + ApplicationContext")
class PrintServiceImplTest {
    private AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);

    private PrintServiceImpl test = context.getBean(PrintServiceImpl.class);
    private String printStr = "";

    @BeforeEach
    void setUp() {
        test.setPrint(s -> printStr += s);
    }

    @Test
    void say() {
        test.say("test");
        assertEquals("тестовая строка", printStr);
    }

    @Test
    void sayln() {
        test.sayln("test");
        assertEquals("тестовая строка\n", printStr);
    }

    @Test
    void say1() {
        test.say("str", "Hello");
        assertEquals("Hello", printStr);
    }

    @Test
    void say2() {
        test.say("str", new String[]{"Hello"});
        assertEquals("Hello", printStr);
    }

    @Test
    void sayln1() {
        test.sayln("str", "Hello");
        assertEquals("Hello\n", printStr);
    }

    @Test
    void sayln2() {
        test.sayln("str", new String[]{"Hello"});
        assertEquals("Hello\n", printStr);
    }
}