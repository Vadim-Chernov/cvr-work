package cvr.otus;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import static cvr.otus.utils.Say.messageln;

public class Main {



    public static void main(String[] args) {
        messageln("Программа тестирования студентов");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        ExamRunner examRunner = new ExamRunner(context);
        examRunner.run();

    }

}
