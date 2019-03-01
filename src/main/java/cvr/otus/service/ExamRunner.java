package cvr.otus.service;

import cvr.otus.domain.Question;
import cvr.otus.domain.Student;
import cvr.otus.service.QuestionService;
import cvr.otus.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Scanner;

import static cvr.otus.utils.Say.message;
import static cvr.otus.utils.Say.messageln;

@Service
public class ExamRunner {
    private Student student;
    private QuestionService service;

    @Autowired
    public ExamRunner( QuestionService service) {
        this.service = service;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
//    void createFakes() {
//        FakeData.createStudents();
//        FakeData.createQuestions();
//    }

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
//        service = context.getBean(QuestionService.class);
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


    public void run() {
//        student = login();
//        if (student == null) {
//            return;
//        }
        examine();
        printResult();

    }
}
