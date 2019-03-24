package cvr.otus;

import cvr.otus.domain.Student;
import cvr.otus.service.ExamRunner;
import cvr.otus.service.LoginService;
import cvr.otus.service.PrintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Service
@Configuration
public class Runner {

    private final PrintService ps;
    private final LoginService loginService;
    private final ExamRunner examRunner;

    @Autowired
    public Runner(PrintService ps, LoginService loginService, ExamRunner examRunner) {
        this.ps = ps;
        this.loginService = loginService;
        this.examRunner = examRunner;
    }

//    public void run() {
//        ps.sayln("program.caption");
//        Student student = loginService.login();
//        if (student != null)
//            examRunner.run(student);
//    }

    public Student login() {
        ps.sayln("program.caption");
        Student student = loginService.login();
        return student;
    }

    public void examine(Student student){
        examRunner.run(student);
    }
}
