package cvr.otus.domain;

import lombok.Data;

@Data
public class Question {
    private int id;
    private String text;
    private String[] answers;
    private int[] trueAns;
}
