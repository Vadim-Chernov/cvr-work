package cvr.otus.service;

import au.com.bytecode.opencsv.CSVReader;
import cvr.otus.domain.Question;
import cvr.otus.utils.Util;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class QuestionService implements IQuestionService {
    private List<Question> questions;

    private Map<Integer, Double> result = new HashMap<>(5);

    @Override
    public List<Question> getQuestions() {
        if (questions == null)
            try {
                questions = loadQuestions();
            } catch (IOException e) {
                e.printStackTrace();
            }
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

    private static List<Question> loadQuestions() throws IOException {
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
}
