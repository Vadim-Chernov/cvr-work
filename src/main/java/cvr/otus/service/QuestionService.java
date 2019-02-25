package cvr.otus.service;

import cvr.otus.dao.IQuestionDao;
import cvr.otus.domain.Question;
import cvr.otus.utils.Util;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionService implements IQuestionService {
    private List<Question> questions;
    private IQuestionDao questionDao;

    private Map<Integer, Double> result = new HashMap<>(5);

    public QuestionService(IQuestionDao questionDao) {
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
    public void checkQuestion(int num, String answer) {
        double ret = Util.examineArrays(Util.StringToIntArray(answer, ","), getQuestion(num).getTrueAns());
        result.put(num, ret);
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
