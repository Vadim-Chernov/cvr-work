package cvr.otus.dao;

import cvr.otus.domain.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class QuestionDaoImplTest {
    private static int COUNT = 5;
    private static String TEXT = "вопрос №";
    private static String ANSWER = "ответ №";


    private static List<Question> questions = new ArrayList<>();

    public static List<Question> getQuestions() {
        return questions;
    }

    public static void init() {
        questions.clear();
        for (int i = 0; i < COUNT; i++) {
            Question question = new Question();
            question.setId(i + 1);
            question.setText(TEXT + (i + 1));
            String[] enabledAnswers = new String[i + 1];
            int[] trueAns = new int[i + 1];
            for (int j = 0; j <= i; j++) {
                enabledAnswers[j] = ANSWER + (j + 1);
                trueAns[j] = j + 1;
            }
            question.setAnswers(enabledAnswers);
            question.setTrueAns(trueAns);
            questions.add(question);
        }

    }

    @BeforeEach
    void setUp() {
        init();
    }


    @Test
    void getQuestionList() {
        assertEquals(COUNT, questions.size());
    }

    @Test
    void getQuestionGetId() {
        for (int i = 0; i < COUNT; i++)
            assertEquals(i + 1, questions.get(i).getId());
    }

    @Test
    void getQuestionText() {
        for (int i = 0; i < COUNT; i++)
            assertEquals(TEXT + (i + 1), questions.get(i).getText());
    }

    @Test
    void getQuestionEnabledAnswers() {
        for (int i = 0; i < COUNT; i++) {
            String[] answers = questions.get(i).getAnswers();
            for (int j = 0; j < i; j++) {
                assertEquals(ANSWER + (j + 1), answers[j]);
            }
        }
    }

    @Test
    void getQuestionTrueAnswers() {
        for (int i = 0; i < COUNT; i++) {
            int[] trueAns = questions.get(i).getTrueAns();
            for (int j = 0; j < i; j++) {
                assertEquals(j+1, trueAns[j]);
            }
        }
    }

}