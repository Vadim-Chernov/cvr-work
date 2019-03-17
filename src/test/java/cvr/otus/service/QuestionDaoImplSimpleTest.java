package cvr.otus.service;

import cvr.otus.dao.QuestionDaoImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Обчыный юнит-тест. Контекст spring не поднимается
@DisplayName("QuestionDaoImplSimpleTest ")
class QuestionDaoImplSimpleTest {
    private QuestionDaoImpl questionDao;

    @BeforeEach
    void setUp() {
        questionDao = new QuestionDaoImpl("./data/questions.csv");
    }

    @Test
    void QuestionDaoImpllList() {
        assertEquals(1, questionDao.getQuestionList().get(0).getId());
    }
}