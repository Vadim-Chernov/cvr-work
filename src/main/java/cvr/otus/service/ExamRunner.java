package cvr.otus.service;

import cvr.otus.domain.Question;
import cvr.otus.domain.Student;
import cvr.otus.utils.PrintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Scanner;


@Service
public class ExamRunner {
    private Student student;
    private QuestionService service;

    @Autowired
    private PrintService ps;


    @Autowired
    public ExamRunner(QuestionService service) {
        this.service = service;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    private void printResult() {
        ps.println("Товарищ " + student.getName() + ",  Ваш результат:");
        Map<Integer, Double> result = service.getResult();
        ps.println("№вопроса      Оценка");
        ps.println("--------      ------");
        double res = 0.0;
        int size = result.size();
        for (int i = 0; i < size; i++) {
            Double dou = result.get(i);
            ps.println("  " + (i + 1) + "            " + dou);
            res += dou;
        }
        ps.println("--------      ------");
        ps.println(" ср. бал       " + res / size);
        ps.println("============================================");
    }

    private void examine() {
        ps.println("Здравствуйте, " + student.getName());
        ps.println("Если в вопросе несколько ответов, перечислите их через запятую");
        for (int i = 0; i < service.size(); i++) {
            printQuestion(service.getQuestion(i));
            answer(i);
        }
    }

    private void answer(int i) {
        Scanner scanner = new Scanner(System.in);
        ps.print("Ваш ответ: ");
        String ans = scanner.next();
        service.checkQuestion(i, ans);
    }

    private void printQuestion(Question question) {
        ps.println("============================================");
        ps.println("Вопрос № " + question.getId());
        ps.println(question.getText());
        int count = 1;
        for (String s : question.getAnswers()) {
            ps.println(count++ + ")" + s);
        }

    }


    public void run() {
        examine();
        printResult();
    }
}
