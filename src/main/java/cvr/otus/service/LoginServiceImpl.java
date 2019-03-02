package cvr.otus.service;

import cvr.otus.domain.Student;
import cvr.otus.utils.PrintService;
import org.springframework.stereotype.Service;

import java.util.Iterator;


@Service
public class LoginServiceImpl implements LoginService {

    private Student student;
    private StudentService service;
    private PrintService ps;

    public void setScanner(Iterator<String> scanner) {
        this.scanner = scanner;
    }

    private Iterator<String> scanner;

    public LoginServiceImpl(StudentService service,PrintService ps) {
        this.service = service;
        this.ps = ps;
    }

    public Student login() {
        int count = 3;
        while (count-- > 0) {
            ps.print("Ваше имя: ");
            String name = scanner.next();
            ps.print("Пароль  : ");
            String password = scanner.next();

            student = service.login(name, password);
            if (student != null)
                return student;
            else {
                if (count > 0)
                    ps.println("Попробуёте ещё раз. Осталось " + count + " попыток");
                else
                    ps.println("Попытки исчернаны, выход из программы");
            }
        }
        return student;
    }

}
