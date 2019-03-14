package cvr.otus.service;

import cvr.otus.Main;
import cvr.otus.dao.StudentDao;
import cvr.otus.domain.Student;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("StudentDaoImplTest + SpringExtension")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Main.class)

class StudentDaoImplTest {

    @Autowired
    private StudentDao studentDao;

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