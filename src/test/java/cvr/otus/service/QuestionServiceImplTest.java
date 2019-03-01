package cvr.otus.service;

import cvr.otus.dao.QuestionDao;
import cvr.otus.dao.QuestionDaoImplTest;
import cvr.otus.domain.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class QuestionServiceImplTest {
    private List<Question> questions;
    private Question question;
    private int questNum = 3;
//    QuestionDao questionDao = mock(QuestionDao.class);
//    QuestionService service;

    @BeforeEach
    void setUp() {
        QuestionDaoImplTest.init();
        questions = QuestionDaoImplTest.getQuestions();
        question = questions.get(questNum);
//        questionDao.getQuestionList().clear();
//        questionDao.getQuestionList().addAll(questions);
//        service = new QuestionServiceImpl(questionDao);
//        when(service.getQuestion(questNum)).thenReturn(question);
    }

    @Test
    @Disabled
    void getQuestions() {
        assertEquals(5, questions.size());
    }

    @Test
    @Disabled
    void getQuestion() {
        assertEquals(3, question.getId());
    }

    @Test
    @Disabled
    void checkQuestion() {
//        service.checkQuestion(0, "1");
//        service.checkQuestion(1, "1,2");
//        service.checkQuestion(2, "1,2,3");
//        Map<Integer, Double> result = service.getResult();

    }

    @Test
    void getResult() {
    }

    @Test
    void size() {
    }
}