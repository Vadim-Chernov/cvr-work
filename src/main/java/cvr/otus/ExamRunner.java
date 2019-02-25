package cvr.otus;

import cvr.otus.domain.Question;
import cvr.otus.domain.Student;
import cvr.otus.service.QuestionService;
import cvr.otus.service.StudentService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;
import java.util.Scanner;

import static cvr.otus.utils.Say.message;

class ExamRunner {
    private Student student;
    private QuestionService service;
    private ClassPathXmlApplicationContext context;

    ExamRunner(ClassPathXmlApplicationContext context) {
        this.context = context;
    }

    void createFakes() {
        FakeData.createStudents();
        FakeData.createQuestions();
    }

    private void printResult() {
        message("Товарищ " + student.getName() + ",  Ваш результат:");
        Map<Integer, Double> result = service.getResult();
        message("№вопроса      Оценка");
        message("--------      ------");
        double res = 0.0;
        int size = result.size();
        for (int i = 0; i < size; i++) {
            Double dou = result.get(i);
            message("  " + (i + 1) + "            " + dou);
            res += dou;
        }
        message("--------      ------");
        message(" ср. бал       " + res / size);
        message("============================================");
    }

    private void examine() {
        message("Здравствуйте, " + student.getName());
        message("Если в вопросе несколько ответов, перечислите их через запятую");
        service = context.getBean(QuestionService.class);
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
        message("============================================");
        message("Вопрос № " + question.getId());
        message(question.getText());
        int count = 1;
        for (String s : question.getAnswers()) {
            message(count++ + ")" + s);
        }

    }

    private Student login() {
        Scanner scanner = new Scanner(System.in);
        int count = 3;
        while (count-- > 0) {
            System.out.print("Ваше имя: ");
            String name = scanner.next();
            System.out.print("Пароль  : ");
            String password = scanner.next();

            student = context.getBean(StudentService.class).login(name, password);
            if (student != null)
                return student;
            else {
                if (count > 0)
                    message("Попробуёте ещё раз. Осталось " + count + " попыток");
                else
                    message("Попытки исчернаны, выход из программы");
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
