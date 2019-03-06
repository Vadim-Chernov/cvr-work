package cvr.otus.dao;

import cvr.otus.Main;
import cvr.otus.domain.Question;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
@DisplayName("QuestionDaoImplTest + ApplicationContext context")
class QuestionDaoImplTest {
    private AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
    private QuestionDao questionDao = context.getBean(QuestionDao.class);

    @Test
    void getQuestionList() {
        assertEquals(5, questionDao.getQuestionList().size());
    }

    @Test
    void getQuestionsIds() {
        List<Question> questions = questionDao.getQuestionList();
        int[] ids = new int[questions.size()];
        int i = 0;
        for (Question question : questions)
            ids[i++] = question.getId();
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, ids);
    }


}