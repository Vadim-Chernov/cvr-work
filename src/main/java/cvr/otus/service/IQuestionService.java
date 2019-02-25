package cvr.otus.service;

import cvr.otus.domain.Question;

import java.util.List;
import java.util.Map;

public interface IQuestionService {
    List<Question> getQuestions();

    Question getQuestion(int num);

    double checkQuestion(int num, String answer);

    Map<Integer, Double> getResult();

    int size();
}
