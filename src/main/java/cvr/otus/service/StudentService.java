package cvr.otus.service;

import cvr.otus.dao.IStudentDao;
import cvr.otus.domain.Student;

import java.util.List;

public class StudentService implements IStudentService {
    private List<Student> students;
    private IStudentDao studentDao;

    public StudentService(IStudentDao studentDao) {
        this.studentDao = studentDao;
    }

    private List<Student> getStudentList() {
        if (students == null)
            students = studentDao.getStudentList();

        return students;
    }


    @Override
    public Student login(String name, String password) {
        List<Student> studentList = getStudentList();


        return  studentList.stream()
                .filter((st) -> name.equalsIgnoreCase(st.getName()) && password.equals(st.getPassword()))
                .findAny().orElse(null);

    }


}
