package cvr.otus.service;

import cvr.otus.domain.Student;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Scanner;

import static cvr.otus.utils.Say.message;
import static cvr.otus.utils.Say.messageln;

@Service
public class LoginServiceImpl implements LoginService {
    private Student student;
    private AbstractApplicationContext context;

    public LoginServiceImpl(AbstractApplicationContext context) {
        this.context = context;
    }

    public Student login() {
        Scanner scanner = new Scanner(System.in);
        int count = 3;
        while (count-- > 0) {
            message("Ваше имя: ");
            String name = scanner.next();
            message("Пароль  : ");
            String password = scanner.next();

            student = context.getBean(StudentService.class).login(name, password);
            if (student != null)
                return student;
            else {
                if (count > 0)
                    messageln("Попробуёте ещё раз. Осталось " + count + " попыток");
                else
                    messageln("Попытки исчернаны, выход из программы");
            }
        }
        return student;
    }

}
