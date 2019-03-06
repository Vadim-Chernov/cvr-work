package cvr.otus;

import cvr.otus.domain.Student;
import cvr.otus.service.ExamRunner;
import cvr.otus.service.LoginService;
import cvr.otus.service.PrintService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
//@PropertySource("classpath:file-path.properties")
public class Main {

    private void run() {

        AnnotationConfigApplicationContext  context = new AnnotationConfigApplicationContext(Main.class);
        PrintService  ps = context.getBean(PrintService.class);
        ps.sayln("program.caption");

        LoginService loginService = context.getBean(LoginService.class);
        Student student = loginService.login();

        if (student != null) {
            ExamRunner examRunner = context.getBean(ExamRunner.class);
            examRunner.setStudent(student);
            examRunner.run();
        }
    }

    public static void main(String[] args) {
        Main m = new Main();
        m.run();
    }
}
