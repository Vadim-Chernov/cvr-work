package cvr.otus;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FakeData {
    public static void createStudents() {
        File file = new File("students.csv");
        if (!file.exists())
            try {
                file.createNewFile();
                FileWriter writer = new FileWriter(file);
                String student = "id,name,password\n";
                writer.write(student);
                student = "1,Ivanov,1\n";
                writer.write(student);
                student = "2,petrov,22\n";
                writer.write(student);
                student = "3,sidorov,333\n";
                writer.write(student);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

    }

    public static void createQuestions() {
        float r=125f;
        File file = new File("questions.csv");
        if (!file.exists())
            try {
                file.createNewFile();
                FileWriter writer = new FileWriter(file);
                String question = "id,text,answers,trueAns\n";
                String answers;
                writer.write(question);
                question = "1,Выбирете допустимое объявление java,";
                answers = " int i=0;: int i=125;: int i=\"asd\";: Int i==125; : float r=125f;,2:1\n";
                writer.write(question);
                writer.write(answers);

                question = "2,Как выглядит оператор присваивания в java,";
                answers = " i eq 125 ;: abc<=>125;: int i==\"asd\";: i=125;: f=125f;,4:5\n";
                writer.write(question);
                writer.write(answers);

                question = "3,Как выглядит оператор сравнения в java,";
                answers = " i eq 125 ;: abc<=>125;: i==12;:i=125; :Int=125.00;,3\n";
                writer.write(question);
                writer.write(answers);

                question = "4,Как выглядит оператор НЕ РАВНО в java,";
                answers = " i <> 125 ;: abc >< 125;: i==12;: i~=125; : jjj != kkk;,5\n";
                writer.write(question);
                writer.write(answers);

                question = "5,Что делает операция ++ в java,";

                answers = " увеличивает объем памяти : ничего ни делает : возращает строку: увеличивает счетчик :  уменьшает счетчик,4\n";
                writer.write(question);
                writer.write(answers);

                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}
