package cvr.otus.dao;

import cvr.otus.Main;
import cvr.otus.domain.Student;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentDaoImplTest {
    private AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
    private StudentDao studentDao = context.getBean(StudentDao.class);

    @Test
    void getQuestionList() {
        assertEquals(3, studentDao.getStudentList().size());
    }

    @Test
    void getQuestionsIds() {
        List<Student> students = studentDao.getStudentList();
        int[] ids = new int[students.size()];
        int i = 0;
        for (Student student : students)
            ids[i++] = student.getId();
        assertArrayEquals(new int[]{1, 2, 3}, ids);
    }

}