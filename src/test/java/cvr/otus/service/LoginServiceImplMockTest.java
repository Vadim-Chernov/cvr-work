package cvr.otus.service;

import cvr.otus.domain.Student;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.BDDMockito.given;

@DisplayName("LoginServiceImplTest + Mock")
@ExtendWith(SpringExtension.class)
@SpringBootTest

class LoginServiceImplMockTest {
    @Autowired
    private LoginServiceImpl loginService;

    private ArrayList<String> words = new ArrayList<>();
//    private String printStr = "";

    @MockBean
    private PrintServiceImpl printService;

    @Test
    @DisplayName("Login попытка входа с верной учеткой ")
    void login() {
        given(printService.next()).willReturn("Ivanov").willReturn("1");
        Student student = loginService.login();
        assertEquals("Ivanov", student.getName());
    }

    @Test
    @DisplayName("Login 3 попытки входа с неверной учеткой ")
    void loginBadly() {
        given(printService.next())
                .willReturn("Ivanov").willReturn("2")
                .willReturn("Ivanov").willReturn("2")
                .willReturn("Ivanov").willReturn("2");
        printService.setScanner(words.iterator());
        Student student = loginService.login();
        assertNull(student);
    }
}