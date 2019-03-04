package cvr.otus.domain;

//import cvr.otus.domain.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuestionTest {

    private final String TEXT = "Вопрос";
    private final int[] TRUE_ANS = new int[]{1, 2, 3};
    private final String[] ENABLED_ANS = new String[]{"1111", "2222", "3333", "4444"};

    private Question question;

    @BeforeEach
    void setUp() {
        question = new Question();
        question.setId(0);
        question.setText(TEXT);
        question.setTrueAns(TRUE_ANS);
        question.setAnswers(ENABLED_ANS);
    }


    @Test
    void getId() {
        assertEquals(0,question.getId());
    }

    @Test
    void getText() {
        assertEquals(TEXT,question.getText());
    }



    @Test
    void getAnswers() {
        assertEquals(ENABLED_ANS,question.getAnswers());

    }

    @Test
    void getTrueAns() {
        assertEquals(TRUE_ANS,question.getTrueAns());
    }
}