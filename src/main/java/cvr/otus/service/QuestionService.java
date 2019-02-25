package cvr.otus.service;

import au.com.bytecode.opencsv.CSVReader;
import cvr.otus.domain.Question;
import cvr.otus.domain.Student;
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
    public double checkQuestion(int num, String answer) {
        Question question = getQuestion(num);
        //String[] split = answer.split(",");
        int[] ints = Util.StringToIntArray(answer, ",");
        int[] trueAns = question.getTrueAns();
        double ret = Util.examineArrays(ints, trueAns);
        result.put(num,ret);
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

    private static List<Question> loadQuestions() throws IOException {
        List<Question> reslt = new ArrayList<>();
        CSVReader reader = new CSVReader(new FileReader("questions.csv"), ',', '"', 1);
        String[] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            if (nextLine != null) {
                Question question = new Question();
                question.setId(Integer.parseInt(nextLine[0]));
                question.setText(nextLine[1]);
                String[] split = nextLine[2].split(":");
                question.setAnswers(split);
                int[] ints = Util.StringToIntArray(nextLine[3], ":");
                question.setTrueAns(ints);
                reslt.add(question);
            }
        }
        return reslt;
    }


}
