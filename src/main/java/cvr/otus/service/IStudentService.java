package cvr.otus.service;

import cvr.otus.domain.Student;

import java.util.List;

public interface IStudentService {
    List<Student> getStudentList();

    Student login(String name, String password);

}
