package cvr.otus.service;

import cvr.otus.domain.Question;
import cvr.otus.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class ExamRunnerImpl implements ExamRunner {
    private Student student;
    private final QuestionService questionService;
    private final PrintService printService;

    @Autowired
    public ExamRunnerImpl(QuestionService service, PrintService printService) {
        this.questionService = service;
        this.printService = printService;
    }

    private void printResult() {
        printService.sayln("hello.kent", student.getName());

        Map<Integer, Double> result = questionService.getResult();
        printService.sayln("question.caption");
        printService.sayln("question.line");
        double res = 0.0;
        int size = result.size();
        for (int i = 0; i < size; i++) {
            Double dou = result.get(i);
            printService.sayln("str", "  " + (i + 1) + "            " + dou);
            res += dou;
        }
        printService.sayln("question.line");
        printService.sayln("gradepoint.average", Double.toString(res / size));
        printService.sayln("str", "============================================");
    }

    private void examine() {
        printService.sayln("student.hello", student.getName());
        printService.sayln("program.attention");
        for (int i = 0; i < questionService.size(); i++) {
            printQuestion(questionService.getQuestion(i));
            answer(i);
        }
    }

    private void answer(int i) {
        printService.say("student.answer");
        String ans = printService.next();
        questionService.checkQuestion(i, ans);
    }

    private void printQuestion(Question question) {
        printService.sayln("str", "============================================");
        printService.sayln("question.number", "" + question.getId());
        printService.sayln("str", question.getText());
        int count = 1;
        for (String s : question.getAnswers()) {
            printService.sayln("str", count++ + ")" + s);
        }
    }

    @Override
    public void run(Student student) {
        this.student = student;
        examine();
        printResult();
    }
}
