package cvr.otus;

import cvr.otus.domain.Question;
import cvr.otus.domain.Student;
import cvr.otus.service.QuestionService;
import cvr.otus.service.StudentService;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;
import java.util.Scanner;

public class Main {



    public static void main(String[] args) {
        System.out.println("Программа тестирования студентов");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        ExamRunner examRunner = new ExamRunner(context);
        examRunner.createFakes();
        examRunner.run();

    }

}
