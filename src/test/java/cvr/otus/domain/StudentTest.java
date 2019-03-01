package cvr.otus.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {
    private Student student;

    @BeforeEach
    void setUp() {
        student = new Student(1, "Ivanov", "1");
    }

    @Test
    void getId() {
        assertEquals(1,student.getId());
    }

    @Test
    void getName() {
        assertEquals("Ivanov",student.getName());
    }

    @Test
    void getPassword() {
        assertEquals("1",student.getPassword());
    }
}