package cvr.otus.service;

import cvr.otus.domain.Student;

import java.util.Iterator;

public interface ExamRunner {
    void setScanner(Iterator<String> scanner);

    void setStudent(Student student);

    void run();
}
