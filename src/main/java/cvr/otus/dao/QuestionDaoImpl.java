package cvr.otus.dao;

import au.com.bytecode.opencsv.CSVReader;
import cvr.otus.FakeData;
import cvr.otus.domain.Question;
import cvr.otus.utils.Util;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QuestionDaoImpl implements QuestionDao {
    @Override
    public List<Question> getQuestionList() {
        List<Question> questions = null;
        try {
            questions = loadQuestions();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return questions;
    }

    private List<Question> loadQuestions() throws IOException {
        List<Question> result = new ArrayList<>();
        CSVReader reader = new CSVReader(new FileReader("questions.csv"), ',', '"', 1);
        String[] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            Question question = new Question();
            question.setId(Integer.parseInt(nextLine[0]));
            question.setText(nextLine[1]);
            question.setAnswers(nextLine[2].split(":"));
            question.setTrueAns(Util.StringToIntArray(nextLine[3], ":"));
            result.add(question);
        }
        return result;
    }

    private void createFake() {
        FakeData.createQuestions();
    }
}
