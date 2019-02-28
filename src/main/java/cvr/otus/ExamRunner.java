package cvr.otus;

import cvr.otus.domain.Question;
import cvr.otus.domain.Student;
import cvr.otus.fake.FakeData;
import cvr.otus.service.QuestionService;
import cvr.otus.service.StudentService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;
import java.util.Scanner;

import static cvr.otus.utils.Say.message;
import static cvr.otus.utils.Say.messageln;

class ExamRunner {
    private Student student;
    private QuestionService service;
    private AnnotationConfigApplicationContext context;

    ExamRunner(AnnotationConfigApplicationContext context) {
        this.context = context;
    }

    private void printResult() {
        messageln("Товарищ " + student.getName() + ",  Ваш результат:");
        Map<Integer, Double> result = service.getResult();
        messageln("№вопроса      Оценка");
        messageln("--------      ------");
        double res = 0.0;
        int size = result.size();
        for (int i = 0; i < size; i++) {
            Double dou = result.get(i);
            messageln("  " + (i + 1) + "            " + dou);
            res += dou;
        }
        messageln("--------      ------");
        messageln(" ср. бал       " + res / size);
        messageln("============================================");
    }

    private void examine() {
        messageln("Здравствуйте, " + student.getName());
        messageln("Если в вопросе несколько ответов, перечислите их через запятую");
        service = context.getBean(QuestionService.class);
        for (int i = 0; i < service.size(); i++) {
            printQuestion(service.getQuestion(i));
            answer(i);
        }
    }

    private void answer(int i) {
        Scanner scanner = new Scanner(System.in);
        message("Ваш ответ: ");
        String ans = scanner.next();
        service.checkQuestion(i, ans);
    }

    private void printQuestion(Question question) {
        messageln("============================================");
        messageln("Вопрос № " + question.getId());
        messageln(question.getText());
        int count = 1;
        for (String s : question.getAnswers()) {
            messageln(count++ + ")" + s);
        }

    }

    private Student login() {
        Scanner scanner = new Scanner(System.in);
        int count = 3;
        while (count-- > 0) {
            message("Ваше имя: ");
            String name = scanner.next();
            message("Пароль  : ");
            String password = scanner.next();

            student = context.getBean(StudentService.class).login(name, password);
            if (student != null)
                return student;
            else {
                if (count > 0)
                    messageln("Попробуёте ещё раз. Осталось " + count + " попыток");
                else
                    messageln("Попытки исчернаны, выход из программы");
            }
        }
        return student;
    }

    void run() {
        student = login();
        if (student == null) {
            return;
        }
        examine();
        printResult();

    }
}
