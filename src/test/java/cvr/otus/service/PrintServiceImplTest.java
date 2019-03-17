package cvr.otus.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("PrintServiceImplTest + SpringExtension")
@ExtendWith(SpringExtension.class)
@SpringBootTest
class PrintServiceImplTest {
    @Autowired
    private PrintServiceImpl test;
    private String printStr = "";

    @BeforeEach
    void setUp() {
        test.setPrinter(s -> printStr += s);
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

    @Test
    void next() {
        List<String> list = new ArrayList<>(2);
        list.add("1111111111");
        list.add("2222222222");
        test.setScanner(list.iterator());
        assertEquals("1111111111", test.next());
        assertEquals("2222222222", test.next());
    }
}