package cvr.otus.service;

import cvr.otus.domain.Question;

import java.util.ArrayList;
import java.util.List;

class FakeData {
    private static int COUNT = 5;
    private static String TEXT = "вопрос №";
    private static String ANSWER = "ответ №";

    List<Question> generate() {
        List<Question> questions = new ArrayList<>(5);
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
        return questions;
    }

}
