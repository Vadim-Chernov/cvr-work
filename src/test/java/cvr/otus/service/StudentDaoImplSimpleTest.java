package cvr.otus.service;

import cvr.otus.dao.StudentDaoImpl;
import cvr.otus.domain.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Обчыный юнит-тест. Контекст spring не поднимается
@DisplayName("StudentDaoImplSimpleTest")
//@ExtendWith(MockitoExtension.class)
class StudentDaoImplSimpleTest {
    private StudentDaoImpl studentDao;

    @BeforeEach
    void setUp() {
        studentDao = new StudentDaoImpl("./data/students.csv");
    }

    @Test
    void StudentDaoImplList() {
        List<Student> studentList = studentDao.getStudentList();
        Student student = studentList.get(0);
        assertEquals(1, student.getId());
    }
}