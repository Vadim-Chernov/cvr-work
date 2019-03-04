package cvr.otus.service;

import cvr.otus.domain.Question;
import cvr.otus.domain.Student;
import cvr.otus.utils.PrintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
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
    public void setScanner(Iterator<String> scanner) {
        this.scanner = scanner;
    }

    private Iterator<String> scanner;

    public void setStudent(Student student) {
        this.student = student;
    }

    private void printResult() {
        ps.sayln("hello.kent", student.getName());

        Map<Integer, Double> result = service.getResult();
        ps.sayln("question.caption");
        ps.sayln("question.line");
        double res = 0.0;
        int size = result.size();
        for (int i = 0; i < size; i++) {
            Double dou = result.get(i);
            ps.sayln("str", "  " + (i + 1) + "            " + dou);
            res += dou;
        }
        ps.sayln("question.line");
        ps.sayln("gradepoint.average", Double.toString ( res / size));
        ps.sayln("str","============================================");
    }

    private void examine() {
        ps.sayln("student.hello", student.getName());
        ps.sayln("program.attention");
        for (int i = 0; i < service.size(); i++) {
            printQuestion(service.getQuestion(i));
            answer(i);
        }
    }

    private void answer(int i) {
//        Scanner scanner = new Scanner(System.in);
        ps.say("answer");
        String ans = scanner.next();
        service.checkQuestion(i, ans);
    }

    private void printQuestion(Question question) {
        ps.sayln("str", "============================================");
        ps.sayln("question", "" + question.getId());
        ps.sayln("str", question.getText());
        int count = 1;
        for (String s : question.getAnswers()) {
            ps.sayln("str", count++ + ")" + s);
        }

    }


    public void run() {
        examine();
        printResult();
    }
}
