package cvr.otus.dao;

import cvr.otus.domain.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class QuestionDaoImplTest {
    public static int COUNT = 5;
    public static String TEXT = "вопрос №";
    public static String ANSWER = "ответ №";


    static List<Question>  questions = new ArrayList<>();

    public static List<Question> getQuestions() {
        return questions;
    }

    public static void init(){
        questions.clear();
        for (int i = 0; i < COUNT; i++) {
            Question question = new Question();
            question.setId(i);
            question.setText(TEXT + i);
            String[] enabledAnswers = new String[i + 1];
            int[] trueAns = new int[i + 1];
            for (int j = 0; j < i; j++) {
                enabledAnswers[j] = ANSWER + j;
                trueAns[j] = j;
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
            assertEquals(i, questions.get(i).getId());
    }

    @Test
    void getQuestionText() {
        for (int i = 0; i < COUNT; i++)
            assertEquals(TEXT + i, questions.get(i).getText());
    }

    @Test
    void getQuestionEnabledAnswers() {
        for (int i = 0; i < COUNT; i++) {
            String[] answers = questions.get(i).getAnswers();
            for (int j = 0; j < i; j++) {
                assertEquals(ANSWER + j, answers[j]);
            }
        }
    }

    @Test
    void getQuestionTrueAnswers() {
        for (int i = 0; i < COUNT; i++) {
            int[] trueAns = questions.get(i).getTrueAns();
            for (int j = 0; j < i; j++) {
                assertEquals(j, trueAns[j]);
            }
        }
    }

}