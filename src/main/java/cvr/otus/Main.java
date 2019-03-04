package cvr.otus;

import cvr.otus.domain.Student;
import cvr.otus.service.ExamRunner;
import cvr.otus.service.LoginService;
import cvr.otus.service.LoginServiceImpl;
import cvr.otus.service.StudentService;
import cvr.otus.utils.PrintService;
import cvr.otus.utils.PrintServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.AbstractApplicationContext;

import java.util.Scanner;

//import static cvr.otus.GLB_CONST.PRG_CAPT;

@ComponentScan
@PropertySource("classpath:file-path.properties")
public class Main {

    private void run() {

        AbstractApplicationContext context = new AnnotationConfigApplicationContext(Main.class);

        PrintService ps = context.getBean(PrintServiceImpl.class);
        ps.sayln("program.caption");


        StudentService studentService = context.getBean(StudentService.class);
        LoginService loginService = context.getBean(LoginService.class);
//        LoginService loginService = new LoginServiceImpl(studentService, ps);

        loginService.setScanner(new Scanner(System.in));
        Student student = loginService.login();

        if (student != null) {
            ExamRunner examRunner = context.getBean(ExamRunner.class);
            examRunner.setStudent(student);
            examRunner.setScanner(new Scanner(System.in));
            examRunner.run();
        }

    }


    public static void main(String[] args) {
        Main m = new Main();
        m.run();
    }
}
