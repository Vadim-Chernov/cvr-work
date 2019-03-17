package cvr.otus.service;

import cvr.otus.dao.QuestionDao;
import cvr.otus.domain.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("QuestionServiceImplTest + mock")
class QuestionServiceImplTest {
    private List<Question> questions;
    private Question question;
    private int questNum = 3;
    private QuestionDao questionDao = mock(QuestionDao.class);
    private QuestionService service = mock(QuestionService.class);;

    private Double delta =0.00001d;

    @BeforeEach
    void setUp() {
        FakeData data = new FakeData();
        questions = data.generate();
        question = questions.get(questNum);
        when(questionDao.getQuestionList()).thenReturn(questions);
    }

    @Test
    void getQuestions() {
        assertEquals(5, questions.size());
    }

    @Test
    void getQuestion() {
        assertEquals(4, question.getId());
    }

    @Test
    void checkQuestionTrue() {
        service.checkQuestion(0, "1");
        service.checkQuestion(1, "1,2");
        service.checkQuestion(2, "1,2,3");
        Map<Integer, Double> result = service.getResult();
        Set<Integer> integers = result.keySet();
        for (Integer integer : integers) {
            Double aDouble = result.get(integer);
            assertEquals(5, aDouble, delta);
        }
    }

    @Test
    void checkQuestionNotTrue() {
        Double[] ex = {0d,2.5d,3.75d};
        service.checkQuestion(0, "2");
        service.checkQuestion(1, "1,3");
        service.checkQuestion(2, "1,2,3,4");
        Map<Integer, Double> result = service.getResult();
        Set<Integer> integers = result.keySet();
        for (Integer integer : integers) {
            Double aDouble = result.get(integer);
            assertEquals(ex[integer], aDouble, delta);
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
        assertEquals(14.25,sum,delta);
    }

    @Test
    void size() {
        assertEquals(5, questions.size());
    }
}