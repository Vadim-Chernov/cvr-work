package cvr.otus.service;

import cvr.otus.Main;
import cvr.otus.dao.QuestionDao;
import cvr.otus.domain.Question;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("QuestionDaoImplTest + SpringExtension")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Main.class)
class QuestionDaoImplTest {

    @Autowired
    private QuestionDao questionDao;

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