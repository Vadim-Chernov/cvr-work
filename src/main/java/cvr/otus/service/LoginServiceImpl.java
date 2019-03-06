package cvr.otus.service;

import cvr.otus.domain.Student;
import org.springframework.stereotype.Service;


@Service
public class LoginServiceImpl implements LoginService {

    private Student student;
    private StudentService service;
    private PrintService ps;

//
//    private Iterator<String> scanner;

//    void setScanner(Iterator<String> scanner) {
//        this.scanner = scanner;
//    }

    PrintService getPs() {
        return ps;
    }

    public LoginServiceImpl(StudentService service, PrintService ps) {
        this.service = service;
        this.ps = ps;
//        this.scanner = scanner;
    }

    public Student login() {
        int count = 3;
        while (count-- > 0) {
            ps.say("student.name");
            String name = ps.next();
            ps.say("student.password");
            String password = ps.next();

            student = service.login(name, password);
            if (student != null)
                return student;
            else {
                if (count > 0)
                    ps.sayln("try.again", new String[]{"" + count});
                else
                    ps.sayln("attempts.exhausted");
            }
        }
        return student;
    }

}
