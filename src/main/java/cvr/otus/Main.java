package cvr.otus;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import static cvr.otus.utils.Say.messageln;

@ComponentScan
public class Main {

    public static void main(String[] args) {
        messageln("Программа тестирования студентов");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        ExamRunner examRunner = new ExamRunner(context);
        examRunner.run();
    }
}
