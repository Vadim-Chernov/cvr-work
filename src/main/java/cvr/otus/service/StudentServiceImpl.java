package cvr.otus.service;

import cvr.otus.dao.StudentDao;
import cvr.otus.domain.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private List<Student> students;
    private StudentDao studentDao;

    public StudentServiceImpl(StudentDao studentDao) {
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
