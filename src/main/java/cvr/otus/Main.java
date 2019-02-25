package cvr.otus;

import cvr.otus.domain.Question;
import cvr.otus.domain.Student;
import cvr.otus.service.IQuestionService;
import cvr.otus.service.IStudentService;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private AbstractApplicationContext context;
    private Student student;
    private IQuestionService service;


    private void createFakes() {
        FakeData.createStudents();
        FakeData.createQuestions();
    }


    public static void main(String[] args) {
        System.out.println("Программа тестирования студентов");
        Main mm = new Main();
        mm.createFakes();
        mm.context = new ClassPathXmlApplicationContext("/spring-context.xml");
        mm.student = mm.login();
        if (mm.student == null) {
            return;
        }
        mm.examine();
        mm.printResult();
    }

    private void printResult() {
        System.out.println("Товарищ "+student.getName() + ",  Ваш результат:");
        Map<Integer, Double> result = service.getResult();
        System.out.println("№вопроса      Оценка");
        System.out.println("--------      ------");
        double res = 0.0;
        int size = result.size();
        for (int i = 0; i < size; i++) {
            Double dou = result.get(i);
            System.out.println("  " + (i + 1) + "            " + dou);
            res += dou;
        }
        System.out.println("--------      ------");
        System.out.println(" ср. бал       " + res / size);
        System.out.println("============================================");
    }

    private void examine() {
        System.out.println("Здравствуйте, " + student.getName());
        System.out.println("Если в вопросе несколько ответов, перечислите их через запятую");
        service = context.getBean(IQuestionService.class);
        for (int i = 0; i < service.size(); i++) {
            printQuestion(service.getQuestion(i));
            answer(i);
        }
    }

    private void answer(int i) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ваш ответ: ");
        String ans = scanner.next();
        service.checkQuestion(i, ans);
    }

    private void printQuestion(Question question) {
        System.out.println("============================================");
        System.out.println("Вопрос № " + question.getId());
        System.out.println(question.getText());
        int count = 1;
        for (String s : question.getAnswers()) {
            System.out.println(count++ + ")" + s);
        }

    }

    private Student login() {
        IStudentService service = context.getBean(IStudentService.class);
        Scanner scanner = new Scanner(System.in);
        int count = 3;
        while (count-- > 0) {
            System.out.print("Ваше имя: ");
            String name = scanner.next();
            System.out.print("Пароль  : ");
            String password = scanner.next();

            student = service.login(name, password);
            if (student != null)
                return student;
            else {
                if (count > 0) {
                    System.out.println("Попробуёте ещё раз");
                    System.out.println("осталось " + count + " попыток");
                } else
                    System.out.println("Попытки исчернаны, выход из программы");
            }
        }
        return student;
    }
}
