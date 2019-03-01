package cvr.otus;

import cvr.otus.domain.Student;
import cvr.otus.service.ExamRunner;
import cvr.otus.service.LoginService;
import cvr.otus.service.LoginServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.AbstractApplicationContext;

import static cvr.otus.utils.Say.messageln;

@ComponentScan
public class Main {

    public static void main(String[] args) {
        messageln("Программа тестирования студентов");
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(Main.class);


        LoginService loginService = new LoginServiceImpl(context);
        Student student = loginService.login();


        if(student!=null) {
            ExamRunner examRunner = context.getBean(ExamRunner.class);
            examRunner.setStudent(student);
            examRunner.run();
        }
    }
}
