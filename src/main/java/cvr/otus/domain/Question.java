package cvr.otus.domain;

import lombok.Data;

@Data
public class Question {
    private int id;
    // Текст вопроса
    private String text;
    // Возможные ответы
    private String[] answers;
    // Номера верных ответов
    private int[] trueAns;
}
