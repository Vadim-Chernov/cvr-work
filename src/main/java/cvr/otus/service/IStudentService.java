package cvr.otus.service;

import cvr.otus.domain.Student;


public interface IStudentService {

    Student login(String name, String password);

}
