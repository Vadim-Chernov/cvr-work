package cvr.otus.service;

import cvr.otus.dao.QuestionDao;
import cvr.otus.domain.Question;
import cvr.otus.utils.Util;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service

public class QuestionServiceImpl implements QuestionService {
    private List<Question> questions;
    private final QuestionDao questionDao;

    private Map<Integer, Double> result = new HashMap<>(5);

    QuestionServiceImpl(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    @Override
    public List<Question> getQuestions() {
        if (questions == null)
            questions = questionDao.getQuestionList();
        return questions;
    }

    @Override
    public Question getQuestion(int num) {
        return getQuestions().get(num);
    }


    @Override
    public Double checkQuestion(int num, String answer) {
        int[] trueAns = getQuestion(num).getTrueAns();
        int[] ints = Util.StringToIntArray(answer, ",");
        double ret = Util.examineArrays(ints, trueAns);
        result.put(num, ret);
        return ret;
    }

    @Override
    public Map<Integer, Double> getResult() {
        return result;
    }


    @Override
    public int size() {
        return getQuestions().size();
    }

}
