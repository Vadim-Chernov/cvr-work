package cvr.otus;

import cvr.otus.domain.Student;
import cvr.otus.service.ExamRunner;
import cvr.otus.service.LoginService;
import cvr.otus.service.PrintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication
public class Main implements CommandLineRunner {
    @Autowired
    private PrintService ps;

    @Autowired
    private LoginService loginService;

    @Autowired
    private ExamRunner examRunner;



    @Override
    public void run(String... args) throws Exception {
        ps.sayln("program.caption");
        Student student = loginService.login();
        if (student != null)
            examRunner.run(student);
    }

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(Main.class);
        application.run(args);
    }

}
