package cvr.otus.service;

import cvr.otus.domain.Student;
import org.springframework.stereotype.Service;


@Service
public class LoginServiceImpl implements LoginService {

    private final StudentService studentService;
    private final PrintService printService;

    PrintService getPrintService() {
        return printService;
    }

    public LoginServiceImpl(StudentService service, PrintService printService) {
        this.studentService = service;
        this.printService = printService;
    }

    public Student login() {
        Student student;
        int count = 3;
        while (count-- > 0) {
            printService.say("student.name");
            String name = printService.next();
            printService.say("student.password");
            String password = printService.next();

            student = studentService.login(name, password);
            if (student != null)
                return student;
            else {
                if (count > 0)
                    printService.sayln("try.again", new String[]{"" + count});
                else
                    printService.sayln("attempts.exhausted");
            }
        }
        return null;
    }

}
