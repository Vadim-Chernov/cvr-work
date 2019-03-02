package cvr.otus.service;

import cvr.otus.dao.QuestionDao;
import cvr.otus.dao.QuestionDaoImplTest;
import cvr.otus.domain.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.PropertySource;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@PropertySource("classpath:file-path.properties")
class QuestionServiceImplTest {
    private List<Question> questions;
    private Question question;
    private int questNum = 3;
    private QuestionDao questionDao = mock(QuestionDao.class);
    private QuestionService service;

    @BeforeEach
    void setUp() {
        QuestionDaoImplTest.init();
        questions = QuestionDaoImplTest.getQuestions();
        question = questions.get(questNum);
        when(questionDao.getQuestionList()).thenReturn(questions);
        service = new QuestionServiceImpl(questionDao);
    }

    @Test
//    @Disabled
    void getQuestions() {
        assertEquals(5, questions.size());
    }

    @Test
//    @Disabled
    void getQuestion() {
        assertEquals(4, question.getId());
    }

    @Test
//    @Disabled
    void checkQuestionTrue() {
        service.checkQuestion(0, "1");
        service.checkQuestion(1, "1,2");
        service.checkQuestion(2, "1,2,3");
        Map<Integer, Double> result = service.getResult();
        Set<Integer> integers = result.keySet();
        for (Integer integer : integers) {
            Double aDouble = result.get(integer);
            assertEquals(5, aDouble, 0.0001);
        }
    }

    @Test
//    @Disabled
    void checkQuestionNotTrue() {
        Double[] ex = {0d,2.5d,5d};
        service.checkQuestion(0, "2");
        service.checkQuestion(1, "1,3");
        service.checkQuestion(2, "1,2,3");
        Map<Integer, Double> result = service.getResult();
        Set<Integer> integers = result.keySet();
        for (Integer integer : integers) {
            Double aDouble = result.get(integer);
            assertEquals(ex[integer], aDouble, 0.0001);
        }
    }

    @Test
    void getResult() {
        Double[] ex = {0.0d, 2.5, 5.0, 3.75, 3.0};
        service.checkQuestion(0, "2");
        service.checkQuestion(1, "1,3");
        service.checkQuestion(2, "1,2,3");
        service.checkQuestion(3, "1,2,3");
        service.checkQuestion(4, "1,2,3");
        double sum = Arrays.stream(ex).mapToDouble(Double::doubleValue).sum();
        assertEquals(14.25,sum,0.0001d);
    }

    @Test
    void size() {
        assertEquals(5, questions.size());
    }
}