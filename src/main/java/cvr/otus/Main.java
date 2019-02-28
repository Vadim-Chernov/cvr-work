package cvr.otus;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.AbstractApplicationContext;

import static cvr.otus.utils.Say.messageln;

@ComponentScan
public class Main {

    public static void main(String[] args) {
        messageln("Программа тестирования студентов");
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        ExamRunner examRunner = new ExamRunner(context);
        examRunner.run();
    }
}
