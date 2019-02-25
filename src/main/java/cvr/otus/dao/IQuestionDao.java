package cvr.otus.dao;

import cvr.otus.domain.Question;

import java.util.List;

public interface IQuestionDao {
    List<Question> getQuestionList();
}
