package cvr.otus.service;

import cvr.otus.domain.Question;
import cvr.otus.domain.Student;
import cvr.otus.utils.PrintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Map;


@Service
public class ExamRunnerImpl implements ExamRunner {
    private Student student;
    private QuestionService service;
    private PrintService ps;
    private Iterator<String> scanner;

    @Autowired
    public ExamRunnerImpl(QuestionService service,PrintService ps,Iterator<String> scanner) {
        this.service = service;
        this.ps = ps;
        this.scanner = scanner;
    }


    @Override
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

    @Override
    public void run() {
        examine();
        printResult();
    }
}
